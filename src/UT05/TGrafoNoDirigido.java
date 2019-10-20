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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        this.desvisitarVertices();
        Collection<TVertice> etiquetas = new LinkedList();
        TVertice verticeOrigen = this.getVertices().get(etiquetaOrigen);
        if (verticeOrigen != null){
            verticeOrigen.setBacon(0);
            verticeOrigen.bea(etiquetas);
        }
        return etiquetas;
    }

    @Override
    public int numBacon(Comparable actor) {
        for(Entry<Comparable,TVertice> en : this.getVertices().entrySet()){
            if(en.getKey().equals(actor)){
                return en.getValue().getBacon();
            }
        }
        return -1;
    }

    @Override
    public Collection<TVertice> getArtPoints(Comparable etiqueta) {
        LinkedList<TVertice> nuevaLista = new LinkedList<>();
        TVertice origen = this.getVertices().get(etiqueta);
        if(origen != null){
            origen.setBPF(1);
            origen.setBajo(1);
            this.desvisitarVertices();
            origen.bpf(nuevaLista);
            if(origen.getAdyacentes().size()<=1){
                origen.setArtPoint(false);
            }
            nuevaLista = new LinkedList<>();
            for(TVertice v:this.getVertices().values()){
                if(v.getArtPoint()){
                    nuevaLista.add(v);
                }
            }
        }
        
        return nuevaLista;
    }
}
