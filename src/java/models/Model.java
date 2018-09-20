package models;

import java.util.ArrayList;

public class Model {
    protected String created_at;
    protected String updated_at;
    
    public Model() {
        this.created_at = null;
        this.updated_at = null;
    }

    public Model(String created_at, String updated_at) {
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    
    public Model(ArrayList<String> cols) {
        int length = cols.size();
        this.created_at = cols.get(length - 2);
        this.updated_at = cols.get(length - 1);
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
