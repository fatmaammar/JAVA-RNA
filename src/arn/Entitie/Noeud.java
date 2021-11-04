
package arn.Entitie;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Noeud {
    
     private static final AtomicInteger ID_FACTORY = new AtomicInteger();
      private int idNoeud ;
    private String sousArbre ;
    private int nbNoeuds;
    private int nbFeuilles ;

    public Noeud( String sousArbre, int nbNoeuds, int nbFeuilles) {
        this.idNoeud = ID_FACTORY.getAndIncrement();
        this.sousArbre = sousArbre;
        this.nbNoeuds = nbNoeuds;
        this.nbFeuilles = nbFeuilles;
    }

    @Override
    public String toString() {
        return "Noeud{" + "idNoeud=" + idNoeud + ", sousArbre=" + sousArbre + ", nbNoeuds=" + nbNoeuds + ", nbFeuilles=" + nbFeuilles + '}';
    }
    
    
    
    
    
}
