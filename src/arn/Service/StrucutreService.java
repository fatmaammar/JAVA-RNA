package arn.Service;

import arn.Entitie.Base;
import arn.Entitie.Sequence;
import arn.Entitie.Structure;
import java.util.ArrayList;


public class StrucutreService {

   
    //Création de la structure
    public Structure CreateStructure(String sequence, String appar) {
        //création de liste
        ArrayList<Base> basesList = new ArrayList<>();
        // parcourir la séquence et il va prendre le premiere caratère et construire l'objet et le rajouté dans la liste d'avant
        for (int i = 0; i < sequence.length(); i++) {
            // instanciation de la class base, i prend la caratère du conteur i
            Base b = new Base(sequence.charAt(i), appar.charAt(i));
            //rajouter dans la liste du début
            basesList.add(b);
        }
        //création d'une nouvelle séquences  avec notre nouvelles baselist
        Sequence seq = new Sequence(sequence, appar, basesList);
        //Description de la structure
        Structure struc = new Structure("desciption", seq);
        // il va nous retourner la descsiption de la structure ainsi que notre sequence
        return struc;
    }

    // Comparaison des formes avec le paramètre strucList de type arrayList
    public String compareForm(ArrayList<Structure> strucList) {

        System.out.println(" méthoode de comparaison  ");
        int i = 0;
        //Prendre la première  structure dans la liste et la stocke dans string form
        String form = strucList.get(0).getSequence().getApparString();
        //Mettre comme résultat par défault
        String result = "les structures sont identiques";
        //On entre dans la boucle pour pouvoir comparer les parenthésages entre elles
        do {
            i++;
            System.out.println(" boucle do while " + i );
            //si le parenthéisme de la ligne i est différents on va donc afficher le résultat
            if (!strucList.get(i).getSequence().getApparString().equals(form)) {
                result = "les structures sont differents";
                System.out.println(" if condtion , " + strucList.get(i).getSequence().getApparString() );
            }

        //tant que le résultat est toujours identique et i est inférieur à la taille de la liste, il va toujours nous retourner un résultat
        } while (result == "les structures sont identiques" && i < strucList.size() - 1);//element de la liste

        return result;
    }

    //Comparaison de la forme et de la séquence
    public String compareFormSeq(ArrayList<Structure> strucList) {

        int i = 0;
        //prendre le parenthessage (appariement) en String, à partir de l'objet sequence
        // qui se trouve dans la structure ayant comme index 0 du list strucList
        String form = strucList.get(0).getSequence().getApparString();
        //prendre la sequence en String, à partir de l'objet sequence
        // qui se trouve dans la structure ayant comme index 0 du list strucList
        String seq = strucList.get(0).getSequence().getSeqString();
        //Mettre comme résultat par défault
        String result = "les structures sont les memes";
        do {
            i++;
            // Si on compare le parenthéisme ou la séquence de la structure i avec la première structure et elles sont différentes alors les structures sont différentes
            if (!strucList.get(i).getSequence().getApparString().equals(form)
                    || !strucList.get(i).getSequence().getSeqString().equals(seq)) {
                result = "les structures sont differents";
            }

            //tant que le résultat est toujours identique et i est inférieur à la taille de la liste, il va toujours nous retourner un résultat
        } while (result == "les structures sont les memes" && i < strucList.size()  - 1);

        return result;
    }

   
}
