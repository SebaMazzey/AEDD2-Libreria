/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT04;

import java.util.LinkedList;

/**
 *
 * @author AX-Book
 */
public class TNodoGrafo<T> implements ITNodoGrafo{
    final Comparable etiqueta;
    private final LinkedList<TAdyacencia> adyacentes = new LinkedList<>();
    private final T dato;
    boolean visitado;
    
    public TNodoGrafo(Comparable clave, T dato){
        this.etiqueta = clave;
        this.dato = dato;
        this.visitado = false;
    }
    
    @Override
    public boolean agregarAdyacencia(TNodoGrafo destino, int costo) {
        if (!adyacentes.stream().noneMatch((a) -> (a.getDestino().equals(destino.etiqueta)))) {
            return false;
        }
        TAdyacencia a = new TAdyacencia(this,destino,costo);
        adyacentes.add(a);
        return true;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable destino) {
        for(TAdyacencia a : adyacentes){
            if(a.getDestino().etiqueta.equals(destino)){
                adyacentes.remove(a);
                return true;
            }
        }
        return false;
    }

    @Override
    public void imprimir() {
        this.visitado = true;
        System.out.println(this.etiqueta);
        TNodoGrafo aux;
        for(TAdyacencia ad : this.adyacentes){
            aux = ad.getDestino();
            if(!aux.visitado){
                aux.imprimir();
            }
        }
    }
    
}
