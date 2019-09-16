/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT02;

/**
 *
 * @author AX-Book
 */
public class TArbolGenerico implements ITArbolGenerico {
    
    private TNodoArbolGenerico raiz;
    
    @Override
    public boolean insertarNodo(String unaEtiqueta, String etiquetaPadre) {
        if (this.raiz == null || etiquetaPadre.equals("")) {
            this.raiz = new TNodoArbolGenerico("root", "");
        }
        return this.raiz.insertarNodo(unaEtiqueta, etiquetaPadre);
    }
    
    @Override
    public boolean buscarNodo(String clave) {
        if (this.raiz == null) {
            return false;
        }
        return this.raiz.buscarNodo(clave);
    }
    
    @Override
    public void listarIndentado() {
        if (this.raiz == null) {
            System.out.println("No hay raiz.");
        } else {
            this.raiz.listarIndentado("");
        }
    }
    
}
