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
public class TNodoArbolGenerico implements ITNodoArbolGenerico {

    private final String clave;
    private final String path;
    private TNodoArbolGenerico primerHijo;
    private TNodoArbolGenerico hermanoDerecho;

    //Devuelve la clave del nodo
    public String Clave() {
        return this.clave;
    }

    //Devuelve la ruta del padre
    public String ParentPath() {
        return this.path;
    }

    //Crea el Nodo con la etiqueta
    public TNodoArbolGenerico(String unaEtiqueta, String parentPath) {
        this.clave = unaEtiqueta;
        this.path = parentPath + "\\" + this.clave;
    }

    @Override
    public boolean insertarNodo(String unaEtiqueta, String etiquetaPadre) {
        if (this.clave.equals(etiquetaPadre)) {
            TNodoArbolGenerico aux = this.primerHijo;
            this.primerHijo = new TNodoArbolGenerico(unaEtiqueta, this.path);
            this.primerHijo.hermanoDerecho = aux;
            return true;
        }
        boolean aux = false;
        TNodoArbolGenerico next = this.hermanoDerecho;

        if (next != null) {
            aux = next.insertarNodo(unaEtiqueta, etiquetaPadre);
        }
        next = this.primerHijo;
        if (next != null && !aux) {
            aux = next.insertarNodo(unaEtiqueta, etiquetaPadre);
        }
        return aux;
    }

    @Override
    public boolean buscarNodo(String clave) {
        if (this.clave.equals(clave)) {
            return true;
        }
        boolean exit = false;
        TNodoArbolGenerico hermano = this.hermanoDerecho;
        while (hermano != null && exit == false) {
            exit = hermano.buscarNodo(clave);
            hermano = hermano.hermanoDerecho;
        }
        TNodoArbolGenerico unHijo = this.primerHijo;
        while (unHijo != null && exit == false) {
            exit = unHijo.buscarNodo(clave);
            unHijo = unHijo.primerHijo;
        }
        return exit;
    }

    @Override
    public void listarIndentado(String dirPadre) {
        System.out.println(this.path);
        TNodoArbolGenerico aux = this.primerHijo;
        if (aux != null) {
            aux.listarIndentado(path);
        }
        aux = this.hermanoDerecho;
        if (aux != null) {
            aux.listarIndentado(dirPadre);
        }
    }
}
