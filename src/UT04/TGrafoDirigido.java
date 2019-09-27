/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT04;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author AX-Book
 */
public class TGrafoDirigido implements ITGrafoDirigido {

    private final HashMap<Comparable, TNodoGrafo> vertices = new HashMap<>();

    @Override
    public TNodoGrafo buscar(Comparable clave) {
        return vertices.get(clave);
    }

    @Override
    public boolean agregarAdyacencia(Comparable origen, Comparable destino, int costo) {
        if (comprobarExistencia(origen) && comprobarExistencia(destino)) {
            return vertices.get(origen).agregarAdyacencia(vertices.get(destino), costo);
        }
        return false;
    }

    @Override
    public boolean agregarVertice(TNodoGrafo vertice) {
        if (comprobarExistencia(vertice.etiqueta)) {
            return false;
        }
        vertices.put(vertice.etiqueta, vertice);
        return true;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable origen, Comparable destino) {
        if (comprobarExistencia(origen)) {
            return vertices.get(origen).eliminarAdyacencia(destino);
        }
        return false;
    }

    private boolean comprobarExistencia(Comparable etiqueta) {
        return vertices.containsKey(etiqueta);
    }

    @Override
    public void imprimir() {
        if (!vertices.isEmpty()) {
            TNodoGrafo nodo;
            for (Entry<Comparable, TNodoGrafo> entry : vertices.entrySet()) {
                nodo = entry.getValue();
                if (!nodo.visitado) {
                    nodo.imprimir();
                }
            }
            for (Entry<Comparable, TNodoGrafo> entry : vertices.entrySet()) {
                nodo = entry.getValue();
                nodo.visitado = false;
            }
        }
    }
}
