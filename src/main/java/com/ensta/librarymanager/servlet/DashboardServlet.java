package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.service.*;
import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class DashboardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getServletPath();
		switch (action) 
		{
			case "/nblivres":
				countLivres(request, response);
				break;
			case "/nbmembres":
				countMembres(request, response);
				break;
			case "/nbemprunts":
				countEmprunts(request, response);
				break;
			case "/listcurrent":
				listEmpruntsActuels(request, response);
				break;
			default:
				System.out.println("Default redirecting case from " + action + " !");
		}
	}

	private void countLivres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int count = 0;
		LivreService lserv = LivreServiceImpl.getInstance();
		try 
		{
			count = lserv.count();
		} 
		catch (ServiceException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("nblivres",count);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		dispatcher.forward(request, response);
	}

	private void countMembres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int count = 0;
		MembreService mserv = MembreServiceImpl.getInstance();
		try 
		{
			count = mserv.count();
		} 
		catch (ServiceException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("nbmembres",count);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		dispatcher.forward(request, response);
	}

	private void countEmprunts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int count = 0;
		EmpruntService eserv = EmpruntServiceImpl.getInstance();
		try 
		{
			count = eserv.count();
		} 
		catch (ServiceException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("nbemprunts",count);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		dispatcher.forward(request, response);
	}

	private void listEmpruntsActuels(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<Emprunt> emprunts = new ArrayList<>();
		EmpruntService eserv = EmpruntServiceImpl.getInstance();
		try 
		{
			emprunts = eserv.getListCurrent();
		} 
		catch (ServiceException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("listcurrent",emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		dispatcher.forward(request, response);
	}

}
