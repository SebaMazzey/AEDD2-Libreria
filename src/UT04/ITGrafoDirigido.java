/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT04;

/**
 *
 * @author AX-Book
 */
public interface ITGrafoDirigido {
    TNodoGrafo buscar(Comparable clave);
    boolean agregarAdyacencia(Comparable origen, Comparable destino, int costo);
    boolean agregarVertice(TNodoGrafo vertice);
    boolean eliminarAdyacencia(Comparable origen, Comparable destino);
    void imprimir();
    
}
