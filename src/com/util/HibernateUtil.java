/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author 
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    
   /* final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    .configure() // configures settings from hibernate.cfg.xml
    .build();*/

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
        	
        	//sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        	
        	/*Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()). buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
             //Session session = sessionFactory.openSession();
        	
        	
        	StandardServiceRegistry standardRegistry = 
        		       new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        			Metadata metaData = 
        		        new MetadataSources(standardRegistry).getMetadataBuilder().build();
        			sessionFactory = metaData.getSessionFactoryBuilder().build();
        			
             //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
        	System.out.println("ERROR **********************");
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     *
     */
    public static void closeSession() {
        sessionFactory.getCurrentSession().close();

    }
    /**
     *
     * @return
     */
    public static Session openSession(){
        return getSessionFactory()
        		.openSession();
        //.withOptions().tenantIdentifier( "school" )

    }
    /**
     *
     * @return
     */
    public static Session openCurrentSession() {
        if(getSessionFactory().getCurrentSession().isOpen()){
            return getSessionFactory().getCurrentSession();

        }
        else{
            return getSessionFactory().openSession();
        }

        //return getSessionFactory().getCurrentSession();
    }
}
