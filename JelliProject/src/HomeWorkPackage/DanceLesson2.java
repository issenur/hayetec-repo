package HomeWorkPackage;
import java.util.Scanner;

public class DanceLesson2
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter How much you have");
        int balance = keyboard.nextInt();
        System.out.println(" Lacag ii sii I have $" + balance);
        System.out.println("Enter inta aa ii  qortii");
        int giveAmount = keyboard.nextInt();
        System.out.println("I let you hold a couple dollars" +
                " mayqo aa rabtaa saxiib?:");
        int takeAmount = keyboard.nextInt();

        try
        {
            if(takeAmount > balance )
                throw new Exception("Error YuhoodExceptionRaised");
            else if (giveAmount > 100)
                throw new Exception("Error LiberalTajirException");
            else if (giveAmount < 2);
                throw new Exception("Error QabriBayaxException");
        }
        catch(Exception e)
        {
            String message = e.getMessage();
            System.out.println(message);
            System.exit(0);
        }
        System.out.println("Peace out Bruh!");
    }
}
