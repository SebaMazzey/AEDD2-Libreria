
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface INodoTrie {

   void insertar(TDispositivo unDispositivo);
   
   void imprimir();

    /**
     *
     * @param mascaraSubRed
     * @param LinkedList
     */
    void buscarDispositivos(String mascaraSubRed, LinkedList<TDispositivo> dispositivos);
    
    public INodoTrie[] getHijos();
    public TDispositivo getDispositivo();
    public void setDispositivo(TDispositivo dispositivo);
    
}
