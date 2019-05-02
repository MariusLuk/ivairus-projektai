package multiFloorParking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parking {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		// Example cars
		List<Car> carList = new ArrayList<Car>();

		carList.add(new Car("ABC541", "petrol/diesel"));
		carList.add(new Car("ABC542", "petrol/diesel"));
		carList.add(new Car("ABC543", "petrol/diesel"));
		carList.add(new Car("ABC544", "petrol/diesel"));
		carList.add(new Car("ABC545", "electric"));
		carList.add(new Car("ABC546", "electric"));
		carList.add(new Car("ABC547", "electric"));
		carList.add(new Car("ABC548", "van"));
		carList.add(new Car("ABC549", "van"));
		carList.add(new Car("ABC540", "van"));

		// Example floors
		Floor floors[] = new Floor[10];

		floors[0] = new Floor("-2", "van");
		floors[1] = new Floor("-1", "van");
		floors[2] = new Floor("1", "petrol/diesel");
		floors[3] = new Floor("2", "petrol/diesel");
		floors[4] = new Floor("3", "petrol/diesel");
		floors[5] = new Floor("4", "petrol/diesel");
		floors[6] = new Floor("5", "petrol/diesel");
		floors[7] = new Floor("6", "petrol/diesel");
		floors[8] = new Floor("7", "electric");
		floors[9] = new Floor("8", "electric");

		String carNo = scanCarNo(scanner);

		Car retrievedCar = retrieveCarByNo(carNo, carList);

		if (retrievedCar != null) {

			Floor entranceFloorChoise = scanFloor(scanner, floors);

			switch (retrievedCar.carType) {
			
			case "petrol/diesel":
				if (entranceFloorChoise.freePlaces > 0) {
					parkCar(entranceFloorChoise);
				} else {
					checkNearestFreeFloorsAndPark(entranceFloorChoise, retrievedCar.carType, floors);
				}
				break;
			
			case "electric":
				// only two highest floors are possible
				if ((entranceFloorChoise.name.equals("8")
						|| entranceFloorChoise.name.equals("7")) && entranceFloorChoise.freePlaces > 0) {
					parkCar(entranceFloorChoise);
				} else {
					checkNearestFreeFloorsAndPark(entranceFloorChoise, retrievedCar.carType, floors);
				}

				break;

			case "van":
				// only two lowest floors are possible
				if ((entranceFloorChoise.name.equals("-2")
						|| entranceFloorChoise.name.equals("-1")) && entranceFloorChoise.freePlaces > 0) {
					parkCar(entranceFloorChoise);
				} else {
					checkNearestFreeFloorsAndPark(entranceFloorChoise, retrievedCar.carType, floors);
				}
				break;
			
			default:
				System.out.println("Such car type not found. Type searched: " + retrievedCar.carType);
				break;
			}
		}
		scanner.close();
	}

	private static void parkCar(Floor entranceFloorChoise) {
		System.out.println("Please park inside the floor " + entranceFloorChoise.name);
		entranceFloorChoise.freePlaces--;
	}

	private static void checkNearestFreeFloorsAndPark(Floor entranceFloorChoise, String carType, Floor[] floors) {
		System.out.println("Sorry, there is no free space for your car type in this floor. We will redirect you to...");

		boolean carParked = false;
		int currentIndex = Arrays.asList(floors).indexOf(entranceFloorChoise);
		int iterator = 1;

		while ((currentIndex + iterator < floors.length || currentIndex - iterator >= 0) && !carParked) {

			if (currentIndex + iterator < floors.length && floors[currentIndex + iterator].freePlaces > 0
					&& (floors[currentIndex + iterator].type.equals(carType) || carType.equals("petrol/diesel"))) {
				parkCar(floors[currentIndex + iterator]);
				carParked = true;
			} else if (currentIndex - iterator >= 0 && floors[currentIndex - iterator].freePlaces > 0
					&& (floors[currentIndex - iterator].type.equals(carType) || carType.equals("petrol/diesel"))) {
				parkCar(floors[currentIndex - iterator]);
				carParked = true;
			}

			iterator++;
		}

		if (!carParked) {
			System.out.println("Sorry, there is no free parking space at all.");
		}

	}

	private static Floor scanFloor(Scanner scanner, Floor[] floors) {
		System.out.println("Choose the entrance floor:"); /* Nuskanuojamas/ivedamas aukstas, per kuri ivaziuotu auto */
		Floor floorFound = null;

		do {
			if (scanner.hasNext()) {
				String input = scanner.nextLine();

				for (Floor floor : floors) {
					if (floor.name.equals(input)) {
						floorFound = floor;
					}
				}

				if (floorFound == null) {
					System.out.println("Incorect choise! Options available from -2 to 8 floor.");
				}
			}

		} while (floorFound == null);

		System.out.println();
		return floorFound;
	}

	public static Car retrieveCarByNo(String carNo, List<Car> carList) {
		Car carFound = null;

		for (Car car : carList) {
			if (car.carNo.equals(carNo)) {
				carFound = car;
				System.out.println("Welcome " + carNo);
			}
		}

		if (carFound == null) {
			System.out.println("Sorry, your car is not in the list!");
		}

		return carFound;
	}

	public static String scanCarNo(Scanner scanner) {
		System.out.println("Welcome! Enter your car number!"); /* Nuskanuojamas/ivedamas privaziavusio auto numeris */
		String carNo = scanner.nextLine();
		return carNo;
	}
}
