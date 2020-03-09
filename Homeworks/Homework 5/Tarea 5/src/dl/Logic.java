package dl;

import bl.Map;
import bl.Person;

import java.util.ArrayList;

public class Logic {
    static Map<Integer, Person> map;
    static ArrayList<Integer> keyList;

    public Logic() {
        this.map = new Map<>();
        keyList = new ArrayList<>();
    }

    public void mapInsert(int key, Person person) {
        this.map.add(key, person);
    }

    public Person searchMap(int key) {
        return this.map.get(key);
    }

    public Map getMap() {
        return this.map;
    }

    public void keyInsert(int key) {
        this.keyList.add(key);
    }

    public ArrayList<Integer> getKeyList() {
        return this.keyList;
    }
}
