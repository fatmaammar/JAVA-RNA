
package arn.Entitie;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Arbre {
    
     private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private int idArbre ;
    private String arbre ;
    private int nbNoeudsTot;
    private int nbFeuillesTot ;
    private ArrayList<Noeud> noeuds ;

    public Arbre( String arbre, int nbNoeudsTot, int nbFeuillesTot, ArrayList<Noeud> noeuds) {
        this.idArbre = ID_FACTORY.getAndIncrement();
        this.arbre = arbre;
        this.nbNoeudsTot = nbNoeudsTot;
        this.nbFeuillesTot = nbFeuillesTot;
        this.noeuds = noeuds;
    }

    @Override
    public String toString() {
        return "Arbre{" + "idArbre=" + idArbre + ", arbre=" + arbre + ", nbNoeudsTot=" + nbNoeudsTot + ", nbFeuillesTot=" + nbFeuillesTot + ", noeuds=" + noeuds + '}';
    }

    public static AtomicInteger getIdFactory() {
        return ID_FACTORY;
    }

    public int getIdArbre() {
        return idArbre;
    }

    public String getArbre() {
        return arbre;
    }

    public int getNbNoeudsTot() {
        return nbNoeudsTot;
    }

    public int getNbFeuillesTot() {
        return nbFeuillesTot;
    }

    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }
}
