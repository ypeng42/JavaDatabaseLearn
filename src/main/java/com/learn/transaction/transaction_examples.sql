-- Set up table
create table SavedData (
	id int,
	textData varchar(20)
)

----- First row will be committed -----
begin transaction
	insert into SavedData values (1, 'asd')
	insert into SavedData values (1, 'asdasdad')
commit transaction

----- Nothing will be committed -----
begin transaction
	insert into SavedData values (1, 'asd')
	alter table SavedData alter column textData varchar(2)
commit transaction

-- Open a new window and try query SavedData, it will be locked since we haven't commit/rollback in current window
-- In current window it will show 'uncommitted data' is there, but only current tx can see it.
begin transaction demo;
	select * from SavedData;
	insert into SavedData values (1, 'uncommitted data');
