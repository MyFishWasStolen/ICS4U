
 /**
 * The modified Old.Puck class is tested.
 */
 public class Hockey {

	public static void main(String[] args) {
		Puck youthPuck = new Puck(4);
		Puck adultPuck = new Puck(5.5);

		System.out.println(youthPuck.getDivision());
		System.out.println("The youth puck has weight " + youthPuck.getWeight() + " ounces.");
		System.out.println("The youth puck as radius " + youthPuck.getRadius() + " and thickness " + youthPuck.getThickness());
		System.out.println(adultPuck.getDivision());
		System.out.println("The adult puck has weight " + adultPuck.getWeight() + " ounces.");
		System.out.println("The adult puck as radius " + adultPuck.getRadius() + " and thickness " + adultPuck.getThickness());
		if (youthPuck.compareTo(adultPuck) < 0 ) {
			System.out.println("The youth puck is smaller than the adult puck.");
		} else if (youthPuck.compareTo(adultPuck) == 0 ) {
			System.out.println("The youth puck is the same size as than the adult puck.");
		} else {
			System.out.println("The youth puck is larger than the adult puck.");
		}
 	}
}