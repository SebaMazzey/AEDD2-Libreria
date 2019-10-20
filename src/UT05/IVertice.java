package UT05;

import java.util.Collection;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ernesto
 */
public interface IVertice {

    TAdyacencia buscarAdyacencia(TVertice verticeDestino);

    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);

    Double obtenerCostoAdyacencia(TVertice verticeDestino);

    TVertice primerAdyacente();

    TVertice siguienteAdyacente(TVertice w);
    
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos);
    
    public boolean tieneCiclo(LinkedList<Comparable> camino);
    
    public void bea(Collection<TVertice> visitados);
    public int bpf(Collection<TVertice> visitados);

    public int getBacon();
    public void setBacon(int newBacon);
    
    public int getBPF();
    public void setBPF(int bpf);
    public int getBajo();
    public void setBajo(int bajo);
    public boolean getArtPoint();
    public void  setArtPoint(boolean statement);
}
