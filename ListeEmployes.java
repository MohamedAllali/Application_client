package presentation;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import domaine.*;

public class ListeEmployes extends JFrame implements ActionListener
{
	private JTable table;
	private ArrayList<Employe> liste;
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;
	
	public ListeEmployes(ArrayList <Employe> liste)
	{
		panneau1=new JPanel();
		panneau2=new JPanel();
		qt = new JButton("Quitter");
		 this.liste=liste;
		  sc  = new JScrollPane();
		  table = new JTable();
		  sc.setViewportView(table);
		  DefaultTableModel modele = (DefaultTableModel)table.getModel();
		  modele.addColumn("Numero Employe");
		  modele.addColumn("Nom");
		  modele.addColumn("Prenom");
		  modele.addColumn("Telephone");
		  		
		  int ligne=0;
		  for (Employe emp : liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(emp.getMatr()),ligne,0);
			  modele.setValueAt(emp.getNom(), ligne, 1);
			  modele.setValueAt(emp.getPrenom(),ligne,2);
			  modele.setValueAt(emp.getPhone(),ligne, 3);
			  ligne++;
		  }
		 
		  setTitle("Liste des employes");
		  setSize(550,500);
		  qt.addActionListener(this); 
		  panneau1.add(sc);
		  panneau2.add(qt);
		  add(panneau1,BorderLayout.NORTH);
		  add(panneau2,BorderLayout.SOUTH);
		  setLocationRelativeTo(null);
		  setVisible(true);
	}
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource()==qt)
    	{
    		dispose();
    		new Client();
    	}
    }
	
}
