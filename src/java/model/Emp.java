/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import annotation.Url;

/**
 *
 * @author faneva
 */
public class Emp {
    String nom;
    
    @Url(valeur="add-emp")
    public int test(){
        return 0;
    }
}
