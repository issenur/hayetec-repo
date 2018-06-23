
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
            System.out.println("Jinalako  Nani? ");
            System.out.println("");
            kids = reader.nextLine();
            String kidsUpper = kids.toUpperCase();
            char kidsFirst = kidsUpper.charAt(0);
           switch(kidsFirst)
           {
               case 'N':
                   System.out.println("");
                   System.out.println("Asc " + kids + " stop watching fear files please ");
                   System.out.println("It is real , I don't want you to get scared. so ASC");
                   break;
               case 'I':
                   System.out.println("");
                   System.out.println("Asc Mr." + kids + "I just want to scare nawal");
                   break;
               default:
                   System.out.println("You must think I am doqon I can see you from the camera");
                   System.out.println("Fadlan your real name sister");
                   break;

           }
           System.out.println("");
           System.out.println("");
           System.out.println("Do you guys want me to make fun of anyone else? Type yes if you do and no if you don't");
           control = reader.nextLine();
        }while(!(control.equalsIgnoreCase("no")));
    }
}
