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
        int opcionDeClasificacion = 9; /* ELEGIR ALGORITMO */
//        IGeneradorDatos gdg = new GeneradorDatosGenericos(10); /* TAMAÑO DE DATOS */
        


//        int[] vectorAleatorio = gdg.generarDatosAleatorios();
//        int[] vectorAscendente = gdg.generarDatosAscendentes();
//        int[] vectorDescendente = gdg.generarDatosDescendentes();

//        clasif.clasificar(vectorAleatorio, opcionDeClasificacion);
//
//        Contador contadorAl = new Contador(vectorAleatorio, opcionDeClasificacion);
//        Contador contadorAs = new Contador(vectorAscendente, opcionDeClasificacion);
//        Contador contadorDes = new Contador(vectorDescendente, opcionDeClasificacion);

//        System.out.println("Tiempo Algoritmo Aleatorio: " + contadorAl.Margen() / 1000000.0 + " milisegundos.");
//        System.out.println("Tiempo Algoritmo Ascendente: "+contadorAs.Margen()/1000000.0+" milisegundos.");
//        System.out.println("Tiempo Algoritmo Descendente: "+contadorDes.Margen()/1000000.0+" milisegundos.");

//        for (int i : vectorAleatorio) {
//            System.out.println(i);
//        }

        /* TEST AREA */
        int[] testVector = {97,19,61,07,34,25,82,56};
        clasif.clasificar(testVector, opcionDeClasificacion);
        for (int d : testVector) {
            System.out.print(d + "-");
        }
    }
}
