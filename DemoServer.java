import java.util.Scanner;
public class DemoServer {

	public static void main(String[] args) {
		String risposta="";
		Scanner tastiera = new Scanner(System.in);
		do
		{
			ServerStr servente = new ServerStr();
			
			InterfacciaServer gui = new InterfacciaServer(servente);
			gui.setLocationRelativeTo(null);
			gui.setVisible(true);
			
			servente.attendi();
			servente.comunica();
			gui.dispose();
			System.out.println("Vuoi rigiocare? <si/no>");
			risposta=tastiera.nextLine();
		}while(risposta.equals("si"));
		tastiera.close();
		System.out.println("Grazie per aver giocato!");
	}
}
