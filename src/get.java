
public class get {

	public double movementTime(double length) {
		double speed = 11.65;
		double movementTime = (length / speed) * 1000;
		return movementTime;
	}

	public double turnTime(double angle) {
		double speed = 9;
		double circumference = 8.9 * Math.PI;
		double time = ((circumference * (angle / 360)) / speed) * 1000;
		return time;

	}
}
