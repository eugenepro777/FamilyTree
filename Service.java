package FamilyTree;

public class Service {

    private int id;

    private FamilyTree tree;

    public Service(FamilyTree tree) {
        this.tree = tree;
    }
    public void addHuman(String name, String surname, Gender gender, String dateBirth, Human father, Human mother) {
        tree.add(new Human(id++, name, surname, gender, dateBirth, father, mother));
    }
    public void addHuman(String name, String surname, Gender gender, String dateBirth) {
        tree.add(new Human(id++, name, surname, gender, dateBirth));
    }

    public void sortByName () {
        tree.getHumanList().sort(new HumanComparatorByName());
    }

    public void sortById () {
        tree.getHumanList().sort(new HumanComparatorById());
    }

    public void sortByBirthday () {
        tree.getHumanList().sort(new HumanComparatorByBirthday());
    }

    public void sortByNumChild () {
        tree.getHumanList().sort(new HumanComparatorByNumberChild());
    }
}
