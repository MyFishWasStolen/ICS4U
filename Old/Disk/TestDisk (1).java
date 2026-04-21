 /**
  * The modified Disk class is tested.
  */
 public class TestDisk {

	public static void main(String[] args) {
		Disk saucer = new Disk(10, 0.02);

		System.out.println("Disk radius: " + saucer.getRadius());
		System.out.println("Disk surface area: " + saucer.area());
		System.out.println("Disk volume: " + saucer.volume());

		Disk plate1 = new Disk(12, 0.05);
		Disk plate2 = new Disk(12, 0.07);
		if (plate1.equals(plate2)) {
			System.out.println("Objects are equal.");
		} else {
			System.out.println("Objects are not equal.");
		}

		if (plate1.compareTo(plate2) < 0) {
			System.out.println("plate1 is smaller then plate2.");
		} else if (plate1.compareTo(plate2) == 0) {
			System.out.println("plate1 is the same size as plate2.");
		} else {
			System.out.println("plate1 is larger then plate2.");
		}
		System.out.println(plate1);
		System.out.println(plate2);
	}
}