package aedd2.libreria;

import UT02.ITArbolGenerico.*;
import UT03.*;
import UT00.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author AX-Book
 */
public class AEDD2Libreria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        UT02.TArbolGenerico tree = new UT02.TArbolGenerico();
        tree.insertarNodo("Usuarios", "root");
        tree.insertarNodo("System", "root");
        tree.insertarNodo("Windows", "System");
        tree.insertarNodo("Ale", "Usuarios");
        tree.insertarNodo("Victoria", "Usuarios");
        tree.insertarNodo("Games", "Ale");
        tree.insertarNodo("Stuff", "Ale");
        
        tree.listarIndentado();
        
        System.out.println("Buscando Clave Usuarios: " + tree.buscarNodo("Usuarios"));
        System.out.println("Buscando Clave ALGO: " + tree.buscarNodo("ALGO"));
        System.out.println("Buscando Clave Games: " + tree.buscarNodo("Games"));
        
        
        TArbolTrie trie = new TArbolTrie();
        
        trie.insertar("palabra");
        trie.insertar("otra");
        trie.insertar("jamas");
        
        System.out.println( trie.buscar("encontrar"));
        
        trie.insertarHash("palabra");
        trie.insertarHash("otra");
        trie.insertarHash("jamas");
        
        System.out.println( trie.buscarHash("encontrar"));
         */
        
        /*
        ManejadorArchivosGenerico mag = new ManejadorArchivosGenerico();
        String[] lineas = mag.leerArchivo("src/aedd2/libreria/Libro Para PD4.txt");
        TArbolTrie trie = new TArbolTrie();
        TArbolTrie trie2 = new TArbolTrie();
        int contadorPalabras = 0;
        for (String linea : lineas) {
            String[] palabras = linea.split(" ");
            for (String palabra : palabras) {
                if (palabra.length() > 0) {
                    trie.insertarHash(palabra, contadorPalabras);
                    //trie2.insertar(palabras[j]);
                    contadorPalabras++;
                }
            }
        */
            //DOCUMENTAL FOREACH
            /*
            ArrayList<String> linea = new ArrayList<>(Arrays.asList(lineas[i].split(" ")));
            linea.forEach(palabra -> trie.insertarHash(palabra));
            */
        /*}*/
        //trie.imprimirHash();
        //trie.imprimirHashConPosicion();
        //System.out.println("Se realizaron: "+  trie.buscarHash("a") + " comparaciones para hallar.");
        //LinkedList<String> list = trie.predecirHash("sher");
        //list.forEach(palabra -> System.out.println(palabra));
        //LinkedList<String> list2 = trie2.predecirHash("wh");
        //list2.forEach(palabra -> System.out.println(palabra));

        TArbolTrie trieIP = new TArbolTrie();
        trieIP.insertarIP("010.024.007.024-Dispositivo 1");
        trieIP.insertarIP("010.024.007.025-Dispositivo 2");
        trieIP.insertarIP("010.024.007.026-Dispositivo 3");
        trieIP.insertarIP("010.024.007.027-Dispositivo 4");
        trieIP.insertarIP("010.024.008.024-Dispositivo 5");
        trieIP.insertarIP("010.251.008.037-Dispositivo 6");
        trieIP.insertarIP("010.251.008.038-Dispositivo 7");
        trieIP.insertarIP("010.251.008.039-Dispositivo 8");
        
        //trieIP.imprimirIP();
        //System.err.println(trieIP.buscarIP("010.251.008.037"));
        //System.err.println(trieIP.buscarIP("010.251.008.700"));
        trieIP.predecirIP("010.024.007").forEach(ip->System.out.println(ip));
        trieIP.predecirIP("010.024.008").forEach(ip->System.out.println(ip));
        
    }

}
