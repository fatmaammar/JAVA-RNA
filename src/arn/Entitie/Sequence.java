
package arn.Entitie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Sequence {
     private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private int idSequence ;
    private String seqString ;
    private String apparString ;
    private ArrayList<Base> bases ;
    
    
    private List<Forme> formes ;

    public Sequence( String seqString, String apparString, ArrayList<Base> bases) {
        this.idSequence = ID_FACTORY.getAndIncrement();
        this.seqString = seqString;
        this.apparString = apparString;
        this.bases = bases;
    }

    
    
    
    
    public Sequence(String seqString, String apparString, ArrayList<Base> bases, List<Forme> formes) {
        this.idSequence = ID_FACTORY.getAndIncrement();
        this.seqString = seqString;
        this.apparString = apparString;
        this.bases = bases;
        this.formes = formes;
    }

    public Sequence() {

    }

    public int getIdSequence() {
        return idSequence;
    }

    public void setIdSequence(int idSequence) {
        this.idSequence = idSequence;
    }

    public String getSeqString() {
        return seqString;
    }

    public void setSeqString(String seqString) {
        this.seqString = seqString;
    }

    public String getApparString() {
        return apparString;
    }

    public void setApparString(String apparString) {
        this.apparString = apparString;
    }

    public ArrayList<Base> getBases() {
        return bases;
    }

    public void setBases(ArrayList<Base> bases) {
        this.bases = bases;
    }

    public List<Forme> getFormes() {
        return formes;
    }

    public void setFormes(List<Forme> formes) {
        this.formes = formes;
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "idSequence=" + idSequence +
                ", seqString='" + seqString + '\'' +
                ", apparString='" + apparString + '\'' +
                ", bases=" + bases +
                ", formes=" + formes +
                '}';
    }
}
