/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exman.main.model;

public class BeanDatabases implements Comparable {

    private static final long serialVersionUID = 1L;
    private int id = -1;
    private String nome = "";

    public BeanDatabases() {
    }

    public BeanDatabases(int id) {
        this.id = id;
    }

    public BeanDatabases(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // necessaria per il binary search
    public int compareTo(Object o) {
        if (!(o instanceof BeanDatabases)) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        BeanDatabases tmp = (BeanDatabases) o;
        return this.id - tmp.id;
    }
}
