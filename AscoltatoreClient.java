import javax.swing.*;
import java.awt.event.*;
public class AscoltatoreClient implements ActionListener  //Ascoltatore dell'interfaccia client
{
	private InterfacciaClient finestra;
	private Giocatore giocatore;
	private int cifraControllo=0;   //Per controllare se il client ha già scelto un personaggio, per non farglielo cambiare
	public AscoltatoreClient(InterfacciaClient f, Giocatore g)
	{
		finestra=f;
		giocatore=g;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		//Se schiaccia sul bottone indovina
		if(source==finestra.bottoneIndovina)
			if(finestra.personaggioSelezionatoLabel2.getText().equals(""))  //Se non ha ancora scelto un personaggio
				  JOptionPane.showMessageDialog(null, "Seleziona prima il tuo personaggio");
			else
				if (finestra.personaggioSceltoIndex == -1) //Se non ha selezionato un personaggio
				{
	                JOptionPane.showMessageDialog(null, "Seleziona un personaggio da indovinare cliccando sull'immagine.");
	            } else {
		            String[] nomiPersonaggi = {
		                    "Mario Rossi", "Giovanni Bianchi", "Luigi Verdi", "Francesca Gallo", "Alessandro Neri",
		                    "Giulia Ferrari", "Marco Russo", "Anna Moretti", "Luca Sarti", "Elena Martino",
		                    "Paolo Ricci", "Simona Conte", "Fabio De Luca", "Valentina Perri", "Stefano Costa",
		                    "Claudio Mancini", "Roberto Romano", "Federico Dini"
		                }; 
		            String nomeSelezionato = nomiPersonaggi[finestra.personaggioSceltoIndex];
		            giocatore.indovina(nomeSelezionato, finestra);
	            }
		
		//Se schiaccia sul bottone "Fai domanda"
		if(source==finestra.bottoneDomanda)
		{
		  if(finestra.personaggioSelezionatoLabel2.getText().equals(""))
			  JOptionPane.showMessageDialog(null, "Seleziona prima il tuo personaggio");
		  else
			  if(finestra.domandeComboBox.getItemCount() == 0)  //Se la combobox è vuota
				  JOptionPane.showMessageDialog(null, "Hai esaurito le domande, devi indovinare");
			  else
			  {
				  giocatore.faiDomanda(Character.getNumericValue(finestra.domandeComboBox.getSelectedItem().toString().charAt(0)) - 1);  //Gli passo l'indice della domanda fatta
			      finestra.domandeComboBox.removeItemAt(finestra.domandeComboBox.getSelectedIndex());
			      //Modifico la finestra
			      finestra.revalidate();
			      finestra.repaint();
			  } 		
		}
		//Se schiaccia su "Scegli personaggio"
		if(source==finestra.scegliButton)
		{
			if(cifraControllo==1)
			   JOptionPane.showMessageDialog(null, "Personaggio già selezionato");
			else
			{
				if (finestra.personaggioSceltoIndex == -1) {
	                JOptionPane.showMessageDialog(null, "Seleziona un personaggio cliccando sull'immagine.");
	            } else {
	                finestra.mostraPersonaggioSelezionato(finestra.personaggioSceltoIndex);
	                String[] nomiPersonaggi = {
		                    "Mario Rossi", "Giovanni Bianchi", "Luigi Verdi", "Francesca Gallo", "Alessandro Neri",
		                    "Giulia Ferrari", "Marco Russo", "Anna Moretti", "Luca Sarti", "Elena Martino",
		                    "Paolo Ricci", "Simona Conte", "Fabio De Luca", "Valentina Perri", "Stefano Costa",
		                    "Claudio Mancini", "Roberto Romano", "Federico Dini"
		                };
		                
		            String nomeSelezionato = nomiPersonaggi[finestra.personaggioSceltoIndex];
		            giocatore.scegliPersonaggioDaIndovinare(nomeSelezionato);
		            cifraControllo=1;
	            }
			}
		}
	}
}
