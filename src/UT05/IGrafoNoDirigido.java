package UT05;


import java.util.Collection;
import java.util.Map;

public interface IGrafoNoDirigido {

    public Collection <TVertice> bea();
    public Collection <TVertice> bea(Comparable etiquetaOrigen);
    
    public Collection <TVertice> bpf();
    public Collection <TVertice> bpf(Comparable etiquetaOrigen);
    
    public TGrafoNoDirigido Prim();

    public TGrafoNoDirigido Kruskal();
    
    public int numBacon(Comparable actor);
    
    public Collection<TVertice> getArtPoints(Comparable origen);

}
