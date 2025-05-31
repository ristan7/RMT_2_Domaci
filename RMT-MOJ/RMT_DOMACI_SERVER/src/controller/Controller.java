/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import main.MainForm;
import model.Prijava;
import model.Putnik;
import model.Zemlja;
import server_paket.ObradaZahteva;

/**
 *
 * @author Korisnik
 */
public class Controller {

    private MainForm sf;
    private static Controller instance;
    List<ObradaZahteva> zahteviKorisnika;
    private int brojKorinika = 0;
    private DBBroker dbb;

    private Controller() {
        zahteviKorisnika = new ArrayList<>();
        dbb = new DBBroker();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<ObradaZahteva> getZahteviKorisnika() {
        return zahteviKorisnika;
    }

    public void setZahteviKorisnika(List<ObradaZahteva> zahteviKorisnika) {
        this.zahteviKorisnika = zahteviKorisnika;
    }

    public int getBrojKorinika() {
        return brojKorinika;
    }

    public void setBrojKorinika(int brojKorinika) {
        this.brojKorinika = brojKorinika;
    }

    public MainForm getSf() {
        return sf;
    }

    public void setSf(MainForm sf) {
        this.sf = sf;
    }

    public void losePokretanje(String message) {
        sf.losePokretanje(message);
    }

    public void dobroPokretanje() {
        sf.dobroPokretanje();
    }

    public void povezanKlijent() {
        sf.povezanKlijent();
    }

    public void neuspesnoKlijent(String message) {
        sf.greskaKlijent(message);
    }

    public void greskaPriCitanjuZahteva(String message) {
        sf.greskaPriCitanjuZahteva(message);
    }

    public void greskaPriSlanjuOdgovora(String message) {
        sf.greskaPriSlanjuOdgovora(message);
    }

    public Putnik login(String username, String pass) {
        Putnik putnik = dbb.login(username, pass);

        return putnik;
    }

    public List<Zemlja> vratiZemlje() {
        return dbb.vratiZemlje();
    }

    public List<Prijava> vratiListuPrijava(Putnik p) {
        return dbb.vratiListuPrijava(p);
    }

}
