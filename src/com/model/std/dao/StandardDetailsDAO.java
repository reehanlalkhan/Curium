package com.model.std.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.std.dto.Classsec;
import com.util.HibernateUtil;

public class StandardDetailsDAO {

	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
    Transaction transaction1;
    SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger(StandardDetailsDAO.class);
    
	public StandardDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Classsec create(Classsec classsec) {
		 try {
	            transaction = session.beginTransaction();
	            session.save(classsec);
	            transaction.commit();
	        } catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	            return classsec;
	        }
	}

    public List<Classsec> viewClasses(int branchId) {
        
        List<Classsec> classsecList = new ArrayList<Classsec>();
        try {
            transaction = session.beginTransaction();
            classsecList = session.createQuery("From Classsec where branchid="+branchId).list();
            transaction.commit();
        } catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
            return classsecList;
        }
    }

    public void deleteMultiple(List ids) {

        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("delete from Classsec where stdrdid IN (:ids)");
                query.setParameterList("ids", ids);
                query.executeUpdate();
                transaction.commit();
        } catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
                hibernateException.printStackTrace();
        }


        
    }
}
