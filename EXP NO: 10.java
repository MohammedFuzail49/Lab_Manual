import java.util.*;

class Student {
    int rollNo;
    String name;
    double percentage;

    Student(int rollNo, String name, double percentage) {
        this.rollNo = rollNo;
        this.name = name;
        this.percentage = percentage;
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> list = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        // Add Records
        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter Student " + (i + 1) + " Details");

            System.out.print("Roll No: ");
            int roll = sc.nextInt();

            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Percentage: ");
            double per = sc.nextDouble();

            list.add(new Student(roll, name, per));
        }

        // Display Records
        System.out.println("\nStudent Records (ArrayList)\n");

        System.out.println("-------------------------------------");
        System.out.printf("%-8s %-10s %-10s\n", "Roll", "Name", "Percentage");
        System.out.println("-------------------------------------");

        for (Student s : list) {
            System.out.printf("%-8d %-10s %.1f\n", s.rollNo, s.name, s.percentage);
        }

        // Search
        System.out.print("\nEnter Roll No to Search: ");
        int search = sc.nextInt();

        for (Student s : list) {
            if (s.rollNo == search) {

                System.out.println("\nRecord Found\n");

                System.out.println("Roll No    : " + s.rollNo);
                System.out.println("Name       : " + s.name);
                System.out.println("Percentage : " + s.percentage);
            }
        }

        // Update
        System.out.print("\nEnter Roll No to Update: ");
        int update = sc.nextInt();

        for (Student s : list) {
            if (s.rollNo == update) {

                System.out.print("Enter New Percentage: ");
                s.percentage = sc.nextDouble();

                System.out.println("Record Updated Successfully.");
            }
        }
        
        // Create HashMap before delete
        HashMap<Integer, Student> map = new HashMap<>();

        for (Student s : list) {
            map.put(s.rollNo, s);
        }

        // Delete
        System.out.print("\nEnter Roll No to Delete: ");
        int delete = sc.nextInt();

        Iterator<Student> it = list.iterator();

        while (it.hasNext()) {
            Student s = it.next();

            if (s.rollNo == delete) {
                it.remove();
                System.out.println("Record Deleted Successfully.");
            }
        }

        // Display After Delete
        System.out.println("\nAfter Deleting Student\n");

        System.out.println("-------------------------------------");
        System.out.printf("%-8s %-10s %-10s\n", "Roll", "Name", "Percentage");
        System.out.println("-------------------------------------");

        for (Student s : list) {
            System.out.printf("%-8d %-10s %.1f\n", s.rollNo, s.name, s.percentage);
        }

       System.out.println("\nStudent Records (HashMap)\n");
       System.out.println("-------------------------------------");

       for (Integer key : map.keySet()) {
            Student s = map.get(key);
            System.out.println(key + " -> " + s.name + " (" + s.percentage + "%)");
        }

        sc.close();
    }
}
