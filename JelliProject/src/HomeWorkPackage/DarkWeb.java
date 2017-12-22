package HomeWorkPackage;

import java.util.Scanner;

public class DarkWeb
{
    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        String kids;
        String control;
        do
        {
            System.out.println("Whats your name? ");
            kids = reader.nextLine();
            String kidsUpper = kids.toUpperCase();
            char kidsFirst = kidsUpper.charAt(0);
           switch(kidsFirst)
           {
               case 'J':
                   System.out.println("Hey listen " + kids + " from the darkness " +
                           "in your eyes, I can tell");
                   System.out.println( "you love sleeping " +
                           "in dark rooms. I am afraid of you so I can't roast you!");
                   break;
               case 'I':
                   System.out.println(kids + " look at them huge eyeballs, you built like ");
                   System.out.println("a starving child in an African Desert. Your mama has");
                   System.out.println("to tape your eyeballs shut at night just so you could.");
                   System.out.println("sleep!");
                   break;
               default:
                   System.out.println("You must think I am dumb don't you?" +
                           " come on kid type in your real name please.");
                   break;
           }
           System.out.println("");
           System.out.println("");
           System.out.println("Do you guys want me to roast someone else? Type yes if you do.");
           control = reader.nextLine();
        }while(!(control.equalsIgnoreCase("no")));
    }
}
