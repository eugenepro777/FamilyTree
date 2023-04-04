package FamilyTree;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FamilyTree family1 = new FamilyTree();

        family1.add(new Human("Иван", "Иванов", Gender.Male, "12.12.1978"));
        family1.add(new Human("Мария", "Иванова", Gender.Female, "12.11.1981"));
        family1.add(new Human("Ольга", "Иванова", Gender.Female, "11.11.2001"));

        FamilyTree family2 = new FamilyTree();

        family2.add(new Human("Петр", "Иванов", Gender.Male, "01.11.1987"));
        family2.add(new Human("Ирина", "Иванова", Gender.Female, "21.01.1991"));
        family2.add(new Human("Семен", "Иванов", Gender.Male, "10.01.2016"));
        family2.add(new Human("Архип", "Иванов", Gender.Male, "31.03.2019"));

        System.out.println("the tree is recorded " + family1.getInfo());

        FileHandler handler = new FileHandler();
        handler.save(family1);


        FamilyTree family3 = (FamilyTree) handler.load();
        System.out.println(family3.getInfo());


//
//        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tree_out.txt"));
//        outputStream.writeObject(family1);
//        outputStream.close();
//        save(family1, "tree_out2.txt");

//        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("tree_out.txt"));
//        family1 = (FamilyTree) inputStream.readObject();
//        System.out.println("the tree is read " + family1.getInfo());


        //System.out.println(human1.getChild());
//        System.out.println(human2.getChild());
//        System.out.println(human4.getChild());
//        System.out.println(human5.getChild());
//
//        System.out.println(family2.getInfo());



    }
}
