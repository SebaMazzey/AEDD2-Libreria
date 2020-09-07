
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mati
 */
public class TArbolTrie implements IArbolTrie {

    private INodoTrie raiz;
    
    @Override
    public Collection<TDispositivo> buscarDispositivos(String mascaraSubRed) {
        if (raiz != null) {
            LinkedList<TDispositivo> dispositivos = new <TDispositivo>LinkedList();
            raiz.buscarDispositivos(mascaraSubRed, dispositivos);
            return dispositivos;
        }else{
            return null;
        }
    }
        

    @Override
    public void insertar(TDispositivo unDispositivo) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(unDispositivo);
    }
    
    
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }
    
}
