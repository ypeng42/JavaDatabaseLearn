package com.learn.hibernate;

import com.learn.hibernate.mapping.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.event.internal.DefaultSaveEventListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class learnHowHibernateSave {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		SessionFactory factory = (SessionFactory) context.getBean("sessionFactory_1");
		Session session = factory.openSession();
		Student student = new Student("yuqing1");

		// this will set autocommit to false
		// AbstractLogicalConnectionImplementor.getConnectionForTransactionManagement().setAutoCommit( false );
//		Transaction transaction = session.beginTransaction();

		// Everything happens in this single line
		session.save(student);


		/**
		 * 1. session.save() -> fires event {@link org.hibernate.internal.SessionImpl#fireSave}, there is only 1 listeners (DefaultSaveEventListener)
		 * 2. do a bunch of things and then -> {@link DefaultSaveEventListener#performSaveOrUpdate}
		 *
		 * 3. {@link DefaultSaveEventListener#entityIsTransient} -> save with generated id bla bla bla ->
		 * 4. {@link org.hibernate.event.internal.AbstractSaveEventListener#performSave}
		 *
		 * 5. {@link org.hibernate.event.internal.AbstractSaveEventListener#addInsertAction} to ActionQueue, right after adding to queue, it will be executed
		 *
		 * 6. callback -> {@link org.hibernate.action.internal.EntityIdentityInsertAction#execute}
		 *
		 * 7. actually does the work -> {@link org.hibernate.persister.entity.AbstractEntityPersister#insert}, the sql is ALREADY HERE!!!
		 * a.k.a sqlIdentityInsertString   insert() is interface func, the delegate can be ignored for now, since the sql is more important.  Get sql from getSQLIdentityInsertString()
		 *
		 * 8. During {@link org.hibernate.persister.entity.AbstractEntityPersister#doLateInit}, the insert sql is already set up in {@link org.hibernate.persister.entity.AbstractEntityPersister#generateIdentityInsertString}
		 *
		 *
		 *
		 * {@link org.hibernate.persister.internal.PersisterFactoryImpl#createEntityPersister} persisterClass is created here
		 * -> {@link org.hibernate.persister.internal.StandardPersisterClassResolver#getEntityPersisterClass}
		 *
		 * seems like {@link org.hibernate.cfg.Configuration#buildSessionFactory} is a good place to start, what is meta data? how we get it?
		 *
		 * LocalSessionFactoryBuilder extends Configuration
		 *
		 * session factory builder -> session factory
		 *
		 * remember in xml, local sesion factory bean only has packagesToScan, hibernateProperties, dataSource being set
		 *
		 * {@link org.springframework.orm.hibernate5.LocalSessionFactoryBean#getMetadataSources}
		 *
		 * back to localSessionFactoryBuilder#scanPackages, it has sth to do with metadataSources.
		 * TODO In the method, study how this.resourcePatternResolver.getResources() work, I can learn a ton of class loading staff. Really neat spring io.
		 *
		 * next step, what does metadataBuilder.build() do? Configuration#buildSessionFactory
		 *
		 * the metadata builder doesn't have too much stuff put into it, only stategy for coming up with names.
		 *
		 * TODO The MetadataBuildingOptionsImpl static class is pretty neat, why it's good?
		 *
		 * meta data source is like raw material, metadata is the final product
		 *
		 * learn from MetadataBuildingProcess
		 */

		System.out.println("done!");
	}
}
