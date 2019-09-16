package UT03;



import java.util.LinkedList;


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
        if (raiz!=null)
        {
            return raiz.buscar(palabra.toLowerCase());
        }
        return 0;
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> lista = new LinkedList<>();
        if (raiz!=null)
        {
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
    
    public void insertarHash(String palabra,int posicion) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertarHash(palabra.toLowerCase(),posicion);
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
        if (raiz!=null)
        {
            return raiz.buscarHash(palabra.toLowerCase());
        }
        return 0;
    }

    @Override
    public LinkedList<String> predecirHash(String prefijo) {
        LinkedList<String> lista = new LinkedList<>();
        if (raiz!=null)
        {
            raiz.predecirHash(prefijo.toLowerCase(), lista);
        }
        return lista;
    }
    
    
}
