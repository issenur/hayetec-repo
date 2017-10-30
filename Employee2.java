package firstPackage;

public class Employee2 extends Employee
{

  public static void main(String []args)
  {
      Employee isse = new Employee();
      Employee jamal = new Employee2();
      Employee.timesTwo(Employee.getWord());
      Employee2.timesTwo(Employee.getWord());
  }

  public  Employee2()
  {
    super();
    System.out.println("FROM INSIDE EMPLOYEE2");
  }

  public static void timesTwo(String s)
  {
    System.out.println(s);
    System.out.println(s);
    System.out.println(s);
  }
}
