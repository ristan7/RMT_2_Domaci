/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class Putnik implements Serializable{

    private int id;
    private String ime;
    private String prezime;
    private String email;
    private long JMBG;
    private int brojPasosa;
    private String username;
    private String password;

    public Putnik() {
    }

    public Putnik(int id, String ime, String prezime, String email, long JMBG, int brojPasosa, String username, String password) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.JMBG = JMBG;
        this.brojPasosa = brojPasosa;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getJMBG() {
        return JMBG;
    }

    public void setJMBG(long JMBG) {
        this.JMBG = JMBG;
    }

    public int getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(int brojPasosa) {
        this.brojPasosa = brojPasosa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Putnik other = (Putnik) obj;
        if (this.JMBG != other.JMBG) {
            return false;
        }
        return this.brojPasosa == other.brojPasosa;
    }
    
}
