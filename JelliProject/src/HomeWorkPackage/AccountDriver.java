package HomeWorkPackage;
import java.util.Scanner;

public class AccountDriver
{
    public static String bankPrompt;

    public static void main(String[] args)
    {

        Scanner reader = new Scanner(System.in);
        Account accountHolder = new Account();
        String bankPrompt;
        String prompt;

        for(int i = 0; i < 20; i++)
        {

            System.out.print("-");

        }
        System.out.print("Configuration: <Default>");
        for(int i = 0; i < 20; i++)
        {
            System.out.print("-");

        }
        System.out.println("");

        double amount = 0;

        do {
            System.out.println("Do you want to open "
                    + "an account, deposit, withdraw? ");

            System.out.println("Enter O for open accoount, "
                    + "D for deposit, W for withdraw: ");
            bankPrompt = reader.next();
            bankPrompt = bankPrompt.toUpperCase();

            switch(bankPrompt)
            {

                case "O":
                    System.out.println("Enter the intial deposit"
                            + " for your new account");
                    amount = reader.nextDouble();
                    accountHolder = new Account(amount);
                    System.out.println("Your account balance: "
                            + accountHolder.getBalance());
                    break;
                case "W":
                    System.out.println("Enter the amount to "
                            + "withdraw: ");
                    amount = reader.nextDouble();
                    accountHolder.withdraw(amount);
                    break;
                case "D":
                    System.out.println("Enter the amount "
                            + "to deposit: ");
                    amount = reader.nextDouble();
                    accountHolder.deposit(amount);
                    System.out.println("Your account balance: "
                            + accountHolder.getBalance());
                    break;
                default:
                    System.out.println("Invalid selection!");
                    break;
            }

            System.out.println("More banking? answer Y "
                    + "to continue, N to exit ");

            prompt = reader.next();
        }

        while(prompt.equalsIgnoreCase("y"));
        reader.close();
        System.out.println("");
        System.out.println("Process completed.");
    }
}