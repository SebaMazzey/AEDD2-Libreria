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
public interface ITArbolGenerico {
    public boolean insertarNodo(String unaEtiqueta, String etiquetaPadre);
    public boolean buscarNodo(String clave);
    public void listarIndentado();
}
