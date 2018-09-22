import dto.Person;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Java10Inspections {

    private List<Person> varOnlyAppliesToLocalVariables(Person person) {
        final var people = List.of(person);
        return people;
    }

    private List<Person> suggestionProvidedIfTypesMatch() {
        var people = new ArrayList<Person>();
        return people;
    }

    private List<Person> suggesitonNotProvidedIfInterfaceUsed() {
        List<Person> people = new ArrayList<>();
        return people;
    }

    private List<Person> suggestionNotProvidedIfDiamondUsed() {
        ArrayList<Person> people = new ArrayList<>();
        return people;
    }

    // Example of best practice, as per http://openjdk.java.net/projects/amber/LVTIstyle.html
    private void chooseVariableNamesThatProvideUsefulInformation() {
        var listOfPerson = getEveryone();
        System.out.println(listOfPerson);
    }

    private void minimizeTheScopeOfLocalVariables() {
        var people = new HashSet<Person>();

        people.add(new Person("John", 33));
        people.add(new Person("Mary", 30));

        // There could be a lot of code between the declaration and the use of this variables

        for (var person: people) {
            System.out.println(person);
        }
    }

    private void considerVarWhenInitializerProvidesSufficientInformationToReader() throws IOException {
        var reader = Files.newBufferedReader(Paths.get("./pom.xml"));

        reader.lines()
                .forEach(System.out::println);

        ByteArrayOutputStream stuff = doTheThing();
        System.out.println(stuff.toString());
    }

    private ByteArrayOutputStream doTheThing() throws IOException {
        var outputStream = new ByteArrayOutputStream();
        outputStream.write("Hello World".getBytes());
        return outputStream;
    }

    private List<Person> getEveryone() {
        return List.of(
                new Person("John", 33),
                new Person("Mary", 30)
        );
    }

    public static void main(String[] args) throws IOException {

        var java10Inspections = new Java10Inspections();

        System.out.println("\n###### chooseVariableNamesThatProvideUsefulInformation ######");
        java10Inspections.chooseVariableNamesThatProvideUsefulInformation();

        System.out.println("\n###### minimizeTheScopeOfLocalVariables ######");
        java10Inspections.minimizeTheScopeOfLocalVariables();

        System.out.println("\n###### considerVarWhenInitializerProvidesSufficientInformationToReader ######");
        java10Inspections.considerVarWhenInitializerProvidesSufficientInformationToReader();
    }

}

