/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ta04;

import java.util.HashMap;

/**
 *
 * @author AlGo
 */
public class UT03TA04 {

private static final int REPETICIONES = 20;

    public static void main(String[] args){
        // valores por defecto
        HashMap hashMap = new HashMap();
        //Consumo de memoria=1328896 Bytes , tiempo de ejecución =153256400 nanosecs 
        
        
        // cambiamos factor de carga
        HashMap hashMap2 = new HashMap(16, 0.90f);
        //Consumo de memoria=1328896 Bytes , tiempo de ejecución =94186500 nanosecs
        
        HashMap hashMap3 = new HashMap(16, 0.5f);
        //Consumo de memoria=1328896 Bytes , tiempo de ejecución =109197100 nanosecs
        
        
        // cambiamos valor inicial de capacidad de la tabla
        HashMap hashMap4 = new HashMap(20);
        //Consumo de memoria=1328896 Bytes , tiempo de ejecución =114047100 nanosecs
        
        HashMap hashMap5 = new HashMap(500);
        //Consumo de memoria=1328896 Bytes , tiempo de ejecución =78896000 nanosecs

        String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo("src\\ut03\\ta04\\listado-general_desordenado.txt");

        Medible[] medibles = new Medible[5];
        int i = 0;
        medibles[i++] = new MedicionInsertarHashMap(hashMap);
        medibles[i++] = new MedicionInsertarHashMap(hashMap2);
        medibles[i++] = new MedicionInsertarHashMap(hashMap3);
        medibles[i++] = new MedicionInsertarHashMap(hashMap4);
        medibles[i++] = new MedicionInsertarHashMap(hashMap5);
        Medicion mi;
        i = 0;
        Object[] params = {REPETICIONES, palabrasclave};
        String[] lineas = new String[6];
        lineas[i++] = "algoritmo,tiempo,memoria";
        for (Medible m : medibles) {
            mi = m.medir(params);
            mi.print();
            lineas[i++] = mi.getTexto() + "," + mi.getTiempoEjecucion().toString() + "," + mi.getMemoria().toString();

        }

        ManejadorArchivosGenerico.escribirArchivo("src\\ut03\\ta04\\salida3.csv", lineas);
    }
}
