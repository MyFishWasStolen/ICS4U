public class Puck extends Disk implements Comparable {
	private double weight;
   private boolean standard, youth;
		
	/**
	 * constructor
	 * pre: none
	 * post: A Disk object has been created with radius r
	 * and thickness t.
	 */
	public Puck(double r, double t, double w, boolean s, boolean y) {
		super(r, t);
		weight = w;
      standard = s;
      youth = y;
	}
   
   public double getWeight()
   
   {
      return(weight);
   }
   
   public String getDivision()
   
   {
      String division;
      if (standard == true && youth == false && weight > 4.0 && weight < 4.5)
      {
         division = "The puck is a standard puck";
      }
      else if (standard = false && youth == true)
      {
         division = "The puck is a youth puck";
      }
      else
      {
         division = "Not regulation puck";
      }
      return (division);
   }
   public String toString()
   {
		String puckString;

		puckString = "The puck has radius " + super.getRadius() +
					 " and thickness " + super.getThickness() + ". along with a weight of: " + weight;
	 	return(puckString);
   }
}