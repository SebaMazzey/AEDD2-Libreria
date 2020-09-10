package sufijosMap;



import sufijos.*;
import java.util.LinkedList;


public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;
    private long cantidadDeInserciones = 0;

    @Override
    public void insertar(String palabra, int index) {
        if (raiz == null) {
            raiz = new TNodoTrie(-1);
        }
        raiz.insertar(palabra, index);
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
    
    public void generarArbolSufijos(String patron){
        if(!patron.isEmpty()){
            this.raiz= new TNodoTrie();
            for (int i = patron.length()-1; i>=0 ; i--) {
                String palabra = patron.substring(i, patron.length());
                this.insertar(palabra, i);
            }
            System.out.println("Se ha generado el arbol de prefijos para: " + patron);
            System.out.println("El árbol cuenta con " + cantidadDeInserciones + " sufijos.");
        }
        
    }
    
    
}
