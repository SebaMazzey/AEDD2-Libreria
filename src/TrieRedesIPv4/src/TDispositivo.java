/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public class TDispositivo {
    private String direccionIP;
    private String nombre;
   // private String telefono;

    TDispositivo(String dirIP, String unNombre) {

        this.direccionIP = dirIP;
        this.nombre = unNombre;
       // this.telefono = unTelefono;
    }

    public String getDirIP() {
        return this.direccionIP;
    }
    public String getNombre() {
        return this.nombre;
    }
/*
    public String getTelefono() {
        return this.telefono;
    }
*/
   
}
