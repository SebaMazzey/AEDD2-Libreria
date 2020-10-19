package agenciaViajes;


import java.util.Collection;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

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
        
        // devuelve un nuevo grafo, que es el Arbol Abarcador de Costo M�nimo
        int costoPrim = 0;
        LinkedList<Comparable> VerticesU = new LinkedList<>();
        LinkedList<Comparable> VerticesV = new LinkedList<>();
        TAristas AristasAAM = new TAristas();

        if (getVertices().isEmpty()) {
            return null;
        }

        // armar la lista VerticesV de trabajo
        VerticesV.addAll(getVertices().keySet());

        // pasar el primero de V a U
        // sacar de VerticesV y agregarlo a VerticesU
        VerticesU.add(VerticesV.removeFirst());

        //boolean vaciaV = VerticesV.esVacia();
        while (!VerticesV.isEmpty()) {
            TArista menorArista = lasAristas.buscarMin(VerticesU, VerticesV);
            AristasAAM.add(menorArista);
            VerticesV.remove(menorArista.getEtiquetaDestino());
            VerticesU.add(menorArista.getEtiquetaDestino());
            costoPrim += menorArista.getCosto();
        }

        //A partir de los vertices del grafo (Vertices.Values????)  las aristas en AristasAAM armar el grafo nuevoGrafo y retornarlo
        //---------COMPLETAR ALGORITMO--------
        LinkedList<TVertice> verticesCopia = new LinkedList<>();
        getVertices().keySet().forEach((v -> verticesCopia.add(new TVertice((v)))));
        // crear nuevoGrafo 
        return new TGrafoNoDirigido(verticesCopia, AristasAAM);
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        LinkedList<TVertice> verticesCopia = new LinkedList<>();
        getVertices().keySet().forEach((v -> verticesCopia.add(new TVertice((v)))));
        TGrafoNoDirigido grafoFinal = new TGrafoNoDirigido(verticesCopia, new LinkedList<>());
        int cantVertices = verticesCopia.size();
        int cantAristas = 0;
        TAristas copiaAristas = (TAristas) lasAristas.clone();

        copiaAristas.sort((o1, o2) -> {
            return (int) (o1.costo - o2.costo); //To change body of generated lambdas, choose Tools | Templates.
        });
        for (TArista ari : copiaAristas) {
            if (!grafoFinal.conectado(ari.getEtiquetaOrigen(), ari.getEtiquetaDestino())) {
                grafoFinal.insertarArista(ari);
                cantAristas++;
            }
            if (cantAristas == cantVertices - 1) {
                return grafoFinal;
            }
        }
        return grafoFinal;
    }

    private boolean conectado(Comparable etO, Comparable etD) {
        desvisitarVertices();
        TVertice origen = this.getVertices().get(etO);
        if (origen != null) {
            return origen.conectado(etD);
        }
        return false;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        desvisitarVertices();
        TVertice origen = this.getVertices().get(etiquetaOrigen);
        LinkedList<TVertice> resultado = new LinkedList<>();
        if (origen != null) {
            origen.bpf(resultado);
        }

        return resultado;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    public Collection<TVertice> puntosDeArticulacion() {
        desvisitarVertices();
        Collection<TVertice> puntosArticulacion = new LinkedList<>();
        int[] contador = new int[1];
        for (TVertice v : getVertices().values()) {
            if (!v.getVisitado()) {
                contador[0] = 1;
                v.predecesor = v.getEtiqueta();
                v.puntosDeArticulacion(contador, puntosArticulacion);
            }
        }

        return puntosArticulacion;
    }

    public Collection<TVertice> puntosDeArticulacion(Comparable etOrigen) {
        TVertice primero = this.getVertices().get(etOrigen);
        Collection<TVertice> puntosArticulacion = new LinkedList<>();
        if (primero != null) {
            desvisitarVertices();
            int[] contador = new int[1];
            contador[0] = 1;
            primero.predecesor = primero.getEtiqueta();
            primero.puntosDeArticulacion(contador, puntosArticulacion);

        }

        return puntosArticulacion;
    }
}
