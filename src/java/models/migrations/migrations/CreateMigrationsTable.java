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
public class CreateMigrationsTable extends Migration{
    @Override
    public void up() {     
        Table table = new Table("migrations");
        table.addID();
        table.add("name", "VARCHAR(191)", "UNIQUE");
        table.integer("batch");
        table.createWithTimestamps();
    }
    
    @Override
    public void down() {
        Table table = new Table("migrations");
        table.drop();        
    }
}
