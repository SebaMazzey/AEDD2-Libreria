package trieTelefonos;

import sufijos.*;
import UT05.UtilGrafos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TArbolTrie trie = new TArbolTrie();
        String[] telefonos = ManejadorArchivosGenerico.leerArchivo("src\\trieTelefonos\\abonados.txt");

        for(String telefono : telefonos){
            String[] data = telefono.split(",");
            Abonado abonado = new Abonado(data[1], data[0]);
            trie.insertar(abonado);
        }
        
        String[] parametros = ManejadorArchivosGenerico.leerArchivo("src\\trieTelefonos\\codigos1.txt"); 
        StringBuilder prefijo = new StringBuilder();
        for(String s : parametros){
            prefijo.append(s.split(":")[1].trim());
        }
        
        ArrayList<String> busqueda = new ArrayList(trie.predecir(prefijo.toString()));
        String[] lineas = busqueda.toArray(new String[busqueda.size()]);
        
        ManejadorArchivosGenerico.escribirArchivo("src\\trieTelefonos\\salida.txt", lineas);
        //trie.imprimir();

//        System.out.println("099123a".charAt(6) - '0');
    }   
}
