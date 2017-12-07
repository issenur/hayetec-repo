package HomeWorkPackage;
import java.util.Scanner;

public class Account
{
    private double balance;
    Scanner reader2 = new Scanner(System.in);

    public Account()
    {
        balance = 0;
    }

    public Account (double intialDeposit)
    {
        balance = intialDeposit;

    }

    public double getBalance()
    {
        return balance;
    }

    public double deposit(double amount)
    {

        try
        {
            if (amount > 0)
                balance = balance + amount;
            else
                throw new Exception("NonPositiveNumberException: "
                        + "Deposit amount must be positive.");
        }
        catch(Exception throwObj)
        {
            System.out.println(throwObj.getMessage());
            System.out.println("Enter a positive number:");
            amount = reader2.nextDouble();
            this.deposit(amount);
        }
        return balance;
    }

    public double withdraw(double amount)
    {
        try
        {
            if (amount < 0)
                throw new Exception("You must withdraw "
                        + "more than zero sdollars\nand "
                        + "less than " + balance + " dollars!");
            else if (amount > balance)
                throw new Exception("Not enough money.\n"
                        + "Insuffient funds to withdraw "
                        + "this amount.\nYour account balance: "
                        + balance);
            else
                balance = balance - amount;
            System.out.println("Your account balance: " + balance);
        }
        catch(Exception throwObj)
        {
            System.out.println(throwObj.getMessage());
        }
        return balance;
    }
}