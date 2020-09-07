/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut02.pd04.indexarlibro;

/**
 *
 * @author AlGo
 */
public class UT02PD04IndexarLibro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String[] libro = ManejadorArchivosGenerico.leerArchivo("src\\ut02\\pd04\\indexarlibro\\libro.txt");
       TArbolTrie trie = new TArbolTrie();
       trie.indizarLibro(libro);
       trie.imprimir();
    }

}
