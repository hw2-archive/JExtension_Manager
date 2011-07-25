/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exman.common;

/**
 *
 * @author Giuseppe
 */

public class FieldModelStruct {
        // TODO pos da implementare
        private int pos;
        private String name;
        private String field;
        private boolean isRicerca;
        private boolean isTableField;
        private int size;

        /**
         * Crea la struttura
         * @param pos indice ordinale ( generato dall'enumeratore )
         * @param name nome "visuale" del campo
         * @param field nome del campo db ( unused )
         * @param isRicerca e' un campo utilizzato nella ricerca
         * @param isTableField e' un campo utilizzato nella tabella
         * @param size grandezza della colonna relativa al campo nella tabella ( se 0 viene usato il valore originale )
         */
        public FieldModelStruct(int pos,String name,String field,boolean isRicerca,boolean isTableField,int size) {
            this.pos = pos;
            this.name = name;
            this.field = field;
            this.isRicerca = isRicerca;
            this.isTableField = isTableField;
            this.size = size;
        }

        public int getPos() {
            return pos;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public boolean isRicerca() {
            return isRicerca;
        }

        public void setRicerca(boolean isRicerca) {
            this.isRicerca = isRicerca;
        }

        public boolean isTableField() {
            return isTableField;
        }

        public void setTableField(boolean isTableField) {
            this.isTableField = isTableField;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
}
