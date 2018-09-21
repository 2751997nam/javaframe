/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ASUS
 */
public class Config {

    protected String path;

    public Config() {
        this.path = "";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        URL url = getClass().getResource(this.path);
        this.path = url.getPath() + "\\" + path;
    }
    
    

    public String get(String key) {
        JSONParser parser = new JSONParser();
        String value = "";
        try {
            JSONObject object = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(this.path)));
            value = (String) object.get(key);
        } catch (ParseException | FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
}
