/**
 * 
 */
package com.model.printids.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.service.FeesService;
import com.model.printids.service.PrintIdsService;
import com.model.stampfees.service.StampFeesService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class PrintIdsAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public PrintIdsAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action, String page) {
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("printpreview")) {
			url = printPreview();
		}else if (action.equalsIgnoreCase("searchDetails")) {
			url = searchDetails();
		}
		return url;
	}
	

	private String searchDetails() {
        
		new PrintIdsService(request, response).searchDetails();
        return "generateids.jsp";
	}

	private String printPreview() {

		new PrintIdsService(request, response).printMultiple();
        return "printpreview.jsp";
	}

}
