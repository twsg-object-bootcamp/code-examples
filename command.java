import java.util.Arrays;
import java.util.List;

class Car {
    public void move() {
        System.out.println("Car moved.");
    }
}

class Person {
    public void move() {
        System.out.println("Person moved.");
    }
}

interface Command {
    void execute();
}

class CarMoveCommand implements Command {
    private Car car;

    public CarMoveCommand(Car car) {
        this.car = car;
    }

    public void execute() {
        car.move();
    }
}

class PersonMoveCommand implements Command {
    private Person person;

    public PersonMoveCommand(Person person) {
        this.person = person;
    }

    public void execute() {
        person.move();
    }
}

class App {
    public static void main(String[] args) {
        Person person = new Person();
        Car car = new Car();

        List<Command> commands = Arrays.asList(
                new CarMoveCommand(car),
                new PersonMoveCommand(person)
        );

        commands.forEach(Command::execute);

//        List<Runnable> commands = Arrays.asList(
//                () -> { car.move(); },
//                () -> { person.move(); }
//        );
//
//        commands.forEach(Runnable::run);
    }
}