// Circle Class

public class Circle

{

	private static final double PI = 3.14;
	private double radius;
	
	//Constructor
	//Pre: none
	//Post: A Circle object created. Radius initialized to 1.
	public Circle()
	
	{
	
		radius = 1;
	
	}
	
	
	//Constructor
	//Pre: none
	//Post: A circle object created with radius r.
	
	public Circle(double r)
	
	{
	
		radius = r;
		
	}
	//Changes the radius of the circle.
	//Pre: none
	//Post: radius has been changed.
	public void setRadius(double newRadius)
	
	{
	
		radius = newRadius;
	
	}
	
	//Calculates the area of the circle.
	//Pre: none
	//Post: The area of the circle has been returned.
	public double area()
	
	{
	
		double circleArea;
		circleArea = PI * radius * radius;
		return(circleArea);
		
	}
	
	//Returns the radius of the circle.
	//Pre: none
	//Post: The radius of the circle has been returned.
	public double getRadius()
	
	{
	
		return (radius);
		
	}
	
	//Displays the formula for the area of a circle.
	//Pre: none
	//Post: The formula for area of a circle has been displayed.
	public static void displayAreaFormula()
	
	{
	
		System.out.println("The formula for the area of a circle is a=Pi*r*r");
		
	}
	
	//Determines if the object is equal to another Circle object.
	//Pre: c is a Circle object.
	//Post: true has been returned if the objects have the same radii.
	//False has been returned otherwise.
	
	public boolean equals(Object c)
	
	{
	
		Circle testObj = (Circle) c;
		
		if (testObj.getRadius() == radius)
		
		{
		
			return (true);
		
		}
		
		else 
		
		{
		
			return(false);
			
		}
		
	}
	
	//Returns a String that represents the Circle object.
	//Pre: none
	//Post: a string representing the Circle object has been returned.
	
	public String toString()
	
	{
	
		String circleString;

		circleString = "Circle has radius " + radius;
		return(circleString);
		
	}
	
}