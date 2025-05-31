/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server_paket;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import klase_za_mreznu_komunikaciju.ClientRequest;
import klase_za_mreznu_komunikaciju.ServerResponse;
import model.Prijava;
import model.Putnik;
import model.Zemlja;
import operacije.Operacije;

/**
 *
 * @author Korisnik
 */
public class ObradaZahteva extends Thread {

    private Socket socket;

    public ObradaZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ClientRequest rq;
        while (!socket.isClosed()) {
            rq = vratiKlijentskiZahtev();
            ServerResponse sr = new ServerResponse();
            switch (rq.getOperation()) {
                case Operacije.LOGIN:
                    String[] zahtev = (String[]) rq.getObject();
                    String username = zahtev[0];
                    String pass = zahtev[1];
                    Putnik putnik = Controller.getInstance().login(username, pass);
                    sr.setResponse(putnik);
                    break;
                case Operacije.UCITAJ_ZEMLJE:
                    List<Zemlja> zemlje = (List<Zemlja>) rq.getObject();
                    zemlje = Controller.getInstance().vratiZemlje();
                    sr.setResponse(zemlje);
                    break;
                case Operacije.VRATI_PUTOVANJA:
                    Putnik p=(Putnik) rq.getObject();
                    List<Prijava> prijave=Controller.getInstance().vratiListuPrijava(p);
                    sr.setResponse(prijave);
                    break;
                default:
                    System.out.println("GRESKA, LOSA OPERACIJA");
            }
            posaljiOdgovorKlijentu(sr);
        }
    }

    private ClientRequest vratiKlijentskiZahtev() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            try {
                return (ClientRequest) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
                //obrada na formi
                Controller.getInstance().greskaPriCitanjuZahteva(ex.getMessage());
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
            //obrada na formi
            Controller.getInstance().greskaPriCitanjuZahteva(ex.getMessage());
        }
        return null;
    }

    private void posaljiOdgovorKlijentu(ServerResponse sr) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(sr);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
            //obbradaNaFormi
            Controller.getInstance().greskaPriSlanjuOdgovora(ex.getMessage());
        }
    }
}
