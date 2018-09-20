/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.net.URL;

/**
 *
 * @author ASUS
 */
public class Database extends Config{
    public Database() {
        URL url = getClass().getResource(this.path);
        this.path = url.getPath() + "\\database.json";
    }
}
