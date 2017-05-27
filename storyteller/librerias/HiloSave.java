/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storyteller.librerias;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import storyteller.program.Logica;

/**
 *
 * @author edgerik
 */
public class HiloSave implements Runnable{
    private Logica logica;
    private Thread hilo;
    public HiloSave(Logica logica) 
    {
        this.logica = logica;
        this.hilo = new Thread(this);
    }
    
    public void start()
    {
    	hilo.start();
    }
    @Override
    public void run() {
        try {
            logica.botonSave();
        } catch (IOException ex) {
            Logger.getLogger(HiloSave.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
