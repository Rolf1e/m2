package src.tpreflect.zetudiants;

import tpreflect.paquetcadeau.PaquetCadeau;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class RunMe {

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        Vector v = new PaquetCadeau().getPaquetCadeau();

        Iterator i = v.iterator();

//        while (i.hasNext()) {
        Object o = i.next();
        introspect(o);
        System.out.println("\n");
//        }

    }

    private static void introspect(Object o) {
        final var aClass = o.getClass();
        System.out.println("Class name: " + aClass.getName());
        System.out.println("toString(): " + o);
        System.out.println("Attributs declareds: " + Arrays.toString(aClass.getDeclaredFields()));
        System.out.println("Attributs: " + Arrays.toString(aClass.getFields()));
        System.out.println("Methods: " + Arrays.toString(aClass.getMethods()));
        System.out.println("Constructors: " + Arrays.toString(aClass.getConstructors()));

        try {
            final var fieldI = aClass.getField("i");
            displayField(o, fieldI);
            System.out.println("Updating object .. ");
            fieldI.setInt(o, 2);
            displayField(o, fieldI);

            final var fieldD = aClass.getDeclaredField("d");
            fieldD.setAccessible(true);
            displayField(o, fieldD);
            fieldD.setDouble(o, 24);
            displayField(o, fieldD);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // fill in!


    }

    private static void displayField(Object o, Field field) throws IllegalAccessException {
        System.out.println("Field " + field.getName() + "(" + field.getType() + "): " + field.get(o));
    }

}
