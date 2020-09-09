package ut03.ta04;


import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FIT
 */
public class MedicionInsertarHashMap extends Medible{

    HashMap hashMap;
    
    public MedicionInsertarHashMap (HashMap hashMap){
        this.hashMap = hashMap;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                hashMap.put(palabra,palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.hashMap;
    }
    
}
