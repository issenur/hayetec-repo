package firstPackage;
/*this is an example of a derived class aka subclass*/
public class EmployeeDerivedDriver extends Employee
{

  public static void main(String []args)
  {
      Employee isse = new Employee();
      /*the object type is whatever is on the left, but
       * whatever is on the right indicates what methods
       * this object will be able to see*/
      Employee jamal = new EmployeeDerivedDriver();
      Employee.timesTwo(Employee.getWord());
      EmployeeDerivedDriver.timesTwo(Employee.getWord());
  }

  public  EmployeeDerivedDriver()
  {
    super();
    System.out.println("FROM INSIDE EMPLOYEE2");
  }
  /*timesTwo method is an example of overiding a method*/
  public static void timesTwo(String s)
  {
    System.out.println(s);
    System.out.println(s);
    System.out.println(s);
  }
}
