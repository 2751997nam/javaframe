/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ASUS
 */
public class Query {
    private String command;
    private String update;
    private String columns;
    private String columnValues;
    private String from;
    private String where;
    private String groupBy;
    private String having;
    private String orderBy;
    private String paginate;
    private String limit;
    
    public Query() {
        this.command = "Select *";
        this.update = "";
        this.columns = "";
        this.columnValues = "";
        this.from = "";
        this.where = "";
        this.orderBy = "";
        this.groupBy = "";
        this.having = "";
        this.paginate = "";
        this.limit = "";
    }

    public String getPaginate() {
        return paginate;
    }

    public void setPaginate(String paginate) {
        this.paginate = paginate;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
    
    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(String columnValues) {
        this.columnValues = columnValues;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getHaving() {
        return having;
    }

    public void setHaving(String having) {
        this.having = having;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = "Limit " + limit;
    }
    
    @Override
    public String toString() {
        return  command + " " 
                + from + " " 
                + update + " "
                + columns + " " 
                + columnValues + " " 
                + where + " " 
                + groupBy + " " 
                + having + " " 
                + orderBy + " "
                + limit + " "
                + paginate;
    }
}
