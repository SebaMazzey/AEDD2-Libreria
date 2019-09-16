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
public interface ITArbolTrie {
    void insertar(String palabra);
    void imprimir();
    int buscar(String palabra);
    LinkedList<String> predecir(String prefijo);
    
    //Hash Functions
    void insertarHash(String palabra);
    void imprimirHash();
    int buscarHash(String palabra);
    LinkedList<String> predecirHash(String prefijo);
    
}
