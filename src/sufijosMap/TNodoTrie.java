package sufijosMap;

import java.util.LinkedList;
import java.util.TreeMap;

public class TNodoTrie implements INodoTrie {

    private final TreeMap<Character, TNodoTrie> hijos;
    private boolean esPalabra;
    private int posicion;

    public TNodoTrie(int index) {
        hijos = new TreeMap<>();
        esPalabra = false;
        posicion = index;
    }

    public TNodoTrie() {
        hijos = new TreeMap<>();
        esPalabra = false;
    }

    @Override
    public void insertar(String unaPalabra, int index) {
        TNodoTrie nodo = this;
        unaPalabra = unaPalabra.toLowerCase();
        for (int c = 0; c < unaPalabra.length(); c++) {
            char letra = unaPalabra.charAt(c);
            if (nodo.hijos == null) {
                nodo.hijos.put(letra, new TNodoTrie(c));
            }
            nodo = nodo.hijos.get(letra);
        }
        nodo.esPalabra = true;
    }

    @Override
    public void imprimir() {

        imprimir("", this);
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                if (nodo.posicion > -1) {
                    System.out.println(s + " - " + nodo.posicion);
                }
            }

            nodo.hijos.entrySet().forEach(hijo -> imprimir(s + hijo.getKey(), nodo.hijos.get(hijo.getKey())));

        }
    }

    private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (char c : s.toCharArray()) {
            nodo = nodo.hijos.get(c);
            if (nodo == null) {
                return null;
            }
        }
        return nodo;
    }

    private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(prefijo + s + " - " + nodo.posicion);
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
        for(char c : s.toCharArray()){
            cont++;
            if (nodo == null) {
                return 0;
            }
            nodo.hijos.get(c);
        }
        if (nodo.esPalabra) {
            return cont;
        }
        return 0;
    }

}
