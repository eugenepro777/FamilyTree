package FamilyTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FamilyTree implements Serializable {
    private List<Human> humanList;

//    public void save(FileHandler fileHandler) {
//        fileHandler.save(this);
//    }
    public void save(Writable writable) {
        writable.save(this);
    }

    public List<Human> getHumanList() {
        return humanList;
    }

    public FamilyTree() {
        this.humanList = new ArrayList<>();
    }

    public Human getByName(String name) {
        for (Human human :
                humanList) {
            if (human.getName().equals(name))
                return human;
        }
        return null;
    }

    public Human getBySurname(String surname) {
        for (Human human :
                humanList) {
            if (human.getSurname().equals(surname))
                return human;
        }
        return null;
    }


    public void addHuman(Human human) {
        humanList.add(human);
    }

    public String getInfo() {
        System.out.println("*".repeat(30));
        StringBuilder tree = new StringBuilder("Семья: \n");
        for (Human human: this.humanList) {
            tree.append(human + "\n");
        }
        return tree.toString();
    }


}
