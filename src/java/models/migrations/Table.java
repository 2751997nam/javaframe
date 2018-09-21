/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.migrations;

import config.MySql;
import java.util.List;
import models.database.DB;
import models.database.Query;

/**
 *
 * @author ASUS
 */
public class Table {
    protected Query query;

    public Table(String name) {
        this.query = new Query();
        this.query.setFrom(name);
        this.query.setColumns("(");
    }
    
    public void addNullable(String key, String value) {
        this.query.setColumns(this.query.getColumns() + ", " + key + " " + value);
    }
    
    public void addNullable(String key, String value, String type) {
        this.query.setColumns(this.query.getColumns() + ", " + key + " " + value + " " + type);
    }
    
    public void add(String key, String value, String type) {
        this.addNullable(key, value, "NOT NULL " + type);
    }
    
    public void add(String key, String value) {
        this.addNullable(key, value, "NOT NULL");
    }
    
    public void string(String key, int length) {
        this.add(key, "VARCHAR(" + length + ")");
    }
    
    public void string(String key) {
        this.add(key, "VARCHAR(191)");
    }
    
    public void text(String key) {
        this.add(key, "TEXT");
    }
    
    public void integer(String key) {
        this.add(key, "INT");
    }
    
    public void bigInteger(String key) {
        this.add(key, "BIGINT");
    }
    
    public void decimal(String key) {
        this.add(key, "DOUBLE");
    }
    
    public void addID() {
        this.query.setColumns(this.query.getColumns() + "`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY");
    }
    
    public void addForeign(String column, String table, String references) {
        this.query.setColumns(this.query.getColumns() + "CONSTRAINT FK_" + this.query.getFrom() + table + "FOREIGN KEY (" + column + ")\n" +
                                                            "REFERENCES " + table + "(" + references + ")" );
    }
    
    public void addTimestamps() {
        this.add("created_at", "TIMESTAMP", "DEFAULT CURRENT_TIMESTAMP");
        this.add("updated_at", "TIMESTAMP", "DEFAULT CURRENT_TIMESTAMP");
    }
    
    public void create() {
        this.query.setCommand("CREATE TABLE");
        this.query.setColumns(this.query.getColumns() + ")");
        new DB().executeQuery(this.query.toString());
    }
    
    public void createWithTimestamps() {
        this.addTimestamps();
        this.create();
    }
    
    public void drop() {
        new DB().executeQuery("Drop Table " +  this.query.getFrom());
    }
    
    public boolean exists(String name) {
        return new DB("migrations")
                .select("*")
                .where(new String[]{"name = '" + name + "'"})
                .checkQuery();
    }
}
