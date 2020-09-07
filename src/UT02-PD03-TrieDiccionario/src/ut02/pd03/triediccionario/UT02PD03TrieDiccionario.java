/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut02.pd03.triediccionario;

/**
 *
 * @author AlGo
 */
public class UT02PD03TrieDiccionario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] lineas = ManejadorArchivosGenerico.leerArchivo("src\\ut02\\pd03\\triediccionario\\PalabrasIndice.txt");
        TArbolTrie trie = new TArbolTrie();
        for(String linea: lineas){
            trie.insertar(linea);
        }
        
        trie.imprimir();
        System.out.println(trie.buscar("noon"));
    }
    
}
