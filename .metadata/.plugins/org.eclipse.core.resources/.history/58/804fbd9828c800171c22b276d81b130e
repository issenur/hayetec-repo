package Assignment4One;

public class VehicleDriver 
{

	public static void main(String[] args)
	{
		Vehicle v = new Vehicle("Jaguar" ,12, "John Doe");
		Vehicle v1 = new Vehicle(v);
		System.out.println(v.getManufacturer());
		System.out.println(v.getCylinders());
		System.out.println(v.getOwner());
		System.out.println(v.toString());
		v1.setOwner("Jane Doe");
		System.out.println(v1.toString());
		System.out.println(v.equals(v1));
		System.out.println(v == v1);
		System.out.println(v1.getOwner());
		System.out.println(v.getOwner());
			
		Truck tchevy = new Truck("Chevy", 8, "John Doe" , 0.5, 2 );
		Truck chevy = new Truck("Chevy", 8, "John Doe", 0.5, 2);
		System.out.println(tchevy.toString());
		
		System.out.println(tchevy.equals(chevy));
	
		chevy.setTruck("chevy", 6, "nobody", 0.75, 2);
		System.out.println(chevy.toString());
		System.out.println(chevy.getCapacity());
		System.out.println(chevy.getLoad());
				
	}

}
