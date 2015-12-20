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
	//private static String ANNEE = "Stages 2013-2014";
	//private static String SERVICE = "Service des stages";
	//private static String DATE = "02/10/2014";
	
	/**
	 * Méthode de traitement.
	 * @param args
	 */
	public texte2excel(String pathFichierImport,String pathFichierExport,String encodage, String[] parametres){

		int i =0 ;//compteur de ligne, un peu factice, pas très utile.
		
		String ligne; //variable pour la lecture de ligne.
		String ligneAvance; //ligne d'avance pour les tests.
		
		//lecture du fichier texte	
		try{
			//aucune raison d'echouer, mais ca ne coute rien de le mettre dans le try.
			InputStream ips=new FileInputStream(pathFichierImport); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);//buffer de lecture
			
			//buffer avec un cran d 'avance la gestion de la double colonne
			InputStream ips2 = new FileInputStream(pathFichierImport); 
			InputStreamReader ipsr2 =new InputStreamReader(ips2);
			BufferedReader ba=new BufferedReader(ipsr2);//buffer de lecture
			
			//prise de l'avance.
			ligneAvance = ba.readLine();

			FileOutputStream dest = new FileOutputStream(pathFichierExport);
		       Writer output = new BufferedWriter(new OutputStreamWriter(dest, encodage));
			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)		
			
			//parcours ligne après ligne du fichier texte (export.txt)
			while ((ligne=br.readLine())!=null){
				ligneAvance = ba.readLine();//lecture de la ligne en cours.
				System.out.println(ligne);// pour avoir un suivi dans la console.
				
				//verification que l'on est pas dans l'une des lignes du pied de page facilement suppressible.
				if (!ligne.contains(parametres[0]) && !ligne.contains(parametres[1]) /**&& !ligne.contains(parametres[2]) **/ ) {
					
					/* alors on peut la mettre dans le csv.
					* les champs étant séparés par une ligne contenant uniquement " ",
					* on peut jouer sur ce critère pour le formatage en colonnes */
					
					//si la ligne vaut " ", que la ligne suivante ne vaut pas " " (cas d'un changement d'élèves bien formaté) et que l'on ne rempli pas une condition de passage a la ligne mal formaté :  passage a une nouvelle colonne
					if(ligne.equals(" ") && !ligneAvance.equals(" ") && !(ligneAvance.contains(parametres[0]) || ligneAvance.contains(parametres[1])) ){ 
						output.write(";"); //marqueur de colonnes en csv.
					}
					else if((ligne.equals(" ") && ligneAvance.equals(" ")) || 
							(ligne.equals(" ") && ligneAvance.contains(parametres[0]) || ligneAvance.contains(parametres[1])) ) { //nouvelle ligne dans le cas standard (2 lignes " ") ou dans le cas malformé ( " " puis "INFORMATIQUE 2A")
						
						output.write("\n" );//println, nouvelle ligne.
					}
					else{ // cas le plus frequent, on continue d'écrire dans la même colonne.
						output.write(ligne);
					}
				}
				i++;				
			}
			// fin de la boucle de parcours
			br.close(); //fermetture du fichier.
			ba.close();
			//cloture du csv
			//on ferme le fichier : 
			output.flush(); 
			output.close(); 
			output=null; 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	
			
	}
}