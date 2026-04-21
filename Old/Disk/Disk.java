// Disk Class

public class Disk extends Circle

{

	private double thickness;
	
	//Constructor
	//Pre: none
	//Post: A Disk object has been created with radius r
	//and thickness t.
	public Disk(double r, double t)
	
	{
	
		super(r);
		thickness = t;
		
	}
	
	//Changes the thickness of the disk.
	//Pre: none
	//Post: Thickness has been changed.
	public void setThickness(double newThickness)
	
	{
	
		thickness = newThickness;
		
	}
	
	//Returns the thickness of the disk.
	//Pre: none
	//Post: The thickness of the disk has been returned.
	public double getThickness()
	
	{
	
		return(thickness);
	
	}
	
	//Returns the volume of the disk.
	//Pre: none
	//Post: The thickness of the disk has been returned.
	public double volume()
	
	{
	
		double v;
		
		v = super.area() * thickness;
		return(v);
	
	}
	
	//Determines if the object is equal to another
	//Disk object.
	//Pre: d is a Disk object.
	//Post: true has been returned if objects have the same
	//radii and thickness. false has been returned otherwise.
	
	public boolean equals (Object d)
	
	{
	
		Disk testObj = (Disk)d;
		
		if (testObj.getRadius() == super.getRadius() && testObj.getThickness() == thickness)
		
		{
		
			return(true);
			
		}
		
		else
		
		{
		
			return(false);
		
		}
	
	}
	
	//Returns a String that represents the Disk object.
	//Pre: none
	//Post: A string representing the Disk object has been returned.
	
	public String toString()
	
	{
	
		String diskString;
		
		diskString = "The disk has radius " + super.getRadius() + " and thickness " + thickness + ".";
		return(diskString);
		
	}
	
}