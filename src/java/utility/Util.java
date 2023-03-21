/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import etu1881.framework.Mapping;

/**
 *
 * @author faneva
 */
public class Util {
    
    public static String lien(String url){
        String[] reg = url.split("/");
//        Vector<String> v = new Vector();
//        for (int i = 4; i < reg.length; i++) {
//            v.add(reg[i]);
//        }
//        Object[] obj = v.toArray();
//        String[] fin = Arrays.copyOf(obj, obj.length, String[].class);
        return reg[4];
    }
    
    
}
