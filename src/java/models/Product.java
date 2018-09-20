/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Product extends Model{
    protected int id;
    protected String name;
    protected int price;

    public Product() {
        super();
        this.id = 0;
        this.name = "";
        this.price = 0;
    }

    public Product(String created_at, String updated_at) {
        super(created_at, updated_at);
        this.id = 0;
        this.name = "";
        this.price = 0;
    }

    public Product(ArrayList<String> cols) {
        super(cols);
        this.id = Integer.parseInt(cols.get(0));
        this.name = cols.get(1);
        this.price = Integer.parseInt(cols.get(2));
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
