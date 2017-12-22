
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
            System.out.println("");
            System.out.println("Whats your name? ");
            System.out.println("");
            kids = reader.nextLine();
            String kidsUpper = kids.toUpperCase();
            char kidsFirst = kidsUpper.charAt(0);
           switch(kidsFirst)
           {
               case 'J':
                   System.out.println("");
                   System.out.println("Hey listen " + kids + " from the darkness in your eyes");
                   System.out.println("I can tell you love sleeping in dark rooms. Scary as hell!");
                   System.out.println("I am afraid of you so I can't roast you!, Sike!");
                   System.out.println("boy You ugly as hell!, with yo two buck teeth lol.");
                   break;
               case 'I':
                   System.out.println("");
                   System.out.println("Dayum look at them huge eyeballs, you built like ");
                   System.out.println("a starving child in an African Desert. Boy, Damn.");
                   System.out.println("Your mama has to tape your eyeballs shut");
                   System.out.println("at night just so you could sleep! Roasted. Your name");
                   System.out.println("should be isuck instead of " + kids + " LOOOL");
                   break;
               default:
                   System.out.println("You must think I am dumb don't you? come on kid type in");
                   System.out.println("your real name please.");
                   break;
           }
           System.out.println("");
           System.out.println("");
           System.out.println("Do you guys want me to roast someone else? Type yes if you do.");
           control = reader.nextLine();
        }while(!(control.equalsIgnoreCase("no")));
    }
}
