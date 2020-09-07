/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AlGo
 */
public class TNodoTrieTest {
    
    private TArbolTrie loadTrie(){
        String[] dispositivosTXT = ManejadorArchivosGenerico.leerArchivo("src/dispositivos.txt");
        TArbolTrie trie = new TArbolTrie();
        for (String p : dispositivosTXT) {
            String[] info = p.split(",");
            TDispositivo dispositivo = new TDispositivo(info[0].trim(), info[1].trim());
            trie.insertar(dispositivo);
        }
        return trie;
    }
    @Test
    public void testBuscarDispositivos0() {
        TArbolTrie trie = loadTrie();
        Collection<TDispositivo> result = trie.buscarDispositivos("200.");
        assertEquals(1, result.size());
    }
    @Test
    public void testBuscarDispositivos1() {
        TArbolTrie trie = loadTrie();
        Collection<TDispositivo> result = trie.buscarDispositivos("005.116.078");
        assertEquals(4, result.size());
    }
    @Test
    public void testBuscarDispositivos2() {
        TArbolTrie trie = loadTrie();
        Collection<TDispositivo> result = trie.buscarDispositivos("300.");
        assertEquals(0, result.size());
    }
    
}
