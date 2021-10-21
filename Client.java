package presentation;

import java.net.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import domaine.*;


public class Client extends JFrame implements ActionListener 
{
	private JLabel lmatr,lnom,lprenom,ltel;
	private JTextField chmatr,chnom,chprenom,chtel;
	private JButton aj,qt,rec,mod,sup,aff;
	private JPanel pan1,pan2,pan3;
	private Socket socket;
	DataOutputStream out;
	DataInputStream in;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    
	public Client() 
	{
		lmatr=new JLabel("Title:");
		lnom= new JLabel("COntenu:");
		lprenom= new JLabel("datecreation:");
		ltel= new JLabel("Categorie");
		chmatr= new JTextField();
		chnom=new JTextField();
		chprenom = new JTextField();
		chtel = new JTextField();
		aj = new JButton("Enregistrer");
		qt = new JButton("Quitter");
		aff= new JButton("Afficher");
		rec = new JButton("Rechercher");
		mod = new JButton("Modifier");
		sup = new JButton("Supprimer");
		aj.addActionListener(this);
		aff.addActionListener(this);
		rec.addActionListener(this);
		mod.addActionListener(this);
		sup.addActionListener(this);
		qt.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3 = new JPanel();
		pan1.setLayout(new GridLayout(1,3));
		pan2.setLayout(new GridLayout(3,2));
		pan1.add(lmatr);
		pan1.add(chmatr);
		pan1.add(rec);
		pan2.add(lnom);
		pan2.add(chnom);
		pan2.add(lprenom);
		pan2.add(chprenom);
		pan2.add(ltel);
		pan2.add(chtel);
		pan3.add(aj);
		pan3.add(mod);
		pan3.add(sup);
		pan3.add(aff);
		pan3.add(qt);
		add(pan1,BorderLayout.NORTH);
		add(pan2,BorderLayout.CENTER);
		add(pan3,BorderLayout.SOUTH);
		setTitle("Client TCP Swing Gestion des Employes");
		setSize(650,200);
		setLocationRelativeTo(null);
		setVisible(true);
		try
		{
		socket= new Socket("localhost",8000);
		out = new DataOutputStream(socket.getOutputStream());
	    in = new DataInputStream(socket.getInputStream());
	    oos=new ObjectOutputStream(socket.getOutputStream());;
	    ois=new ObjectInputStream(socket.getInputStream());
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	public void actionPerformed(ActionEvent e)
	{
      if (e.getSource()==rec)
		{
    	  try
    	  {
    		oos.writeObject("rechercher");  
    		oos.flush();
    	    String num = chmatr.getText();
  			int matr =Integer.parseInt(num);
  			out.writeInt(matr);
  			out.flush();
  			Employe p =(Employe)ois.readObject();
  			if (p==null)
  				JOptionPane.showMessageDialog(null, "Employe inexistant!!!");
  			else
  			{
  				chnom.setText(p.getNom());
  	  			chprenom.setText(p.getPrenom());
  	  			chtel.setText(p.getPhone());
  			}
  			
    	  }
    	  
    	  catch(Exception ex)
    	  {
    		  System.out.println(ex.getMessage());
    	  }
			
		}
	 else
			if (e.getSource()==mod)
			{
				try
		    	  {
		    		oos.writeObject("modifier");  
		    		oos.flush();
		    	    String num = chmatr.getText();
		  			int matr =Integer.parseInt(num);
		  			String nom = chnom.getText();
		  			String prenom = chprenom.getText();
		  			String tel = chtel.getText();
		  			Employe p = new Employe(matr,nom,prenom,tel);
		  			oos.writeObject(p);
		  			oos.flush();
		  			chmatr.setText("");
		    		chnom.setText("");
		    		chprenom.setText("");
		    		chtel.setText("");
		    
		  			  
		    	  }
		    	  catch(Exception ex)
		    	  {
		    		  System.out.println(ex.getMessage());
		    	  }		
			}
			else
				if (e.getSource()==sup)
				{
					 try
			    	  {
			    		oos.writeObject("supprimer");  
			    		oos.flush();
			    	    String num = chmatr.getText();
			  			int matr =Integer.parseInt(num);
			  			out.writeInt(matr);
			  			out.flush();
			  			chmatr.setText("");
			    		chnom.setText("");
			    		chprenom.setText("");
			    		chtel.setText("");
			    
			    	  }
			    	  catch(Exception ex)
			    	  {
			    		  System.out.println(ex.getMessage());
			    	  }		
				}
	   else
		   if (e.getSource()==aj)
		 {
			
			Employe p;
			 try
			 {
			 

		    	    oos.writeObject("ajout");
		        	oos.flush();
		        	String smatr=chmatr.getText();
		    		int matr = Integer.parseInt(smatr);
		    		String nom=chnom.getText();
		    		String prenom=chprenom.getText();
		    		String phone=chtel.getText();
		    	    p = new Employe(matr,nom,prenom,phone);
		    		oos.writeObject(p);
		    		oos.flush();
		    		chmatr.setText("");
		    		chnom.setText("");
		    		chprenom.setText("");
		    		chtel.setText("");
		    }
			 catch(Exception ex)
			 {
				 System.out.println(ex.getMessage());
			 }
		}
		
		else
			
			if (e.getSource()==aff)
			{
				try
				{
					oos.writeObject("lister");	
			         oos.flush();
			         ArrayList<Employe> liste=(ArrayList<Employe>)ois.readObject();
			         new ListeEmployes(liste);
			        
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		
			else
				if (e.getSource()==qt)
				{
					try
					{
					 oos.writeObject("fin");
			    	 oos.flush();
			    	 socket.close();
					dispose();
					System.exit(0);
				    }
					catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
				}	
	}
	public static void main(String args[])
	{
		new Client();
	 
     }
}