/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Korisnik
 */
public class Konekcija {
    private static Konekcija instance;
    private Connection connection;
    

    private Konekcija() {
        String url="jdbc:mysql://localhost:3306/rmt";
        String user="root";
        String password="Harmonika1";
        try {
            connection=DriverManager.getConnection(url, user, password);
            //naFormiPrikazati
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
            //obradiNaFormi
        }
    }
    
    public static Konekcija getInstance(){
        if(instance==null)
            instance=new Konekcija();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
}
