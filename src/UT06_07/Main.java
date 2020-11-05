package UT06_07;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tomas Abreu
 */
public class Main {

    public static final int METODO_CLASIFICACION_BUBBLESORT = 1;
    public static final int METODO_CLASIFICACION_INSERTSORT = 2;
    public static final int METODO_CLASIFICACION_SHELLSORT = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg300 = new GeneradorDatosGenericos(300);
        GeneradorDatosGenericos gdg10000 = new GeneradorDatosGenericos(10000);
        int[] vectorAleatorio300 = gdg300.generarDatosAleatorios();
        int[] vectorAscendente300 = gdg300.generarDatosAscendentes();
        int[] vectorDescendente300 = gdg300.generarDatosDescendentes();

        int[] vectorAleatorio10000 = gdg10000.generarDatosAleatorios();
        int[] vectorAscendente10000 = gdg10000.generarDatosAscendentes();
        int[] vectorDescendente10000 = gdg10000.generarDatosDescendentes();

        for (int i = 1; i <= 4; i++) {
            System.out.println("Caso N°" + i);
            int[] aleatorioCopia = vectorAleatorio300.clone();
            int[] ascendenteCopia = vectorAscendente300.clone();
            int[] descendenteCopia = vectorDescendente300.clone();

            long initAs = System.nanoTime();
            clasif.clasificar(ascendenteCopia, i);
            long finAs = System.nanoTime();
            System.out.println("Tiempo Ascendente = " + (finAs - initAs));

            long initDes = System.nanoTime();
            clasif.clasificar(descendenteCopia, i);
            long finDes = System.nanoTime();
            System.out.println("Tiempo Descendente = " + (finDes - initDes));

            long initAl = System.nanoTime();
            clasif.clasificar(aleatorioCopia, i);
            long finAl = System.nanoTime();
            System.out.println("Tiempo Aleatorio = " + (finAl - initAl));
        }
    }
}
