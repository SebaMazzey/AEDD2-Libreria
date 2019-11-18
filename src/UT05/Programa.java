package UT05;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        // cargar grafo con actores y relaciones

        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                "src/UT05/actores.csv",
                "src/UT05/en_pelicula.csv",
                false, TGrafoNoDirigido.class);

        // invocar a numBacon como indica la letra y mostrar en consola el resultado
        
        
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());

        UtilGrafos.imprimirMatriz(matrizCostos, gnd.getVertices());
        
        gnd.bea("Kevin_Bacon");
        
        String[] exit = {
            "John_Goodman "+String.valueOf(gnd.numBacon("John_Goodman")),
            "Tom_Cruise "+String.valueOf(gnd.numBacon("Tom_Cruise")),
            "Jason_Statham "+String.valueOf(gnd.numBacon("Jason_Statham")),
            "Lukas_Haas "+String.valueOf(gnd.numBacon("Lukas_Haas")),
            "Djimon_Hounsou "+String.valueOf(gnd.numBacon("Djimon_Hounsou"))};
        
        System.out.println(exit);
        //ManejadorArchivosGenerico mda = new ManejadorArchivosGenerico();
        //mda.escribirArchivo("src/salida.txt",exit);
        */
        
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                "src/UT05/vectores_1.csv",
                "src/UT05/aristas_1.csv",
                false, TGrafoNoDirigido.class);

            /*Collection<TVertice> artPoints =  gnd.getArtPoints("E");
            System.out.println("Puntos de Articulación: ");
            for(TVertice v : artPoints){
                System.out.println(v.getEtiqueta() + " " + v.getBPF()  + " " + v.getBajo());
            }
            
            System.out.println("\n\nVer TODO: ");
            for(TVertice v : gnd.getVertices().values()){
                System.out.println(v.getEtiqueta() + " " + v.getBPF()  + " " + v.getBajo());
            }*/
            Collection<TVertice> artPoints = gnd.puntosArticulacion("E");
            for(TVertice v : artPoints){
                System.out.println(v.getEtiqueta() + " " + v.getBPF()  + " " + v.getBajo());
            }
    }

}
