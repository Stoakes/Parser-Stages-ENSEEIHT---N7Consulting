##PARSER STAGES ENSEEIHT - N7CONSULTING

Un petit parser java pour passer des PDF fournit par le service des stages (http://intran7.enseeiht.fr/fr/scolarite/stage/recherche-de-stage.html) � un tableau excel exploitable pour du ciblage Dev' Co � N7 Consulting

#Imitialisation
- Compilez en bytecode en ayant ajout� itext/itextpdf-5.5.6.jar au buildpath de votre projet.

#Utilisation

- D�posez un fichier pdf dans le dossier /src
- Entrez son adresse relative dans  pdf2texte.java:PREFACE.
- Compilez puis executez pdf2texte.java
- Modifiez les 3 constantes au d�but texte2excel.java pour supprimer les motifs de texte r�current facilement suppressible
- Compilez puis executez texte2excel.java
- Votre tableau se trouve dans resultat.csv
- Supprimez les lignes ne vous interessant pas.

#Note

Pour des raisons de vie priv�e, je ne mets pas de fichier d'exemple pour tester le parseur. Allez vous fournir sur l'intran7 : http://intran7.enseeiht.fr/fr/scolarite/stage/recherche-de-stage.html
