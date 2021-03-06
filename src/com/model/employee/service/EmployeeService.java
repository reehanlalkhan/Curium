package com.model.employee.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.position.dao.positionDAO;
import com.model.position.dto.Position;
import com.model.std.dao.standardDetailsDAO;
import com.model.std.dto.Classsec;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.user.dao.UserDAO;
import com.model.user.dto.Login;
import com.model.user.service.UserService;
import com.util.DataUtil;
import com.util.DateUtil;

public class EmployeeService {

	private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;
    
	public EmployeeService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}

	public boolean addEmployee() {
		Teacher employee = new Teacher();
		
		employee.setTeachername(DataUtil.emptyString(request.getParameter("name")));
		employee.setGender(DataUtil.emptyString(request.getParameter("gender")));
		employee.setAddress(DataUtil.emptyString(request.getParameter("address")));
		employee.setContactnumber(DataUtil.emptyString(request.getParameter("contactnumber")));
		employee.setEmail(DataUtil.emptyString(request.getParameter("email")));
		employee.setDateofjoining(DateUtil.simpleDateParser(request.getParameter("dateofjoining")));
		employee.setTotalexperience(DataUtil.emptyString(request.getParameter("totalexperience")));
		employee.setQualification(DataUtil.emptyString(request.getParameter("qualification")));
		employee.setDepartment(DataUtil.emptyString(request.getParameter("department")));
		employee.setDesignation(DataUtil.emptyString(request.getParameter("designation")));
		employee.setSalary(DataUtil.emptyString(request.getParameter("salary")));
		employee.setRemarks(DataUtil.emptyString(request.getParameter("remarks")));
		
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int count =4;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		employee.setTeacherexternalid(builder.toString());
		
		if(new EmployeeDAO().create(employee)){
			if(new UserService(request, response).addUser(employee)){
				return true;
			}else{
				new EmployeeDAO().delete(employee);
			}
		}
		
		return false;
	}

	public boolean ViewAllEmployee() {
		
		boolean result = false;
    try {
    	List<Teacher> list = new EmployeeDAO().readListOfObjects();
        httpSession.setAttribute("employeeList", list);
        httpSession.setAttribute("employeeListProcessSalary", list);
        result = true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

	public boolean viewDetailsEmployee() {
		 boolean result = false;
	        try {
	            long id = Long.parseLong(request.getParameter("id"));
	            Teacher employee = new EmployeeDAO().readUniqueObject(id);
	            Login employeeLogin = new UserDAO().getUserDetails(employee.getTeacherexternalid());
	           
	            if (employee.getTid() != null) {
	            	httpSession.setAttribute("employee", employee);
	                request.setAttribute("stafflogin", employeeLogin);
	                return true;
	            } 
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = false;
	        }
	        return result;
	}

	public String updateEmployee() {
		Teacher employee = new Teacher();
		
		
        String id = "";
        id = request.getParameter("id");
        
        System.out.println("THE ID IS: " + id);

        int employeeId = 0;
        int parentsId = 0;
        employeeId = Integer.parseInt(id);
        
        System.out.println("M in in personal service and ID is :::::::::::::::::::::::::::::: " + employeeId);

        employee.setTid(employeeId);
        employee.setTeachername(DataUtil.emptyString(request.getParameter("name")));
		employee.setGender(DataUtil.emptyString(request.getParameter("gender")));
		employee.setAddress(DataUtil.emptyString(request.getParameter("address")));
		employee.setContactnumber(DataUtil.emptyString(request.getParameter("contactnumber")));
		employee.setEmail(DataUtil.emptyString(request.getParameter("email")));
		employee.setDateofjoining(DateUtil.datePars(request.getParameter("dateofjoining")));
		employee.setTotalexperience(DataUtil.emptyString(request.getParameter("totalexperience")));
		employee.setQualification(DataUtil.emptyString(request.getParameter("qualification")));
		employee.setDepartment(DataUtil.emptyString(request.getParameter("department")));
		employee.setDesignation(DataUtil.emptyString(request.getParameter("designation")));
		employee.setSalary(DataUtil.emptyString(request.getParameter("salary")));
		employee.setRemarks(DataUtil.emptyString(request.getParameter("remarks")));
		employee.setTeacherexternalid(DataUtil.emptyString(request.getParameter("teacherexternalid")));
				
		
		employee = new EmployeeDAO().update(employee);
				
        String empId = employee.getTid().toString();
        return empId;
	}

	public void deleteMultiple() {
		 String[] employeeIds = request.getParameterValues("employeeIDs");
		 if(employeeIds!=null){
	        List ids = new ArrayList();
	        for (String id : employeeIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + employeeIds.length);
	        new EmployeeDAO().deleteMultiple(ids);
		 }
	}

	public void viewAllRelations() {
		List<Department> listDepartment = new departmentDAO().readListOfObjects();
        httpSession.setAttribute("listDepartment", listDepartment);
        List<Position> listPosition = new positionDAO().readListOfObjects();
        httpSession.setAttribute("listPosition", listPosition);
		
	}

	public void searchEmployee() {
		String staffName = request.getParameter("staffName");
		String staffDepartment = request.getParameter("staffDepartment");
		List<Teacher> employeeList = new ArrayList<Teacher>();
		if(staffName!=""){
			employeeList = new EmployeeDAO().readListOfEmployeesByName(staffName);
		}else if(staffDepartment!=""){
			employeeList = new EmployeeDAO().readListOfEmployeesByDepartment(staffDepartment);
		}
		request.setAttribute("employeeList", employeeList);
		
		new EmployeeService(request, response).ViewAllEmployee();
	}

}
