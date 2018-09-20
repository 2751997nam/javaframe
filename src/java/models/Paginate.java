/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class Paginate {

    public void setPaginate(HttpServletRequest request, int page_number, int current) {
        StringBuffer current_url = request.getRequestURL();
        String[] links = new String[5];
        links[0] = current_url + "?page=1";
        links[1] = current_url + "?page=" + (current != 1 ? current - 1 : 1);
        links[2] = current_url + "?page=" + current;
        links[3] = current_url + "?page=" + (current != page_number ? current + 1 : page_number);
        links[4] = current_url + "?page=" + page_number;

        request.setAttribute("page_number", page_number);
        request.setAttribute("links", links);
        request.setAttribute("current", current);
        request.setAttribute("url", current_url + "?page=");
    }
}
