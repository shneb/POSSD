
public class Employee extends Person {
	public Employee(int id, String name, String address, String postcode, String phoneNumber, String email) {
		super(id, name, address, postcode, phoneNumber, email);
		// TODO Auto-generated constructor stub
	}
	private float payRate;
	private float clockin;
	private float clockout;
	private float hours;
	public float getPayRate() {
		return payRate;
	}
	public void setPayRate(float payRate) {
		this.payRate = payRate;
	}
	public float getClockin() {
		return clockin;
	}
	public void setClockin(float clockin) {
		this.clockin = clockin;
	}
	public float getClockout() {
		return clockout;
	}
	public void setClockout(float clockout) {
		this.clockout = clockout;
	}
	public float getHours() {
		return hours;
	}
	public void setHours(float hours) {
		this.hours = hours;
	}
	
}
