package etu1881.framework.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import helper_classes.*;
import etu1881.framework.Mapping;
import java.lang.reflect.Method;
import java.util.*;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls; 

    @Override
    public void init() throws ServletException{
        this.MappingUrls = new HashMap<String, Mapping>();
        ServletContext context = getServletContext();
        String absolutePath = context.getRealPath("/WEB-INF/classes");
        File workspace = new File(absolutePath);
        String[] test=workspace.list();
        for (String packageName : test) {
            String packagePath = absolutePath+'\\'+packageName;
            PackageClasse.checkMethod(packagePath,packageName, this.MappingUrls);
        }
    }
    

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        if (request.getMethod().equals("GET")) {
            out.println("Method GET");
        } else if (request.getMethod().equals("POST")) {
            out.println("Method POST");
        } else {
            // Si la méthode HTTP n'est ni GET ni POST, on renvoie une erreur "Méthode non autorisée"
            out.println("Methode non reconnu");
        }
        processRequest(request, response);
    }
    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String url = String.valueOf(req.getRequestURL());
        String parameter_url = Util.getParamURL(url);
        out.println("URL : "+url);
        out.println("MAPPING :"+this.MappingUrls.toString());
        out.println("Parameter url : "+parameter_url);
        HashMap<String, Mapping> hashMap = this.MappingUrls;
        Mapping temp = hashMap.get(parameter_url);
        if (temp != null) {
            out.println("Value of "+parameter_url+": ");
            out.println("\t ClassName : "+temp.getClassName());
            out.println("\t Method : "+temp.getMethod());
            try {
                Class<?> cls = Class.forName(temp.getClassName());
                Method test = cls.getMethod(temp.getMethod());
                test.invoke(cls);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            out.println("The url `"+parameter_url+"` is not defined");
        }
    }
    
}
