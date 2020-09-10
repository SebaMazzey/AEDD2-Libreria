package trieTelefonos;

import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface INodoTrie {

    int buscar(String s); // devuelve la cantidad de comparaciones!
    void imprimir();

    void insertar(Abonado abonado);
    public void predecir(String prefijo, TreeSet<String> palabras);
    
    
}
