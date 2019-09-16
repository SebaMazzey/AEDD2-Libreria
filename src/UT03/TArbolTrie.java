package UT03;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TArbolTrie implements ITArbolTrie {

    private TNodoTrie raiz;

    @Override
    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(palabra.toLowerCase());
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        if (raiz != null) {
            return raiz.buscar(palabra.toLowerCase());
        }
        return 0;
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> lista = new LinkedList<>();
        if (raiz != null) {
            raiz.predecir(prefijo.toLowerCase(), lista);
        }
        return lista;
    }

    //HASH FUNCTIONS
    @Override
    public void insertarHash(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertarHash(palabra.toLowerCase());
    }

    public void insertarHash(String palabra, int posicion) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertarHash(palabra.toLowerCase(), posicion);
    }

    @Override
    public void imprimirHash() {
        if (raiz != null) {
            raiz.imprimirHash();
        }
    }

    public void imprimirHashConPosicion() {
        if (raiz != null) {
            raiz.imprimirHashConPosicion();
        }
    }

    @Override
    public int buscarHash(String palabra) {
        if (raiz != null) {
            return raiz.buscarHash(palabra.toLowerCase());
        }
        return 0;
    }

    @Override
    public LinkedList<String> predecirHash(String prefijo) {
        LinkedList<String> lista = new LinkedList<>();
        if (raiz != null) {
            raiz.predecirHash(prefijo.toLowerCase(), lista);
        }
        return lista;
    }

    //IP FUNCTIONS
    public void insertarIP(String ip) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        String[] arr = ip.split("-");
        Pattern pattern = Pattern.compile("^(?=.*[^\\.]$)((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.?){4}$");
        Matcher matcher = pattern.matcher(arr[0]);
        if (matcher.find()) {
            raiz.insertarIP(arr);
        }
    }

    public void imprimirIP() {
        if (raiz != null) {
            raiz.imprimirIP();
        }
    }

    public TNodoTrie buscarIP(String ip) {
        if (raiz != null) {
            Pattern pattern = Pattern.compile("^(?=.*[^\\.]$)((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.?){4}$");
            Matcher matcher = pattern.matcher(ip);
            if (matcher.find()) {
                return raiz.buscarIP(ip);
            }
        }
        return null;
    }

    public LinkedList<String> predecirIP(String mascara) {
        LinkedList<String> lista = new LinkedList<>();
        if (raiz != null) {
            raiz.predecirIP(mascara, lista);
        }
        return lista;
    }

}
