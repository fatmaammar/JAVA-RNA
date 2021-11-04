
package arn.Entitie;

import java.util.concurrent.atomic.AtomicInteger;


public class Forme {
     private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private int idForme;
    private String nomForm ; 
    private int tailleForm ;
    
    
    public Forme( String nomForm, int tailleForm) {
        this.idForme = ID_FACTORY.getAndIncrement();
        this.nomForm = nomForm;
        this.tailleForm = tailleForm;
    }

    @Override
    public String toString() {
        return "Forme{" + "idForme=" + idForme + ", nomForm=" + nomForm + ", tailleForm=" + tailleForm + '}';
    }
    
    
    
    
    
}
