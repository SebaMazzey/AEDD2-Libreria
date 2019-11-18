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
public class Contador {
    int[] vectorOriginal;
    int tipo;
    long tiempoCascara;
    long tiempoAlgoritmo;
    double tiempoResolucion;
    TClasificador clasificador = new TClasificador();
    
    public Contador(int[] vectorOriginal,int tipo){
        this.tiempoResolucion = vectorOriginal.length*0.01;
        this.vectorOriginal = vectorOriginal;
        this.tipo = tipo;
    }
    public void CalcularAlgoritmo(){
        long t1 = System.nanoTime();
        long total = 0;
        int cantLlamadas = 0;
        while (total<tiempoResolucion){
            cantLlamadas++;
            int[] datosCopias = vectorOriginal.clone();
            clasificador.clasificar(datosCopias, tipo, false);
            long t2 = System.nanoTime();
            total = t2-t1;
        }
        tiempoAlgoritmo = total/cantLlamadas;
    }
    public long Margen(){
        CalcularAlgoritmo();
        CalcularCascara();
        return tiempoAlgoritmo-tiempoCascara;
    }
    public void CalcularCascara(){
        long t1 = System.nanoTime();
        long total = 0;
        int cantLlamadas = 0;
        while (total<tiempoResolucion){
            cantLlamadas++;
            int[] datosCopias = vectorOriginal.clone();
            clasificador.clasificar(datosCopias, tipo, true);
            long t2 = System.nanoTime();
            total = t2-t1;
        }
        tiempoCascara = total/cantLlamadas;
    }
}
