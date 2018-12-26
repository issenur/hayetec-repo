import java.io.*;
class RethrowingExceptions
{
	public static void main(String args[])
	{
		try
		{
			someMethod();
		}
		catch(Exception ex)
		{
			ex.printStackTrace(System.out);
		}
	}
	
	public static void someMethod()
	{
		try
		{
			someMethod2();
		}
		catch(Exception ex)
		{
			ex.printStackTrace(System.out);
			throw ex;
		}
	}
	
	public static void someMethod2()
	{
		try
		{
			throw new Exception("Custom Exception");
		}
		catch(Exception ex)
		{
			ex.printStackTrace(System.out);
		}
	}
}

