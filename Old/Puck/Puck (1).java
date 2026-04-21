/**
 * Old.Puck class.
 */
public class Puck extends Disk implements Comparable {
	public double MIN_STD_WEIGHT = 5;
	public double MAX_STD_WEIGHT = 5.5;
	public double MIN_YTH_WEIGHT = 4;
	public double MAX_YTH_WEIGHT = 4.5;
	private double weight;
	private boolean standard, youth;
		
	/**
	 * constructor
	 * pre: none
	 * post: A Old.Puck object has been created with with weight w.
	 * standard and youth are set depending on the weight.
	 */
	public Puck(double w) {
		super(1.5, 1);
		weight = w;
		if (weight >= MIN_STD_WEIGHT && weight <= MAX_STD_WEIGHT) {
			standard = true;
			youth = false;
		} else {
			youth = true;
			standard = false;
		}
	}


	/** 
	 * Returns the weight of the puck.
	 * pre: none
	 * post: The weight of the puck has been returned.
	 */
	public double getWeight() {
	 	return(weight);
	}
	
	
	/**
	 * Returns the division.
	 * pre: none
	 * post: A string stating whether the puck is standard or youth is returned.
	 */
	public String getDivision() {
		String div;
		
		if (standard) {
			div = "Old.Puck is standard";
		} else {
			div = "Old.Puck is youth";
		}
		return(div);
	}

	
	/** 
	 * Determines if the object is equal to another
	 * Old.Puck object.
	 * pre: none
	 * post: true has been returned if the objects are the same division. 
	 * false has been returned otherwise.
	 */
	public boolean equals(Object obj) {
		Puck testObj = (Puck)obj;
		
	 	if (testObj.getDivision() == getDivision()) {
			return(true);
		} else {
			return(false);
		}
	}


	/** 
	 * Returns a String that represents the Old.Puck object.
	 * pre: none
	 * post: A string representing the Old.Puck object has
	 * been returned.
	 */
	public String toString() {
		String puckString;

		puckString = "The puck has weight " + getWeight() +
					 " and division " + getDivision() + ".";
	 	return(puckString);
	}


	/** 
	 * Determines if object p is smaller, the same, or larger
	 * than this Old.Puck object
	 * pre: p is a Old.Puck object
	 * post: -1 has been returned if p is larger than this Old.Puck, 0 has
	 * been returned if p is the same size as this Old.Puck, and 1 has
	 * been returned if p is smaller than this Old.Puck.
	 */
	public int compareTo(Object p) {
		Puck testPuck = (Puck)p;

		if (weight < testPuck.getWeight()) {
			return(-1);
		} else if (weight == testPuck.getWeight()) {
			return(0);
		} else {
			return(1);
		}
	}
}