/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.main.model;

import hw2.common.FieldModel;
import hw2.common.FieldModelStruct;
import hw2.common.MyDBMethods;

/**
 *
 * @author Giuseppe
 */
public class FieldsModelDatabases extends FieldModel {

    public enum emDatabases {
        // param1: nome (nome da visualizzare)
        // param2: campo ( nome del campo)
        // param3: isRicerca ( se utilizzarlo nelle ricerche)
        // param4: isTableField ( se utilizzarlo nelle tabelle)
        // param5: larghezza colonna, 0 default

        ID("ID", "id", true, false, 0),
        NOME("Name", "TABLE_NAME", true, true, 0);

        private emDatabases(String name, String field, boolean isRicerca, boolean isTableField, int size) {
            this.struct = new FieldModelStruct(this.ordinal(), name, field, isRicerca, isTableField, size);
        }
        private FieldModelStruct struct;

        public FieldModelStruct getStruct() {
            return struct;
        }
    }

    public FieldsModelDatabases() {
        for (emDatabases s : emDatabases.values()) {
            createField(s.getStruct());
        }
    }

    /**
     *  recupera l'oggetto del campo richiesto ( solitamente usato nelle tabelle )
     */
    public Object getValueAt(int colonna, BeanDatabases database) {
        emDatabases col = emDatabases.values()[findKeyOrdinal(colonna, false)];

        switch (col) {
            case ID:
                return database.getId();
            case NOME:
                return database.getNome();
        }
        return "errore";
    }

    /**
     *  Gestisce la ricerca per campi
     * @param field campo selezionato
     * @param searchText valore da cercare
     * @return
     */
    public String getSearchCond(int field, String searchText) {
        emDatabases col = emDatabases.values()[findKeyOrdinal(field, true)];
        String condition = "";

        searchText = MyDBMethods.fixSqlString(searchText);
        if (!searchText.isEmpty()) {
            switch (col) {
                case ID:
                    try {
                        int value = Integer.parseInt(searchText);
                        condition = condition.concat(" ideditore = " + value);
                    } catch (NumberFormatException e) {
                    }
                    break;
                case NOME:
                    condition = condition.concat(" nome LIKE '%" + searchText + "%'");
                    break;
            }
        }

        return condition;
    }
}
