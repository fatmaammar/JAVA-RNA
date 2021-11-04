
package arn.Entitie;

import java.util.concurrent.atomic.AtomicInteger;


public class Base {
    private static final AtomicInteger ID_FACTORY = new AtomicInteger();
    private int idBase ;
    private Character lib; // CGAU
    private Forme forme;
    private Character symbole;  // - ( ) 

    public Base( Character lib, Character symbole) {
        this.idBase = ID_FACTORY.getAndIncrement();
        this.lib = lib;
        this.symbole = symbole;
    }

    

    
    
    
    
    
    
    
    
    public Base( Character lib, Forme forme, Character symbole) {
        this.idBase = ID_FACTORY.getAndIncrement();
        this.lib = lib;
        this.forme = forme;
        this.symbole = symbole;
    }

    @Override
    public String toString() {
        return "Base{" + "idBase=" + idBase + ", lib=" + lib + ", forme=" + forme + ", symbole=" + symbole + '}';
    }
    
    
    
}
