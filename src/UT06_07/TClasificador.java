package UT06_07;

import java.lang.reflect.Array;
import java.util.Random;
import sun.tools.jar.resources.jar;

public class TClasificador {

    public static final int METODO_CLASIFICACION_BUBBLESORT = 1;
    public static final int METODO_CLASIFICACION_INSERTSORT = 2;
    public static final int METODO_CLASIFICACION_SHELLSORT = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;

    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        if (datosParaClasificar == null) {
            return null;
        }
        iterationPrint(datosParaClasificar, 0);
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_BUBBLESORT:
                return ordenarPorBurbuja(datosParaClasificar);
            case METODO_CLASIFICACION_INSERTSORT:
                return ordenarPorInsercion(datosParaClasificar);
            case METODO_CLASIFICACION_SHELLSORT:
                return ordenarPorShell(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORT:
                return ordenarPorQuickSort(datosParaClasificar);
            case METODO_CLASIFICACION_HEAPSORT:
                return ordenarPorHeapSort(datosParaClasificar);
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    public boolean estaOrdenado(int[] vectorAVerificar) {
        for (int i = 0; i < vectorAVerificar.length - 1; i++) {
            if (vectorAVerificar[i] > vectorAVerificar[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void iterationPrint(int[] arr, int iter) {
        System.out.println("Iteración: " + iter);
        String salida = "";
        for (int d : arr) {
            salida += d + "-";
        }
        System.out.println(salida.substring(0, salida.length() - 1));
    }

    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion, boolean cascara) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_BUBBLESORT:
                if (cascara) {
                    return ordenarPorCascara(datosParaClasificar);
                } else {
                    return ordenarPorBurbuja(datosParaClasificar);
                }
            case METODO_CLASIFICACION_INSERTSORT:
                if (cascara) {
                    return ordenarPorCascara(datosParaClasificar);
                } else {
                    return ordenarPorInsercion(datosParaClasificar);
                }
            case METODO_CLASIFICACION_SHELLSORT:
                if (cascara) {
                    return ordenarPorCascara(datosParaClasificar);
                } else {
                    return ordenarPorShell(datosParaClasificar);
                }
            case METODO_CLASIFICACION_QUICKSORT:
                if (cascara) {
                    return ordenarPorCascara(datosParaClasificar);
                } else {
                    return ordenarPorQuickSort(datosParaClasificar);
                }
            case METODO_CLASIFICACION_HEAPSORT:
                if (cascara) {
                    return ordenarPorCascara(datosParaClasificar);
                } else {
                    return ordenarPorHeapSort(datosParaClasificar);
                }
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return null;
    }

    protected int[] ordenarPorCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    /*INSERCION DIRECTA*/
    public int[] ordenarPorInsercion(int[] datosParaClasificar) {
        for (int i = 1; i < datosParaClasificar.length; i++) {
            int j = i - 1;
            while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) {
                intercambiar(datosParaClasificar, j, j + 1);
                j--;
            }
            iterationPrint(datosParaClasificar, i);
        }
        return datosParaClasificar;
    }

    /*QUICKSORT*/
    private void quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;
        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < entrada[posicionPivote]) && (izquierda < j)) {
                    izquierda++;
                }
                while ((entrada[posicionPivote] < entrada[derecha]) && (derecha > i)) {
                    derecha--;
                }
                
                if (izquierda <= derecha) {
                    intercambiar(entrada, izquierda, derecha);
                    izquierda++;
                    derecha--;
                }
            }
            System.out.println("\nPivote: " + entrada[posicionPivote]);
            iterationPrint(entrada, quickProf++);
            
            if (i < derecha) {
                quicksort(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksort(entrada, izquierda, j);
            }
        }
    }
    private int quickProf = 1;
    
    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    public int encuentraPivote(int izquierda, int derecha, int[] entrada) {
        return (int) (izquierda + derecha) / 2;
    }

    /*SHELLSORT*/
    public int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 10, 3, 1};

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j
                                + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);

                        }
                        j = j - inc;
                    }
                    iterationPrint(datosParaClasificar, i);
                }
            }
        }
        return datosParaClasificar;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }
        for (int i = datosParaClasificar.length - 1; i > 0; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

    private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int r = primero;
            while (r <= ultimo / 2) {
                if (ultimo == 2 * r) { //r tiene un hijo solo
                    if (datosParaClasificar[r] < datosParaClasificar[r * 2]) {
                        intercambiar(datosParaClasificar, r, 2 * r);
                        r *= 2;
                    } else {
                        r = ultimo;
                    }
                } else { //r tiene 2 hijos
                    int posicionIntercambio = 0;
                    if (datosParaClasificar[2 * r] < datosParaClasificar[2 * r + 1]) {
                        posicionIntercambio = 2 * r + 1;
                    } else {
                        posicionIntercambio = 2 * r;
                    }
                    if (datosParaClasificar[r] < datosParaClasificar[posicionIntercambio]) {
                        intercambiar(datosParaClasificar, r, posicionIntercambio);
                        r = posicionIntercambio;
                    } else {
                        r = ultimo;
                    }
                }
            }
        }
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }
}
