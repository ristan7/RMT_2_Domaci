/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase_za_mreznu_komunikaciju;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class ServerResponse implements Serializable{
    private Object response;

    public ServerResponse() {
    }

    public ServerResponse(Object response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
    
}
