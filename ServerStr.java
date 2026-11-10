import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerStr {
	 
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    String personaggioScelto="";
    String personaggioClient="";
    String stringaRicevutaDalClient;
    
    public void scegliPersonaggioDaIndovinare(String nome) {
    	personaggioScelto=nome;
    }
    
    public Socket attendi() {
    	try
    	{
    		System.out.println("SERVER partito in esecuzione ...");
    		//Creo un server sulla porta 6789
    		server = new ServerSocket(6789);
    		//Rimane in attesa di un client
    		client = server.accept();
    		//Chiudo il server per inibire altri client
    		server.close();
    		//Associo due oggetti al socket dei client per effettuare la scrittura e la lettura
    		inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
    		outVersoClient = new DataOutputStream(client.getOutputStream());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println("Errore durante l'istanza del server");
    		System.exit(1);
    	}
    	return client;
    }
    
    public void comunica()
    {
    	try
    	{
    		// rimango in attesa della riga trasmessa dal client
	    	System.out.println("Benvenuto client. Attendo ...");
    		while(true)  //Finché non si chiude la connessione col client (quindi finché il cliente non vince o perde)
    		{
	    		stringaRicevuta = inDalClient.readLine();
	    		
	    		if(stringaRicevuta.startsWith("personaggioClient:"))
	    		{
	    			personaggioClient=stringaRicevuta;
	    		}
	    		
	    	    if(stringaRicevuta.startsWith("tentativo:"))  
	    	    {
	    	      if(personaggioScelto.equals(""))
	    	    	  outVersoClient.writeBytes("Il server non ha ancora scelto un personaggio" + '\n');
	    	      else
	    	      {
			    	  if(("tentativo:" + personaggioScelto).equals(stringaRicevuta))
			    	  {
			    		  outVersoClient.writeBytes("Hai indovinato!" + '\n');
			    		  System.out.println("Il client ha vinto!");
			    	  }
				      else
				      {
				    	 outVersoClient.writeBytes("Non hai indovinato!" + '\n');
			    	     // termina elaborazione sul server : chiudo la connessione del client
				    	 System.out.println("Il server ha vinto!");
				      }
				      return;
	    	      }
	    	    }
		    	  
	    	    if(stringaRicevuta.startsWith("domanda:"))
	    	    {
	    	    	if(personaggioScelto.equals(""))
		    	    	  outVersoClient.writeBytes("Il server non ha ancora scelto un personaggio" + '\n');
		    	    else
		    	    {
		    	    	int numeroDomanda = Integer.parseInt(stringaRicevuta.substring(stringaRicevuta.indexOf(":") + 1));
		    	    	switch(numeroDomanda)
		    	    	{
		    	    	   case 0:
		    	    	   {
		    	    		   if(personaggioScelto.equals("Luigi Verdi") || personaggioScelto.equals("Elena Martino"))
		    	    			   outVersoClient.writeBytes("Si" + '\n');
		    	    		   else
		    	    			   outVersoClient.writeBytes("No" + '\n');
		    	    		   break;
		    	    	   }
		    	    	   case 1:
		    	    	   {
		    	    		   if(personaggioScelto.equals("Giulia Ferrari") || personaggioScelto.equals("Anna Moretti") || personaggioScelto.equals("Simona Conte"))
		    	    			   outVersoClient.writeBytes("Si" + '\n');
		    	    		   else
		    	    			   outVersoClient.writeBytes("No" + '\n');
		    	    		   break;
		    	    	   }
		    	    	   case 2:
		    	    	   {
		    	    		   if(personaggioScelto.equals("Giulia Ferrari") || personaggioScelto.equals("Anna Moretti") || personaggioScelto.equals("Simona Conte") || personaggioScelto.equals("Francesca Gallo") || personaggioScelto.equals("Elena Martino"))
		    	    			   outVersoClient.writeBytes("No" + '\n');
		    	    		   else
		    	    			   outVersoClient.writeBytes("Si" + '\n');
		    	    		   break;
		    	    	   }
		    	    	   case 3:
		    	    	   {
		    	    		   if(personaggioScelto.equals("Mario Rossi") || personaggioScelto.equals("Luca Sarti") || personaggioScelto.equals("Valentino Perri") || personaggioScelto.equals("Clauio Mancini") || personaggioScelto.equals("Federico Dini"))
		    	    			   outVersoClient.writeBytes("Si" + '\n');
		    	    		   else
		    	    			   outVersoClient.writeBytes("No" + '\n');
		    	    		   break;
		    	    	   }
		    	    	   case 4:
		    	    	   {
		    	    		   if(personaggioScelto.equals("Giulia Ferrari") || personaggioScelto.equals("Marco Russo") || personaggioScelto.equals("Elena Martino") || personaggioScelto.equals("Fabio De Luca"))
		    	    			   outVersoClient.writeBytes("Si" + '\n');
		    	    		   else
		    	    			   outVersoClient.writeBytes("No" + '\n');
		    	    		   break;
		    	    	   }
		    	    	   case 5:
		    	    	   {
		    	    		   if(personaggioScelto.equals("Marco Russo") || personaggioScelto.equals("Luca Sarti") || personaggioScelto.equals("Valentino Perri") || personaggioScelto.equals("Paolo Ricci") || personaggioScelto.equals("Simona Conte"))
		    	    			   outVersoClient.writeBytes("Si" + '\n');
		    	    		   else
		    	    			   outVersoClient.writeBytes("No" + '\n');
		    	    		   break;
		    	    	   }
		    	    	   case 6:
		    	    	   {
		    	    		   if(personaggioScelto.equals("Giovanni Bianchi") || personaggioScelto.equals("Elena Martino") || personaggioScelto.equals("Roberto Romano"))
		    	    			   outVersoClient.writeBytes("Si" + '\n');
		    	    		   else
		    	    			   outVersoClient.writeBytes("No" + '\n');
		    	    		   break;
		    	    	   }
		    	    	}
		    	    }
	    	    }
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println("Errore durante l'istanza del server");
    		System.exit(1);
    	}
    }
    	
	public void indovina(String nome) {
    	try
    	{   		
    		if(personaggioClient.equals(""))
    			JOptionPane.showMessageDialog(null, "Il client non ha ancora scelto un personaggio");
    		else
    		{
	    		if(("personaggioClient:" + nome).equals(personaggioClient))
	    		{
	    			JOptionPane.showMessageDialog(null, "Hai vinto!");
	    			System.out.println("Il server ha vinto!");
	    		}
	    		else
	    		{
	    			JOptionPane.showMessageDialog(null, "Hai perso");
	    			System.out.println("Il client ha vinto!");
	    		}	
	    		client.close();
    		}
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println("Errore durante la comunicazione col server!");
    		System.exit(1);
    	}
    }
	
	public void faiDomanda(int numeroDomanda)
	{
		if(personaggioClient.equals(""))
			JOptionPane.showMessageDialog(null, "Il client non ha ancora scelto un personaggio");
		else
	    	switch(numeroDomanda)
	    	{
	    	   case 0:
	    	   {
	    		   if(personaggioClient.equals("personaggioClient:Luigi Verdi") || personaggioClient.equals("personaggioClient:Elena Martino"))
	    			   JOptionPane.showMessageDialog(null, "Si");
	    		   else
	    			   JOptionPane.showMessageDialog(null, "No");
	    		   break;
	    	   }
	    	   case 1:
	    	   {
	    		   if(personaggioClient.equals("personaggioClient:Giulia Ferrari") || personaggioClient.equals("personaggioClient:Anna Moretti") || personaggioClient.equals("personaggioClient:Simona Conte"))
	    			   JOptionPane.showMessageDialog(null, "Si");
	    		   else
	    			   JOptionPane.showMessageDialog(null, "No");
	    		   break;
	    	   }
	    	   case 2:
	    	   {
	    		   if(personaggioClient.equals("personaggioClient:Giulia Ferrari") || personaggioClient.equals("personaggioClient:Anna Moretti") || personaggioClient.equals("personaggioClient:Simona Conte") || personaggioClient.equals("personaggioClient:Francesca Gallo") || personaggioClient.equals("personaggioClient:Elena Martino"))
	    			   JOptionPane.showMessageDialog(null, "No");
	    		   else
	    			   JOptionPane.showMessageDialog(null, "Si");
	    		   break;
	    	   }
	    	   case 3:
	    	   {
	    		   if(personaggioClient.equals("personaggioClient:Mario Rossi") || personaggioClient.equals("personaggioClient:Luca Sarti") || personaggioClient.equals("personaggioClient:Valentino Perri") || personaggioClient.equals("personaggioClient:Clauio Mancini") || personaggioClient.equals("personaggioClient:Federico Dini"))
	    			   JOptionPane.showMessageDialog(null, "Si");
	    		   else
	    			   JOptionPane.showMessageDialog(null, "No");
	    		   break;
	    	   }
	    	   case 4:
	    	   {
	    		   if(personaggioClient.equals("personaggioClient:Giulia Ferrari") || personaggioClient.equals("personaggioClient:Marco Russo") || personaggioClient.equals("personaggioClient:Elena Martino") || personaggioClient.equals("personaggioClient:Fabio De Luca"))
	    			   JOptionPane.showMessageDialog(null, "Si");
	    		   else
	    			   JOptionPane.showMessageDialog(null, "No");
	    		   break;
	    	   }
	    	   case 5:
	    	   {
	    		   if(personaggioClient.equals("personaggioClient:Marco Russo") || personaggioClient.equals("personaggioClient:Luca Sarti") || personaggioClient.equals("personaggioClient:Valentino Perri") || personaggioClient.equals("personaggioClient:Paolo Ricci") || personaggioClient.equals("personaggioClient:Simona Conte"))
	    			   JOptionPane.showMessageDialog(null, "Si");
	    		   else
	    			   JOptionPane.showMessageDialog(null, "No");
	    		   break;
	    	   }
	    	   case 6:
	    	   {
	    		   if(personaggioClient.equals("personaggioClient:Giovanni Bianchi") || personaggioClient.equals("personaggioClient:Elena Martino") || personaggioClient.equals("personaggioClient:Roberto Romano"))
	    			   JOptionPane.showMessageDialog(null, "Si");
	    		   else
	    			   JOptionPane.showMessageDialog(null, "No");
	    		   break;
	    	   }
	    	}
	}
}
