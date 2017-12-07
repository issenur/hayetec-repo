package HomeWorkPackage;


import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class PrintStuff
{

    public void printMethod() throws FileNotFoundException
    {

        PrintWriter qoral = null;

        try
        {
            qoral = new PrintWriter(new FileOutputStream("isse.txt"));

        }
        catch (FileNotFoundException throwObj)
        {
            System.out.println("File Not Found");
            System.exit(0);
        }
        qoral.close();
    }

}
