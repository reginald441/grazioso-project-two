package grazioso;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Driver class is the entry point of the application (contains the only main method).
 * Project Two: Includes input validation, intake for dogs/monkeys, reserve logic, and print options.
 */
public class Driver {

    // ArrayLists to store Dogs and Monkeys
    private static ArrayList<Dog> dogList = new ArrayList<>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<>();

    public static void main(String[] args) {

        // Initialize starter animals (helpful for testing)
        initializeDogList();
        initializeMonkeyList();

        Scanner scanner = new Scanner(System.in);

        String userChoice = "";
        while (!userChoice.equalsIgnoreCase("q")) {

            displayMenu();
            System.out.print("Enter a menu selection: ");
            userChoice = scanner.nextLine().trim();

            // Project Two: Menu input validation
            if (!(userChoice.equalsIgnoreCase("1")
                    || userChoice.equalsIgnoreCase("2")
                    || userChoice.equalsIgnoreCase("3")
                    || userChoice.equalsIgnoreCase("4")
                    || userChoice.equalsIgnoreCase("5")
                    || userChoice.equalsIgnoreCase("6")
                    || userChoice.equalsIgnoreCase("q"))) {

                System.out.println("Invalid selection. Please choose a valid option.");
                continue; // loop back to menu
            }

            switch (userChoice.toLowerCase(Locale.ROOT)) {
                case "1":
                    intakeNewDog(scanner);
                    break;

                case "2":
                    intakeNewMonkey(scanner);
                    break;

                case "3":
                    reserveAnimal(scanner);
                    break;

                case "4":
                    printAnimals("dog");
                    break;

                case "5":
                    printAnimals("monkey");
                    break;

                case "6":
                    printAnimals("available");
                    break;

                case "q":
                    System.out.println("Goodbye! Program closing.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    // Displays the user menu
    public static void displayMenu() {
        System.out.println("\n\nRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are in service and available");
        System.out.println("[q] Quit application");
    }

    // Starter dogs (pre-populated for testing)
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "male", "1", "25.6", "05-12-2019",
                "United States", "in service", false, "United States", "German Shepherd");
        Dog dog2 = new Dog("Rex", "male", "3", "35.2", "02-03-2020",
                "United States", "Phase I", false, "United States", "Labrador Retriever");

        dogList.add(dog1);
        dogList.add(dog2);
    }

    // Starter monkeys (pre-populated for testing)
    public static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("Milo", "male", "2", "12.0", "01-10-2021",
                "Brazil", "in service", false, "United States",
                "capuchin", "20.0", "14.0", "18.0");

        monkeyList.add(monkey1);
    }

    /**
     * Project Two: Intake new dog.
     * Prompts user for input, validates name duplication, sets attributes, adds to dogList.
     */
    public static void intakeNewDog(Scanner scanner) {

        System.out.print("Enter the dog's name: ");
        String name = scanner.nextLine().trim();

        // Duplicate check by name
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name)) {
                System.out.println("This dog is already in our system.");
                return;
            }
        }

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine().trim();

        System.out.print("Enter age: ");
        String age = scanner.nextLine().trim();

        System.out.print("Enter weight: ");
        String weight = scanner.nextLine().trim();

        System.out.print("Enter acquisition date: ");
        String acquisitionDate = scanner.nextLine().trim();

        System.out.print("Enter acquisition country: ");
        String acquisitionCountry = scanner.nextLine().trim();

        System.out.print("Enter training status: ");
        String trainingStatus = scanner.nextLine().trim();

        System.out.print("Enter in-service country: ");
        String inServiceCountry = scanner.nextLine().trim();

        System.out.print("Enter breed: ");
        String breed = scanner.nextLine().trim();

        Dog newDog = new Dog(name, gender, age, weight, acquisitionDate, acquisitionCountry,
                trainingStatus, false, inServiceCountry, breed);

        dogList.add(newDog);
        System.out.println("New dog successfully added!");
    }

    /**
     * Project Two: Intake new monkey.
     * Validates duplicate name and validates species list per specification.
     */
    public static void intakeNewMonkey(Scanner scanner) {

        System.out.print("Enter the monkey's name: ");
        String name = scanner.nextLine().trim();

        // Duplicate check by name
        for (Monkey monkey : monkeyList) {
            if (monkey.getName().equalsIgnoreCase(name)) {
                System.out.println("This monkey is already in our system.");
                return;
            }
        }

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine().trim();

        System.out.print("Enter age: ");
        String age = scanner.nextLine().trim();

        System.out.print("Enter weight: ");
        String weight = scanner.nextLine().trim();

        System.out.print("Enter acquisition date: ");
        String acquisitionDate = scanner.nextLine().trim();

        System.out.print("Enter acquisition country: ");
        String acquisitionCountry = scanner.nextLine().trim();

        System.out.print("Enter training status: ");
        String trainingStatus = scanner.nextLine().trim();

        System.out.print("Enter in-service country: ");
        String inServiceCountry = scanner.nextLine().trim();

        // Species validation (required by Project Two)
        System.out.print("Enter species (Capuchin, Guenon, Macaque, Marmoset, Squirrel monkey, Tamarin): ");
        String species = scanner.nextLine().trim();

        if (!isValidMonkeySpecies(species)) {
            System.out.println("Invalid species. Monkey not added.");
            return;
        }

        System.out.print("Enter tail length: ");
        String tailLength = scanner.nextLine().trim();

        System.out.print("Enter height: ");
        String height = scanner.nextLine().trim();

        System.out.print("Enter body length: ");
        String bodyLength = scanner.nextLine().trim();

        Monkey newMonkey = new Monkey(name, gender, age, weight, acquisitionDate, acquisitionCountry,
                trainingStatus, false, inServiceCountry, species, tailLength, height, bodyLength);

        monkeyList.add(newMonkey);
        System.out.println("New monkey successfully added!");
    }

    /**
     * Project Two: Reserve an animal.
     * Reserves the FIRST matching animal that is "in service" AND not reserved in the given country.
     */
    public static void reserveAnimal(Scanner scanner) {

        System.out.print("Enter animal type (dog/monkey): ");
        String animalType = scanner.nextLine().trim().toLowerCase(Locale.ROOT);

        System.out.print("Enter in-service country: ");
        String country = scanner.nextLine().trim();

        if (!animalType.equals("dog") && !animalType.equals("monkey")) {
            System.out.println("Invalid animal type. Please enter 'dog' or 'monkey'.");
            return;
        }

        if (animalType.equals("dog")) {
            for (Dog dog : dogList) {
                if (!dog.getReserved()
                        && dog.getInServiceCountry().equalsIgnoreCase(country)
                        && dog.getTrainingStatus().equalsIgnoreCase("in service")) {

                    dog.setReserved(true);
                    System.out.println("Dog reserved: " + dog.getName());
                    return;
                }
            }
            System.out.println("No available dogs found for that country.");
        }

        if (animalType.equals("monkey")) {
            for (Monkey monkey : monkeyList) {
                if (!monkey.getReserved()
                        && monkey.getInServiceCountry().equalsIgnoreCase(country)
                        && monkey.getTrainingStatus().equalsIgnoreCase("in service")) {

                    monkey.setReserved(true);
                    System.out.println("Monkey reserved: " + monkey.getName());
                    return;
                }
            }
            System.out.println("No available monkeys found for that country.");
        }
    }

    /**
     * Project Two: Print animals.
     * listType = "dog", "monkey", or "available"
     */
    public static void printAnimals(String listType) {

        if (listType.equalsIgnoreCase("dog")) {
            System.out.println("\n--- Dog List ---");
            for (Dog dog : dogList) {
                System.out.println(dog.getName()
                        + " | " + dog.getTrainingStatus()
                        + " | Reserved: " + dog.getReserved()
                        + " | Country: " + dog.getInServiceCountry());
            }
            return;
        }

        if (listType.equalsIgnoreCase("monkey")) {
            System.out.println("\n--- Monkey List ---");
            for (Monkey monkey : monkeyList) {
                System.out.println(monkey.getName()
                        + " | " + monkey.getTrainingStatus()
                        + " | Reserved: " + monkey.getReserved()
                        + " | Country: " + monkey.getInServiceCountry());
            }
            return;
        }

        // Exceeds expectations: all animals "in service" AND "available" (not reserved)
        if (listType.equalsIgnoreCase("available")) {
            System.out.println("\n--- Available Animals (In Service, Not Reserved) ---");

            for (Dog dog : dogList) {
                if (dog.getTrainingStatus().equalsIgnoreCase("in service") && !dog.getReserved()) {
                    System.out.println("Dog: " + dog.getName() + " | " + dog.getInServiceCountry());
                }
            }

            for (Monkey monkey : monkeyList) {
                if (monkey.getTrainingStatus().equalsIgnoreCase("in service") && !monkey.getReserved()) {
                    System.out.println("Monkey: " + monkey.getName() + " | " + monkey.getInServiceCountry());
                }
            }
        }
    }

    // Project Two: Valid monkey species from specification
    private static boolean isValidMonkeySpecies(String species) {
        String s = species.trim().toLowerCase(Locale.ROOT);
        return s.equals("capuchin")
                || s.equals("guenon")
                || s.equals("macaque")
                || s.equals("marmoset")
                || s.equals("squirrel monkey")
                || s.equals("tamarin");
    }
}