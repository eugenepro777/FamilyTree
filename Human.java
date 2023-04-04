package FamilyTree;

import javax.imageio.IIOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Human implements Writable {

    private  int id;
    private String name;
    private String surname;
    private String dateBirth;

    private Gender gender;
    private Human father;
    private Human mother;
    private List<Human> childList;

    private Human lifeCompanion;
    private String maidenName;

    private String dateDeath;

    private FamilyConnect connect;

    private final String nameRegex = "^[\\p{L} .'-]+$";

    public Human(String name, String surname, Gender gender, String dateBirth) {
        this.name = name;
        this.surname = surname;
        this.dateBirth = dateBirth;
        this.gender = gender;

        this.father = null;
        this.mother = null;
        this.lifeCompanion = null;
        this.maidenName = "";
        this.childList = new ArrayList<>();
    }

    public Human(int id, String name, String surname, Gender gender, String dateBirth, Human father, Human mother) {
        this.name = name;
        this.surname = surname;
        this.dateBirth = dateBirth;
        this.gender = gender;
        //this.id = id;

        this.father = father;
        this.mother = mother;
        this.maidenName = "";
        this.childList = new ArrayList<>();
    }


    public Human(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.father = null;
        this.mother = null;
    }

    public Human() {
        this.name = "unknown";
        this.surname = "unknown";
        this.dateBirth = "unknown";
        this.father = null;
        this.mother = null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public List<Human> getChildList() {
        return childList = new ArrayList<>();
    }


    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("Поле не должно быть пустым");
        } else {
            this.name = name;
        }
    }

    public void setSurname(String surname) {
        if (surname.isEmpty()) {
            System.out.println("Поле не должно быть пустым");
        } else {
            this.surname = surname;
        }
    }

//    public void setDateBirth(String dateBirth) {
//        this.dateBirth = dateBirth;
//    }

    public void setDateBirth(String dateBirth) {
        if (dateBirth.isEmpty()) {
            System.out.println("Поле не должно быть пустым");
        } else {
            this.dateBirth = dateBirth;
        }
    } public void setDateDeath(String dateDeath) {
        if (dateDeath.isEmpty()) {
            System.out.println("Поле не должно быть пустым");
        } else {
            this.dateDeath = dateDeath;
        }
    }


//    public void setFather(Human father) {
//        this.father = father;
//    }


    public void setFather(Human father) {
        if (!this.hasConnect(FamilyConnect.Father)) {
            if (!father.getChildList().contains(this)) {
                father.getChildList().add(this);
            }
            this.father = father;
        } else {
            throw new IllegalArgumentException("Отец уже есть");
        }
    }

//    public void setFatherV2(Human father) {
//        if (!father.getChildList().contains(this)) {
//            father.getChildList().add(this);
//        }
//        this.father = father;
//        } else {
//        throw new IllegalArgumentException("Отец уже есть");
//        }
//    }

//    public void setMother(Human mother) {
//        this.mother = mother;
//    }

    public void setMother(Human mother) {
        if (!this.hasConnect(FamilyConnect.Mother)) {
            if (!mother.getChildList().contains(this)) {
                mother.getChildList().add(this);
            }
            this.mother = mother;
        } else {
            throw new IllegalArgumentException("Мать уже есть");
        }
    }


// new methods

    public int numChildren() {
        return getChildList().size();
    }

    public void setChildList(List<Human> childList) {
        this.childList = childList;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Human getLifeCompanion() {
        return lifeCompanion;
    }

    public Gender getGender() {
        return gender;
    }

    public String getMaidenName() {
        return this.maidenName;
    }

    public final void setNameRegex(String name) {
        if (name.trim().matches(nameRegex)) {
            this.name = name.trim();
        } else {
            throw new IllegalArgumentException("Неверно задано имя");
        }
    }

    public final void setSurnameRegex(String surname) {
        if (surname.trim().matches(nameRegex)) {
            this.surname = surname.trim();
        } else {
            throw new IllegalArgumentException("Неверно задана фамилия");
        }
    }

    public void setMaidenName(String maidenName) {
        if (maidenName.trim().matches(nameRegex)) {
            if (this.gender == Gender.Male) {
                this.maidenName = maidenName.trim();
            } else {
                throw new IllegalArgumentException("Девичья фамилия для женщин");
            }
        } else if (maidenName.isEmpty()) {
            this.maidenName = "";
        } else {
            throw new IllegalArgumentException("Неверно задана фамилия");
        }

    }

    public void setLifeCompanion(Human lifeCompanion) {
        if (!this.hasConnect(FamilyConnect.Spouse)) {
            lifeCompanion.setChildList(this.getChildList());
            this.lifeCompanion = lifeCompanion;
            if (!this.getLifeCompanion().hasConnect(FamilyConnect.Spouse)){
                lifeCompanion.setLifeCompanion(this);
            }
        } else {
            throw new IllegalArgumentException("Супруг(а) уже есть");
        }
    }

    public boolean hasConnect(FamilyConnect connect) {
        switch (connect) {
            case Father -> {
                return this.getFather() != null;
            }
            case Mother -> {
                return this.getMother() != null;
            }
            case Spouse -> {
                return this.getLifeCompanion() != null;
            }
            case Parents -> {
                return this.hasConnect(FamilyConnect.Father) || this.hasConnect(FamilyConnect.Mother);
            }
            case Maidenname -> {
                return !this.getMaidenName().isEmpty();
            }
            case Children -> {
                return !this.getChildList().isEmpty();
            }
            case Grandpa -> {
                return this.father.getFather() != null;
            }
        }
        return false;
    }

    public void addConnect(FamilyConnect connect, Human human) {
        switch (connect) {
            case Father -> this.setFather(human);
            case Mother -> this.setMother(human);
            case Children -> this.addChild(human);
            case Spouse -> this.setLifeCompanion(human);

        }
    }

    public void addChild(Human child) {
        if (!childList.contains(child)) {
        childList.add(child);
        } else  {
            System.out.println("Ребенок уже есть в списке");
        }
        //childList = new ArrayList<>();
    }

    public boolean addChilds(Human child) {
        if(!childList.contains(child)) {
            childList.add(child);
            return true;
        }
        return false;
    }

//    public String getChild() {
//        System.out.println("*".repeat(30));
//        StringBuilder child = new StringBuilder(getName() + " " + getSurname() + " дети: \n");
//        for (Human human: this.childList) {
//            child.append(human + "\n");
//        }
//        return child.toString();
//    }

    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate currentDate = LocalDate.now();
        LocalDate date = LocalDate.parse(dateBirth, formatter);
        return Period.between(date, currentDate).getYears();

    }


    @Override
    public String toString() {
        return this.name +  " " + this.surname + " " +
                "Пол: " + getGender() + " " +
                "Возраст: " + getAge() + " лет ";
    }

//    @Override
//    public String toString() {
//        return this.name +  " " + this.surname + " " +
//                "Пол: " + getGender() + " " +
//                "Возраст: " + getAge() + " лет " + "\n" + "Отец: " + getFather() + "Мать: " + getMother();
//    }

    public Human getMother() {
        return mother;
    }

    public Human getFather() {
        return father;
    }

    // new methods
    public String getFatherInfo() {
        String res = "Отец: ";
        if (father != null) {
            res += father.getName();
        } else {
            res += "неизвестен";
        }
        return res;
    }

    public String getMotherInfo() {
        String res = "Мать: ";
        if (mother != null) {
            res += mother.getName();
        } else {
            res += "неизвестна";
        }
        return res;
    }

    public String getChildrenInfo() {
        StringBuilder childs = new StringBuilder();
        childs.append("дети: ");
        if (childList.size() != 0) {
            childs.append(childList.get(0).getName());
            for (int i = 1; i < childList.size(); i++) {
                childs.append(", ");
                childs.append(childList.get(i).getName());
            }
        } else {
            childs.append("без детей");
        }
        return childList.toString();
    }

//    public String getChild() {
//        StringBuilder child = new StringBuilder();
//        child.append("\nдети: ");
//        if (childList.size() != 0){
//            child.append(childList.get(0).getName());
//            for (Human human: childList) {
//                child.append(human).append("\n");
//            }
//        } else {
//            child.append("без детей");
//        }
//        return child.toString();
//    }


    public String getInfo() {
        System.out.println("*".repeat(30));
        StringBuilder human = new StringBuilder();
        human.append(name).append(" ")
                .append(surname).append(", ")
                .append(getGender()).append(", ")
                .append(getAge()).append(" лет, ")
                .append(getFather()).append(", ")
                .append(getMother()).append(", ")
                .append(getChildrenInfo());

        return human.toString();
    }

    @Override
    public void save(Writable serializable) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("out.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(serializable);
        }
        catch (IIOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Writable load() throws ClassNotFoundException, InvalidObjectException {
        try (FileInputStream fis = new FileInputStream("out.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Writable object = (Human) ois.readObject();
            return object;
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        throw new InvalidObjectException("Object fail");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Human)) {
            return false;
        }
        Human human = (Human) obj;
        return human.getName().equals(getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //    @Override
//    public void writeObject(ObjectOutputStream stream) {
//        try {
//            stream.defaultWriteObject();
//            System.out.println("Записано");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void readObject(ObjectInputStream stream) {
//        try {
//            stream.defaultReadObject();
//            System.out.println("Загружено");
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


    //    public void addChild(String name, String surname) {
//        Human child = new Human(name, surname);
//        child.setFather(this);
//        this.childList.add(child);
//    }

//    public void addChild(Human child) {
//        child.setFather(this);
//        this.childList.add(child);

//    }
//    public void getChildList(String name) {
//        for (Human item:
//             childList) {
//            if (human.get)
//        }
//       // return childList;

//    }
//    SimpleDateFormat dayFormat = new SimpleDateFormat("EEE, d MMMM", Locale.getDefault());
//    private Calendar calendar;
//
//    String myString = dayFormat.format(calendar.getTime());

    //    public void setFather(Human father) {
//        this.father = father;
//        father.addChild(this);
//    }
//
//    public void setMother(Human mother) {
//        this.mother = mother;
//        mother.addChild(this);
//    }




}
