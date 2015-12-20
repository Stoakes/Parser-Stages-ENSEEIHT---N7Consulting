
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class IHM {
	
	protected JPanel conteneurVues;
	protected static String VUEACC = "PDF 2 EXCEL - N7C";
	protected JFrame fenetre;
	protected CardLayout cl;
	protected Vue vue;
	
	
	public IHM() {
		
		//Creer la fenetre et initialiser les options qui y sont reliees 
		fenetre = new JFrame("PDF 2 EXCEL - N7C");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(true);
		fenetre.setMinimumSize(new Dimension(800,400));
		fenetre.setSize(800,400);
	
		//Creer le volet
			
		//Creer les vues
		vue = new Vue();
		
		//Creer le conteneur des vues et les y ajouter
		cl = new CardLayout();
		conteneurVues = new JPanel(cl);
		conteneurVues.add(vue, VUEACC);
		cl.show(conteneurVues, VUEACC);		//Afficher la vue d'accueil

	
		Container contenuFenetre = fenetre.getContentPane();
		contenuFenetre.setLayout(new BorderLayout());
		contenuFenetre.add(conteneurVues,BorderLayout.CENTER);

		//Ajouter les listeners
		vue.btnChargerFichier.addActionListener(new ChargerFichier());
		vue.btnActDecodage.addActionListener(new ActionDecoderFichier());
		
		fenetre.setVisible(true);
	}
	
	/////////////////////////////////  Action Listeners ///////////////////////////////////////
	public class ChargerFichier implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Creer un selecteur de path base par defaut sur le repertoire courant
		    JFileChooser selecteurFichier = new JFileChooser(".");
		    FileNameExtensionFilter filtre = new FileNameExtensionFilter(
		        "PDF Files", "pdf");
		    selecteurFichier.setFileFilter(filtre);
		    //Pour ne pas charger de fichier si l'utilisateur annule son action
		    int returnVal = selecteurFichier.showOpenDialog(selecteurFichier);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       vue.champCheminFichier.setText(selecteurFichier.getSelectedFile().getPath());
		    }
		}
	}
	
	public class ActionDecoderFichier implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {		
			//Ne pas tenter de décoder si le path n'est pas rempli
			if (vue.champCheminFichier.getText().length()!=0) {
				try {
					pdf2texte pdfParser = new pdf2texte(vue.champCheminFichier.getText());
					pdfParser.toString();
					//constituion du tableau des motis récuurents a supprimer
					String[] parametres = {vue.champMotif1.getText(),vue.champMotif2.getText()};
					texte2excel texteParser = new texte2excel(vue.champCheminFichier.getText()+".txt",vue.champCheminFichier.getText()+".csv", vue.selectionEncodage.chaineVersEncodage(), parametres);
					texteParser.toString();
					//signalons que l'operation est terminée.
					vue.champCheminFichier.setText("Tableau disponible dans le dossier du fichier source.");
				}
				//Pour chaque exception, on ouvre une fenetre affichant le message d'erreur
				catch (Exception e3) {
					e3.printStackTrace();
					JFrame fenetreErreur = new JFrame("Erreur");
					fenetreErreur.setLayout(new GridBagLayout());
					GridBagConstraints contraintesPlacement = new GridBagConstraints();
					
					contraintesPlacement.insets = new Insets(20, 20, 20, 20);
					contraintesPlacement.gridwidth= GridBagConstraints.REMAINDER;
					JLabel txtErreur = new JLabel(e3.getMessage());
					JLabel detailsErreur = new JLabel(e3.toString());
					
					fenetreErreur.add(txtErreur,contraintesPlacement);
					fenetreErreur.add(detailsErreur,contraintesPlacement);
					fenetreErreur.setVisible(true);
					fenetreErreur.pack();
				}
			}
		}
	}
	
}
