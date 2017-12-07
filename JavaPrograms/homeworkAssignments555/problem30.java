package homeworkAssignments;


import java.io.PrintWriter;


public class problem30 
{
	private static void menu(File fileIn, File fileOut, Scanner keyboard)
	{
		PrintWriter fileOutput = outputFileManager(fileOut);
		int control = 0;
		do
		{
			System.out.println("Select an option: \n" + 
					"\n		1 - Determine word statistiscs" +
					"\n		2 - List shortest words\n" +
					"\n		3 - List longest words\n" +
					"\n		4 - Search for a word\n" +
					"\n 	5 -	Exit\r\n");
			fileOutput.println(" Select an option: ");
			fileOutput.println("	1 - Determine word statistics");
			fileOutput.println("    2 - List shortest words");
			fileOutput.println("    3 - List longest words");
			fileOutput.println("    4 - Search for a word");
			fileOutput.println("    5 - Exit");
			try
			{
				control = keyboard.nextInt();
				fileOutput.println(control);
				fileOutput.flush();
				
				switch(control)
				{
				case 1:
					wordStatistics(fileIn, fileOut);
					break;
				case 2:
					shortestWords(fileIn,fileOut);
					break;
				case 3:
					longestWords(fileIn,fileOut);
					break;
				case 4:
					search(fileIn,fileOut,keyboard);
					break;
				case 5:
					System.out.println("Process completed");
					fileOutput.println("Process completed");
					break;
				default:
					System.out.println("Invalid entry");
					fileOutput.println("Invalid entry");
					break;
				}			
			}
			catch(InputMismatchException e)
			{
				System.out.println("Invalid entry");
				System.out.println("Invalid entry");
			}
		}while(control !=5);
		fileOutput.close();
	}
}
