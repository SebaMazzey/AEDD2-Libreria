package UT05;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice<T> implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int bacon = Integer.MAX_VALUE;
    private int bpf;
    private int bajo;
    private boolean isArtPoint = false;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public int bpf(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);
        int bpfTemp = this.getBPF();
        int minBajo = this.getBajo();
        int auxBPF = this.getBPF();
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.setBPF(++bpfTemp);
                vertAdy.setBajo(bpfTemp);
                auxBPF = vertAdy.bpf(visitados);
                if (auxBPF >= bpfTemp) {
                    bpfTemp = auxBPF;
                }
                minBajo = vertAdy.getBajo();
                if (minBajo < this.getBajo()) {
                    this.setBajo(minBajo);
                }
            }

        }
        if (minBajo > this.getBPF()) {
            this.setArtPoint(true);
        } else {
            this.setBajo(minBajo);
        }
        return bpfTemp;
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        setVisitado(true);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                if (vertAdy.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacente);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacente);
                    vertAdy.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacente);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {
        this.setVisitado(true);
        LinkedList<TVertice> retorno = new LinkedList<>();
        retorno.add(this);
        while (!(retorno.isEmpty())) {
            TVertice aux = retorno.pop();
            LinkedList<TAdyacencia> adyacentes = aux.getAdyacentes();
            for (TAdyacencia ad : adyacentes) {
                TVertice destino = ad.getDestino();
                if (!destino.getVisitado()) {
                    destino.setVisitado(true);
                    destino.setBacon(aux.getBacon() + 1);
                    visitados.add(destino);
                    retorno.add(destino);
                }
            }

        }
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TVertice siguiente = null;
        for(TAdyacencia a : this.getAdyacentes()){
            if(a.getDestino().getEtiqueta().equals(w.getEtiqueta())){
                for(TAdyacencia ad : (LinkedList<TAdyacencia>) a.getDestino().getAdyacentes()){
                    if(!ad.getDestino().getVisitado()){
                        siguiente = ad.getDestino();
                        break;
                    }
                }
            }
            if(siguiente!=null){
                break;
            }
        }
        return siguiente;
    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        this.setVisitado(true);
        camino.add(etiqueta);
        boolean salir = false;
        for (TAdyacencia a : this.getAdyacentes()) {
            if (!salir) {
                if (!a.getDestino().getVisitado()) {
                    salir = a.getDestino().tieneCiclo(camino);
                } else if (camino.contains(a.getDestino().getEtiqueta())) {
                    return true;

                }
            }
        }
        return salir;
    }

    @Override
    public int getBacon() {
        return this.bacon;
    }

    @Override
    public void setBacon(int newBacon) {
        this.bacon = newBacon;
    }

    @Override
    public int getBPF() {
        return this.bpf;
    }

    @Override
    public void setBPF(int bpf) {
        this.bpf = bpf;
    }

    @Override
    public int getBajo() {
        return this.bajo;
    }

    @Override
    public void setBajo(int bajo) {
        this.bajo = bajo;
    }

    @Override
    public boolean getArtPoint() {
        return this.isArtPoint;
    }

    @Override
    public void setArtPoint(boolean statement) {
        this.isArtPoint = statement;
    }

    void puntosArticulacion(LinkedList<TVertice> puntosA) {
        this.visitado = true;
        for (TAdyacencia adyacente : adyacentes) {
            TVertice aux = adyacente.getDestino();

            if (!aux.visitado) {
                if (aux.getBajo()<= this.getBPF()){

                    puntosA.add(this);
                }
                aux.puntosArticulacion(puntosA);
            }

        }
    }

    void numerar(LinkedList<TVertice> listaAuxiliar, TVertice padre) {
        this.visitado = true;
        TVertice ultimo = listaAuxiliar.getLast();

        this.setBPF(ultimo.getBPF()+1);
        this.setBajo(ultimo.getBajo()+1);

        listaAuxiliar.add(this);

        for (TAdyacencia adyacente : adyacentes) {
            TVertice hijo = adyacente.getDestino();
            if (!hijo.visitado) {

                hijo.numerar(listaAuxiliar, this);
                this.setBajo(Integer.min(this.getBPF(),Integer.min(hijo.getBajo(), padre.getBPF())));
            }
            if (!hijo.equals(padre)) {
                this.setBajo(Integer.min(this.getBajo(), hijo.getBajo()));
            }
        }
    }
    
    public int numBacon(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);
        Queue<TVertice> cola = new LinkedList<>();
        cola.add(this);
        if(etiqueta.equals("Kevin_Bacon")){
            setBacon(0);
            return getBacon();
        }
        int numNuevo = 0;
        while(!cola.isEmpty()){
            TVertice x = cola.remove();
            setBacon(numNuevo);
            for(TAdyacencia ady: (LinkedList<TAdyacencia>) x.adyacentes){
                if(!ady.getDestino().visitado){
                    ady.getDestino().setBacon(x.getBacon()+1);
                    ady.getDestino().setVisitado(true);
                    visitados.add(ady.getDestino());
                    cola.add(ady.getDestino());
                    if(ady.getDestino().etiqueta.equals("Kevin_Bacon")){
                        return ady.getDestino().getBacon();
                    }
                }
            }
            numNuevo += 1;
        }
        return -1;
    }

    void numBaconDistancia(Collection<TVertice> actores, int maxSaltos) {
        setVisitado(true);
        Queue<TVertice> cola = new LinkedList<>();
        cola.add(this);  
        int numNuevo = 0;
        while(!cola.isEmpty()){
            TVertice x = cola.remove();
            setBacon(numNuevo);
            for(TAdyacencia ady: (LinkedList<TAdyacencia>) x.adyacentes){
                ady.getDestino().setBacon(x.getBacon()+1);
                if(!(ady.getDestino().visitado) && (ady.getDestino().getBacon() <= maxSaltos)){
                    ady.getDestino().setVisitado(true);
                    cola.add(ady.getDestino());
                    actores.add(ady.getDestino());
                }
            }
            numNuevo += 1;
        }
    }



}
