import java.util.Scanner;

public class DemoJava
{
  public static void main(String[] args)
  {
    Scanner reader = new Scanner(System.in);
    String prompt2;
    char prompt;
    String bull;
    do {
              System.out.println("Who are you?");

              prompt2 = reader.next();
              prompt = prompt2.charAt(0);
              prompt = prompt.toUpperCase();
              bull = reader.nextLine();
              switch(prompt)
              {

                  case "J":
                      System.out.println("hmmm, from the look of the darkness in your");
                      System.out.println("eyes it looks you like to sleep in darkness.");
                      System.out.println("You puma wearing punk");

                      break;
                  case "I":
                      System.out.println("hmmm, from the look of the darkness in your");
                      System.out.println("eyes it looks you like to sleep in darkness.");
                      System.out.println("You puma wearing punk");
                  default:
                      System.out.println("That is not your name! You think I am stupid?");
                      break;
              }

          }while(prompt!="E");
    }
}
