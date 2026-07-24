import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter integer value for Integer Box: ");
        int intValue = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter string value for String Box: ");
        String strValue = sc.nextLine();

        Box<Integer> integerBox = new Box<>(intValue);
        Box<String> stringBox = new Box<>(strValue);

        System.out.println("\nInteger Box Value : " + integerBox.getValue());
        System.out.println("Type of stored item : " + integerBox.getType());

        System.out.println("String Box Value : " + stringBox.getValue());
        System.out.println("Type of stored item : " + stringBox.getType());

        System.out.println("\n---- Key-Value Pairs ----");

        System.out.print("Enter name key: ");
        String name = sc.next();

        System.out.print("Enter marks value: ");
        int marks = sc.nextInt();

        Pair<String, Integer> pair1 = new Pair<>(name, marks);

        System.out.print("Enter roll number key: ");
        int rollNo = sc.nextInt();

        System.out.print("Enter department value: ");
        String department = sc.next();

        Pair<Integer, String> pair2 = new Pair<>(rollNo, department);
        System.out.println();
        System.out.println("----Key-Value Pairs----");
        System.out.println(pair1.getKey() + " = " + pair1.getValue());
        System.out.println(pair2.getKey() + " = " + pair2.getValue());

        System.out.print("\nEnter three integer numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        System.out.println("Maximum Number : " + findMax(a, b, c));

        System.out.print("Enter three names: ");
        String n1 = sc.next();
        String n2 = sc.next();
        String n3 = sc.next();

        System.out.println("Maximum (Alphabetical) : " + findMax(n1, n2, n3));

        System.out.print("Enter three marks: ");
        double m1 = sc.nextDouble();
        double m2 = sc.nextDouble();
        double m3 = sc.nextDouble();

        System.out.println("Maximum Marks : " + findMax(m1, m2, m3));

        sc.close();
    }

    public static <T extends Comparable<T>> T findMax(T a, T b, T c) {
        T max = a;

        if (b.compareTo(max) > 0) {
            max = b;
        }

        if (c.compareTo(max) > 0) {
            max = c;
        }

        return max;
    }
}

class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getType() {
        return value.getClass().getName();
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
