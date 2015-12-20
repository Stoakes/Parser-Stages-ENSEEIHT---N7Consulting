import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public final class Vue extends JPanel {
	
	private static final long serialVersionUID = 3192114046327538274L;
	protected JTextField champCheminFichier;	//Champ de texte contenant le chemin du pdf a parser
	protected JButton btnChargerFichier;		//Bouton permettant d'aller chercher le pdf a parser par le biais d'interface
	protected JButton btnActDecodage; 			//Bouton permettant de lancer le parsing
	
	protected JTextField champMotif1;	//champ pour le motif récurrent 1 ("Stages 2013-2014")
	protected JTextField champMotif2; 	//motif récurrent 2 ("Service des stages")
	protected JTextField champMotif3;	//champ motif récurrent 3 ("02/10/2014";)
	
	protected Selection selectionEncodage;

	
	public Vue() {
		
		super(new GridBagLayout());
		JLabel titreVueDecodage = new JLabel("Extraire les données d'un fichier du service des stages :");
		JLabel	texteParametres = new JLabel("Paramètres à adapter selon les cas (cf les bas de pages) :");
		
		this.champCheminFichier = new JTextField(40);
		this.btnChargerFichier = new JButton ("Charger un fichier");
		this.btnActDecodage = new JButton ("Extraire les données");
		
		this.champMotif1 = new JTextField(40);
		this.champMotif1.setText("INFORMATIQUE");
		
		this.champMotif2 = new JTextField(40);
		this.champMotif2.setText("2A");
		
		this.champMotif3 = new JTextField(40);
		this.champMotif3.setText("inutile");
		
		this.selectionEncodage = new Selection();
		


			
		GridBagConstraints contraintesPlacement = new GridBagConstraints();
		
		//le titre
		contraintesPlacement.gridx = 0;
		contraintesPlacement.gridy = 0;
		contraintesPlacement.fill = GridBagConstraints.HORIZONTAL;
		contraintesPlacement.gridwidth = GridBagConstraints.REMAINDER;
		contraintesPlacement.gridheight = 1;
		contraintesPlacement.insets = new Insets(5, 5, 5, 5);
		contraintesPlacement.weightx = 1;
		contraintesPlacement.weighty = 5;
		contraintesPlacement.anchor = GridBagConstraints.PAGE_START;
		this.add(titreVueDecodage,contraintesPlacement);
		
		
		// le chemin de selection du fichier
		contraintesPlacement.gridx = 0;
		contraintesPlacement.gridy = 1; 
		contraintesPlacement.fill = GridBagConstraints.HORIZONTAL;
		contraintesPlacement.gridwidth = 1;
		contraintesPlacement.gridheight = 1;
		contraintesPlacement.insets = new Insets(5,5,5,5);
		contraintesPlacement.weightx = 70;
		contraintesPlacement.weighty = 15;
		this.add(champCheminFichier,contraintesPlacement);
		
		
		// le bouton de validation.
		contraintesPlacement.gridx = 1;
		contraintesPlacement.gridy = 1; 
		contraintesPlacement.fill = GridBagConstraints.HORIZONTAL;
		contraintesPlacement.gridwidth =1;
		contraintesPlacement.gridheight = 1;
		contraintesPlacement.insets = new Insets(5,5,5,5);
		contraintesPlacement.weightx = 30;
		contraintesPlacement.weighty = 15;
		contraintesPlacement.anchor = GridBagConstraints.BASELINE;
		this.add(btnChargerFichier,contraintesPlacement);

		
		//titre des parametres
		contraintesPlacement.gridx = 0;
		contraintesPlacement.gridy = 2; 
		contraintesPlacement.fill = GridBagConstraints.HORIZONTAL;
		contraintesPlacement.gridwidth = GridBagConstraints.REMAINDER;
		contraintesPlacement.gridheight = 1;
		contraintesPlacement.insets = new Insets(5,5,5,5);
		contraintesPlacement.weightx = 20;
		contraintesPlacement.weighty = 5;
		contraintesPlacement.anchor = GridBagConstraints.BASELINE;
		this.add(texteParametres, contraintesPlacement);
		
		//champ motif 1
		contraintesPlacement.gridx = 0;
		contraintesPlacement.gridy = 3; 
		contraintesPlacement.fill = GridBagConstraints.HORIZONTAL;
		contraintesPlacement.gridheight = 1;
		contraintesPlacement.gridwidth = 1;
		contraintesPlacement.insets = new Insets(5,5,5,5);
		contraintesPlacement.weightx = 20;
		contraintesPlacement.weighty = 15;
		contraintesPlacement.anchor = GridBagConstraints.BASELINE;
		this.add(champMotif1, contraintesPlacement);
		
		//champ motif 2
		contraintesPlacement.gridx = 1;
		contraintesPlacement.gridy = 3; 
		contraintesPlacement.fill = GridBagConstraints.HORIZONTAL;
		contraintesPlacement.gridheight = 1;
		contraintesPlacement.gridwidth = 1;
		contraintesPlacement.insets = new Insets(5,5,5,5);
		contraintesPlacement.weightx = 30;
		contraintesPlacement.weighty = 15;
		contraintesPlacement.anchor = GridBagConstraints.BASELINE;
		this.add(champMotif2, contraintesPlacement);
		
		
		//bouton de lancement du parsing
		contraintesPlacement.gridx = 0;
		contraintesPlacement.gridy = 4; 
		contraintesPlacement.fill = GridBagConstraints.HORIZONTAL;
		contraintesPlacement.gridwidth = 1;
		contraintesPlacement.gridheight = 1;
		contraintesPlacement.insets = new Insets(5,5,5,5);
		contraintesPlacement.weightx = 20;
		contraintesPlacement.weighty = 65;
		contraintesPlacement.anchor = GridBagConstraints.CENTER;
		this.add(selectionEncodage,contraintesPlacement);
		
		
		//bouton de lancement du parsing
		contraintesPlacement.gridx = 0;
		contraintesPlacement.gridy = 5; 
		contraintesPlacement.fill = GridBagConstraints.HORIZONTAL;
		contraintesPlacement.gridwidth = 1;
		contraintesPlacement.gridheight = 1;
		contraintesPlacement.insets = new Insets(5,5,5,5);
		contraintesPlacement.weightx = 20;
		contraintesPlacement.weighty = 65;
		contraintesPlacement.anchor = GridBagConstraints.CENTER;
		this.add(btnActDecodage,contraintesPlacement);

		
	}
	
}
