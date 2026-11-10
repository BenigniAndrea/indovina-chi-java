import java.util.Scanner;
public class DemoGiocatore {

	public static void main(String[] args) {
		String risposta = "";
		Scanner tastiera = new Scanner(System.in);

		do {
		    Giocatore cliente = new Giocatore();
		    
		    InterfacciaClient gui = new InterfacciaClient(cliente);
		    gui.setLocationRelativeTo(null);
		    gui.setVisible(true);

		    cliente.connetti();
		    boolean socketChiuso = false;

		    while (true) {
		        if (cliente.mioSocket.isClosed()) {
		            socketChiuso = true;
		            break; // Esci dal ciclo interno quando il socket è chiuso
		        }
		    }

		    if (socketChiuso) {
		        System.out.println("Vuoi rigiocare? <si/no>");
		        risposta = tastiera.nextLine();
		    }
		} while (risposta.equalsIgnoreCase("si")); // Accetta "si" indipendentemente dal caso (maiuscolo/minuscolo)
		tastiera.close();
		System.out.println("Grazie per aver giocato!");
	}
}
