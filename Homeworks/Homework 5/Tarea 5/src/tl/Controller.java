package tl;

import bl.HashNode;
import bl.Map;
import bl.Person;
import dl.Logic;

import java.util.ArrayList;

public class Controller {
    static Logic logic;

    public Controller() {
        logic = new Logic();
    }

    public void mapInsert(int key, String[] personValue) {
        Person person = new Person(personValue[0], personValue[1]);
        logic.mapInsert(key, person);
        logic.keyInsert(key);
    }

    public Person searchMap(int key) {
        return logic.searchMap(key);
    }

    public String[] getPeopleByLastname(String apellido) {
        Map map = logic.getMap();
        ArrayList<Integer> keyList = logic.getKeyList();
        ArrayList<String> list = new ArrayList();
        HashNode<Integer, Person> hashNode;
        for (int i = 0; i < keyList.size(); i++) {
            hashNode = (HashNode<Integer, Person>) map.get(1);
            if (true) {
            }
        }

        return null;
    }
}
