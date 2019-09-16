/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT03;

import java.util.LinkedList;

/**
 *
 * @author AX-Book
 */
public interface ITNodoTrie {
    void insertar(String palabra);
    void imprimir();
    void predecir(String prefijo, LinkedList<String> lista);
    int buscar(String palabra);
    //HashMap Functions
    void insertarHash(String palabra);
    void imprimirHash();
    void predecirHash(String prefijo, LinkedList<String> lista);
    int buscarHash(String palabra);
    
}
