package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.utils.FillDatabase;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao {

	String SELECT_ALL =  "SELECT id, titre, auteur, isbn FROM livre;";
	public List<Livre> getList() throws DaoException
	{
		List<Livre> result = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL))
		{
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Livre livre =  new Livre();
				livre.setId(rs.getInt("id"));
				livre.setTitre(rs.getString("titre"));
				livre.setAuteur(rs.getString("auteur"));
				livre.setIsbn(rs.getString("isbn"));
				result.add(film);
			}
			return result;
        	}
		catch (DaoException e)
		{
			System.out.println(e);
		}
	}

	public Livre getById(int id) throws DaoException;
	public int create(String titre, String auteur, String isbn) throws DaoException;
	public void update(Livre livre) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
