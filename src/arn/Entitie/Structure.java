
package arn.Entitie;

import java.util.concurrent.atomic.AtomicInteger;


public class Structure {
     private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private int idStructure ;
    private String descStructure;
    private Sequence sequence ;
    private Arbre arbre ;

    public Structure( String descStructure, Sequence sequence) {
        this.idStructure = ID_FACTORY.getAndIncrement();
        this.descStructure = descStructure;
        this.sequence = sequence;
    }
    //Constructeur: la première fonction pour faire l instanciation d'une classe
    public Structure( String descStructure, Sequence sequence, Arbre arbre) {
        this.idStructure = ID_FACTORY.getAndIncrement();
        this.descStructure = descStructure;
        this.sequence = sequence;
        this.arbre = arbre;
    }

    public int getIdStructure() {
        return idStructure;
    }

    public void setIdStructure(int idStructure) {
        this.idStructure = idStructure;
    }

    public String getDescStructure() {
        return descStructure;
    }

    public void setDescStructure(String descStructure) {
        this.descStructure = descStructure;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public Arbre getArbre() {
        return arbre;
    }

    public void setArbre(Arbre arbre) {
        this.arbre = arbre;
    }

    @Override
    public String toString() {
        return "Structure{" + "idStructure=" + idStructure + ", descStructure=" + descStructure + ", sequence=" + sequence + '}';
    }


    public static AtomicInteger getIdFactory() {
        return ID_FACTORY;
    }
}
