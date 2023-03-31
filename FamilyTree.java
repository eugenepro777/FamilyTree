package FamilyTree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree implements Writable {
    private List<Human> humanList;

//    public void save(FileHandler fileHandler) {
//        fileHandler.save(this);
//    }


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

//

    @Override
    public void writeObject(ObjectOutputStream stream) {
        try {
            stream.defaultWriteObject();
            System.out.println("Записано");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readObject(ObjectInputStream stream) {
        try {
            stream.defaultReadObject();
            System.out.println("Загружено");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
