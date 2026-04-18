package grazioso;
/**
 * Dog class inherits from RescueAnimal and adds the breed attribute.
 */
public class Dog extends RescueAnimal {

    private String breed;

    // Default constructor
    public Dog() {
        super();
        setAnimalType("Dog");
    }

    // Full constructor (example style referenced by rubric)
    public Dog(String name, String gender, String age, String weight, String acquisitionDate,
               String acquisitionCountry, String trainingStatus, boolean reserved,
               String inServiceCountry, String breed) {

        super(name, "Dog", gender, age, weight, acquisitionDate, acquisitionCountry,
              trainingStatus, reserved, inServiceCountry);
        this.breed = breed;
    }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
}
