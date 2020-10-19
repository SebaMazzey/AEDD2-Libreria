package UT04y05;

import agenciaViajes.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    protected TAristas lasAristas = new TAristas();
    private final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        vertices.forEach((vertice) -> {
            insertarVertice(vertice.getEtiqueta());
        });
        aristas.forEach((arista) -> {
            insertarArista(arista);
        });
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     * @param nombreVertice
     * @return
     */
    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Matodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                lasAristas.add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }

        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    /**
     * @return the vertices
     */
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<TVertice> bpf() {
        desvisitarVertices();
        LinkedList<TVertice> resultado = new LinkedList<>();
        vertices.entrySet().stream().map((m) -> m.getValue()).filter((aux) -> (!aux.getVisitado())).forEachOrdered((aux) -> {
            aux.bpf(resultado);
        });
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        desvisitarVertices();
        LinkedList<TVertice> resultado = new LinkedList<>();
        if (vertices.containsKey(etiquetaOrigen)) {
            vertices.get(etiquetaOrigen).bpf(resultado);
        }
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        LinkedList<TVertice> exit = new LinkedList<>();
        if (vertices.containsKey(vertice.getEtiqueta())) {
            vertice.bpf(exit);
        }
        return exit;
    }

    @Override
    public boolean tieneCiclo(TCamino camino) {
        desvisitarVertices();
        boolean salir = false;
        TVertice vertice = camino.getOrigen();
        vertice.setVisitado(true);
        for (Comparable et : camino.getOtrosVertices()) {
            vertice = this.getVertices().get(et);
            if (vertice.getVisitado()) {
                salir = true;
                break;
            }
            vertice.setVisitado(true);
        }
        return salir;
    }

    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        desvisitarVertices();
        LinkedList<Comparable> camino = new LinkedList<>();
        boolean salir = false;
        TVertice vert = this.getVertices().get(etiquetaOrigen);
        if (vert != null) {
            if (!vert.getVisitado() && !salir) {
                salir = vert.tieneCiclo(camino);
            }
        }
        return salir;
    }

    @Override
    public boolean tieneCiclo() {
        desvisitarVertices();
        LinkedList<Comparable> camino = new LinkedList<>();
        boolean salir = false;
        for (TVertice v : vertices.values()) {
            if (!v.getVisitado() && !salir) {
                salir = v.tieneCiclo(camino);
            }
        }
        return salir;
    }

    @Override
    public Double[][] floyd() {
        int tamano = vertices.size();
        Double[][] a = new Double[tamano][tamano];
        Double[][] p = new Double[tamano][tamano];
        Double[][] c = UtilGrafos.obtenerMatrizCostos(vertices);

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                p[i][j] = 0.0;
                a[i][j] = c[i][j];
            }
        }
        for (int k = 0; k < tamano; k++) {
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    if ((a[i][k] + a[k][j]) < a[i][j]) {
                        a[i][j] = a[i][k] + a[k][j];
                        p[i][j] = k + 0.0;

                    }
                }
            }
        }
        return a;
    }

    @Override
    public Comparable centroDelGrafo() {
        Double[][] floydTemp = this.floyd();
        Comparable centro;
        Comparable etiquetaCentro = "";
        Set<Comparable> etiquetas = this.vertices.keySet();
        Comparable[] arrayEtiquetas = new Comparable[floydTemp.length];
        arrayEtiquetas = etiquetas.toArray(arrayEtiquetas);
        Comparable[] excentricidades = new Comparable[floydTemp.length];
        for (int i = 0; i < arrayEtiquetas.length; i++) {
            Comparable exTemp = obtenerExcentricidad(arrayEtiquetas[i]);
            excentricidades[i] = exTemp;
        }
        centro = excentricidades[0];
        for (int i = 0; i < excentricidades.length; i++) {
            Comparable candidato = excentricidades[i];
            if (candidato.compareTo(centro) < 0) {
                centro = candidato;
                etiquetaCentro = arrayEtiquetas[i];
            }
        }
        return etiquetaCentro;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] floydTemp = this.floyd();
        Set<Comparable> etiquetasDeVertices = this.vertices.keySet();
        Comparable[] array = new Comparable[floydTemp.length];
        array = etiquetasDeVertices.toArray(array);
        int numeroColumna = 0;
        for (int c = 0; c < array.length; c++) {
            if (array[c] == etiquetaVertice) {
                numeroColumna = c;
                break;
            }

        }
        Double excentricidad = 0.0;
        for (Double[] floydTemp1 : floydTemp) {
            if (floydTemp1[numeroColumna] > excentricidad) {
                if (floydTemp1[numeroColumna] < Double.MAX_VALUE && floydTemp1[numeroColumna] > 0.0) {
                    excentricidad = floydTemp1[numeroColumna];
                }
            }
        }

        return excentricidad;
    }

    @Override
    public boolean[][] warshall() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(getVertices());
        boolean[][] matrizWarshall = new boolean[matrizCostos.length][matrizCostos.length];
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                matrizWarshall[i][j] = false;

                if (i != j && matrizCostos[i][j] != Integer.MAX_VALUE) {
                    matrizWarshall[i][j] = true;
                }
            }
        }
        for (int k = 0; k < matrizWarshall.length; k++) {
            for (int i = 0; i < matrizWarshall.length; i++) {
                for (int j = 0; j < matrizWarshall.length; j++) {
                    if ((i != k) && (k != j) && (i != j)) {
                        if (!matrizWarshall[i][j]) {
                            matrizWarshall[i][j] = matrizWarshall[i][k] && matrizWarshall[k][j];
                        }
                    }
                }
            }
        }
        return matrizWarshall;
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCaminos todosLosCaminos = new TCaminos();
        this.desvisitarVertices();
        TVertice v = buscarVertice(etiquetaOrigen);
        if (v != null) {
            TCamino caminoPrevio = new TCamino(v);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }

    @Override
    public Double[] dijkstra(Comparable etiqueta) {
        LinkedList<Comparable> S = new LinkedList<>();
        Double[] D = new Double[vertices.size()];
        Double[][] matrizDeCostos = UtilGrafos.obtenerMatrizCostos(vertices);
        S.add(vertices.get(etiqueta).getEtiqueta());
        LinkedList<Comparable> V = new LinkedList<>(vertices.keySet());
        LinkedList<Comparable> ConjuntoDeVertices = new LinkedList<>(vertices.keySet());
        LinkedList<Integer> visitados = new LinkedList<>();

        int indiceDeOrigen = V.indexOf(etiqueta);
        for (int i = 0; i < vertices.size(); i++) {
            D[i] = matrizDeCostos[indiceDeOrigen][i];
        }

        while (!V.isEmpty()) {
            Integer indiceDeW = obtenerMinimo(D, visitados);
            Comparable w = ConjuntoDeVertices.get(indiceDeW);
            S.add(w);
            V.removeAll(S);
            V.forEach((v) -> {
                TVertice verticeV = vertices.get(v);
                int numVinD = ConjuntoDeVertices.indexOf(v);
                Double costoWV = matrizDeCostos[indiceDeW][numVinD];
                if ((D[indiceDeW] + costoWV) < D[numVinD]) {
                    D[numVinD] = D[indiceDeW] + costoWV;
                }
            });
        }
        return D;
    }

    /**
     * Devuelve el número de saltos por nivel hasta llegar al actor a buscar
     * 
     * @param actor
     * @return 
     */
    @Override
    public int numBacon(Comparable actor) {
        desvisitarVertices();
        TVertice kevin = this.getVertices().get("Kevin_Bacon");
        if (kevin != null) {
            System.out.println("Número de bacon de: " + actor);
            if (kevin.getEtiqueta().equals(actor)) {
                return 0;
            }
            return kevin.beaBacon(actor);
        }
        return -1;
    }

    public Collection<TVertice> numBaconDistancia(Comparable actor, int maxSaltos) {
        desvisitarVertices();
        Collection<TVertice> actores = new LinkedList<>();
        TVertice vertice = getVertices().get(actor);

        if (vertice != null) {
            vertice.numBaconDistancia(actores, maxSaltos);
        }
        return actores;
    }

    public Collection<TVertice> clasificacionTopologica(Comparable etiquetaOrigen) {
        desvisitarVertices();
        LinkedList<TVertice> lista = new LinkedList<>();
        if (!tieneCiclo()) {
            TVertice vertice = vertices.get(etiquetaOrigen);
            vertice.clasificacionTopologica(lista);
        }
        desvisitarVertices();
        return lista;
    }

    /**
     * 
     * @param array
     * @param visitados
     * @return 
     */
    public int obtenerMinimo(Double[] array, LinkedList<Integer> visitados) {
        Double min = Double.POSITIVE_INFINITY;
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min && !visitados.contains(i)) {
                min = array[i];
                num = i;
            }
        }
        visitados.add(num);
        return num;
    }
    
    @Override
    public Collection<TVertice> bea() {
        desvisitarVertices();
        LinkedList<TVertice> resultado = new LinkedList<>();
        vertices.entrySet().stream().map((Map.Entry<Comparable, TVertice> m) -> {
            return m.getValue();
        }).filter((aux) -> (!aux.getVisitado())).forEachOrdered((aux) -> {
            aux.bea(resultado);
        });
        return resultado;
    }
    
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

}
