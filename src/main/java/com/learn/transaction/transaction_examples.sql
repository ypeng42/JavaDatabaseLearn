-- Set up table
create table SavedData (
	id int,
	textData varchar(20)
)


----- Point No.1 (IMPLICIT_TRANSACTIONS Off) -----------------------
-- when off (default), a.k.a autocommit mode, sql server will wrap a statement with BEGIN TRANSACTION and COMMIT TRANSACTION
--
-- Ex.
insert into SavedData values (1, 'asd')
--
-- will be turned into:
--
begin transaction
	insert into SavedData values (1, 'asd')
commit transaction


----- Point No.2 (IMPLICIT_TRANSACTIONS On) -----------------------
-- when on, sql server will add BEGIN TRANSACTION before a statement (Each transaction is explicitly completed with a COMMIT or ROLLBACK statement)
--
-- Ex.
SET IMPLICIT_TRANSACTIONS On
	insert into SavedData values (1, 'asd')
--
-- will be turned into:
--
begin transaction
	insert into SavedData values (1, 'asd')


----- Point No.3 (Locks) -----------------------
-- https://docs.microsoft.com/en-us/previous-versions/sql/sql-server-2008-r2/ms186396(v%3dsql.105)
-- To check lock
SELECT * FROM sys.dm_tran_locks

-- Exclusive locks (X): modification operators such as Insert, Update or Delete, always acquired and held till end of transaction
-- Shared locks (S): read operations such as SELECT, duration depends on isolation level
-- S is compatible with S
-- X is not compatible with any lock

-- Ex.1
-- Run this in 1 window
begin transaction
	insert into SavedData values (1, 'asd')

-- check in another window (a X lock should be placed on the row)
SELECT * FROM sys.dm_tran_locks

-- Ex.2
-- Run this in another window (it will try to acquire a lock but can't)
begin transaction
	select * from SavedData




----- Point No.4 (iso LEVEL READ UNCOMMITTED) -----------------
-- READ UNCOMMITTED -> dirty read is allow, S lock not acquired
-- tx 1 modifies row 1 place a X lock on that row
begin transaction
insert into SavedData values (2, 'uncommitted data')

-- tx 2 tries to read that row, since doesn't need to get a S lock, (dirty) read can happen
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
begin transaction
select * from SavedData
commit transaction

-- nolock table hint works the same way
select * from SavedData with(nolock)



----- Point No.5 (iso LEVEL READ COMMITTED) -----------------
-- default iso level
-- dirty read is not allowed
-- acquired and released immediately

-- tx 1 modifies row 1 place a X lock on that row
begin transaction
insert into SavedData values (3, 'dirty data')

-- tx 2 tries to read that row -> It needs to get S lock (only for a short time) -> it must wait util X lock is released
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
begin transaction
	select * from SavedData
commit transaction




----- Point No.5 (REPEATABLE READ) -----------------
truncate table SavedData
insert into SavedData values (1, 'dirty data 1')
insert into SavedData values (2, 'dirty data 2')
-- dirty read not allowed
-- non-repeatable read not allowed (a.k.a this won't happen: tx 1 reads row 1, tx 2 updates row 1, tx 1 reads row 1 again and get different data; tx 2 won't be able to update row read by tx 1)
-- S lock held until end of transaction

--Demo: what is non-repeatable read?
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
begin transaction
select * from SavedData where id = 1
WAITFOR DELAY '00:00:05'
commit transaction

--
begin transaction
update SavedData set textData = 'updated data' where id = 1 -- change it to 2 works
print 'done'
commit transaction



-- tx 1 reads a row, the S lock will be there for 5 minutes
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
begin transaction
	select * from SavedData where id = 1
	WAITFOR DELAY '00:05'
commit transaction

-- tx 2 can't update, since it needs to get X lock, but there is already S lock there
begin transaction
	update SavedData set textData = 'updated data' where id = 1 -- change it to 2 works
	print 'done'
commit transaction



----- Point No.6 (SERIALIZABLE) -----------------
truncate table SavedData
insert into SavedData values (1, 'data 1')
insert into SavedData values (2, 'data 2')
insert into SavedData values (3, 'data 3')
-- dirty read not allowed
-- non-repeatable read not allowed
-- phantom read not allowed

-- What is phantom read??
-- tx 1 retrieves a set of rows satisfying a given condition (not 1 row, but a set of rows)
-- in this iso lvl, not only update not allowed, but insertion/deletion of range of row is not allowed
select * from SavedData where textData like 'data%'
WAITFOR DELAY '00:00:10'
select * from SavedData where textData like 'data%'

-- during those 5 seconds, tx 2 inserts or updates a row meets that condition
insert into SavedData values (4, 'data phantom 1')


-- how is phantom read prevented?
-- range lock hold until end of tx
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
begin transaction
	select * from SavedData where textData like 'data%'
	WAITFOR DELAY '00:05'
commit transaction

-- tx 2 won't be able to insert the row
insert into SavedData values (5, 'data phantom 2')
print 'done'







----- First row will be committed -----
begin transaction
	insert into SavedData values (1, 'example 1') -- not rolled back
	insert into SavedData values (1, 'more than 20 char asdasdad askdjhasdhjk') -- error
commit transaction


----- Nothing will be committed -----
begin transaction
	insert into SavedData values (1, 'example 2') -- roll back
	alter table SavedData alter column textData varchar(2) -- error
commit transaction





-- Why in both cases, there is 1 error in tx, but 1 is rolled back the other is not?

-- there is a setting called XACT_ABORT, which indicates whether rollback tx when error happens (default is off)
-- a.k.a, don't rollback when error happens
-- but if sql server thinks the error is severe enough, it will rollback tx even when  XACT_ABORT = off

-- when XACT_ABORT = on, they behave the same
set XACT_ABORT on
begin transaction
	insert into SavedData values (1, 'should be rolled back')
	insert into SavedData values (1, 'more than 20 char asdasdad askdjhasdhjk') -- error
commit transaction