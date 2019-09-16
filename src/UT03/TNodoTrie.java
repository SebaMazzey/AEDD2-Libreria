/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author AX-Book
 */
public class TNodoTrie implements ITNodoTrie {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private final TNodoTrie[] hijos;
    private boolean esPalabra;
    private final HashMap<String, TNodoTrie> hijosHash;
    private ArrayList<Integer> posicion;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        hijosHash = new HashMap();
        esPalabra = false;
        posicion = null;
    }

    @Override
    public void insertar(String unaPalabra) {
        try{
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;}
        catch(Exception err)
        {
            System.out.println("Error al insertar: " + unaPalabra);
        }
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private TNodoTrie buscarTNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo;
    }

    private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(prefijo + s);

            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    predecir(s + (char) (c + 'a'), prefijo, palabras, nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrie nodo = buscarTNodoTrie(prefijo);
        predecir("", prefijo, palabras, nodo);
    }

    @Override
    public int buscar(String s) {
        TNodoTrie nodo = this;
        int salida = 0;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            salida = c;
            if (nodo.hijos[indice] == null) {
                return 0;
            }
            nodo = nodo.hijos[indice];

        }
        if (nodo.esPalabra) {
            return salida;
        }
        return 0;
    }

    @Override
    public void insertarHash(String palabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < palabra.length(); c++) {
            if (nodo.hijosHash.get(palabra.charAt(c) + "") == null) {
                nodo.hijosHash.put(Character.toString(palabra.charAt(c)), new TNodoTrie());
            }
            nodo = (TNodoTrie) nodo.hijosHash.get(palabra.charAt(c) + "");
        }
        nodo.esPalabra = true;
    }

    public void insertarHash(String palabra, int posicion) {
        TNodoTrie nodo = this;
        for (int c = 0; c < palabra.length(); c++) {
            if (nodo.hijosHash.get(palabra.charAt(c) + "") == null) {
                nodo.hijosHash.put(Character.toString(palabra.charAt(c)), new TNodoTrie());
            }
            nodo = (TNodoTrie) nodo.hijosHash.get(palabra.charAt(c) + "");
        }
        nodo.esPalabra = true;
        nodo.posicion.add(posicion);
    }

    @Override
    public void imprimirHash() {
        imprimirHash("", this);
    }
    
    public void imprimirHashConPosicion() {
        imprimirHashConPosicion("", this);
    }

    private void imprimirHash(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);

            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijosHash.get((char) (c + 'a') + "") != null) {
                    imprimir(s + (char) (c + 'a'), (TNodoTrie) nodo.hijosHash.get((char) (c + 'a') + ""));
                }
            }
        }
    }

    private void imprimirHashConPosicion(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);

            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijosHash.get((char) (c + 'a') + "") != null) {
                    imprimir(s + (char) (c + 'a'), (TNodoTrie) nodo.hijosHash.get((char) (c + 'a') + ""));
                }
            }
        }
    }

    @Override
    public void predecirHash(String prefijo, LinkedList<String> lista) {
        TNodoTrie nodo = buscarTNodoTrieHash(prefijo);
        predecirHash("", prefijo, lista, nodo);
    }

    private void predecirHash(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(prefijo + s);
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                String cha = (char) (c + 'a') + "";
                if (nodo.hijosHash.get(cha) != null) {
                    predecirHash(s + cha, prefijo, palabras, nodo.hijosHash.get(cha));
                }
            }
        }
    }

    private TNodoTrie buscarTNodoTrieHash(String s) {
        TNodoTrie nodo = this;
        s = s.toLowerCase();
        for (int c = 0; c < s.length(); c++) {
            String indice = s.charAt(c) +"";
            nodo = nodo.hijosHash.get(indice);
            if (nodo == null) {
                return null;
            }
        }
        return nodo;
    }
    
    @Override
    public int buscarHash(String palabra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
