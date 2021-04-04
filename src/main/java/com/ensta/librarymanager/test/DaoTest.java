package com.ensta.librarymanager.test;

import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate ;

public class DaoTest 
{
	public static void main( String[] args ) throws DaoException
	{

		// Tests pour LivreDao
		System.out.println();
		System.out.println("LIVRE DAO TEST");
		List<Livre> livresAvant = new ArrayList<>();
		List<Livre> livresApres = new ArrayList<>();
		Livre livre8 =  new Livre();
		Livre livre1 = new Livre(1, "Les Oiseaux Migrateurs","Patrick FICHTER","978-2817704876"); // On reecrit le titre avec des majuscules
		int countLivresAvant = -1;
		int countLivresApres = -1;
		try (Connection connection = ConnectionManager.getConnection())
		{
			LivreDaoImpl dao = LivreDaoImpl.getInstance();
			livre8 = dao.getById(8); // Recuperer le livre pour lequel id = 8
			livresAvant = dao.getList(); // Lister tous les livres
			countLivresAvant = dao.count(); // Compter le nombre de livres
			dao.create("Incendies", "Wajdi Mouawad", "656-29456668"); // Creer un livre
			dao.delete(4); // Supprimer le livre 4
			dao.update(livre1); // Mettre a jour le livre 1
			livresApres = dao.getList(); // Lister tous les livres
			countLivresApres = dao.count(); // Compter le nombre de livres
        	}
		catch (DaoException | SQLException e)
		{
			System.out.println(e);
		}
		System.out.println();
		System.out.println("SELECT_ALL :");
		System.out.println(livresAvant.toString()); // Base de donnees avant
		System.out.println();
		System.out.println("COUNT :"); 
		System.out.println(countLivresAvant); // Nombre de livres avant
		System.out.println();
		System.out.println("SELECT_BY_ID :"); 
		System.out.println(livre8.toString());
		System.out.println();
		System.out.println("SELECT_ALL :");
		System.out.println(livresApres.toString()); // Base de donnees apres
		System.out.println();
		System.out.println("COUNT :");
		System.out.println(countLivresApres); // Nombre de livres apres
		System.out.println();

		// Tests pour MembreDao
		System.out.println();
		System.out.println("MEMBRE DAO TEST");
		List<Membre> membresAvant = new ArrayList<>();
		List<Membre> membresApres = new ArrayList<>();
		Membre membre4 =  new Membre();
		Membre membre1 = new Membre(1, "CHERIF", "Kader", "2 rue Sadi Carnot", "kcherif@email.com", "0243585672", Abonnement.VIP); //Upgrade Abonnement
		int countMembresAvant = -1;
		int countMembresApres = -1;
		try (Connection connection = ConnectionManager.getConnection())
		{
			MembreDaoImpl dao = MembreDaoImpl.getInstance();
			membre4 = dao.getById(4);
			membresAvant = dao.getList();
			countMembresAvant = dao.count();
			dao.create("DELAJUNGLE", "George", "56 quai des bananiers", "g2lajunge@hotmail.com", "0656869624"); // Creer un membre
			dao.delete(6); // Supprimer le membre 6
			dao.update(membre1); // Mettre à jour le membre 1
		}
		catch (DaoException | SQLException e)
		{
			System.out.println(e);
		}
		try (Connection connection = ConnectionManager.getConnection())
		{
			MembreDaoImpl dao = MembreDaoImpl.getInstance();
			membresApres = dao.getList(); // Lister tous les membres
			countMembresApres = dao.count(); // Compter le nombre de membres
		}
		catch (DaoException | SQLException e)
		{
			System.out.println(e);
		}
		try (Connection connection = ConnectionManager.getConnection())
		{
			MembreDaoImpl dao = MembreDaoImpl.getInstance();
			membresApres = dao.getList(); // Lister tous les membres
			countMembresApres = dao.count(); // Compter le nombre de membres
		}
		catch (DaoException | SQLException e)
		{
			System.out.println(e);
		}
		System.out.println();
		System.out.println("SELECT_ALL :");
		System.out.println(membresAvant.toString()); // Base de donnees avant
		System.out.println();
		System.out.println("COUNT :");
		System.out.println(countMembresAvant); // Nombre de membres avant
		System.out.println();
		System.out.println("SELECT_BY_ID :");
		System.out.println(membre4.toString());
		System.out.println();
		System.out.println("SELECT_ALL :");
		System.out.println(membresApres.toString()); // Base de donnees apres
		System.out.println();
		System.out.println("COUNT :");
		System.out.println(countMembresApres); // Nombre de livres apres
		System.out.println();

		// Tests pour EmpruntDao
		System.out.println();
		System.out.println("EMPRUNT DAO TEST");
		List<Emprunt> emprunts = new ArrayList<>();
		List<Emprunt> empruntsCourants = new ArrayList<>();
		//List<Emprunt> empruntsMembre = new ArrayList<>();
		Emprunt emprunt5 = new Emprunt();	
		Emprunt emprunt6 = new Emprunt(6,membre4,livre8,LocalDate.of(2019,05,01),LocalDate.of(2019,05,15));
		System.out.println();
		System.out.println("emprunt6 : " + emprunt6);
		System.out.println();
		int countEmpruntsAvant = -1;
		int countEmpruntsApres = -1;
		try (Connection connection = ConnectionManager.getConnection())
		{
			EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
			emprunts = dao.getList(); // Lister tous les emprunts
			empruntsCourants = dao.getListCurrent(); // ... courants
			//empruntsMembre = dao.getListCurrentByMembre(2); // ... pour le membre 2
			emprunt5 = dao.getById(5); // Recuperer l'emprunt 5
			countEmpruntsAvant = dao.count();
        	}
		catch (DaoException | SQLException e)
		{
			System.out.println(e);
		}
		System.out.println();
		System.out.println("SELECT_ALL :");
		System.out.println(emprunts.toString()); // Base de donnees avant
		System.out.println();
		System.out.println("COUNT :");
		System.out.println(countEmpruntsAvant); // Nombre de livres apres
		System.out.println();
		System.out.println("SELECT_CURRENT :");
		System.out.println(empruntsCourants.toString()); 
		System.out.println();
		System.out.println("SELECT_BY_ID :");
		System.out.println(emprunt5.toString()); 
		System.out.println();
		/*
		System.out.println("SELECT_BY_MEMBRE :");
		System.out.println(empruntsMembre.toString()); 
		System.out.println();
		*/
		try (Connection connection = ConnectionManager.getConnection())
		{
			EmpruntDaoImpl dao = EmpruntDaoImpl.getInstance();
			
			dao.create(1,1,LocalDate.of(2019,03,11));
			dao.update(emprunt6);
			emprunts = dao.getList(); // Lister tous les emprunts
			countEmpruntsApres = dao.count();
        	}
		catch (DaoException | SQLException e)
		{
			System.out.println(e);
		}
		System.out.println();
		System.out.println("SELECT_ALL :");
		System.out.println(emprunts.toString()); // Base de donnees apres
		System.out.println();
		System.out.println("COUNT :");
		System.out.println(countEmpruntsApres); // Nombre de livres apres
		System.out.println();
	}
}
