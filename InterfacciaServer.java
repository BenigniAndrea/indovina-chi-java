import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacciaServer extends JFrame {
    public static final int LARGHEZZA = 1700;
    public static final int ALTEZZA = 900;

    public JPanel pannello;
    public JLabel[] labels; // Array di JLabel per le immagini
    public JLabel[] etichette; // Array di JLabel per i nomi
    public JButton scegliButton; // Pulsante per la scelta del personaggio
    public JComboBox<String> domandeComboBox; // Combo box con domande
    public JLabel personaggioSelezionatoLabel; // Etichetta per il personaggio scelto
    public JLabel fotoPersonaggioLabel; // Etichetta per la foto del personaggio
    public JLabel personaggioSelezionatoLabel2;
    public JButton bottoneIndovina;
    public JButton bottoneDomanda;

    public int personaggioSceltoIndex = -1; // Indice del personaggio scelto

    public InterfacciaServer(ServerStr server) {
        // Impostazione della finestra
        setTitle("Indovina chi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGHEZZA, ALTEZZA);
        //setResizable(false);

        // Creazione del pannello principale
        pannello = new JPanel();
        pannello.setLayout(null);
        pannello.setBackground(new Color(248, 248, 248)); // Colore di sfondo chiaro e moderno
        getContentPane().add(pannello);

        // Aggiunta del titolo
        JLabel titolo = new JLabel("Indovina Chi - Server", SwingConstants.CENTER);
        titolo.setFont(new Font("Verdana", Font.BOLD, 36));
        titolo.setBounds(0, 10, LARGHEZZA, 50);
        titolo.setForeground(new Color(26, 13, 171)); // Colore blu intenso
        pannello.add(titolo);

        // Inizializzazione degli array di immagini e nomi
        labels = new JLabel[18];
        etichette = new JLabel[18];

        // Posizioni iniziali e configurazione della griglia di immagini
        int x = 100, y = 80;
        int offsetX = 200; // Spaziatura orizzontale
        int rows = 3, cols = 6; // Numero di righe e colonne

        // Creazione dinamica di immagini e nomi associati
        for (int i = 0; i < 18; i++) {
            // Caricamento immagine
            ImageIcon imageIcon = new ImageIcon("Foto\\" + (i + 1) + ".jpg");
            // Scala l'immagine per adattarla al riquadro
            Image scaledImage = imageIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Contenitore riquadro
            JPanel riquadro = new JPanel();
            riquadro.setLayout(new BorderLayout());
            riquadro.setBounds(x + (i % cols) * offsetX, y + (i / cols) * 250, 150, 200);
            riquadro.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 5)); // Bordo blu scuro
            riquadro.setBackground(Color.WHITE);
            pannello.add(riquadro);

            // Creazione JLabel per immagine
            JLabel immagineLabel = new JLabel(scaledIcon);
            immagineLabel.setHorizontalAlignment(SwingConstants.CENTER);
            immagineLabel.setVerticalAlignment(SwingConstants.CENTER);
            riquadro.add(immagineLabel, BorderLayout.CENTER);

            // Creazione JLabel per nome
            String[] nomiPersonaggi = {
                "Mario Rossi", "Giovanni Bianchi", "Luigi Verdi", "Francesca Gallo", "Alessandro Neri",
                "Giulia Ferrari", "Marco Russo", "Anna Moretti", "Luca Sarti", "Elena Martino",
                "Paolo Ricci", "Simona Conte", "Fabio De Luca", "Valentino Perri", "Stefano Costa",
                "Claudio Mancini", "Roberto Romano", "Federico Dini"
            };
            JLabel nomeLabel = new JLabel(nomiPersonaggi[i], SwingConstants.CENTER);
            nomeLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            nomeLabel.setForeground(new Color(26, 13, 171)); // Colore blu intenso
            riquadro.add(nomeLabel, BorderLayout.SOUTH);

            // Effetto hover
            final int index = i;
            riquadro.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    riquadro.setBorder(BorderFactory.createLineBorder(new Color(255, 69, 0), 5)); // Arancione
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    riquadro.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 5)); // Blu scuro
                }

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    personaggioSceltoIndex = index;
                }
            });
        }
        
        bottoneIndovina = new JButton("Prova ad indovinare");
        bottoneIndovina.setBounds(LARGHEZZA - 340, 360, 200, 40);
        bottoneIndovina.setFont(new Font("Arial", Font.BOLD, 16));
        bottoneIndovina.setBackground(new Color(0, 153, 204)); // Blu
        bottoneIndovina.setForeground(Color.WHITE);
        bottoneIndovina.addActionListener(new AscoltatoreServer(this, server));
        pannello.add(bottoneIndovina);

        // Configurazione pulsante scelta personaggio
        scegliButton = new JButton("Scegli Personaggio");
        scegliButton.setBounds(LARGHEZZA - 340, 100, 200, 40);
        scegliButton.setFont(new Font("Arial", Font.BOLD, 16));
        scegliButton.setBackground(new Color(0, 153, 204)); // Blu
        scegliButton.setForeground(Color.WHITE);
        scegliButton.addActionListener(new AscoltatoreServer(this, server));
        pannello.add(scegliButton);
        
        bottoneDomanda = new JButton("Fai domanda");
        bottoneDomanda.setBounds(LARGHEZZA - 340, 750, 200, 40);
        bottoneDomanda.setFont(new Font("Arial", Font.BOLD, 16));
        bottoneDomanda.setBackground(new Color(0, 153, 204)); // Blu
        bottoneDomanda.setForeground(Color.WHITE);
        bottoneDomanda.addActionListener(new AscoltatoreServer(this, server));
        pannello.add(bottoneDomanda);

        // Aggiunta domande
        String[] domande = {
            "1 Il tuo personaggio porta gli occhiali?",
            "2 Il tuo personaggio ha i capelli lunghi?",
            "3 Il tuo personaggio è maschio?",
            "4 Il tuo personaggio ha i baffi?",
            "5 Il tuo personaggio indossa un cappello?",
            "6 Il tuo personaggio è biondo?",
            "7 Il tuo personaggio ha i capelli arancioni?"
        };
        domandeComboBox = new JComboBox<>(domande);
        domandeComboBox.setBounds(LARGHEZZA - 390, 550, 300, 40);
        domandeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        pannello.add(domandeComboBox);

        JLabel domandeTitle = new JLabel("Domande per indovinare", SwingConstants.CENTER);
        domandeTitle.setFont(new Font("Verdana", Font.BOLD, 18));
        domandeTitle.setForeground(new Color(26, 13, 171));
        domandeTitle.setBounds(LARGHEZZA - 390, 500, 300, 40);
        pannello.add(domandeTitle);
        
        // Classe per creare il componente della linea orizzontale
        class LineaOrizzontale extends JComponent {
            
            // Override del metodo paintComponent per disegnare la linea
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Cast dell'oggetto Graphics in Graphics2D per un controllo maggiore
                Graphics2D g2d = (Graphics2D) g;
                
                // Impostiamo il colore della linea (ad esempio, blu)
                g2d.setColor(Color.BLUE);
                
                // Disegniamo una linea orizzontale tra (x1, y1) e (x2, y1)
                g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); // Linea orizzontale al centro del componente
            }
        }
        
        // Etichette per visualizzazione personaggio selezionato
        personaggioSelezionatoLabel = new JLabel("Personaggio Selezionato: ", SwingConstants.RIGHT);
        personaggioSelezionatoLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        personaggioSelezionatoLabel.setForeground(new Color(26, 13, 171));
        personaggioSelezionatoLabel.setBounds(LARGHEZZA - 600, 180, 500, 40);
        pannello.add(personaggioSelezionatoLabel);
        personaggioSelezionatoLabel2 = new JLabel("", SwingConstants.RIGHT);
        personaggioSelezionatoLabel2.setFont(new Font("Verdana", Font.BOLD, 20));
        personaggioSelezionatoLabel2.setForeground(new Color(26, 13, 171));
        personaggioSelezionatoLabel2.setBounds(LARGHEZZA - 670, 235, 500, 40);
        pannello.add(personaggioSelezionatoLabel2);
        // Creiamo il componente LineaOrizzontale
        LineaOrizzontale linea = new LineaOrizzontale();
        linea.setBounds(LARGHEZZA - 400, personaggioSelezionatoLabel.getY() + personaggioSelezionatoLabel.getHeight() + 50, 300, 5); // Posiziona la linea orizzontale sopra l'etichetta
        pannello.add(linea); // Aggiungiamo la linea al pannello

        fotoPersonaggioLabel = new JLabel();
        fotoPersonaggioLabel.setBounds(LARGHEZZA - 500, 90, 100, 150);
        pannello.add(fotoPersonaggioLabel);

        setVisible(true);
    }

    // Metodo per mostrare il personaggio selezionato
    public void mostraPersonaggioSelezionato(int index) {
        String[] nomiPersonaggi = {
            "Mario Rossi", "Giovanni Bianchi", "Luigi Verdi", "Francesca Gallo", "Alessandro Neri",
            "Giulia Ferrari", "Marco Russo", "Anna Moretti", "Luca Sarti", "Elena Martino",
            "Paolo Ricci", "Simona Conte", "Fabio De Luca", "Valentino Perri", "Stefano Costa",
            "Claudio Mancini", "Roberto Romano", "Federico Dini"
        };

        personaggioSelezionatoLabel2.setText(nomiPersonaggi[index]);
        ImageIcon selectedImage = new ImageIcon("Foto" + (index + 1) + ".jpg");
        Image scaledImage = selectedImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        fotoPersonaggioLabel.setIcon(new ImageIcon(scaledImage));
    }
}
