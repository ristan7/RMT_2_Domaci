/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import model.Putnik;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NacinPrevoza;
import model.Prijava;
import model.Zemlja;

/**
 *
 * @author Korisnik
 */
public class DBBroker {

    public Putnik login(String username, String pass) {
        String upit = "SELECT id, ime, prezime, email, JMBG, broj_pasosa, username,lozinka FROM stanovnici WHERE username = ? AND lozinka = ?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                int id=rs.getInt("id");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                String email=rs.getString("email");
                long JMBG=rs.getLong("JMBG");
                int broj_pasosa=rs.getInt("broj_pasosa");
                Putnik putnik=new Putnik(id, ime, prezime, email, JMBG, broj_pasosa, username, pass);
                return putnik;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            //obrada na serverskoj formi
        }
        return null;
    }

    public List<Zemlja> vratiZemlje() {
        List<Zemlja> listaZemalja=new ArrayList<>();
        String upit="SELECT * FROM zemlja";
        try {
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            while(rs.next()){
                int id=rs.getInt("id");
                String naziv=rs.getString("naziv");
                Zemlja z=new Zemlja(id, naziv);
                listaZemalja.add(z);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            //obrada na serveru
        }
        return listaZemalja;
    }

    public List<Prijava> vratiListuPrijava(Putnik putnik) {
        List<Prijava> prijave=new ArrayList<>();
        String upit="SELECT * FROM prijava p JOIN zemlja z ON p.zemlja=z.id JOIN stanovnici s ON p.putnik=s.id JOIN nacin_prevoza n ON p.nacin_prevoza=n.id WHERE s.jmbg= ? AND s.broj_pasosa= ?";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, putnik.getJMBG());
            ps.setInt(2, putnik.getBrojPasosa());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int idPrijave=rs.getInt("p.id");
                Date datumUlaska=rs.getDate("p.datum_ulaska");
                Date datumIzlaska=rs.getDate("p.datum_izlaska");
                NacinPrevoza nacinPrevoza=NacinPrevoza.valueOf(rs.getString("n.naziv"));
                
                int idZemlje=rs.getInt("z.id");
                String nazivZemlje=rs.getString("z.naziv");
                Zemlja z=new Zemlja(idZemlje, nazivZemlje);
                
                Prijava p=new Prijava(idPrijave, datumUlaska, datumIzlaska, z, putnik, nacinPrevoza);
                prijave.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prijave;
    }

}
