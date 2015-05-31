import java.io.*;
/**
 * Classe permettant de passer d'un fichier texte (r�sultat de pdf2texte.java) � un tablea excel.
 * @author Antoine B.
 *
 */

public class texte2excel{
	
	/**
	 * Texte de chaque pied de page.
	 * Permet de supprimer les textes r�current qui pollueraient le tableau si on les conservaient.
	 * Les autres textes tels l'ann�e � l'enseeiht, la fili�re ne sont pas supprim�e car plus difficile 
	 * � rep�rer avec certitudes.
	 * De plus "�me" sert pour la d�tection de nouvelle ligne.
	 */
	private static String ANNEE = "Stages 2013-2014";
	private static String SERVICE = "Service des stages";
	private static String DATE = "02/10/2014";

	
	/**
	 * M�thode de traitement.
	 * @param args
	 */
	public static void main(String[] args){
		
		String fichier ="src/export.txt";// l'adresse d'import du fichier texte (r�sultat de pdf2texte)

		int i =0 ;//compteur de ligne, un peu factice, pas tr�s utile.
		
		String ligne; //variable pour la lecture de ligne.
		String ligneAvance; //ligne d'avance pour les tests.
		
		//lecture du fichier texte	
		try{
			//aucune raison d'echouer, mais ca ne coute rien de le mettre dans le try.
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);//buffer de lecture
			
			//buffer avec un crand 'avance la gestion de la double colonne
			InputStream ips2 = new FileInputStream(fichier); 
			InputStreamReader ipsr2 =new InputStreamReader(ips2);
			BufferedReader ba=new BufferedReader(ipsr2);//buffer de lecture
			
			//prise de l'avance.
			ligneAvance = ba.readLine();
			//sortie vers le csv.
			PrintStream l_out = new PrintStream(new FileOutputStream("src/resultat.csv")); 
			
			//parcours ligne apr�s ligne du fichier texte (export.txt)
			while ((ligne=br.readLine())!=null){
				ligneAvance = ba.readLine();
				System.out.println(ligne);// pour avoir un suivi dans la console.
				
				//verification que l'on est pas dans l'une des lignes du pied de page facilement suppressible.
				if (!ligne.contains(ANNEE) && !ligne.contains(SERVICE) && !ligne.contains(DATE) ) {
					
					/* alors on peut la mettre dans le csv.
					* les champs �tant s�par�s par une ligne contenant uniquement " ",
					* on peut jouer sur ce crit�re pour le formatage en colonnes */
					
					if(ligne.equals(" ") ){ // " " => nouveau paragrpahe => nouvelle colonne. ;
						l_out.print(";"); //marqueur de colonnes en csv.
					}
					else if(ligneAvance != null && ligneAvance.length() > 3 && ligneAvance.substring(0,3).equals("Du ") ) { //nouvelle ligne en utilisant la particularit� que la date est toujours apres le nom
						l_out.println(Integer.toString(i)+";" );//println, nouvelle ligne.
						l_out.print(ligne+";");
					}
					else{ // cas le plus frequent, on continue d'�crire dans la m�me colonne.
						l_out.print(ligne);
					}
					if(ligneAvance != null && ligneAvance.length() > 3){
						//System.out.print( ligneAvance.substring(0,3) );
					}
				}
				i++;				
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