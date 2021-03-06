package com.model.hr.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.department.dto.Department;
import com.model.feescategory.dto.Feescategory;
import com.model.hr.dto.Leaveapplication;
import com.model.hr.dto.Leavedetails;
import com.model.hr.dto.Leavetypemaster;
import com.model.hr.dto.Payadvancesalary;
import com.model.hr.dto.Paybasic;
import com.model.hr.dto.Payhead;
import com.model.hr.dto.Payheadstaffdetails;
import com.model.hr.dto.Pf;
import com.model.hr.dto.Processsalarydetails;
import com.model.hr.dto.Processsalarydetailsheads;
import com.util.HibernateUtil;

public class HrDAO {

	
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    

	public HrDAO() {
		session = HibernateUtil.openSession();
	}

	public List<Leavetypemaster> readListOfLeaveTypes() {
		
		List<Leavetypemaster> list = new ArrayList<Leavetypemaster>();

		try {
            transaction = session.beginTransaction();
            list = session.createQuery("From Leavetypemaster").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	public boolean saveLeaveType(Leavetypemaster leaveMaster) {
		
		try {
            transaction = session.beginTransaction();
            session.save(leaveMaster);
            transaction.commit();
            return true;
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
		return false;
	}

	public boolean deleteLeaveType(Leavetypemaster leaveType) {
		try {
            transaction = session.beginTransaction();
            session.delete(leaveType);
            transaction.commit();
            return true;
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
		return false;
	}

	public boolean addLeaves(List<Leavedetails> leaveDetailsList) {

		try {
            transaction = session.beginTransaction();
            for (Leavedetails leavedetails : leaveDetailsList) {
            	session.save(leavedetails);
			}
            transaction.commit();
            return true;
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
		return false;
	}

	public List<Leavedetails> getLeaveDetails(String teacherId, String academicYear) {
		List<Leavedetails> leaveDetailsList = new ArrayList<Leavedetails>();
		
		try {
            transaction = session.beginTransaction();
            leaveDetailsList = session.createQuery("From Leavedetails where idteacher="+teacherId+" and academicyear='"+academicYear+"'").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }

		return leaveDetailsList;
		
	}

	public boolean savePayHead(Payhead payHead) {
		
		try {
            transaction = session.beginTransaction();
            session.save(payHead);
            transaction.commit();
            return true;
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
		return false;
	}

	public List<Payhead> getPayHeadList(String academicYear) {
		List<Payhead> payHead = new ArrayList<Payhead>();
		
		try {
			transaction = session.beginTransaction();
			payHead = session.createQuery("from Payhead where academicyear='"+academicYear+"'").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return payHead;
	}

	public boolean addPayHeadStaffDetails(
			List<Payheadstaffdetails> payHeadStaffDetailsList) {
		
		try {
			transaction = session.beginTransaction();
			for (Payheadstaffdetails payheadstaffdetails : payHeadStaffDetailsList) {
				session.save(payheadstaffdetails);
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;		
	}

	public boolean savePayBasic(List<Paybasic> payBasicList) {
			
			try {
				transaction = session.beginTransaction();
				for (Paybasic payBasic : payBasicList) {
					session.save(payBasic);
				}
				transaction.commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			return false;		
	}

	public void addPf(Pf pf) {

		try {
			transaction = session.beginTransaction();
			session.save(pf);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}

	public List<Pf> pfSettings() {
		
		List<Pf> pf = new ArrayList<Pf>();
		
		try {
			transaction = session.beginTransaction();
			pf = session.createQuery("From Pf order by date Desc").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return pf;
	}

	public void deletePf(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Pf where idpf IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}finally{
			session.close();
		}
		
	}

	public boolean saveAdvanceSalary(Payadvancesalary payAdvanceSalary) {

		try {
			transaction = session.beginTransaction();
			session.save(payAdvanceSalary);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public List<Payadvancesalary> salaryApprovalDispaly() {
		
		List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
		try {
			transaction = session.beginTransaction();
			payAdvanceSalary = session.createQuery("from Payadvancesalary where status='apply'").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return payAdvanceSalary;
	}

	public boolean saveAdvanceSalaryApproval(Payadvancesalary payAdvance) {

		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update Payadvancesalary set reason = '"+payAdvance.getReason()+"',status = '"+payAdvance.getStatus()+"' where idpayadvancesalary="+payAdvance.getIdpayadvancesalary());
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public boolean deleteAdvaceSalaryApproval(Payadvancesalary payAdvance) {
		try {
            transaction = session.beginTransaction();
            session.delete(payAdvance);
            transaction.commit();
            return true;
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
		return false;
	}

	public List<Payadvancesalary> salaryIssue() {
		List<Payadvancesalary> payAdvanceSalary = new ArrayList<Payadvancesalary>();
		try {
			transaction = session.beginTransaction();
			payAdvanceSalary = session.createQuery("from Payadvancesalary where status='approved' or status='rejected'").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return payAdvanceSalary;
	}

	public boolean applyLeave(Leaveapplication leaveApplication) {
		
		try {
			transaction = session.beginTransaction();
			session.save(leaveApplication);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public List<Leaveapplication> leaveApprovals(String currentAcademicYear) {
		
		List<Leaveapplication> listLeaveApplication = new ArrayList<Leaveapplication>();
		
		try {
			transaction = session.beginTransaction();
			listLeaveApplication = session.createQuery("from Leaveapplication where academicyear='"+currentAcademicYear+"'").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return listLeaveApplication;
	}

	public boolean rejectLeave(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update Leaveapplication set status = 'rejected' where idleaveapplication IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
		
	}

	public boolean approveLeave(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update Leaveapplication set status = 'approved' where idleaveapplication IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
		
	}

	public boolean processStaffSalary(List<Processsalarydetails> processsalarydetailsList, List<Processsalarydetailsheads> processSalarydetailsheadList) {

		try {
			transaction = session.beginTransaction();
			for (Processsalarydetails processsalarydetails : processsalarydetailsList) {
				session.save(processsalarydetails);
				for (Processsalarydetailsheads processsalarydetailsheads : processSalarydetailsheadList) {
					processsalarydetailsheads.setProcesssalarydetails(processsalarydetails);
					session.save(processsalarydetailsheads);
				}
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
		
	}

	public List<Payhead> getPayHeadListDynamic(String payHeadType, String academicYear) {
			List<Payhead> payHead = new ArrayList<Payhead>();
			
			try {
				transaction = session.beginTransaction();
				payHead = session.createQuery("from Payhead where payheadtype='"+payHeadType+"' and academicyear='"+academicYear+"'").list();
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			
			return payHead;
	}

	public Paybasic getBasicPay(int idteacher, String academicYear) {
		
		Paybasic basicPay = new Paybasic();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery("select * from Paybasic where idteacher = "+idteacher+" and academicyear='"+academicYear+"' ORDER BY idpaybasic DESC LIMIT 1").addEntity(Paybasic.class);
			basicPay = (Paybasic) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return basicPay;
	}

	public List<Payheadstaffdetails> getPayHeadStaff(int teacherid, String academicYear) {
		
		List<Payheadstaffdetails> payHeadStaffList = new ArrayList<Payheadstaffdetails>();
		
		try {
			transaction = session.beginTransaction();
			payHeadStaffList = session.createQuery("from Payheadstaffdetails where idteacher = "+teacherid+" and academicyear='"+academicYear+"'").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return payHeadStaffList;
	}

	public List<Processsalarydetails> issueStaffSalary(String academicYear) {
		
	List<Processsalarydetails> processSalaryDetails = new ArrayList<Processsalarydetails>();
		
		try {
			transaction = session.beginTransaction();
			processSalaryDetails = session.createQuery("from Processsalarydetails where academicyear='"+academicYear+"'").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return processSalaryDetails;
	}

	public Processsalarydetails getProcessSalaryDetails(int processId) {
		
		Processsalarydetails processSalaryDetails = new Processsalarydetails();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetails where idprocesssalarydetails="+processId+"");
			processSalaryDetails = (Processsalarydetails) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return processSalaryDetails;
	}

	public List<Processsalarydetailsheads> getProcessSalaryHeads(int processId) {
		
		List<Processsalarydetailsheads> processSalaryHeadsList = new ArrayList<Processsalarydetailsheads>();
		
		try {
			transaction = session.beginTransaction();
			processSalaryHeadsList = session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+"").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return processSalaryHeadsList;
	}

	public Processsalarydetailsheads getProcessSalaryBasicPay(int processId) {
		
		Processsalarydetailsheads processSalaryHeads = new Processsalarydetailsheads();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+" and payheadname='Basic Pay'");
			processSalaryHeads = (Processsalarydetailsheads) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return processSalaryHeads;
	}

	public List<Payheadstaffdetails> getStaffDetails(int staffId, String academicYear) {
		
		List<Payheadstaffdetails> PayHeadStaffDetailsList = new ArrayList<Payheadstaffdetails>();
		
		try {
			transaction = session.beginTransaction();
			PayHeadStaffDetailsList = session.createQuery("from Payheadstaffdetails where idteacher="+staffId+" and academicyear='"+academicYear+"'").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return PayHeadStaffDetailsList;
	}

	public List<Processsalarydetails> getStaffinfo(int teacherId) {

		List<Processsalarydetails> processSalaryDetails = new ArrayList<Processsalarydetails>();
		
		try {
			transaction = session.beginTransaction();
			processSalaryDetails = session.createQuery("from Processsalarydetails where teacherid="+teacherId+"").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return processSalaryDetails;
	}

	public boolean deletePayHeadStaff(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Payheadstaffdetails where idpayheadstaffdetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
			
			} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public Processsalarydetails checkprocessedStaffSalary(int staffId, String month, String year) {
		
		Processsalarydetails processSalaryDetails = new Processsalarydetails();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Processsalarydetails where teacherid="+staffId+" and month='"+month+"' and year='"+year+"'");
			processSalaryDetails = (Processsalarydetails) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return processSalaryDetails;
	}

	public boolean issueProcessedSalary(List ids) {

		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update Processsalarydetails set status = 'ISSUED' where idprocesssalarydetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public boolean cancelProcessedSalary(List ids) {
		
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update Processsalarydetails set status = 'CANCELLED' where idprocesssalarydetails IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}
	
}
