package trieTelefonos;

import java.util.LinkedList;
import java.util.TreeSet;

public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;
    private long cantidadDeInserciones = 0;

    @Override
    public void insertar(Abonado abonado) {
        if (raiz == null) {
            raiz = new TNodoTrie("");
        }
        raiz.insertar(abonado);
        cantidadDeInserciones++;
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
            return raiz.buscar(palabra);
        }
        return 0;
    }

    @Override
    public TreeSet<String> predecir(String prefijo) {
        TreeSet<String> list = new TreeSet<>();
        if (raiz != null) {
            raiz.predecir(prefijo, list);
        }
        return list;
    }

}
