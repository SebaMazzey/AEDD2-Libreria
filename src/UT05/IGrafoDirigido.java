package UT05;


import java.util.Collection;
import java.util.Map;

public interface IGrafoDirigido {

    Collection<TVertice> bpf();
    Collection <TVertice> bea();
    Collection<TVertice> bpf(TVertice vertice);
    Collection<TVertice> bpf(Comparable etiquetaOrigen);
    Comparable centroDelGrafo();
    Double[][] floyd();
    boolean[][] warshall();
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino);
    public Double[] dijkstra (Comparable etiqueta);
    public int numBacon(Comparable actor);
    
    
    boolean insertarArista(TArista arista);
    boolean insertarVertice(TVertice vertice);
    
    boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino);
    boolean eliminarVertice(Comparable nombreVertice);

    boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino);
    boolean existeVertice(Comparable unaEtiqueta);
    public void desvisitarVertices();
    
    Comparable obtenerExcentricidad(Comparable etiquetaVertice);

    public Map<Comparable, TVertice> getVertices();

    public boolean tieneCiclo(TCamino camino);

    public boolean tieneCiclo(Comparable etiquetaOrigen);

    public boolean tieneCiclo();

    
}
