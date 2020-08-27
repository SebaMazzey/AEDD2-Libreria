package sufijos;


import java.util.LinkedList;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();
        String patron = "panamabananas";

        for (int i = patron.length()-1; i>=0; i--) {
            String palabra = patron.substring(i,patron.length()-1);
                trie.insertar(palabra, i);
        }
        trie.imprimir();       
    }
}