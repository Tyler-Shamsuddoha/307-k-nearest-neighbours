
import java.util.ArrayList;

public class IrisNode implements Comparable<IrisNode> {

	private ArrayList<Double> attributes = new ArrayList<Double>();
	private String name;
	private double distance;


	public IrisNode(ArrayList<Double> attributes, String name) {
		this.attributes = attributes;
		this.name = name;
	}

	public int compareTo(IrisNode other) {

		if (this.getDistance() - other.getDistance() > 0) {
			return 1;
		}else if (this.getDistance() - other.getDistance() < 0) {
			return -1;
		}else {
			return 0;
		}
	}	
	public ArrayList<Double> getAttributes() {
		return attributes;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public void setAttributes(ArrayList<Double> attributes) {
		this.attributes = attributes;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	
	public String getName() {
		return this.name;
	}
}
