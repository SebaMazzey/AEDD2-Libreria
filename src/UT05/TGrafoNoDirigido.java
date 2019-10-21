package UT05;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 *
 * @author AX-Book
 */
public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        Collection<Comparable> universo = new LinkedList<>();
        Collection<Comparable> vertices = getVertices().keySet();
        TGrafoNoDirigido grafo = new TGrafoNoDirigido(this.getVertices().values(), new TAristas());
        universo.add(getLasAristas().getFirst().getEtiquetaOrigen());
        while (!vertices.isEmpty()) {
            TArista arista = lasAristas.buscarMin(universo, vertices);
            universo.add(arista.getEtiquetaDestino());
            grafo.insertarArista(arista);
            grafo.lasAristas.add(arista);
            vertices.removeAll(universo);
        }
        return grafo;
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TGrafoNoDirigido arbolAbarcador = new TGrafoNoDirigido(this.getVertices().values(), new TAristas());
        Collection<Comparable> clavesVertices = this.getVertices().keySet();
        TAristas ConjuntoSeleccion = (TAristas) this.lasAristas.clone();
        boolean existeCamino;
        TArista aristaMinima;
        while (arbolAbarcador.lasAristas.size() < this.getVertices().size() - 1) {
            aristaMinima = ConjuntoSeleccion.buscarMin(clavesVertices, clavesVertices);
            ConjuntoSeleccion.remove(aristaMinima);
            existeCamino = arbolAbarcador.todosLosCaminos(aristaMinima.etiquetaOrigen, aristaMinima.etiquetaDestino).getCaminos().size() > 0;
            if (!existeCamino) {
                arbolAbarcador.insertarArista(aristaMinima);
                arbolAbarcador.lasAristas.add(aristaMinima);
            }
        }
        return arbolAbarcador;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        this.desvisitarVertices();
        Collection<TVertice> etiquetas = new LinkedList();
        TVertice verticeOrigen = this.getVertices().get(etiquetaOrigen);
        if (verticeOrigen != null) {
            verticeOrigen.setBacon(0);
            verticeOrigen.bea(etiquetas);
        }
        return etiquetas;
    }

    @Override
    public int numBacon(Comparable actor) {
        for (Entry<Comparable, TVertice> en : this.getVertices().entrySet()) {
            if (en.getKey().equals(actor)) {
                return en.getValue().getBacon();
            }
        }
        return -1;
    }

    @Override
    public Collection<TVertice> getArtPoints(Comparable etiqueta) {
        TVertice vertRaiz = this.getVertices().get(etiqueta);
        LinkedList<TVertice> puntosA = new LinkedList<>();

        if (vertRaiz != null) {
            this.numerar(etiqueta);
            vertRaiz.puntosArticulacion(puntosA);
            puntosA.remove(etiqueta);
        }
        return puntosA;

    }

    public LinkedList<TVertice> puntosArticulacion(){
        Comparable raiz = this.lasAristas.peek().etiquetaOrigen;
        this.numerar(raiz);
        TVertice vertRaiz = this.getVertices().get(raiz);

        LinkedList<TVertice> puntosA = new LinkedList<>();
        vertRaiz.puntosArticulacion(puntosA);
        puntosA.remove(raiz);

        return puntosA;
    }
    
    public void numerar(Comparable etiqueta) {
        Comparable raiz = this.lasAristas.peek().etiquetaOrigen;
        TVertice vertRaiz = this.getVertices().get(raiz);

        LinkedList<TVertice> listaAuxiliar = new LinkedList<>();
        vertRaiz.setBajo(0);

        listaAuxiliar.add(vertRaiz);
        vertRaiz.numerar(listaAuxiliar, vertRaiz);

        this.desvisitarVertices();
    }

    @Override
    public Collection<TVertice> AAM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
