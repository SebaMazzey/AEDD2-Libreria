/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author AX
 */
public class TArbolTrieTest {

    public TArbolTrieTest(){}
    
    @Test
    public void testBuscar() {
        TArbolTrie trie = new TArbolTrie();
        trie.insertar("banana");
        assertEquals(6, trie.buscar("banana"));
    }
    @Test
    public void testBuscar2() {
        TArbolTrie trie = new TArbolTrie();
        trie.insertar("banana");
        assertEquals(4, trie.buscar("bana"));
    }
    @Test
    public void testBuscar3() {
        TArbolTrie trie = new TArbolTrie();
        trie.insertar("banana");
        assertEquals(1, trie.buscar(""));
    }

    @Test
    public void testPredecir() {
    }

    @Test
    public void testInsertarHash_String() {
    }

    @Test
    public void testInsertarHash_String_int() {
    }

    @Test
    public void testImprimirHash() {
    }

    @Test
    public void testImprimirHashConPosicion() {
    }

    @Test
    public void testBuscarHash() {
    }

    @Test
    public void testPredecirHash() {
    }
    
}
