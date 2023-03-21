/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu1881.framework.servlet;

import annotation.Fonction;
import annotation.Url;
import etu1881.framework.Mapping;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import utility.Util;

/**
 *
 * @author faneva
 */
public class FrontServlet extends HttpServlet {

    HashMap<String, Mapping> mappingUrls = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response,String url)
            throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            for (Map.Entry<String,Mapping> test: mappingUrls.entrySet()) {
                out.println(test.getKey()+" | "+test.getValue().getClassName()+" | "+test.getValue().getMethod());
            }
                
               
            /* TODO output your page here. You may use following sample code. */
//            String []valiny = Util.lien(url);
//            for(String v:valiny){
//                out.println("<p>"+v+"</p>");
//            }            
    }

    @Override
    public void init() throws ServletException {
        try {
            Class[] all = Fonction.getAllClasses();
            for(Class a:all){
                Method[] mtd = Fonction.getMethodsWithAnnotation(a, Url.class);
                for (int i = 0; i < mtd.length; i++) {
                    Url m = (Url) mtd[i].getAnnotation(Url.class);
                    this.mappingUrls.put(m.valeur(),new Mapping(a.getName(), mtd[i].getName()));
                }
            }
            
        } catch (Exception e) {
        }
    }
    
    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        processRequest(request, response,url);
//        RequestDispatcher disp = request.getRequestDispatcher("/jsp/newjsp.jsp");
//        disp.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        processRequest(request, response,url);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
