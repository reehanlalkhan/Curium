/**
 * 
 */
package com.model.feescollection.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.feescollection.dto.Feescollection;
import com.model.feescollection.dto.Receiptinfo;
import com.util.HibernateUtil;

/**
 * @author Musaib_2
 *
 */
public class feesCollectionDAO {
	Session session = null;
    Transaction transaction = null;
    Transaction transaction1;
    SessionFactory sessionFactory;

	public feesCollectionDAO() {
		session = HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public boolean create(List<Feescollection> feescollectionList) {
		 
		boolean result = false;
		try {
			 
			 transaction = session.beginTransaction();
			
			for (Feescollection singleFeescollection :  feescollectionList) {
				 session.save(singleFeescollection);
			}
	            transaction.commit();
	            result = true;
			 
		} catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();
	        } finally {
	            session.close();
	        }
		return result;

	}
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Feescollection> readListOfObject(Integer feeid) {

		List<Feescollection> results = new ArrayList<Feescollection>();
		try {

			transaction = session.beginTransaction();
			results = (List<Feescollection>) session.createQuery("From Feescollection where feesdetailsid="+feeid).list();
			
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			// session.close();
			return results;
		}
	}

	public List<Feescollection> getFeesForTheCurrentYear(long id, String currentAcademicYear) {
		List<Feescollection> results = new ArrayList<Feescollection>();
		try {

			transaction = session.beginTransaction();
			results = (List<Feescollection>) session.createQuery("From Feescollection where sid='"+id+"' and academicyear = '"+currentAcademicYear+"'").list();
			
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			// session.close();
			return results;
		}
	}

	public void createReceipt(Receiptinfo receiptInfo) {

		try {
			 transaction = session.beginTransaction();
			 session.save(receiptInfo);
			 transaction.commit();
		} catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();
	        } finally {
	            session.close();
	        }
	}

	public Receiptinfo getReceiptInfoDetails(Integer receiptNumber, String currentAcademicYear) {
		
		Receiptinfo receiptDetails = new Receiptinfo();
		
		try {
			 
			 transaction = session.beginTransaction();
			 Query query = session.createQuery("from Receiptinfo where receiptnumber = '"+receiptNumber+"' and academicyear = '"+currentAcademicYear+"'");
			 receiptDetails = (Receiptinfo) query.uniqueResult();
			 transaction.commit();
		} catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();
	        } finally {
	           // session.close();
	        }
		
		return receiptDetails;
		
	}

	public List<Receiptinfo> getReceiptDetailsPerStudent(long id,
			String currentacademicyear) {
		List<Receiptinfo> receiptInfo = new ArrayList<Receiptinfo>();
		try{
			transaction = session.beginTransaction();
			receiptInfo = session.createQuery("from Receiptinfo where sid = '"+id+"' and academicyear = '"+currentacademicyear+"'").list();
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return receiptInfo;
	}

}
