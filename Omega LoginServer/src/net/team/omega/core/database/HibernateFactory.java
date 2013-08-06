package net.team.omega.core.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateFactory
{

    private static SessionFactory sessionFactory;
    
    public static void setupSessionFactory()
    {
	try {
	    Configuration _config = new Configuration().configure("/net/team/omega/core/database/hibernate.cfg.xml");
	    sessionFactory = _config.buildSessionFactory(new ServiceRegistryBuilder().applySettings(_config.getProperties()).buildServiceRegistry());
	} catch (HibernateException ex) {
	    throw new RuntimeException("Hibernate configuration error : " + ex.getMessage(), ex);
	}
    }
    
    public static Session getSession()
    {
	return sessionFactory.openSession();
    }
}
