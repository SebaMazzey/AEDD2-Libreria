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
public interface ITNodoGrafo {
    boolean agregarAdyacencia(TNodoGrafo destino, int costo);
    boolean eliminarAdyacencia(Comparable destino);
    void imprimir();
}
