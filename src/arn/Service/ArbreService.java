
package arn.Service;

import arn.Entitie.Arbre;
import arn.Entitie.Noeud;
import java.util.ArrayList;
import java.util.HashMap;

public class ArbreService {
    public String apparToArbreString(String sequence, String appar) {
        StringsFormatting formatting = new StringsFormatting();
        System.out.println("arbre à creer pour : ");
        System.out.println("sequence = " + sequence);
        System.out.println("appariement = " + appar);
        int cursorNode = 0;
        System.out.println("Initialisation cursorNode = " + cursorNode );
        int numNode = 0;
        System.out.println("Initialisation numNode = " + numNode );
        String arbre = "N" + String.valueOf(numNode) + "[]";
        System.out.println("Initialisation arbre = " + arbre );
        for (int i = 0; i < appar.length(); i++) {
            System.out.println(" ******************    i = " + i + "  appar.length() =  " + appar.length()  + "    ******************");
            if (appar.charAt(i) == '-') {
                System.out.println("appar.charAt(i) =  " + appar.charAt(i));
                System.out.println("arbre avant traitement =  " + arbre);
                int pos = arbre.length() - 2 - cursorNode;
                System.out.println("sous chaine à ajouter = " +  sequence.charAt(i) + ",");
                System.out.println("@ position = " + pos);
                arbre = StringsFormatting.insertString(arbre, sequence.charAt(i) + ",", arbre.length() - 1 - cursorNode);
                System.out.println("nouvelle arbre ");
                System.out.println(arbre);
                System.out.println("Cursornode =  " + cursorNode  );
                System.out.println("numNode  =  "  + numNode);
            } else if (appar.charAt(i) == '(') {
                System.out.println("appar.charAt(i) =  " + appar.charAt(i));
                System.out.println("arbre avant traitement =  " + arbre);
                int pos = arbre.length() - 2 - cursorNode;
                System.out.println("sous chaine à ajouter = " + "N" + String.valueOf(numNode + 1) +  "[(" + sequence.charAt(i) + "," + "x" + "),]" );
                System.out.println("@ position = " + pos);
                arbre = StringsFormatting.insertString(arbre,
                        "N" + String.valueOf(numNode +1 ) + "[(" + sequence.charAt(i) + "," + "x" + "),]",
                        arbre.length() - 1 - cursorNode);
                System.out.println("nouvelle arbre ");
                System.out.println(arbre);
                cursorNode++;
                numNode++;
                System.out.println("Cursornode =  " + cursorNode  );
                System.out.println("numNode  =  "  + numNode);

            } else if (appar.charAt(i) == ')') {

                System.out.println("appar.charAt(i) =  " + appar.charAt(i));
                System.out.println("Position de dernier x =  " + arbre.lastIndexOf("x") ) ;
                int pos = arbre.lastIndexOf("x");
                arbre = arbre.substring(0, pos) + "" + arbre.substring(pos + 1);
                System.out.println("arbre avec le denier x effacé ");
                System.out.println(arbre);
                arbre = StringsFormatting.insertString(arbre, String.valueOf(sequence.charAt(i)), pos );
                System.out.println("nouvelle arbre ");
                System.out.println(arbre);
                cursorNode--;
                System.out.println("Cursornode =  " + cursorNode  );
                System.out.println("numNode  =  "  + numNode);

            }
        }
        //AUCGAUCGAUCGACGUCGC GAUCGGCAAUC
        //--(((-((-----)))))- (-(----)--)-
        //N0[A,N1[(B,E),C,D,]F,]
        return arbre;
    }
    public String apparToArbreString2(String sequence, String appar) {
        StringsFormatting formatting = new StringsFormatting();
        int cursorNode = 0;
        int numNode = 0;
        //Initialisation de l'arbre : N0[]
        String arbre = "N" + String.valueOf(numNode) + "[]";
        //Elle nous permet la génération de l'arbre à partir du parenthésage
        for (int i = 0; i < appar.length(); i++) {
            //Si le charactère @ index i est - (feuille)
            if (appar.charAt(i) == '-') {
                //inserer le characère correspandant ( sequence.charAt(i) ) dans l'arbre
                                 //à la position  ( arbre.length() - 1 - cursorNode )
                arbre = StringsFormatting.insertString(arbre, sequence.charAt(i) + ",",
                        arbre.length() - 1 - cursorNode);
            } else if (appar.charAt(i) == '(') {
                // on incremente cursorNode ( appar.charAt(i) = '(' qui correspond à un debut de noeud )
                cursorNode++;
                // on incremente numNode ( appar.charAt(i) = '(' qui correspond à un nouveau noeud )
                numNode++;
                //inserer le characère correspandant ( N?[(?,?)] ) dans l'arbre
                              // à la position ( arbre.length() - 1 - cursorNode )
                arbre = StringsFormatting.insertString(arbre,
                        "N" + String.valueOf(numNode ) + "[(" + sequence.charAt(i) + "," + "x" + "),]",
                        arbre.length() - 1 - cursorNode);
            } else if (appar.charAt(i) == ')') {
                //  on decremente cursorNode ( appar.charAt(i) = '(' qui correspond à un fin de noeud )
                cursorNode--;
                // recuperer la position du dernier x qui correspond à la dernière paire non-Fermé
                int pos = arbre.lastIndexOf("x");
                // effacer x
                arbre = arbre.substring(0, pos) + "" + arbre.substring(pos + 1);
                //inserer le characère correspandant ( sequence.charAt(i)) dans l'arbre à la position de x
                arbre = StringsFormatting.insertString(arbre, String.valueOf(sequence.charAt(i)), pos);
                //String.valueOf(?) : pour changer le type de 'char' à 'string'
            }
        }
        return arbre;
    }
    //Conversion de l'arbre vers le parenthéage et la séquence
    public ArrayList<String> arbreToAppar(String arbre) {
        //Initialiser resulat
        ArrayList<String> resultat = new ArrayList<String>();
        //Initialiser paire (pour stocker les fermetures des paires jusqu'à la fin du noeud)
        String paire = " ";
        //Initialiser sequence/apapriement
        String sequence = "";
        String appar = "";
        //parcourir toute la longeur  de  l'arbre
        for (int i = 0; i < arbre.length(); i++) {
            // Si le char à la position i est une lettre suivant d'une virgule :
                            // soit une feuille ou la première partie d'un paire
            if (Character.isLetter(arbre.charAt(i)) && arbre.substring(i + 1, i + +2).equals(",")) {
                //Si ce caractere est precedé par ( :  c'est un debut de noeud
                if (arbre.substring(i - 1, i).equals("(")) {
                    // mettre à jour la sequence et le parenthesage
                    sequence = sequence + arbre.substring(i, i + 1);
                    appar = appar + "(";
                } else { //Si ce caractere est precedé par- :  c'est une feuille
                    // mettre à jour la sequence et le parenthesage
                    sequence = sequence + arbre.substring(i, i + 1);
                    appar = appar + "-";
                }
                // Si le char à la position i est une lettre suivant d'une )  : la deuxieme partie d'un paire
            } else if (Character.isLetter(arbre.charAt(i)) && arbre.substring(i + 1, i + +2).equals(")")) {
                // On stock ce char à la liste des fermeture des noeuds pour la recupérer à la fin du noeud
                paire = paire + arbre.substring(i, i + 1);
                // Si le char à la position i est une ]  : fin du noeud
            } else if (arbre.substring(i, i + 1).equals("]")) {
                // recuperer le dernier nucleotide stocké
                // mettre à jour la sequence / parenthesage
                sequence = sequence + paire.substring(paire.length() - 1, paire.length());
                appar = appar + ")";
                // supprimer le nucléotide de la liste
                paire = paire.substring(0, paire.length() - 1);
            }
        }
        appar = appar.substring(0, appar.length() - 1);
        // ajouter la sequence et parenthesage à la liste de resultat
        resultat.add(sequence.substring(0,sequence.length()-1));
        resultat.add(appar);
        return resultat;

    }
    //Extraction des noeuds
    public ArrayList<String> extractNodes(String arbre) {
        arbre = "  " + arbre + "  ";
        StringsFormatting formatting = new StringsFormatting();
        //Instancation de l arraylist pour chaque liste contient différent noeud ensemble
        ArrayList<String> resultat = new ArrayList<String>();

        int i = 0;
        //Déclaration des noeuds vide
        String noeud = "";

        // verifier si Ni existe , de N0 jusqu'à la fin des noeuds
        while (arbre.indexOf("N" + Integer.toString(i)) > 0) {
            //A ce point on a trouvé Ni (N0 ou N1 ou N2 ..), donc le compteur des noeud inistalisé à 1
            int DebutNoeud = 1;
            //Compteur pour détécter les ] = fin des noeuds
            int FinNoeud = 0;
            //j prend l'index (position ) de N1, et ca sera le debut du noeud
            int j = arbre.indexOf("N" + Integer.toString(i));
            j++; // j incrimenté tant que le compteur des noeuds ouverts est > au compteur des noeuds fermés
            while (DebutNoeud != FinNoeud) {
                j++;
                //Si le caractère à l'index j est N donc on incremente le compteur DebutNoeud
                if (arbre.substring(j, j + 1).equals("N")) {
                    DebutNoeud++;
                //Si le caractère à l'index j est  ] donc on incremente le compteur FinNoeud
                } else if (arbre.substring(j, j + 1).equals("]")) {
                    FinNoeud++;
                }
            }
            // chaque noeud sera une sous chaine de l'arbre ayant comme debut la position du Ni
            // et comme fin : j+1 (psk j sera incrementé tant que les noeuds ouverts > noeuds fermés
            noeud = arbre.substring(arbre.indexOf("N" + Integer.toString(i)), j + 1);
            // on stock les resutltat dans une liste
            resultat.add(noeud);
            //incrimenter i pour verifier l'existencedu Ni
            i++;
        };
        return resultat;
    }

    public Arbre apparToArbre(String sequence, String appar) {
        String arbre = apparToArbreString(sequence, appar);
        arbre = "  " +arbre + "  ";
        //Instanciation
        ArbreService arbCont = new ArbreService();
        StringsFormatting formatting = new StringsFormatting();
        ArrayList<String> listNoeudsString = arbCont.extractNodes(arbre);
        ArrayList<Noeud> listNoeuds = new ArrayList<Noeud>();

        for (int i = 0; i < listNoeudsString.size(); i++) {
            int nbNoeuds = formatting.occurence(listNoeudsString.get(i), "N".charAt(0));
            int nbFeuille = formatting.occurence(listNoeudsString.get(i), ",".charAt(0)) - nbNoeuds * 2;
            Noeud n = new Noeud(listNoeudsString.get(i), nbNoeuds, nbFeuille);
            listNoeuds.add(n);
        }
        int nbNoeudsTot = formatting.occurence(appar, "(".charAt(0));
        int nbFeuilleTot = formatting.occurence(appar, "-".charAt(0));
        Arbre ar = new Arbre(arbre, nbNoeudsTot, nbFeuilleTot, listNoeuds);
        return ar;
    }

    public HashMap<String, String> compArbres(String arbre1 , String arbre2){
        StringsFormatting formatting = new StringsFormatting();
        HashMap<String, String> resultat = new HashMap<String, String>();
        if (arbre1.charAt(1) == '0'){
            arbre1 = arbre1.substring(arbre1.indexOf('[') +1 , arbre1.length()-1);
        }
        if (arbre2.charAt(1) == '0'){
            arbre2 = arbre2.substring(arbre2.indexOf('[') +1 , arbre2.length()-1);
        }
        String arbre1AppSeq = formatting.neutralizeNoeuds(arbre1);
        String arbre2AppSeq = formatting.neutralizeNoeuds(arbre2);
        String arbre1App = formatting.neutralizeSeq(arbre1AppSeq);
        String arbre2App = formatting.neutralizeSeq(arbre2AppSeq);

        if (arbre1AppSeq.contains(arbre2AppSeq)){
            resultat.put("motif avec seq" , " strcuture 2 est un motif de structure 1");
        } else if (arbre2AppSeq.contains(arbre1AppSeq)){
            resultat.put("motif avec seq" , " strcuture 1 est un motif de structure 2");
        } else {
            resultat.put("motif avec seq" , " aucune de ces structures est motif dans l'autre");
        }
        if (arbre1App.contains(arbre2App)){
            resultat.put("motif sans seq" , " strcuture 2 est un motif de structure 1");
        } else if (arbre2App.contains(arbre1App)){
            resultat.put("motif sans seq" , " strcuture 1 est un motif de structure 2");
        } else {
            resultat.put("motif sans seq" , " aucune de ces structures est motif dans l'autre");
        }
        return resultat;

    }
    //Affichage Arbre
    public ArrayList<String> affArbre(String arbre){
        // initialiser  la liste qui sera le resultat du notre fonction : chaque case de la liste est un niveau d'arborressence
        ArrayList<String> affichage = new ArrayList<>();
        // ajouter le premier  noeud N0 à la première case
        affichage.add("    N0");
        // Initialiser le curseur qui va tracker le niveau d'arboressence à gérer ,
        int niveau = 1;
        // Initialiser curseur qui va tracker chaque changement de niveau : noeud vers sous noeud / noeud vers noeud mere
        boolean sameLevel = false;
        // parcourir l'arbre a partir l index 3 : N0[
        int i = 3;
        while ( i < arbre.length()){
            //verifier si le caractere à index i est AUCG (feuille)
            if ( arbre.charAt(i) == 'A' ||   arbre.charAt(i) == 'U'
                    ||   arbre.charAt(i) == 'C'   ||   arbre.charAt(i) == 'G'  ){
                // verifier si  le niveau de saisi est superieur à taille de la list d'affichage
                if ( affichage.size() <= niveau){
                    // on crée la nouvelle case vide
                    affichage.add("\0");
                }
                // verifier si le niveau d'arboressence a changé
                if (sameLevel == false){
                    // recupere le nombre des espace necessaire avant la nouvelle saisi : pour eviter l'intersection des noeuds
                    String spaces = neededSpaces(affichage,niveau);
                    // recuperer les appariements du niveau ciblé pour lui append le nouveau neucleotide
                    String line = affichage.get(niveau);
                    line = line + spaces  +arbre.charAt(i);
                    affichage.set(niveau,line);
                } else { //verifer si pas de changement du niveau d'arboressence
                    // recuperer les appariement du niveau ciblé pour lui append le nouveau neucleotide
                    String line = affichage.get(niveau);
                    line = line + "    " + arbre.charAt(i);
                    affichage.set(niveau,line);
                }
                sameLevel = true;  // simple feuille a été saisi , l'element suivant sera saisi au meme niveau d'arborescence
                i++;  // incrementer i
                //verifier si le caractere à index i est N (debut du noeud)
            } else if ( arbre.charAt(i) == 'N'){
                // verifier si  le niveau de saisi est superieur à taille du list del' affichage
                if ( affichage.size() <= niveau){
                    affichage.add(""); // on crée la nouvelle case vide
                }
                // verifier si le niveau d'arboressence est changé
                if (sameLevel == false){
                    // recupere le nombre des espace necessaire avant la nouvelle saisi : pour eviter l'intersection des noeuds
                    String spaces = neededSpaces(affichage,niveau);
                    // recuperer les appariement du niveau ciblé pour lui append le nouveau nucléotide
                    String line = affichage.get(niveau);
                    line = line + spaces + arbre.substring(i,i+8); // N?[(?,?) : debut de noeud à 8 caractere
                    affichage.set(niveau,line);
                } else { //verifer si pas de changement du niveau d'arboressence
                    String line = affichage.get(niveau);
                    line = line + "    " + arbre.substring(i,i+8);// N?[(?,?) : debut de noeud à 8 caractere
                    affichage.set(niveau,line);
                }
                // Nouvelle noeud est initié donc les elements suivant seront au niveau de ces sous elements (sous noeuds et feuille)
                sameLevel = false;
                niveau++;
                // passer / sauter la declaration du debut de noeud deja affecté
                i = i + 8 ;
            } else if ( arbre.charAt(i) == ']' ){ // verifier si le caractere à l index i est ] : cloture du noeud
                // changement du niveau : noeud vers noeud mére
                sameLevel = false;
                niveau --;
                i++; // incrémenter i
            } else if (arbre.charAt(i) == ',' || arbre.charAt(i) == ')'){ // les autres caractéres n'affectent pas notre arboresensce
                i++;
            }
        } //retourner resultat : affichage
        return  affichage;
    }
    // calculer le nombre d'espace à laisser avant les saisi des nouveau sous arbres , pour eviter l intersection des sous noeuds
    // en passant comme parametre la liste (affichage) et le niveau de saisi
    public String neededSpaces (ArrayList<String> list , int niveau){
        // on suppose que la premier case (premier niveau d'arborecence ) est la chaine la plus longue ,
        //  on recupere sa longeur , niveau le plus long sera 0 , le nombre des espaces necessaire initialisé à 0
        int res = list.get(0).length();
        int longestLevel = 0;
        int neededSpaces = 0 ;
        // comparer les longeurs des elements de la liste pour trouver le niveau d'arborersence le plus long
        for ( int i = 0 ; i < list.size() ; i++){
            if (list.get(i).length() > res){
                res = list.get(i).length();
                longestLevel = i ;
            }
        }
        // recupere l'element lui meme du niveau plus long
        String longestString = list.get(longestLevel);
        // tester si le niveau de saisi acctuel est superieur au niveau plus long
        if (  (niveau>longestLevel)){
            // le debut du nouveau niveau d'arborescence sera just au dessus du declaration des noeud : la position du N
            neededSpaces = longestString.lastIndexOf('N');
            // tester si le niveau de saisi actuel est inferieur au niveau plus long
        } else if (  (niveau<longestLevel)){
            // le debut du nouveau niveau d'arborescence sera aprés la fin du niveau plus longue + marge de 20 espaces
            neededSpaces = 20 + longestString.length();
            // tester si le niveau de saisi actuel est déja le niveau plus long
        }else if ( longestLevel == niveau){
            // laisser une marge de  quelques espaces
            return "       " ;
        }
        // à ce point on a le nombre des espaces necessaire , on utilise cette boucle pour créer la chaine d'espaces
        String spaces = "";
        for (int sp = list.get(niveau ).length(); sp <neededSpaces;sp ++){
            spaces = spaces + " ";
        }
        // retourner le resultat
        return  spaces;
    }
}