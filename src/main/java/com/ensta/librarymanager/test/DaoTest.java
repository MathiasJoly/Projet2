package com.ensta.librarymanager.test;

import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.utils.FillDatabase;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class DaoTest 
{
	public static void main( String[] args )
	{
		List<Livre> livres = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();)
		{
			LivreDaoImpl dao = new LivreDaoImpl();
			livres = dao.getList();
        	}
		catch (DaoException e)
		{
			System.out.println(e);
		}
		System.out.println(livres.toString());
	}
}
