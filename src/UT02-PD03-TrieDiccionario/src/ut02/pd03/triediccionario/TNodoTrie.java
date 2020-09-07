package ut02.pd03.triediccionario;

import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrie implements INodoTrie {

    private final HashMap<Character, TNodoTrie> hijos;
    private boolean esPalabra;

    public TNodoTrie() {
        hijos = new HashMap();
        esPalabra = false;
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int i = 0; i < unaPalabra.length(); i++) {
            char ch = unaPalabra.charAt(i);
            if (nodo.hijos.get(ch) == null) {
                nodo.hijos.put(ch, new TNodoTrie());
            }
            nodo = nodo.hijos.get(ch);
            if (i == unaPalabra.length() - 1) {
                nodo.esPalabra = true;
            }
        }
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);

            }
            nodo.hijos.entrySet().forEach(hijo -> imprimir(s + hijo.getKey(), hijo.getValue()));
        }
    }

    @Override
    public void imprimir() {

        imprimir("", this);
    }

    private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (char ch : s.toCharArray()) {
            if (nodo.hijos.get(ch) == null) {
                return null;
            }
            nodo = nodo.hijos.get(ch);
        }
        return nodo;
    }

    private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(prefijo + s);
            }
            nodo.hijos.entrySet().forEach(hijo -> predecir(s + hijo.getKey(), prefijo, palabras, hijo.getValue()));
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrie nodo = buscarNodoTrie(prefijo);
        predecir("", prefijo, palabras, nodo);
    }

    @Override
    public int buscar(String s) {
        TNodoTrie nodo = this;
        int cont = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            nodo = nodo.hijos.get(ch);
            cont++;
            if (nodo == null) {
                return 0;
            }
        }

        if (nodo.esPalabra) {
            return cont;
        }

        return 0;
    }

}
