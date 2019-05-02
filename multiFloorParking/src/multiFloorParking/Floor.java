package multiFloorParking;

public class Floor {
	String name;
	String type;
	int freePlaces = 5; /*Example, as a default*/

	public Floor(String name, String type) {
		this.name = name;
		this.type = type;
	}
}
