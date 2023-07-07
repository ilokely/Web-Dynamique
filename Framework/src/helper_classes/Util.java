package helper_classes;

import etu1881.framework.*;
import java.util.*;

import annotation.Url;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {
    // exemple URL : http://localhost:8080/Spring1/doHttpProcess
    public static String getParamURL(String url) {
        String paramUrl = "";
        String domain = url.substring(url.indexOf("//")+2);
        domain = domain.substring(domain.indexOf("/")+1);
        String temp = domain.substring(domain.indexOf("/")+1);
        if (temp.contains("?")) {
            temp = temp.replace("?", "//Split//");
            paramUrl = temp.split("//Split//")[0];
        } else {
            paramUrl = temp;
        }
        return paramUrl;
    }

    public static Modelview invokeMethod(HttpServletRequest request, Mapping mapping) throws Exception{
        ArrayList<Class<?>> type = new ArrayList<>();
        ArrayList<Object> value = new ArrayList<>();
        Util.setArgValue(request, mapping, type, value);

        Object obj = Util.initObjectForm(request, mapping);

        return (Modelview) obj.getClass().getMethod(mapping.getMethod(), type.toArray(Class[]::new)).invoke(obj, value.toArray(Object[]::new));
    }

    public static void setArgValue(HttpServletRequest request, Mapping mapping, ArrayList<Class<?>> type, ArrayList<Object> value) throws Exception {
        Method m = Util.getMethodByClassName(mapping.getClassName(), mapping.getMethod());

        if(m.isAnnotationPresent(Url.class)  && !m.getAnnotation(Url.class).param_name().equals("") ) {
            type.addAll(List.of(m.getParameterTypes()));

            String[] paramName = m.getAnnotation(Url.class).param_name().split(",");

            if(paramName.length != type.size()) throw new Exception("Number of argument exception \n" +
                    "\t" + paramName.length + " declared but " + type.size() + " expected");

            String value_temp;
            for (int i=0; i< paramName.length; i++) {
                value_temp = request.getParameter(paramName[i].trim());
                value.add(Util.castPrimaryType(value_temp, type.get(i)));
            }
        }
    }

    public static Object initObjectForm(HttpServletRequest request, Mapping map) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(map.getClassName());
        Object obj = clazz.getDeclaredConstructor().newInstance();

        Field[] allField = obj.getClass().getDeclaredFields();
        String field_name,value;

        for(Field f : allField) {
            field_name = f.getName();
            value = request.getParameter(field_name);
            if(value != null) {
                try {
                    obj.getClass()
                            .getMethod("set"+Util.capitalize(field_name), f.getType())
                            .invoke(obj, Util.castPrimaryType(value, f.getType()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj;
    }

    public static Method getMethodByClassName(String className, String method) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Object o = clazz.getDeclaredConstructor().newInstance();

        Method result = null;
        Method[] allMethod = o.getClass().getDeclaredMethods();
        for (Method m : allMethod) {
            if(m.getName() .equals(method)) {
                result = m;
                break;
            }
        }

        return result;
    }

    public static Object castPrimaryType(String data, Class<?> type) throws ParseException {
        if(data == null || type == null) return null;
        if(data.equals("")) {
            if(type.equals(Date.class) || type.equals(String.class)) return null;
            else return 0;
        }

        if (type.equals(Date.class)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return type.cast(format.parse(data));
        }else if(type.equals(int.class)) return Integer.parseInt(data);
        else if(type.equals(float.class)) return Float.parseFloat(data);
        else if(type.equals(double.class)) return Double.parseDouble(data);
        else if(type.equals(boolean.class)) return Boolean.getBoolean(data);

        return data;
    }

    public static String capitalize(String input) {
        char[] strrep = input.toCharArray();
        strrep[0] = Character.toUpperCase(strrep[0]);

        return new String(strrep);
    }
}
