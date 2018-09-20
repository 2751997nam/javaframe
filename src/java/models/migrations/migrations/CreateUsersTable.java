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
public class CreateUsersTable extends Migration {

    public void up() {
        if (this.exists()) {
            return;
        }
        this.addMigration(this.getClass().getName());
        Table table = new Table("users");
        table.addID();
        table.string("name", 255);
        table.string("email", 255);
        table.string("password", 255);
        table.string("remember_token", 100);
        table.createWithTimestamps();
    }

    public void down() {
        Table table = new Table("users");
        if (!this.exists()) {
            return;
        }
        table.drop();
        this.removeMigration(this.getClass().getName());
    }
}
