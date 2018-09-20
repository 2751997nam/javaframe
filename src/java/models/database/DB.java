package models.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;

public class DB {

    protected Connection connection;
    protected String table;
    protected String class_name;
    protected Query query;
    
    public DB() {
        try {
            this.connection = MySQLConnect.getMySQLConnection();
            this.query = new Query();        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DB(String table) {
        try {
            this.table = table;
            this.connection = MySQLConnect.getMySQLConnection();
            this.query = new Query();
            this.query.setFrom("From " + table);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public DB(String table, String class_name) {
        try {
            this.table = table;
            this.connection = MySQLConnect.getMySQLConnection();
            this.query = new Query();
            this.query.setFrom("From " + table);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        this.class_name = class_name;
    }

    public String getQuery() {
        return this.query.toString();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public List execute(String query) {
        List result = new ArrayList();
        try {
            System.out.println(query);
            Statement statement = this.connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                List<String> cols = new ArrayList();
                int length = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= length; i++) {
                    cols.add(rs.getString(i));
                }
                result.add(Class.forName(this.class_name).getConstructor(ArrayList.class).newInstance(cols));
            }
            this.connection.close();
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | ClassNotFoundException | NoSuchMethodException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public List excuteWithoutClass(String query) {
        List result = new ArrayList();
        try {
            System.out.println(query);
            Statement statement = this.connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                int length = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= length; i++) {
                    map.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                }
                
                result.add(map);
            }
            this.connection.close();
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public boolean checkQuery(String query) {
        try {
            System.out.println(query);
            Statement statement = this.connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            if(rs.next()) {
                this.connection.close();
                return true;
            }
            this.connection.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean checkQuery() {
        return this.checkQuery(this.query.toString());
    }

    public void executeQuery(String sql) {
        try {
            Statement statement = this.connection.createStatement();
            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void execute() {
        this.executeQuery(this.query.toString());
    }

    public void newQuery() {
        this.query = new Query();
    }

    public List all() {
        return execute("Select * from " + this.table);
    }

    public String getColumns() {
        String columns = "(";
        try {
            Class.forName(this.class_name).getDeclaredFields();
            Field[] fields = Stream.concat(
                    Arrays.stream(Class.forName(this.class_name).getDeclaredFields()),
                    Arrays.stream(Class.forName(this.class_name).getSuperclass().getDeclaredFields())
            ).toArray(Field[]::new);

            for (Field f : fields) {
                columns += f.getName();
                if (!f.equals(fields[fields.length - 1])) {
                    columns += ", ";
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        columns += ")";

        return columns;
    }

    public String getValues(String values[]) {
        String columnValues = "(";
        columnValues += "NULL, ";
        for (String value : values) {
            columnValues += "'" + value + "'";
            if (value.compareTo(values[values.length - 1]) != 0) {
                columnValues += ", ";
            }
        }
        columnValues += ", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP";
        columnValues += ")";

        return columnValues;
    }

    public DB insert(String values[]) {
        this.query.setCommand("INSERT INTO");
        this.query.setFrom(this.table);
        this.query.setColumns(this.getColumns());
        this.query.setColumnValues("VALUES " + this.getValues(values));
        this.execute();

        return this;
    }

    public DB update(Map<String, String> map) {
        this.query.setCommand("Update");
        this.query.setFrom(this.table);
        String sql = "SET ";
        ArrayList<String> sets = new ArrayList();
        map.entrySet().forEach((entry) -> {
            sets.add(entry.getKey() + " = " + "\'" + entry.getValue() + "\'");
        });

        for (String set : sets) {
            sql += set;
            if (set.compareTo(sets.get(sets.size() - 1)) != 0) {
                sql += ", ";
            }
        }
        this.query.setUpdate(sql);

        return this;
    }

    public DB delete() {
        this.query.setCommand("Delete");

        return this;
    }

    public DB select(String select) {
        this.query.setCommand("SELECT " + select);

        return this;
    }
    
    public DB insert() {
        this.query.setCommand("INSERT INTO ");
        
        return this;
    }
    
    public DB update() {
        this.query.setCommand("UPDATE ");
        
        return this;
    }

    public DB table(String table) {
        this.query.setFrom(table);

        return this;
    }

    public DB where(String[] wheres) {
        String allWhere = "WHERE ";
        int length = wheres.length;
        for (String where : wheres) {
            allWhere += where;
            if (where.compareTo(wheres[length - 1]) != 0) {
                allWhere += "AND";
            }
        }
        this.query.setWhere(allWhere);

        return this;
    }

    public DB whereIn(String column, String where) {
        String whereIn = "Where " + column + " IN (" + where + ")";
        this.query.setWhere(whereIn);

        return this;
    }

    public DB groupBy(String groupBy) {
        this.query.setGroupBy("GROUP BY " + groupBy);

        return this;
    }

    public DB orderBy(String order) {
        this.query.setOrderBy("Order By " + order);

        return this;
    }

    public DB having(String having) {
        this.query.setHaving("HAVING " + having);

        return this;
    }

    public List get() {
        if(this.class_name != null) {
            return execute(this.query.toString());
        } else {
            return excuteWithoutClass(this.query.toString());
        }
    }

    public DB paginate(HttpServletRequest request, int limit) {
        int current = 1;
        if(request.getParameter("page") != null) {
            current = Integer.parseInt(request.getParameter("page"));
        }
        new Paginate().setPaginate(request, getPageNumber(limit, current), current);
        
        this.query.setLimit(limit);
        this.query.setPaginate("Offset " + limit * (current - 1));

        return this;
    }

    public int getPageNumber(int limit, int offset) {
        int page_number = 0;
        try {
            String sql = "SELECT (CASE\n"
                    + "WHEN COUNT(*) % " + limit + " = 0\n"
                    + "THEN COUNT(*) DIV " + limit + "\n"
                    + "ELSE COUNT(*) DIV " + limit + " + 1\n"
                    + "END) as total\n"
                    + "FROM " + this.table;

            Statement statement = this.connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                page_number = rs.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return page_number;
    }
    
    public Object find(int id) {
        Object obj = new Object();
        
        obj = this.where(new String[]{"id = " + id}).execute(this.query.toString()).get(0);
        
        return obj;
    }
    
    public DB limit(int limit) {
        this.query.setLimit(limit);
        
        return this;
    }
    
}
