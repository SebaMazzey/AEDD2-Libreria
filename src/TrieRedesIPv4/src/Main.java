

import java.util.Collection;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();

       // CARGAR EN EL TRIE LOS DISPOSITIVOS PARTIR DEL ARCHIVO DISPOSITIVOS.TXT
        String[] dispositivosTXT = ManejadorArchivosGenerico.leerArchivo("src/dispositivos.txt");
        for (String p : dispositivosTXT) {
            String[] info = p.split(",");
            TDispositivo dispositivo = new TDispositivo(info[0].trim(), info[1].trim());
            trie.insertar(dispositivo);
        }
        
        trie.imprimir();
        
        String subRed = "141." ; // utilizar el indicado en el archivo "subredes.txt"
        Collection<TDispositivo> dispositivos = trie.buscarDispositivos(subRed);
        String[] salida = new String[dispositivos.size()];
        int i = 0;
        for(TDispositivo dispositivo: dispositivos){
            salida[i] = dispositivo.getNombre()+" - "+dispositivo.getDirIP();
            i++;
        }
        
        // crear el archivo "salida.txt", con los dispositivos(1 por linea) 
        // correspondientes a la subred indicada
        // imprimir Nombre y dirección de  IP, 
        ManejadorArchivosGenerico.escribirArchivo("./src/salida_"+subRed+".txt", salida);
        
        
        
        String subRed2 = "005.116" ; // utilizar el indicado en el archivo "subredes.txt"
        Collection<TDispositivo> dispositivos2 = trie.buscarDispositivos(subRed2);
        String[] salida2 = new String[dispositivos2.size()];
        i = 0;
        for(TDispositivo dispositivo: dispositivos2){
            salida2[i] = dispositivo.getNombre()+" - "+dispositivo.getDirIP();
            i++;
        }
        
        ManejadorArchivosGenerico.escribirArchivo("./src/salida_"+subRed2+".txt", salida2);
        
    }
}