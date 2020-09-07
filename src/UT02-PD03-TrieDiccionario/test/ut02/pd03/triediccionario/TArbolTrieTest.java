/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut02.pd03.triediccionario;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AlGo
 */
public class TArbolTrieTest {

    private TArbolTrie buildTrie() {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo("src\\ut02\\pd03\\triediccionario\\PalabrasIndice.txt");
        TArbolTrie trie = new TArbolTrie();
        for (String linea : lineas) {
            trie.insertar(linea);
        }
        return trie;
    }

    @Test
    public void testBuscar0() {
        TArbolTrie trie = buildTrie();
        assertEquals(0, trie.buscar("zzzzzzz"));
    }

    @Test
    public void testBuscar1() {
        TArbolTrie trie = buildTrie();
        assertEquals(4, trie.buscar("noon"));
    }

    @Test
    public void testBuscar2() {
        TArbolTrie trie = buildTrie();
        assertEquals(4, trie.buscar("sofa"));
    }

}
