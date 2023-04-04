package helper_classes;
import java.io.File;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import annotation.*;

import etu1881.framework.Mapping;


public class PackageClasse {
    static String[] methodsName = {"equals", "toString", "hashCode", "annotationType", "isProxyClass", "newProxyInstance", "getInvocationHandler", "getProxyClass", "wait", "getClass", "notify", "notifyAll"};
/* 
 * equals
 * hashCode
 * toString
 * annotationType
 */
    public static File[] getFileInPackage(String packageName){
        File[] files = convert(getFiles(packageName));
        return files;
    }
    public static Vector<File> getFiles(String folder) {
        File file = new File(folder);
        Vector<File> reponse = new Vector<>();
        File[] files = file.listFiles();
        if(files == null) {
            return reponse;
        }
        for (int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()) {
                reponse.addAll(
                getFiles(files[i].getPath())
                );
            } else {
                if(files[i].getName().endsWith(".class")) {
                    reponse.add(files[i]);
                }        
            }
        }
        return reponse;
    }

    public static File[] convert(Vector<File> listFiles) {
        File[] toReturn = new File[listFiles.size()];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = listFiles.get(i);
        }
        return toReturn;
    }

    public static String getClassName(File file) {
        String className = file.getPath();
        className = className.replace("\\", ".");
        className = className.substring(className.indexOf("classes")+8);
        className = className.substring(0, className.length() - 6);
        System.out.println("CLASSNAME : "+className);
        return className;
    }

    public static void checkMethod(String packagePath,String packageName, HashMap<String, Mapping> map){
        System.out.println("\nPackage name : "+packagePath);
            File[] files = getFileInPackage(packagePath);

            // Obtenez toutes les classes de ce package
            for (File file : files) {
                try {
                    String className = getClassName(file);
                    Class<?> cls = Class.forName(className);
                    System.out.println("\tclasses = "+cls.getCanonicalName());
                    if (!Modifier.isAbstract(cls.getModifiers())) {
                        // Obtenez toutes les méthodes de cette classe
                        Method[] methods = cls.getDeclaredMethods();
                        for (Method method : methods) {
                            if (method.isAnnotationPresent(Url.class)) {
                                Annotation annotation = method.getDeclaredAnnotation(Url.class);
                                String key = (String) annotation.getClass().getDeclaredMethod("value").invoke(annotation);
                                System.out.println("c'est la mort !!! "+className+" , "+method.getName());
                                map.put(key, new Mapping(className, method.getName()));
                            }
                        }
                    }
                } catch (Exception e) {
                    //out.println("Error: " + e.getMessage());
                    System.err.println("Error :" + e);
                }
                
            }
    }

    public static boolean notIn(String[] all,String target) {
        for (String item : all) {
            if (target.equals(item)) {
                return false;
            }
        }
        return true;
    }
}
