import java.util.Scanner;

public class Person {
    private String name;
    private int age;
    private String mobile;

    // method to set values for name, age, and mobile
    public void setValues() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("enter name: ");
        name = scanner.nextLine();

        System.out.print("enter age: ");
        age = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("enter mobile: ");
        mobile = scanner.nextLine();
        
        scanner.close();
    }

    // method to print name, age, and mobile
    public void printValues() {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("mobile: " + mobile);
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.setValues();
        p.printValues();
    }
}
