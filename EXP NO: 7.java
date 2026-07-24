import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        FileWriter fw = new FileWriter("students.txt");

        System.out.println("Enter Details of 3 Students\n");

        for (int i = 1; i <= 3; i++) {

            System.out.println("Student " + i);

            System.out.print("Roll No : ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Name : ");
            String name = sc.nextLine();

            System.out.print("Marks : ");
            int marks = sc.nextInt();
            sc.nextLine();

            fw.write(roll + "," + name + "," + marks + "\n");
            System.out.println();
        }

        fw.close();

        System.out.println("Student records saved successfully.");

        System.out.println("----- Student Records -----");

        BufferedReader br = new BufferedReader(new FileReader("students.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();

        System.out.print("\nEnter Roll Number to Search : ");
        int searchRoll = sc.nextInt();

        br = new BufferedReader(new FileReader("students.txt"));

        boolean found = false;

        while ((line = br.readLine()) != null) {

            String data[] = line.split(",");

            if (Integer.parseInt(data[0]) == searchRoll) {

                System.out.println("\nStudent Found\n");
                System.out.println("Roll No : " + data[0]);
                System.out.println("Name : " + data[1]);
                System.out.println("Marks : " + data[2]);

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nStudent Not Found");
        }

        br.close();
        sc.close();
    }
}
