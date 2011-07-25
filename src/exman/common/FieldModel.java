/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exman.common;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Giuseppe
 */
public class FieldModel {

    public List<FieldModelStruct> fieldsValues = new ArrayList<FieldModelStruct>();

    public void createField (FieldModelStruct modelStruct) {
        fieldsValues.add(modelStruct);
    }

    public String getName(int key) {
        return fieldsValues.get(key).getName();
    }

    public String getField(int key) {
        return fieldsValues.get(key).getField();
    }

    public boolean isRicerca(int key) {
        return fieldsValues.get(key).isRicerca();
    }

    public boolean isTableField(int key) {
        return fieldsValues.get(key).isTableField();
    }

    /**
     *  imposta la grandezza delle colonne della jDate corrispondente al fieldmodel
     * @param table
     */

    public void initColumnSize(JTable table) {
        List<FieldModelStruct> values = getAllValues(false);

        for (int i = 0; i < values.size(); i++) {
            if (i < table.getColumnCount()) {
                if (values.get(i).getSize() > 0) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(values.get(i).getSize());
                }
            }
        }
    }



    /**
     * Ricerca la chiave data una determinata posizione della lista ricerca o tabella
     * @param position
     * @param isRicerca
     * @return
     */
    public int findKeyOrdinal(int position,boolean isRicerca) {
        List<FieldModelStruct> values = getAllValues(isRicerca);

	return position <= values.size() ? values.get(position).getPos() : -1;
    }

    /**
     * Ricerca la posizione della chiave nelle liste data una determinata chiave
     * @param key
     * @param isRicerca
     * @return
     */

    public int getPositionOf(int key,boolean isRicerca) {
        List<FieldModelStruct> values = getAllValues(isRicerca);

        for (int i = 0; i < values.size() ; i++)
            if (key == values.get(i).getPos())
                return i;

        return -1;
    }



    /**
     *  Recupera tutti i valori attivati
     *  il parametro isRicerca funge da switch:
     *  quando e' true ritorna i valori di ricerca, quando e' false ritorna quelli per le tabelle
     */
    public List<FieldModelStruct> getAllValues(boolean isRicerca) {
        List<FieldModelStruct> values = new ArrayList<FieldModelStruct>();
        for (int i = 0; i < fieldsValues.size() ; i++)
            if (isRicerca && fieldsValues.get(i).isRicerca() == true)
                values.add(fieldsValues.get(i));
            else if (!isRicerca && fieldsValues.get(i).isTableField() == true)
                values.add(fieldsValues.get(i));

        return values;
    }

    /**
     *  Recupera tutti i nomi visuali
     *  il parametro isRicerca funge da switch:
     *  quando a'ï¿½ true ritorna i valori di ricerca, quando a'ï¿½ false ritorna quelli per le tabelle
     */
    public String[] getAllNames(boolean isRicerca) {
        List<FieldModelStruct> values = getAllValues(isRicerca);

        String names[] = new String[values.size()];
        for (int i = 0; i < values.size() ; i++)
            names[i] = values.get(i).getName();

        return names;
    }

    /**
     *  Recupera tutti i nomi dei campi del db
     *  il parametro isRicerca funge da switch:
     *  quando a'ï¿½ true ritorna i valori di ricerca, quando a'ï¿½ false ritorna quelli per le tabelle
     */
    public String[] getAllFieldsName(boolean isRicerca) {
        List<FieldModelStruct> values = getAllValues(isRicerca);

        String fields[] = new String[values.size()];
        for (int i = 0; i < values.size() ; i++)
            fields[i] = values.get(i).getField();

        return fields;
    }
}
