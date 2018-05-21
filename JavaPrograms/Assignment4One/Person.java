package Assignment4One;

public class Person
{
	private String name;

	public Person()
	{
		this.name = getName();
	}
	
	public Person(String theName)
	{
		setName(theName);
	}
	
	public Person(Person originalPerson)
	{
		name = originalPerson.name;
	}
	
	public String getName()
	{
		 return name;
	}
	
	public void setName(String theName)
	{
		this.name = theName;
	}
	
	public String toString()
	{
		return (getName() );
	}
	
	public boolean equals(Person otherPerson)
 	{
 	    if (otherPerson == null)
 	        return false;
 	    else
 	        return  (name.equals(otherPerson.name));
   }
	
	public static void main(String[] args)
	{
		
	}
}