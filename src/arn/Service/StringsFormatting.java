
package arn.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class StringsFormatting {
    //Formation du StringBuffer(pré-definie)
    public static String insertString(String originalString, String stringToBeInserted, int index) {
        // Creation d'un nouveau StringBuffer(pré-definie)
        StringBuffer newString
                = new StringBuffer(originalString);
        // Insérer les chaînes à insérer
        // utilisation de la méthode insert()
        newString.insert(index, stringToBeInserted);
        // renvoyer la chaîne modifiée
        return newString.toString();
    }
    // On va calculer le nombre de charactère
    public int occurence(String str, char car) {

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == car) {
                count++;
            }
        }
        return count;

    }
    //Anticipation des Erreurs : Control de saisie
    public HashMap<String, String> bienSaisi(String seq, String appar) {

        System.out.println("initaliser ");
        HashMap<String, String> resultat = new HashMap<String, String>();
        //il donne un résultat par défault avec une clé et une valeur
        resultat.put("resultat","OK");
        // si il y'a pas de séquence ou d'appariement on sort un message d'erreur à l'utilisateur pour pouvoir les saisir
        if (seq.length() == 0 || appar.length() == 0){
            resultat.clear();
            //messages d'erreurs
            resultat.put("resultat", "ERROR");
            resultat.put("message", "veuillez saisir la sequence et son appariement");
            return  resultat;
       }
        // Si la longueur de la séquence et de son appariement sont différents
        if (seq.length() != appar.length()){
            resultat.clear();//effacer ok du resultat
            resultat.put("resultat", "ERROR");
            resultat.put("message", "la sequence et l'appariement doivent etre de meme longeur");
            return  resultat;
       } else{
            //si le test sur la séquence est faux
            if ( seqTest(seq) == false){
                resultat.clear();
                resultat.put("resultat", "ERROR");
                resultat.put("message", "verifier votre sequence, elle doit contenir uniquement ces lettres (A,C,G,U)");
                return  resultat;
            }
            // si le test du parenthésage est faux
            if ( apparTest(appar) == false){
                resultat.clear();
                resultat.put("resultat", "ERROR");
                resultat.put("message", "verifier vos parenthésages, il doit contenir uniquement ces symboles (-,(,))");
                return  resultat;
            }

            //si le nombre de parenthèse ouvrante et le nombre de parenthèse fermante est différents
        } if (compterOccurrences(appar,')') != compterOccurrences(appar,'(')){
            resultat.clear();
            resultat.put("resultat", "ERROR");
            resultat.put("message", "Vérifier vos nombres de parenthesages : ( ) ");
            return resultat;
        }
      //initialisation de l'ArbreService
       ArbreService arbCont = new ArbreService();
        String arbre = arbCont.apparToArbreString(seq,appar);
        System.out.println(arbre);
        //si presence d'appariement de base
        if ( ! pairesTest(arbre)  ){
            resultat.clear();
            resultat.put("resultat", "ERROR");
            resultat.put("message", "Vérifier l'appariement de base ");
            return resultat;
        }

       return resultat;

    }
    // verifier que sequence contient ACGU
    public boolean seqTest(String seq) {
        //résultat par défault
       boolean  resultat = true;
       int i = 0 ;
       do {
           if ((seq.charAt(i) != 'A') && (seq.charAt(i) != 'C') && (seq.charAt(i) != 'G') &&(seq.charAt(i) != 'U') ){
               resultat = false ;
           }
            i++;
       } while (resultat == true && i < seq.length() );

        return resultat;

    }
    // verifier que le parenthésage contient uniquement -()
    public boolean apparTest(String appar) {
        //résultat par défault
        boolean  resultat = true;
        int i = 0 ;
        do {
            if ((appar.charAt(i) != '-') && (appar.charAt(i) != '(') && (appar.charAt(i) != ')')  ){
                resultat = false ;
            }
            i++;
        } while (resultat == true && i < appar.length() );

        return resultat;

    }
    // On va calculer le nombre de parenthèse ouvrante et fermante
    public static int compterOccurrences(String maChaine, char recherche)  {
        //initialisation du nombre à 0
        int nb = 0;
        for (int i=0; i < maChaine.length(); i++)
        {
            // si la chaine de charactère i est pareil à un ( ou ) (recherche fait précedemment)
            if (maChaine.charAt(i) == recherche)
                nb++;
        }
        return nb;
    }
    // Vérification des Appariements des bases
    public static boolean pairesTest(String arbre ) {
        boolean  resultat = true;
        int i = 0 ;
        do {
            if (arbre.charAt(i) == '(' ){
                if (arbre.charAt(i+1) == 'A' && arbre.charAt(i+3) != 'U'){
                    resultat = false;
                } else  if (arbre.charAt(i+1) == 'U' && arbre.charAt(i+3) != 'A'){
                    resultat = false;
                } else  if (arbre.charAt(i+1) == 'G' && arbre.charAt(i+3) != 'C'){
                    resultat = false;
                } else  if (arbre.charAt(i+1) == 'C' && arbre.charAt(i+3) != 'G'){
                    resultat = false;
                }
            }
            i++;
        } while (resultat == true && i < arbre.length() );
        return  resultat;
    }
    //Remplacement des bon parenthésages du fichier stocholm
    public String fileGetParenth(File file ) throws FileNotFoundException {
        Scanner fileIn;
        fileIn = new Scanner(file);
        //délaration vide de parenthésage
        String parenth =  "";
        while (fileIn.hasNextLine() && parenth.equals("")){
            String line = fileIn.nextLine();
            if (line.contains("#=GC SS_cons")){
                parenth = line.substring(line.lastIndexOf(" ") + 1 , line.length());
            }
        }
        parenth = parenth.replace("<","(");
        parenth =parenth.replace(">",")");
        parenth =parenth.replace(",","-");
        parenth =parenth.replace(".","-");
        parenth =parenth.replace(":","-");
        parenth =parenth.replace(";","-");
        parenth =parenth.replace("_","-");
        return  parenth;
    }
    //il va recuperer les sequence clean qui va les mettre dans la liste
    public HashMap<String, String> fileGetSeq(String line , String parenth) {
        HashMap<String, String> resultat = new HashMap<String, String>();
        StringBuilder newSeq =  new StringBuilder(line);
        StringBuilder newParenth =  new StringBuilder(parenth);
   //     System.out.printf("line  = " + line);
     //   System.out.println("parenth = "  + parenth);
        for ( int i = 0 ; i < line.length();i++){
            if (newSeq.charAt(i) == '-'){ // Quand on va trouver - on va la changer en espace " "
                newSeq.setCharAt(i,' '); // On va la changer dans la sequence
                newParenth.setCharAt(i,' '); // en plus dans le parenthesage du meme index (emplacement )
            }
        }
        String finalSeq = newSeq.toString();
        String finalParenth = newParenth.toString();
        finalSeq = finalSeq.replace(" " , ""); // remplacer les espaces avec des chaines vide "" pour pouvoir les enlever
        finalParenth = finalParenth.replace(" ", ""); // la meme chose avec le parenthésage
        resultat.put("sequence" , finalSeq); // on va mettre le resultat de la sequence de hashmap resultat en utilisant put (" clé" , "valeur" )
        resultat.put("parenth" , finalParenth); // la meme chose avec le parenthésage
        return resultat; //retourner resutat
    }
    //Neutralisation des noeuds pour enlever les numéros des noeuds dans la comparaison de l'arbre
    public String neutralizeNoeuds(String arbre){
        StringBuilder arbreSB = new StringBuilder(arbre);
        for (int i = 0 ; i < arbre.length() ; i++){
            if ( Character.isDigit(arbreSB.charAt(i))){
                arbreSB.setCharAt(i,' ');
            }
        }
        String  res = arbreSB.toString().replace(" ", "");
        return res;
    }
    //Neutralisation des séquences pour avoir un motif sans séquence dans l'arbre
    public String neutralizeSeq(String arbre){
        StringBuilder arbreSB = new StringBuilder(arbre);
        for (int i = 0 ; i < arbre.length() ; i++){
            if ( arbreSB.charAt(i) =='A' || arbreSB.charAt(i) =='U'
                || arbreSB.charAt(i) =='C' || arbreSB.charAt(i) =='G'){
                arbreSB.setCharAt(i,' ');
            }
        }
        String  res = arbreSB.toString().replace(" ", "X");
        return res;
    }
    //Création d une chaine de caractère
    public String creatStringOfChar(int nb , char car){
        String resutat = "";
        for (int i = 0 ; i <= nb ;i++){
            resutat = resutat+ car;
        }
        return  resutat;
    }
    //Transformer noeud en arbre pour que la comparaison soit arbre à arbre
    public String noeudToArbre ( String noeud){
        int n =  0 ;
        for ( int i = 0 ; i < noeud.length() ; i++){
            if ( noeud.charAt(i) == 'N' ){
                noeud = noeud.substring(0,i +1 ) + noeud.substring(i+2 , noeud.length());
                noeud =  insertString(noeud, Integer.toString(n) , i) ;
                 }
        }
        return noeud;
    }
}











