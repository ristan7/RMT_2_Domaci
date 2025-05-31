/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server_paket;

import controller.Controller;
import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Korisnik
 */
public class PokretanjeServera extends Thread {

    private ServerSocket serverSocket;

    public PokretanjeServera() {
        try {
            serverSocket = new ServerSocket(9000);
            
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
            //izbaciGreskuNaFormi
            Controller.getInstance().losePokretanje(ex.getMessage());
        }
    }

    @Override
    public void run() {
        while (serverSocket != null && !serverSocket.isClosed()) {
            Controller.getInstance().dobroPokretanje();
            System.out.println("SERVER JE USPESNO POKRENUT");
            try {
                //otvaranje klijentskog soketa
                Socket s = serverSocket.accept();
                ObradaZahteva nit = new ObradaZahteva(s);
                Controller.getInstance().getZahteviKorisnika().add(nit);
                Controller.getInstance().setBrojKorinika(Controller.getInstance().getBrojKorinika() + 1);
                nit.start();
                System.out.println("POVEZAO SE KLIJENT BROJ: " + Controller.getInstance().getBrojKorinika());
                Controller.getInstance().povezanKlijent();
            } catch (IOException ex) {
                Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
                //obrada greske na formi
                Controller.getInstance().neuspesnoKlijent(ex.getMessage());
            }
        }
    }

}
