/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import klase_za_mreznu_komunikaciju.ClientRequest;
import klase_za_mreznu_komunikaciju.ServerResponse;
import model.Putnik;

/**
 *
 * @author Korisnik
 */
public class Komunikacija {

    private static Komunikacija instance;
    private Socket socket;
    private Putnik ulogovaniPutnik;

    private Komunikacija() {
        try {
            socket = new Socket("localhost", 9000);
            ulogovaniPutnik = null;
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            //obrada na serverskoj formi
        }

    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public ServerResponse primiOdgovor() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            try {
                return (ServerResponse) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
                //obrada na formi
            }
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            //obrada na formi
        }
        return null;
    }

    public void posaljiZahtev(ClientRequest rq) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(rq);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            //obrada na formi
        }
    }

    public Putnik getUlogovaniPutnik() {
        return ulogovaniPutnik;
    }

    public void setUlogovaniPutnik(Putnik ulogovaniPutnik) {
        this.ulogovaniPutnik = ulogovaniPutnik;
    }

}
