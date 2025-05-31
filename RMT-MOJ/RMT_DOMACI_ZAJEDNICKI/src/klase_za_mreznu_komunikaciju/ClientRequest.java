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
public class ClientRequest implements Serializable{
    private Object object;
    private int operation;

    public ClientRequest() {
    }

    public ClientRequest(Object object, int operation) {
        this.object = object;
        this.operation = operation;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
}
