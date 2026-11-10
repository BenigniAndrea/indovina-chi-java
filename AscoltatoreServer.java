import javax.swing.*;
import java.awt.event.*;
public class AscoltatoreServer implements ActionListener  //Ascoltatore dell'interfaccia server
{
	private InterfacciaServer finestra;
	private ServerStr server;
	private int cifraControllo=0;   //Per controllare se il client ha già scelto un personaggio, per non farglielo cambiare
	public AscoltatoreServer(InterfacciaServer f, ServerStr s)
	{
		finestra=f;
		server=s;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		//Se schiaccia sul bottone indovina
		if(source==finestra.bottoneIndovina)
			if(finestra.personaggioSelezionatoLabel2.getText().equals(""))
				  JOptionPane.showMessageDialog(null, "Seleziona prima il tuo personaggio");
			else
				if (finestra.personaggioSceltoIndex == -1) {
	                JOptionPane.showMessageDialog(null, "Seleziona un personaggio da indovinare cliccando sull'immagine.");
	            } else {
		            String[] nomiPersonaggi = {
		                    "Mario Rossi", "Giovanni Bianchi", "Luigi Verdi", "Francesca Gallo", "Alessandro Neri",
		                    "Giulia Ferrari", "Marco Russo", "Anna Moretti", "Luca Sarti", "Elena Martino",
		                    "Paolo Ricci", "Simona Conte", "Fabio De Luca", "Valentina Perri", "Stefano Costa",
		                    "Claudio Mancini", "Roberto Romano", "Federico Dini"
		                }; 
		            String nomeSelezionato = nomiPersonaggi[finestra.personaggioSceltoIndex];
		            server.indovina(nomeSelezionato);
	            }
		
		if(source==finestra.bottoneDomanda)
		{
		  if(finestra.personaggioSelezionatoLabel2.getText().equals(""))
			  JOptionPane.showMessageDialog(null, "Seleziona prima il tuo personaggio");
		  else
			  if(finestra.domandeComboBox.getItemCount() == 0)
				  JOptionPane.showMessageDialog(null, "Hai esaurito le domande, devi indovinare");
			  else
			  {
				  server.faiDomanda(Character.getNumericValue(finestra.domandeComboBox.getSelectedItem().toString().charAt(0)) - 1);
			      finestra.domandeComboBox.removeItemAt(finestra.domandeComboBox.getSelectedIndex());
			      finestra.revalidate();
			      finestra.repaint();
			  } 
		}
		
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
		            server.scegliPersonaggioDaIndovinare(nomeSelezionato);
		            cifraControllo=1;
	            }
			}
		}
	}
}

