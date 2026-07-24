package shapes;

interface Shape {
    double calculateArea();
}

class Circle implements Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }
}

class Rectangle implements Shape {
    private double length, width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double calculateArea() {
        return length * width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}

class Triangle implements Shape {
    private double base, height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double calculateArea() {
        return 0.5 * base * height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }
}

public class Main {
    public static void main(String[] args) {

        Circle circle = new Circle(5.0);
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        Triangle triangle = new Triangle(3.0, 8.0);

        System.out.println("_---- Shape Area Calculator -----");

        System.out.println("Circle:");
        System.out.println("Radius = " + circle.getRadius());
        System.out.println("Area of Circle = " + circle.calculateArea());

        System.out.println();

        System.out.println("Rectangle:");
        System.out.println("Length = " + rectangle.getLength() + ", Width = " + rectangle.getWidth());
        System.out.println("Area of Rectangle = " + rectangle.calculateArea());

        System.out.println();

        System.out.println("Triangle:");
        System.out.println("Base = " + triangle.getBase() + ", Height = " + triangle.getHeight());
        System.out.println("Area of Triangle = " + triangle.calculateArea());
    }
}
