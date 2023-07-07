package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import etu1881.framework.*;
import annotation.Auth;
import annotation.Scope;
import annotation.SessionConfig;
import annotation.Url;

@Scope(types = "singleton")
public class Emp {
    int id;

    String name;

    FileUpload badge;

    @SessionConfig
    HashMap<String, Object> session;

    public Emp () {

    }
    

    public Emp(String name) {
        this.name = name;
    }


    @Url(value = "getEmp.do")
    public static Modelview findAll() {
        System.out.println("hello world !!");
        Modelview mv = new Modelview("Hello.jsp");
        List<Emp> liste = new ArrayList<>();
        liste.add(new Emp("Jean"));
        liste.add(new Emp("Jeanne"));
        liste.add(new Emp("Rakoto"));
        mv.addItem("list", liste);
        return mv;
    }
    @Url(value = "save.do")
    public Modelview save(){
        Modelview mv = new Modelview("result.jsp");
        mv.addItem("my_object", this);
        
        return mv;
    }

    @Url(value = "test.do", param_name = "nom,poids")
    public Modelview test(String nom, double poids) {
        Modelview mv = new Modelview("result_arg.jsp");
        mv.addItem("nom", nom);
        mv.addItem("poids", poids);
        return mv;
    }

    @Url(value = "login.do")
    public Modelview login() {
        Modelview mv = new Modelview("login.jsp");
        return mv;
    }

    @Url(value = "connect.do", param_name = "user,mdp")
    public Modelview connect(String user, String mdp){
        Modelview mv = new Modelview("connect.jsp");
        if (user.equals("admin")&&mdp.equals("useradmin")) {
            mv.setAttributeSession("isConnected", true,session);
            mv.setAttributeSession("profil", "admin",session);
            mv.setAttributeSession("message", "If you see this, you getted the session value",session);
            mv.setAttributeSession("obj", new Test(), session);
        } else {
            mv.setView("error.jsp");
        }
        return mv;
    }

    @Auth(profil = "admin")
    @Url(value = "admin.do")
    public Modelview admin() {
        Modelview mv = new Modelview("admin.jsp");
        mv.addItem("message", session.get("message"));
        mv.addItem("obj", session.get("obj"));
        return mv;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileUpload getBadge() {
        return badge;
    }

    public void setBadge(FileUpload badge) {
        this.badge = badge;
    }


    public HashMap<String, Object> getSession() {
        return session;
    }


    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }
    
    
    
}
