/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.database;

import java.util.ArrayList;
import models.Model;

/**
 *
 * @author ASUS
 */
public class Migration extends Model{
    protected int id;
    protected String name;
    protected int batch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public Migration() {
    }

    public Migration(int id, String name, int batch) {
        this.id = id;
        this.name = name;
        this.batch = batch;
    }

    public Migration(ArrayList<String> cols) {
        super(cols);
        this.id = Integer.parseInt(cols.get(0));
        this.name = cols.get(1);
        this.batch = Integer.parseInt(cols.get(2));
    }
}
