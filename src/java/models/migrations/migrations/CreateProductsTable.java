/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.migrations.migrations;

import models.migrations.Migration;
import models.migrations.Table;

/**
 *
 * @author ASUS
 */
public class CreateProductsTable extends Migration {

    @Override
    public void up() {
        if (this.exists()) {
            return;
        }
        this.addMigration(this.getClass().getName());
        Table table = new Table("products");
        table.addID();
        table.string("name");
        table.bigInteger("price");
        table.text("description");
        table.createWithTimestamps();
    }

    @Override
    public void down() {
        Table table = new Table("products");
        if (!this.exists()) {
            return;
        }
        table.drop();
        this.removeMigration(this.getClass().getName());

    }
}
