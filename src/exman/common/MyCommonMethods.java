/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exman.common;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author Giuseppe
 */
public class MyCommonMethods {

    /**
     * Recupera l'indice del vettoriale della lista applicata al modello della tabella
     *
     * @param jTable    la tabella da cui recuperare l'indice
     * @param selectedRow   la riga selezionata
     * @return -1 se la riga non esiste, altrimenti restituisce il corrispondente indice del vettore
     */
    public static int getListIndexFromTable(javax.swing.JTable jTable,int selectedRow) {
        int modelRow = 0;

        if(selectedRow < 0 || selectedRow >= jTable.getRowCount())
                return -1;

        int viewRow = selectedRow;

        if (viewRow >= 0) {
            modelRow = jTable.convertRowIndexToModel(viewRow);
        }

        return modelRow;
    }
    
    /**
     * Metodo per clonare una lista
     * 
     * @param dest   lista di destinazione
     * @param src    lista da cui copiare i dati
     */
    private <T> void cloneList(List<T> dest, List<? extends T> src) {
        dest.clear();

        // Tipo Aggiungi
        dest.addAll(src);
        
        // Tipo Copy
        //Collections.copy(dest,src);

        // Tipo Clone ( esegue un copyOf )
        //ArrayList<T> tmp  = (ArrayList<T>) src;
        //dest = (List<T>) tmp.clone();
        
        // Passaggio lista al nuovo array ( esegue un copyOf )
        // dest = new ArrayList<T>(src);
    }

    public static void setWindowCenterPosition(Frame frame) {
        setWindowCenterPosition((Window)frame);
    }

    public static void setWindowCenterPosition(Dialog frame) {
        setWindowCenterPosition((Window)frame);
    }

    /**
     *  Imposta la posizione di una qualsiasi finestra al centro dello schermo
     * 
     * @param frame il frame da centrare
     */
    public static void setWindowCenterPosition(Window frame)
    {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-frame.getHeight())/2, (screenSize.height-frame.getWidth())/2, frame.getWidth(), frame.getHeight());
    }

    /**
     *  Effettua un confronto dei dati da una lista all'altra e restituisce una nuova lista con tutti i dati
     *  di "comparator" esclusi quelli di "list".
     *
     * @param <T>
     * @param list la lista contenente i dati da cercare
     * @param comparator lista che contiene i dati per effettuare la ricerca
     * @return una nuova lista che a'� il risultato dell'esclusione dei dati di list dal comparator
     */
    public <T extends Comparable<T>> List<T> listBinaryExclusion(List<T> list,List<T> comparator) {
        List<T> result = new ArrayList<T>();
        List<T> copiedList = new ArrayList<T>();
        
        cloneList(copiedList,list);
        if (list != null) {
            Collections.sort(copiedList); // viene ordinata la copia ma non la lista originale
            for (T D: comparator)
                if(Collections.binarySearch(copiedList, D, null)<0)
                    result.add(D);
        }
        return result;
    }

    /**
     *  Per Debug: aumenta le dimensioni
     *  di una lista per testare la gestione della memoria.
     *
     * @param <T>
     * @param list lista da ingrandire
     * @param multipler moltiplica la dimensione della lista per il valore passato
     */
    public  <T> void ListCacheBenchmark(List<T> list,int multipler) {
        List <T> copy = new ArrayList<T>();
        cloneList(copy,list);
        
        for (int i=0; i<multipler;i++)
            list.addAll(copy);
    }

    /**
     *  Mostra l'internal frame o la finestra esterna
     * 
     * @param iFrame
     * @param frame
     * @param internal
     */
    public static void setWindowMode(JInternalFrame iFrame, JFrame frame,boolean isInternal) {
        // mostra una delle due finestre
        iFrame.setVisible(isInternal);
        frame.setVisible(!isInternal);
        // setta il contentpane all'internal frame o al frame esterno
        Container cPane = frame.getContentPane();
        if (isInternal)
            iFrame.setContentPane(cPane);
        else
            frame.setContentPane(cPane);
        // il frame esterno necessita di un aggiornamento
        frame.getRootPane().updateUI();
    }

}
