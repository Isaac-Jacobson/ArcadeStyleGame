
public class AtwoodsCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double[] accels = { 1.978, 2.009, 2.023, 2.012, 1.994, 2.004, 1.989 };

		double[] test = { 1.9, 1.95, 1.99, 2, 2.001, 2.01, 2.04 };

		double counter = 0;
		double aCounter = 0;

		for (int i = 0; i < accels.length; i++) {

			aCounter += accels[i];

			System.out.println("Trial " + (i + 1));
			System.out.println("The Acceleration was: " + accels[i]);
			System.out.println("The mass was		: " + calcMass(accels[i]));
			System.out.println("--------------------------------");

			counter += calcMass(accels[i]);
		}
		System.out.println(counter / accels.length);
		System.out.println(aCounter / accels.length);
	}

	public static double calcMass(double accel) {
		return ((50 * accel) + (50 * 9.8)) / (9.8 - accel);
	}

}
