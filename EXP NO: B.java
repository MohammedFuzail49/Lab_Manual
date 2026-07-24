import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));

            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Department: ");
            String department = sc.next();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            employees.add(new Employee(id, name, department, salary));
        }

        System.out.println("\n---- All Employees ----");
        employees.forEach(Employee::display);

        System.out.println("\n---- Salary Above 50000 (High to Low) ----");

        Predicate<Employee> salaryAbove50000 = emp -> emp.getSalary() > 50000;

        employees.stream()
                .filter(salaryAbove50000)
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .forEach(emp -> System.out.println(emp.getName() + " -> " + emp.getSalary()));

        System.out.println("\n---- Employee Names ----");

        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(names);

        System.out.println("\n---- Employees Grouped by Department ----");

        Map<String, List<String>> groupedByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        LinkedHashMap::new,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));

        groupedByDepartment.forEach((department, employeeNames) ->
                System.out.println(department + " : " + employeeNames)
        );

        System.out.println("\n---- Average Salary per Department ----");

        Map<String, Double> averageSalary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        averageSalary.forEach((department, average) ->
                System.out.printf("%s : %.2f%n", department, average)
        );

        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        long cseCount = employees.stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase("CSE"))
                .count();

        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        System.out.printf("%nTotal Salary Paid : %.2f%n", totalSalary);
        System.out.println("Number of CSE Employees : " + cseCount);

        if (highestPaid.isPresent()) {
            Employee emp = highestPaid.get();
            System.out.println("Highest Paid : " + emp.getName() + " (" + emp.getSalary() + ")");
        }

        sc.close();
    }
}

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void display() {
        System.out.println(id + " " + name + " " + department + " " + salary);
    }
}
