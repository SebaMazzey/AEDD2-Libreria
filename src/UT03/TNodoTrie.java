/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author AX-Book
 */
public class TNodoTrie implements ITNodoTrie {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private final TNodoTrie[] hijos;
    public boolean esPalabra;
    private final HashMap<String, TNodoTrie> hijosHash;
    private final ArrayList<Integer> posicion;
    private String nombreDispositivo = "";

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        hijosHash = new HashMap();
        esPalabra = false;
        posicion = new ArrayList<>();
    }

    @Override
    public void insertar(String unaPalabra) {
        try {
            TNodoTrie nodo = this;
            for (int c = 0; c < unaPalabra.length(); c++) {
                int indice = unaPalabra.charAt(c) - 'a';
                if (nodo.hijos[indice] == null) {
                    nodo.hijos[indice] = new TNodoTrie();
                }
                nodo = nodo.hijos[indice];
            }
            nodo.esPalabra = true;
        } catch (Exception err) {
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

    //HASH Functions
    @Override
    public void insertarHash(String palabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < palabra.length(); c++) {
            TNodoTrie aux = nodo.hijosHash.get(palabra.charAt(c) + "");
            if (nodo.hijosHash.get(palabra.charAt(c) + "") == null) {
                nodo.hijosHash.put(Character.toString(palabra.charAt(c)), new TNodoTrie());
            }
            nodo = aux;
        }
        nodo.esPalabra = true;
    }

    public void insertarHash(String palabra, int posicion) {
        TNodoTrie nodo = this;
        for (int c = 0; c < palabra.length(); c++) {
            TNodoTrie aux = nodo.hijosHash.get(palabra.charAt(c) + "");
            if (aux == null) {
                nodo.hijosHash.put(Character.toString(palabra.charAt(c)), new TNodoTrie());
            }
            nodo = nodo.hijosHash.get(palabra.charAt(c) + "");
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
                TNodoTrie aux = nodo.hijosHash.get((char) (c + 'a') + "");
                if (aux != null) {
                    imprimirHash(s + (char) (c + 'a'), (TNodoTrie) nodo.hijosHash.get((char) (c + 'a') + ""));
                }
            }
        }
    }

    private void imprimirHashConPosicion(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s + ", \tAppears : " + nodo.posicion.size() + " time(s).");

            }
            for (Map.Entry<String, TNodoTrie> entry : nodo.hijosHash.entrySet()) {
                imprimirHashConPosicion(s + entry.getKey(), entry.getValue());
            }
//            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
//                if (nodo.hijosHash.get((char) (c + 'a') + "") != null) {
//                    imprimirHashConPosicion(s + (char) (c + 'a'), (TNodoTrie) nodo.hijosHash.get((char) (c + 'a') + ""));
//                }
//            }
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
            String indice = s.charAt(c) + "";
            nodo = nodo.hijosHash.get(indice);
            if (nodo == null) {
                return null;
            }
        }
        return nodo;
    }

    @Override
    public int buscarHash(String palabra) {
        TNodoTrie nodo = this;
        int count = 0;
        for (int i = 0; i < palabra.length(); i++) {
            nodo = nodo.hijosHash.get(palabra.charAt(i) + "");
            count++;
            if (nodo == null) {
                break;
            }
        }
        return count;
    }

    //IP Functions
    public void insertarIP(String[] arr) {
        String[] ip = arr[0].split(".");
        TNodoTrie nodo = this;
        for (int c = 0; c < 4; c++) {
            TNodoTrie aux = nodo.hijosHash.get(ip[c]);
            if (nodo.hijosHash.get(ip[c]) == null) {
                nodo.hijosHash.put(ip[c], new TNodoTrie());
            }
            nodo = aux;
        }
        nodo.esPalabra = true;
        nodo.nombreDispositivo = arr[1];
    }

    public void imprimirIP() {
        imprimirIP("", this);
    }

    private void imprimirIP(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            nodo.hijosHash.entrySet().forEach((entry) -> {
                imprimirIP(s + "." + entry.getKey(), entry.getValue());
            });
        }
    }

    public void predecirIP(String prefijo, LinkedList<String> lista) {
        TNodoTrie nodo = buscarIP(prefijo);
        predecirIP("", prefijo, lista, nodo);
    }

    private void predecirIP(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(prefijo + s);
            }
            nodo.hijosHash.entrySet().forEach((entry) -> {
                predecirHash(s + "." + entry.getKey(), prefijo, palabras, entry.getValue());
            });
        }
    }

    public TNodoTrie buscarIP(String s) {
        TNodoTrie nodo = this;
        String[] arr = s.split(".");
        for (String part : arr) {
            nodo = nodo.hijosHash.get(part);
            if (nodo == null) {
                return null;
            }
        }
        return nodo;
    }

}
