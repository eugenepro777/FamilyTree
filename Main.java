package FamilyTree;

public class Main {
    public static void main(String[] args) {

        FamilyTree family1 = new FamilyTree();

        Human human1 = new Human("Иван", "Иванов", Gender.Male);
        Human human2 = new Human("Мария", "Иванова", Gender.Female);
        Human human3 = new Human("Ольга", "Иванова", Gender.Female);

        System.out.println(family1.getInfo());



    }
}
