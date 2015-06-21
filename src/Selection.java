import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class Selection extends JPanel {
	
	private static final long serialVersionUID = 4503239381904015738L;
	protected JLabel titreChoixCouleur;
	protected JComboBox<String> selecteurEncodage;
	
	
	public Selection() {
		super(new GridBagLayout());
		GridBagConstraints contraintesPlacement = new GridBagConstraints(); 
		titreChoixCouleur = new JLabel("Choix de l'encodage :");
		selecteurEncodage = new JComboBox<String>();
		selecteurEncodage.addItem("ISO-8859-1");
		selecteurEncodage.addItem("UTF8");
		
		contraintesPlacement.gridy = 0;
		contraintesPlacement.insets = new Insets(0,0,5,0);
		contraintesPlacement.gridwidth = GridBagConstraints.REMAINDER;
		this.add(titreChoixCouleur,contraintesPlacement);
		
		contraintesPlacement.gridy = 1;
		contraintesPlacement.insets = new Insets(5,0,0,0);
		contraintesPlacement.gridwidth = GridBagConstraints.REMAINDER;
		this.add(selecteurEncodage,contraintesPlacement);
	}
	
	public String chaineVersEncodage() {
		String encodage;
		String chaine = (String) this.selecteurEncodage.getSelectedItem();
		switch (chaine) {
		
		case "ISO-8859-1" :
			encodage="ISO-8859-1";
			break;
			
		case "UTF8" :
			encodage="UTF8";
			break;
			
		default :
			encodage="ISO-8859-1";
			break;
		}
		return encodage;
	}
}
