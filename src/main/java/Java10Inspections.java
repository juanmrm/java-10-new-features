import dto.Person;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class Java10Inspections {

    private List<Person> getEveryone() {
        return List.of(
                new Person("John", 33),
                new Person("Mary", 30)
        );
    }


    // Creating UNMODIFIABLE COLLECTIONS

    private List<Person> makeUnmodifiableList(List<Person> people) {
        return List.copyOf(people);
    }

    private Set<Person> makeUnmodifiableSet(List<Person> people) {
        return people.stream()
                .collect(toUnmodifiableSet());
    }




    // Working with LOCAL VARIABLE TYPE INFERENCE

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


    /* Example of BEST PRACTICES: http://openjdk.java.net/projects/amber/LVTIstyle.html **/

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

    private void takeCareWhenUsingVarWithDiamondOrGenericMethods() {
        var safeList = List.of("One");

        // Si lo pasamos a var sera una List<Object>
        List<String> unsafeList = List.of();

        var safeArrayList = new ArrayList<String>();

        // Si lo pasamos a var sera una ArrayList<Object>
        ArrayList<String> unsafeArrayList = new ArrayList<>();
    }

    private Optional<String> useVarToBreakUpChainedOrNestedExpressionsWithLocalVariables(List<String> strings) {
        var entries = strings.stream()
                             .collect(groupingBy(s -> s, counting()))
                             .entrySet();

        var max = entries.stream()
                         .max(Map.Entry.comparingByValue());

        return max.map(Map.Entry::getKey);
    }

    private Map removeMatches(Map<? extends String, ? extends Number> map, String key, int max) {
        for (var iterator = map. entrySet().iterator(); iterator.hasNext();) {
            var entry = iterator.next();
            if (max > 0 && entry.getKey().matches(key)) {
                iterator.remove();
                max--;
            }
        }
        return map;
    }

    private String exampleTryWithResources(Socket socket, String charsetName) throws IOException {
        try (var is = socket.getInputStream();
             var isr = new InputStreamReader(is, charsetName);
             var buf = new BufferedReader(isr)) {
            return buf.readLine();
        }
    }

    public static void main(String[] args) throws IOException {

        var strings = List.of(
                "One",
                "Two",
                "Three",
                "Four",
                "Five",
                "One"
        );

        System.out.println("\nAvailable processors: " + Runtime.getRuntime().availableProcessors());

        var java10Inspections = new Java10Inspections();

        /*

        System.out.println("\n###### chooseVariableNamesThatProvideUsefulInformation ######");
        java10Inspections.chooseVariableNamesThatProvideUsefulInformation();

        System.out.println("\n###### minimizeTheScopeOfLocalVariables ######");
        java10Inspections.minimizeTheScopeOfLocalVariables();

        System.out.println("\n###### considerVarWhenInitializerProvidesSufficientInformationToReader ######");
        java10Inspections.considerVarWhenInitializerProvidesSufficientInformationToReader();

        System.out.println("\n##### useVarToBreakUpChainedOrNestedExpressionsWithLocalVariables #####");
        java10Inspections.useVarToBreakUpChainedOrNestedExpressionsWithLocalVariables(strings)
                .ifPresent(System.out::println);

        */

        System.out.println("\n##### removeMatches #####");
        var entries = strings.stream()
                             .collect(groupingBy(s -> s, counting()));


        System.out.println(java10Inspections.removeMatches(entries, "One", 2));


    }

}

