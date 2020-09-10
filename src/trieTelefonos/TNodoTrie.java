package trieTelefonos;

import sufijos.*;
import java.util.LinkedList;
import java.util.TreeSet;

public class TNodoTrie implements INodoTrie {

    private final TNodoTrie[] hijos = new TNodoTrie[10];
    public Abonado dato = null;
    public String et;

    public TNodoTrie(String etiqueta) {
        this.et = etiqueta;
    }

    @Override
    public void insertar(Abonado abonado) {
        String numero = abonado.telefono;
        TNodoTrie nodo = this;
        for (char c : numero.toCharArray()) {
            int indice = c - '0';
            if (indice <= 9) {
                if (nodo.hijos[indice] == null) {
                    nodo.hijos[indice] = new TNodoTrie(String.valueOf(indice));
                }
                nodo = nodo.hijos[indice];
            }
        }
        nodo.dato = abonado;
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.dato != null) {
                System.out.println(s + " - Titular: " + nodo.dato.nombre);
            }
            for (TNodoTrie hijo : nodo.hijos) {
                if (hijo != null) {
                    imprimir(s + nodo.et, hijo);
                }
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (char c : s.toCharArray()) {
            int indice = c - '0';
            if (indice <= 9) {
                if (nodo.hijos[indice] == null) {
                    return null;
                }
                nodo = nodo.hijos[indice];
            }
        }
        return nodo;
    }

    private void predecir(String s, String prefijo, TreeSet<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.dato != null) {
                palabras.add(nodo.dato.nombre + " - " + prefijo + s);
            }
            for (int c = 0; c < nodo.hijos.length; c++) {
                if (nodo.hijos[c] != null) {
                    predecir(s + nodo.et, prefijo, palabras, nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void predecir(String prefijo, TreeSet<String> palabras) {
        TNodoTrie nodo = buscarNodoTrie(prefijo);
        predecir("", prefijo, palabras, nodo);
    }

    @Override
    public int buscar(String s) {
        TNodoTrie nodo = this;
        int cont = 0;
        for (char c : s.toCharArray()) {
            int indice = c - '0';
            cont++;
            nodo = nodo.hijos[indice];
            if (nodo == null) {
                return 0;
            }
        }
        if (nodo.dato != null) {
            return cont;
        }
        return 0;
    }

}
