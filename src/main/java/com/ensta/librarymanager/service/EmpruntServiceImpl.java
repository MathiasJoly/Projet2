package com.ensta.librarymanager.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate ;

import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntServiceImpl implements EmpruntService
{
	private static EmpruntServiceImpl instance;

	private EmpruntServiceImpl() {}

	public static EmpruntServiceImpl getInstance()
	{
		if (instance == null){
		instance = new EmpruntServiceImpl();
		}
		return instance;
	}

	public List<Emprunt> getList() throws ServiceException
	{ 
		List<Emprunt> result = new ArrayList<>();
		EmpruntDao edao = EmpruntDaoImpl.getInstance();
		try
		{
			result = edao.getList();
		}
		catch (DaoException e)
		{
			System.out.println(e.getMessage());
		};
		return result;
	}

	public List<Emprunt> getListCurrent() throws ServiceException
	{ 
		List<Emprunt> result = new ArrayList<>();
		EmpruntDao edao = EmpruntDaoImpl.getInstance();
		try
		{
			result = edao.getListCurrent();
		}
		catch (DaoException e)
		{
			System.out.println(e.getMessage());
		};
		return result; 
	}

	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException
	{ List<Emprunt> result = new ArrayList<>(); return result; }

	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException
	{ List<Emprunt> result = new ArrayList<>(); return result; }

	public Emprunt getById(int id) throws ServiceException
	{
		EmpruntDao edao = EmpruntDaoImpl.getInstance();
		Emprunt emprunt = new Emprunt();
		try
		{
			emprunt = edao.getById(id);
		}
		catch ( DaoException e)
		{
			System.out.println(e.getMessage());
		};
		return emprunt;
	}

	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException
	{
		EmpruntDao edao = EmpruntDaoImpl.getInstance();
		try
		{
			edao.create(idMembre, idLivre, dateEmprunt);
		}
		catch ( DaoException e)
		{
			System.out.println(e.getMessage());
		};
	}

	public void returnBook(int id) throws ServiceException
	{
		EmpruntDao edao = EmpruntDaoImpl.getInstance();
		try
		{
			Emprunt emprunt = this.getById(id);
			emprunt.setDateRetour( LocalDate.now() );
			edao.update(emprunt);
		}
		catch (DaoException | ServiceException e)
		{
			System.out.println(e.getMessage());
		};
	}

	public int count() throws ServiceException
	{ 
		EmpruntDao edao = EmpruntDaoImpl.getInstance();
		int count = 0;
		try
		{
			count = edao.count();
		}
		catch ( DaoException e)
		{
			System.out.println(e.getMessage());
		};
		return count;
	}

	public boolean isLivreDispo(int idLivre) throws ServiceException
	{
		boolean result = true;
		List<Emprunt> emprunts = new ArrayList<>();
		try
		{
			emprunts = this.getListCurrent(); 
		}
		catch (ServiceException e)
		{
			System.out.println(e.getMessage());
		}
		for(Emprunt e : emprunts)
		{
			if (e.getLivre().getId() == idLivre) result = false;
		} 
		return result; 
	}

	public boolean isEmpruntPossible(Membre membre) throws ServiceException
	{ 
		boolean result = true;
		List<Emprunt> emprunts = new ArrayList<>();
		try
		{
			emprunts = this.getListCurrent(); 
		}
		catch (ServiceException e)
		{
			System.out.println(e.getMessage());
		}
		Membre emembre = new Membre();
		for(Emprunt e : emprunts)
		{
			emembre = e.getMembre();
			if (emembre.getId() == membre.getId()) 
			{
				if (emembre.getAbonnement() == Abonnement.BASIC) result = false;
			};
		} 
		return result;  }
}

