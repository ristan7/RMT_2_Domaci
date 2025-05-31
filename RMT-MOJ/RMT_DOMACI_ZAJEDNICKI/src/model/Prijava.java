/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class Prijava implements Serializable {

    private int id;
    private Date datumUlaska;
    private Date datumIzlaska;
    private Zemlja zemlja;
    private Putnik putnik;
    private NacinPrevoza nacinPrevoza;

    public Prijava() {
    }

    public Prijava(int id, Date datumUlaska, Date datumIzlaska, Zemlja zemlja, Putnik putnik, NacinPrevoza nacinPrevoza) {
        this.id = id;
        this.datumUlaska = datumUlaska;
        this.datumIzlaska = datumIzlaska;
        this.zemlja = zemlja;
        this.putnik = putnik;
        this.nacinPrevoza = nacinPrevoza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatumUlaska() {
        return datumUlaska;
    }

    public void setDatumUlaska(Date datumUlaska) {
        this.datumUlaska = datumUlaska;
    }

    public Date getDatumIzlaska() {
        return datumIzlaska;
    }

    public void setDatumIzlaska(Date datumIzlaska) {
        this.datumIzlaska = datumIzlaska;
    }

    public Zemlja getZemlja() {
        return zemlja;
    }

    public void setZemlja(Zemlja zemlja) {
        this.zemlja = zemlja;
    }

    public Putnik getPutnik() {
        return putnik;
    }

    public void setPutnik(Putnik putnik) {
        this.putnik = putnik;
    }

    public NacinPrevoza getNacinPrevoza() {
        return nacinPrevoza;
    }

    public void setNacinPrevoza(NacinPrevoza nacinPrevoza) {
        this.nacinPrevoza = nacinPrevoza;
    }

    @Override
    public String toString() {
        return putnik + ";" + zemlja + ";" + nacinPrevoza.toString();
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
        final Prijava other = (Prijava) obj;
        if (!Objects.equals(this.datumUlaska, other.datumUlaska)) {
            return false;
        }
        if (!Objects.equals(this.datumIzlaska, other.datumIzlaska)) {
            return false;
        }
        if (!Objects.equals(this.zemlja, other.zemlja)) {
            return false;
        }
        return Objects.equals(this.putnik, other.putnik);
    }
    
}
