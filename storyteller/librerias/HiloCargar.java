/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storyteller.librerias;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import storyteller.interfaz.Interfaz;
import storyteller.program.Logica;

/**
 *
 * @author edgerik
 */
public class HiloCargar implements Runnable{
    private Logica logica;
    private Thread hilo;
    public HiloCargar(Logica logica) 
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
            // Logica Cargar
            logica.botonCargar();
        } catch (IOException | ParseException | InterruptedException | URISyntaxException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
