package UT04y05;

import agenciaViajes.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice<T> implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int bacon = Integer.MAX_VALUE;
    public Comparable predecesor;
    public int numBpf;
    public int numBajo;
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
        int bpfTemp = this.numBpf;
        int minBajo = this.numBajo;
        int auxBPF = this.numBpf;
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.numBpf = ++bpfTemp;
                vertAdy.numBajo = bpfTemp;
                auxBPF = vertAdy.bpf(visitados);
                if (auxBPF >= bpfTemp) {
                    bpfTemp = auxBPF;
                }
                minBajo = vertAdy.numBajo;
                if (minBajo < this.numBajo) {
                    this.numBajo = minBajo;
                }
            }

        }
        if (minBajo > this.numBpf) {
            this.isArtPoint = true;
        } else {
            this.numBajo = minBajo;
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
        LinkedList<TVertice> cola = new LinkedList<>();
        cola.add(this);
        while (!(cola.isEmpty())) {
            TVertice aux = cola.pop();
            LinkedList<TAdyacencia> adyacentes = aux.getAdyacentes();
            for (TAdyacencia ad : adyacentes) {
                TVertice destino = ad.getDestino();
                if (!destino.getVisitado()) {
                    destino.setVisitado(true);
                    destino.setBacon(aux.getBacon() + 1);
                    visitados.add(destino);
                    cola.add(destino);
                }
            }

        }
    }

    public int beaBacon(Comparable actor) {
        this.setVisitado(true);
        LinkedList<TVertice> cola = new LinkedList();
        this.setBacon(0);
        cola.add(this);
        while (!cola.isEmpty()) {
            TVertice nodo = cola.remove();
            LinkedList<TAdyacencia> adyacencias = nodo.getAdyacentes();
            for (TAdyacencia ady : adyacencias) {
                TVertice destino = ady.getDestino();
                if (destino.getEtiqueta().equals(actor)) {
                    return nodo.getBacon() + 1;
                }
                if (!destino.getVisitado()) {
                    destino.setVisitado(true);
                    destino.setBacon(nodo.getBacon() + 1);
                    cola.add(destino);
                }
            }
        }
        return -1;
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

    public int getBacon() {
        return this.bacon;
    }

    public void setBacon(int newBacon) {
        this.bacon = newBacon;
    }

    public void puntosDeArticulacion(int[] contador, Collection<TVertice> puntosArticulacion) {
        setVisitado(true);
        numBpf = contador[0]++;
        int cantHijos = 0;
        int mayorBajo = 0;
        numBajo = numBpf;
        for (TAdyacencia ady : this.adyacentes) {
            TVertice destino = ady.getDestino();
            if (!destino.getVisitado()) {
                destino.predecesor = this.etiqueta;
                destino.puntosDeArticulacion(contador, puntosArticulacion);
                if (destino.numBajo < numBajo) {
                    numBajo = destino.numBajo;
                }
                if (mayorBajo < destino.numBajo) {
                    mayorBajo = destino.numBajo;
                }
                cantHijos++;
                // Retroceso.
            } else if (!destino.getEtiqueta().equals(predecesor)) {
                if (destino.numBpf < numBajo) {
                    numBajo = destino.numBpf;
                }
            }
        }
        // Caso de la raíz.
        if (numBpf == 1) {
            if (cantHijos >= 2) {
                puntosArticulacion.add(this);
            }
        } else {
            if (mayorBajo >= numBpf) {
                puntosArticulacion.add(this);
            }
        }
    }

    public boolean conectado(Comparable et) {
        this.visitado = true;
        if (etiqueta.equals(et)) {
            return true;
        }
        return this.adyacentes.stream().anyMatch((ady) -> (!ady.getDestino().getVisitado() && ady.getDestino().conectado(et)));
    }

    public void numBaconDistancia(Collection<TVertice> actores, int maxSaltos) {
        setVisitado(true);
        Queue<TVertice> cola = new LinkedList<>();
        cola.add(this);
        int numNuevo = 0;
        while (!cola.isEmpty()) {
            TVertice x = cola.remove();
            setBacon(numNuevo);
            for (TAdyacencia ady : (LinkedList<TAdyacencia>) x.adyacentes) {
                ady.getDestino().setBacon(x.getBacon() + 1);
                if (!(ady.getDestino().visitado) && (ady.getDestino().getBacon() <= maxSaltos)) {
                    ady.getDestino().setVisitado(true);
                    cola.add(ady.getDestino());
                    actores.add(ady.getDestino());
                }
            }
            numNuevo += 1;
        }
    }

    public void clasificacionTopologica(LinkedList<TVertice> lista) {
        visitado = true;
        for (IAdyacencia adyacente : adyacentes) {
            if (!adyacente.getDestino().getVisitado()) {
                adyacente.getDestino().clasificacionTopologica(lista);
            }
        }
        lista.addFirst(this);
    }

}
