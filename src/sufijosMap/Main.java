package sufijosMap;

import sufijos.*;
import UT05.UtilGrafos;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String patron = "banana";
        TArbolTrie trie = new TArbolTrie();
        trie.generarArbolSufijos(patron);
        trie.imprimir();
    }   
}
