import dto.Person;

import java.util.ArrayList;
import java.util.List;

public class Java10Inspections {

    private final Person person = new Person("John", 33);

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


}

