package models;

import config.Database;
import models.database.DB;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class User extends Model {

    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected String remember_token;

    public User() {
        super();
        this.id = 0;
        this.name = "";
        this.email = "";
        this.password = "";
        this.remember_token = "";
    }

    public User(int id, String name, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(ArrayList<String> cols) {
        super(cols);
        this.id = Integer.parseInt(cols.get(0));
        this.name = cols.get(1);
        this.email = cols.get(2);
        this.password = cols.get(3);
        this.remember_token = cols.get(4);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean checkAuth(String name, String password) {
        return name.equals("username") && password.equals("password");
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
    }

    public List getAllUser(HttpServletRequest request) {
        return new DB("users", "models.User")
                .orderBy("name")
                .paginate(request,
                        Integer.parseInt(new Database().get("paginate"))
                ).get();       
    }

}
