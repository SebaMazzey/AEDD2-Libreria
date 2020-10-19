package UT04y05;


import agenciaViajes.*;
import java.util.Collection;
import java.util.LinkedList;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                "src/agenciaViajes/aeropuertos_2.txt",
                "src/agenciaViajes/conexiones_2.txt",
                false, TGrafoNoDirigido.class);
        
        

    }
}
