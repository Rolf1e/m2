Tigran SLAMA M2I2L

**Question 1:**

La méthode toString() permet d'avoir une réprésentation sous forme de String d'un objet Java.

**Question 2:**

```text
tpreflect.paquetcadeau.hidden.Type1@4517d9a3
tpreflect.paquetcadeau.hidden.Type2@372f7a8d
Muahahahahahahaaaa!!!

interface tpreflect.paquetcadeau.hidden.Type
```

La méthode toString() de la classe Vector nous donne le contenu de celui ci en appellant la méthode toString() des
objets qu'il contient. On peut voir que pour la plupart des objets, celle ci n'a pas était surchargée, nous donnant
simplement son adresse dans la JVM.

**Question 3:**

On peut voir qu'un objet est caché derrière une interface. Un autre a été la méthode toString() surchargée.

**Question 4:**

Les classes ne sont pas des objets, mais en java il existe une classe `Class`, instanciable qui contient la métadata
d'un objet au runtime dans la JVM. Elle est instanciable, ce qui permet de construire des objets à partir d'appels au
runtime (attention très dangereux et imprévisible).

**Question 5:**

On peut accèder à toute la métadata de notre objet:

En lecture:

- Les attributs
- Lien d'héritage
- Les constructeurs
- Les annotations
- Les méthodes

En écriture

- Les attributs

**Question 6:**

```java
public class Question6 {
    // ...

    private static void introspect(Object o) {
        final var aClass = o.getClass();
        System.out.println("Class name: " + aClass.getName());
        System.out.println("toString(): " + o);
        System.out.println("Attributs declareds: " + Arrays.toString(aClass.getDeclaredFields()));
        System.out.println("Attributs: " + Arrays.toString(aClass.getFields()));
        System.out.println("Methods: " + Arrays.toString(aClass.getMethods()));
        System.out.println("Constructors: " + Arrays.toString(aClass.getConstructors()));
    }
}
```

`getFields()` retourne les champs d'accès public, `getDeclaredFields()` retourne tous les champs.

**Question 7:**

Il y a des incidents sur la sécurité, car on peut accèder aux membres méthodes privés en utilisant la reflexivité. :/

**Question 8:**

Elle permet a des frameworks comme Spring de pouvoir créer des Beans en ne connaissant pas encore les Class au moment où
ils développent le framework. Elle permet de compiler du code sans connaître les sources à l'avance. Par exemple pour
les drivers SQL de JPA.

**Question 9:**

La classe `Class` est `final` pour s'assurer que personne n'hérite de cette classe autre que la JVM, sauf en utilisant
la reflexivité. Dans quel cas le développeur est au courant.

**Question 10:**

```java

public class Question10 {
    // ...

    public static void introspect() {
        try {
            final var field = aClass.getField("i");
            displayField(o, field);
            System.out.println("Updating object .. ");
            field.setInt(o, 2);
            displayField(o, field);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void displayField(Object o, Field field) throws IllegalAccessException {
        System.out.println("Field " + field.getName() + "(" + field.getType() + "): " + field.get(o));
    }
}
```

**Question 11:**

Elle change la visibilité d'un membre d'une classe.

**Question 12:**

```java
public class Question12 {
    // ...

    public static void introspect() {
        final var fieldD = aClass.getDeclaredField("d");
        fieldD.setAccessible(true);
        displayField(o, fieldD);
        fieldD.setDouble(o, 24);
        displayField(o, fieldD);
    }
}
```

On modifie la visibilité de l'attribut "d" afin de pouvoir accèder en lecture/écriture.

**Question 13:**

```java

public class Question12 {
    public static void main() {
        System.setSecurityManager(new SecurityManager());
    }
}
 output:Exception in thread"main"java.security.AccessControlException:access denied("java.lang.reflect.ReflectPermission""suppressAccessChecks")

```

PS: j'ai essayé de surcharger la méthode du `SecurityManager` afin de contrer son effet. Malhreuseusement (ou
heureusement pour nous :D) l'API de reflexivité ne permet pas cette action. Le seul moyen de pouvoir leur faire et de
modifier directement dans le BytCode voir dans l'ASM. Cela dit, en encapsulant notre objet dans un `proxy`, comme le
font les frameworks de tests unitaires comme Mockito afin de changer les actions (principalement dans la valuer de
retour) des méthodes.
