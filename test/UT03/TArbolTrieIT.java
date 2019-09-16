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
public class TArbolTrieIT {
    
    public TArbolTrieIT() {
    }

    TArbolTrie trie = new TArbolTrie();
        
    @Test
    public void testBuscar() {
        trie.insertar("banana");
        trie.insertar("anana");
        trie.insertar("naranja");
        assertEquals(null, trie.buscar("mandarina"));
    }
    @Test
    public void testBuscar2() {
        trie.insertar("banana");
        trie.insertar("anana");
        trie.insertar("naranja");
        assertNotNull(trie.buscar("anana"));
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

    @Test
    public void testInsertarIP() {
    }

    @Test
    public void testImprimirIP() {
    }

    @Test
    public void testBuscarIP() {
    }

    @Test
    public void testPredecirIP() {
    }
    
}
