import java.io.*;
import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Giocatore {
    String nomeServer = "localHost";  //Indirizzo server locale
    int portaServer = 6789;           //Porta x servizio data e ora
    Socket mioSocket;
    BufferedReader tastiera;          //Buffer per l'input da tastiera
    String nome;                      //Stringa inserita da utente
    String stringaRicevutaDalServer;  //Stringa ricevuta dal server
    DataOutputStream outVersoServer;  //Stream di output
    BufferedReader inDalServer;       //Stream di input
    String personaggioScelto="";
    String stringaRicevuta = null;
    
    public Socket connetti(){
    	System.out.println("CLIENT partito in esecuzione...");
    	try
    	{
    		//Per l'input da tastiera
    		tastiera = new BufferedReader(new InputStreamReader(System.in));
    		//Creo un socket
    		mioSocket = new Socket(nomeServer, portaServer);
    		//mioSocket = new Socket(InetAddress.getLocalHost(), 6789);
    		//Associo due oggetti al socket per effettuare la scrittura e la lettura
    		outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
    		inDalServer = new BufferedReader(new InputStreamReader (mioSocket.getInputStream()));
    	}
    	catch (UnknownHostException e) {
    		System.err.println("Host sconosciuto"); }
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    		System.out.println("Errore durante la connessione!");
    		System.exit(1);
    	}
    	return mioSocket;
    }
    
    public void scegliPersonaggioDaIndovinare(String nome) {
    	try
    	{  
	    	personaggioScelto="personaggioClient:" + nome;
	    	outVersoServer.writeBytes(personaggioScelto+'\n');
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println("Errore durante la comunicazione col server!");
    		System.exit(1);
    	}
    }
    
    public void indovina(String nome, InterfacciaClient finestra) {
    	try
    	{   		
    		nome="tentativo:" + nome;
    		System.out.println("Invio il nome al server e attendo ...");
    		outVersoServer.writeBytes(nome+'\n');
    		
    		stringaRicevutaDalServer = inDalServer.readLine();
    		//System.out.println(stringaRicevutaDalServer);
    		JOptionPane.showMessageDialog(null, stringaRicevutaDalServer);
    		if(stringaRicevutaDalServer.equals("Il server non ha ancora scelto un personaggio"))
    			return;
    		System.out.println("CLIENT: termina elaborazione e chiude connessione");
    		mioSocket.close();
    		finestra.dispose();
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
    	try
    	{   		
    		String domanda="domanda:" + numeroDomanda;
    		System.out.println("Invio la domanda al server e attendo ...");
    		outVersoServer.writeBytes(domanda +'\n');
    		
    		stringaRicevutaDalServer = inDalServer.readLine();
    		JOptionPane.showMessageDialog(null, stringaRicevutaDalServer);
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println("Errore durante la comunicazione col server!");
    		System.exit(1);
    	}
    }
}

