import java.io.*;
/**
 * Classe permettant de passer d'un fichier texte (résultat de pdf2texte.java) à un tablea excel.
 * @author Antoine B.
 *
 */

public class texte2excel{
	
	/**
	 * Texte de chaque pied de page.
	 * Permet de supprimer les textes récurrent qui pollueraient le tableau si on les conservaient.
	 * Les autres textes tels l'année à l'enseeiht, la filière ne sont pas supprimée car plus difficile 
	 * à repérer avec certitudes.
	 * De plus "ème" sert pour la détection de nouvelle ligne.
	 */
	private static String ANNEE = "Stages 2013-2014";
	private static String SERVICE = "Service des stages";
	private static String DATE = "02/10/2014";

	
	/**
	 * Méthode de traitement.
	 * @param args
	 */
	public static void main(String[] args){
		
		String fichier ="src/export.txt";// l'adresse d'import du fichier texte (résultat de pdf2texte)

		int i =0 ;//compteur de ligne, un peu factice, pas très utile.
		
		String ligne; //variable pour la lecture de ligne.
		
		//lecture du fichier texte	
		try{
			//aucune raison d'echouer, mais ca ne coute rien de le mettre dans le try.
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			//sortie vers le csv.
			PrintStream l_out = new PrintStream(new FileOutputStream("src/resultat.csv")); 
			
			//parcours ligne après ligne du fichier texte (export.txt)
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);// pour avoir un suivi dans la console.
				
				//verification que l'on est pas dans l'une des lignes du pied de page facilement suppressible.
				if (!ligne.contains(ANNEE) && !ligne.contains(SERVICE) && !ligne.contains(DATE) ) {
					
					/* alors on peut la mettre dans le csv.
					* les champs étant séparés par une ligne contenant uniquement " ",
					* on peut jouer sur ce critère pour le formatage en colonnes */
					
					if(ligne.equals(" ") ){ // " " => nouveau paragrpahe => nouvelle colonne. ;
						l_out.print(";"); //marqueur de colonnes en csv.
					}
					else if(ligne.equals("ème") ) { //nouvelle ligne en utilisant la particularité de la ligne "ème"
						l_out.println(Integer.toString(i)+";" );//println, nouvelle ligne.
					}
					else{ // cas le plus frequent, on continue d'écrire dans la même colonne.
						l_out.print(ligne);
					}
					
				}
								
			}
			// fin de la boucle de parcours
			br.close(); //fermetture du fichier.
			
			//cloture du csv
			//on ferme le fichier : 
			l_out.flush(); 
			l_out.close(); 
			l_out=null; 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	
			
	}
}