package domaine;

import java.io.*;


public class Employe implements Serializable

{
	private int matr;
	private String nom;
	private String prenom;
	private String phone;
	
	public Employe(int matr, String nom, String prenom, String phone) {
		
		this.matr = matr;
		this.nom = nom;
		this.prenom = prenom;
		this.phone = phone;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getMatr() {
		return matr;
	}
	public void setMatr(int matr) {
		this.matr = matr;
	}

}
