/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT04;

/**
 *
 * @author AX-Book
 */
public class TAdyacencia implements ITAdyacencia {
    private final TNodoGrafo origen, destino;
    int costo = 0;
    
    public TAdyacencia(TNodoGrafo Origen, TNodoGrafo Destino, int Costo){
        this.origen = Origen;
        this.destino = Destino;
        this.costo = Costo;
    }
    
    @Override
    public TNodoGrafo getOrigen() {
        return this.origen;
    }

    @Override
    public TNodoGrafo getDestino() {
        return this.destino;
    }

    @Override
    public int getCosto() {
        return this.costo;
    }
    
}
