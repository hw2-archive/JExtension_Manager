/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exman.main.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hw2.common.PropConnection;
import exman.main.persistence.HandlerMainQuery;
import java.util.Arrays;

/**
 *
 * @author Aurora
 */
public class TableModelDatabases extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BeanDatabases> listaDatabases = Arrays.asList(new BeanDatabases());
    private String columns[];
    private FieldsModelDatabases fieldsInfo = new FieldsModelDatabases();
    //private HandlerOrdiniQuery handler = new HandlerOrdiniQuery();


    /*public TableModelDatabases () {
        this.listaOrdini = handler.loadOrdini("");
        this.columns = fieldsInfo.getAllNames(false);
    }*/

	public TableModelDatabases () {
        this.columns = fieldsInfo.getAllNames(false);
    }

    public int getRowCount() {
        return listaDatabases.size();
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int row, int col) {
       BeanDatabases database = listaDatabases.get(row);
       return fieldsInfo.getValueAt(col, database);
    }
    
    public void refreshList(int searchOption, String searchText, PropConnection prop) {
        listaDatabases = new HandlerMainQuery().LoadTables(fieldsInfo.getSearchCond(searchOption, searchText),prop);
        fireTableDataChanged();
    }
    
    public List<BeanDatabases> getList() {
    	return listaDatabases;
    }
}
