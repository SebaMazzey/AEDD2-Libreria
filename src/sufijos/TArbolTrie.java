package sufijos;



import java.util.LinkedList;


public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;

    @Override
    public void insertar(String palabra, int index) {
        if (raiz == null) {
            raiz = new TNodoTrie(-1);
        }
        raiz.insertar(palabra, index);
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        if (raiz!=null){
            return raiz.buscar(palabra);
        }
        return 0;
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> list = new LinkedList<>();
        if(raiz != null)
            raiz.predecir(prefijo, list);
        return list;
    }
    
    
}
