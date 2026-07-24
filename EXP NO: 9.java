import java.sql.*;

public class StudentJDBC {

    public static void main(String args[]) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college",
                    "root",
                    "password");

            Statement st = con.createStatement();

            // INSERT
            st.executeUpdate("INSERT INTO student VALUES(101,'Rahul','CSE',87)");
            st.executeUpdate("INSERT INTO student VALUES(102,'Sneha','ISE',91)");

            System.out.println("Records Inserted Successfully.\n");

            // UPDATE
            st.executeUpdate("UPDATE student SET marks=95 WHERE rollno=101");

            System.out.println("Record Updated Successfully.\n");

            // SEARCH
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM student WHERE rollno=101");

            System.out.println("Student Details\n");

            while(rs.next()) {

                System.out.println("Roll No : " + rs.getInt("rollno"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Department : " + rs.getString("department"));
                System.out.println("Marks : " + rs.getInt("marks"));
            }

            // DISPLAY ALL
            rs = st.executeQuery("SELECT * FROM student");

            System.out.println("\nStudent Records");
            System.out.println("----------------------------------------------");
            System.out.printf("%-8s %-10s %-12s %-5s\n",
                    "Roll","Name","Department","Marks");
            System.out.println("----------------------------------------------");

            while(rs.next()) {

                System.out.printf("%-8d %-10s %-12s %-5d\n",
                        rs.getInt("rollno"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getInt("marks"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}


/*
CREATE DATABASE college;

USE college;

CREATE TABLE student (
    rollno INT PRIMARY KEY,
    name VARCHAR(30),
    department VARCHAR(20),
    marks INT
);

javac -cp .;mysql-connector-j-8.x.x.jar StudentJDBC.java

java -cp .;mysql-connector-j-8.x.x.jar StudentJDBC
*/
