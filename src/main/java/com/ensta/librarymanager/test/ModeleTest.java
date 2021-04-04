package com.ensta.librarymanager.test;

import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

import java.time.LocalDate;

public class ModeleTest 
{
	public static void main( String[] args ) throws Exception
	{
		try
		{
			int id = 1;
			Membre membre = new Membre(id, "Joly", "Mathias", "4 rue Bailly 75003 Paris", "mathias.joly@ensta-paris.fr", "06 93 91 94 95", Abonnement.VIP);
			Livre livre = new Livre(id, "L'insoutenable légèreté de l'être", "Milan Kundera", "817525766-0");
			LocalDate dateEmprunt = LocalDate.of(2021,3,9);
			LocalDate dateRetour = LocalDate.of(2021,3,30);
			Emprunt emprunt = new Emprunt(id, membre, livre, dateEmprunt, dateRetour);
			System.out.println(emprunt.toString());
		}
		catch (Exception e)
		{
		e.printStackTrace();
		}
	}
}
