
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mati
 */
public class TNodoTrie implements INodoTrie {

    private final INodoTrie[] hijos;
    private TDispositivo dispositivo;

    public TNodoTrie() {
        hijos = new INodoTrie[10];
        dispositivo = null;
    }

    @Override
    public INodoTrie[] getHijos() {
        return hijos;
    }

    @Override
    public TDispositivo getDispositivo() {
        return dispositivo;
    }

    @Override
    public void setDispositivo(TDispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public void insertar(TDispositivo unDispositivo) {
        INodoTrie nodo = this;
        for (int c = 0; c < unDispositivo.getDirIP().length(); c++) {
            if (unDispositivo.getDirIP().charAt(c) != '.') {

                int pos = unDispositivo.getDirIP().charAt(c) - '0';
                if (pos >= 0 && pos < unDispositivo.getDirIP().length()) {
                    if (nodo.getHijos()[pos] == null) {
                        nodo.getHijos()[pos] = new TNodoTrie();
                    }
                    nodo = nodo.getHijos()[pos];

                }

            }
        }
        nodo.setDispositivo(unDispositivo);
    }

    @Override
    public void imprimir() {
        imprimir(this);
    }

    public void imprimir(INodoTrie nodo) {
        if (nodo != null) {
            if (nodo.getDispositivo() != null) {
                System.out.println(nodo.getDispositivo().getDirIP() + " - " + nodo.getDispositivo().getNombre());
            }
            for (int c = 0; c < 10; c++) {
                if (nodo.getHijos()[c] != null) {
                    imprimir(nodo.getHijos()[c]);
                }
            }
        }
    }

    @Override
    public void buscarDispositivos(String mascaraSubRed, LinkedList<TDispositivo> dispositivos) {
        INodoTrie nodo = this;
        for (int c = 0; c < mascaraSubRed.length(); c++) {
            if (mascaraSubRed.charAt(c) != '.') {
                int pos = mascaraSubRed.charAt(c) - '0';
                if (nodo != null && nodo.getHijos() != null && nodo.getHijos()[pos] != null) {
                    nodo = nodo.getHijos()[pos];
                } else {
                    nodo = null;
                }
            }
        }

        generarLinkedList(nodo, dispositivos);
    }

    private void generarLinkedList(INodoTrie nodo, LinkedList<TDispositivo> dispositivos) {
        if (nodo != null) {
            if (nodo.getDispositivo() != null) {
                dispositivos.add(nodo.getDispositivo());
            }
            for (int pos = 0; pos < 10; pos++) {
                if (nodo.getHijos()[pos] != null) {
                    generarLinkedList(nodo.getHijos()[pos], dispositivos);
                }
            }
        }
    }

}
