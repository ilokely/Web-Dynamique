///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package main;
//
//import annotation.*;
//import java.io.File;
//import java.lang.reflect.Method;
//import java.util.Vector;
//
///**
// *
// * @author faneva
// */
//public class Main {
//    public static void main(String[] args) {
//        try {
//            
//            String path = Fonction.class.getClassLoader().getResource("").getPath();
//            File f = new File(path);
//                       
//            Vector<String> test = Fonction.getClasses(f);
//            for (int i = 0; i < test.size(); i++) {
//                System.out.println(test.get(i));
//            }
//            
////            Class[] all = Fonction.getAllClasses();
////            for(Class a:all){
////                Method[] mtd = Fonction.getMethodsWithAnnotation(a, Url.class);
////                for (int i = 0; i < mtd.length; i++) {
////                    Url m = (Url) mtd[i].getAnnotation(Url.class);
////                    System.out.println(m.valeur()+" "+a.getSimpleName()+" "+mtd[i].getName());
////                }
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//    }
//}
