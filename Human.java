package FamilyTree;

import javax.imageio.IIOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Human implements Writable {
    private String name;
    private String surname;
    private String dateBirth;

    //Не забываем про инкапсуляцию. Поля почти всегда private - сделал
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


    public Human(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Human() {
        this.name = "unknown";
        this.surname = "unknown";
        this.dateBirth = "unknown";
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

//    public void setName(String name) {
//        this.name = name;
//    }

    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("Поле не должно быть пустым");
        } else {
            this.name = name;
        }
    }

//    public void setSurname(String surname) {
//        this.surname = surname;
//    }


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

    //не хватает метода addChild - добавил
    public void addChild(Human child) {
        childList = new ArrayList<>();
        this.childList.add(child);
    }

    public String getChild() {
        System.out.println("*".repeat(30));
        StringBuilder child = new StringBuilder(getName() + " " + getSurname() + " дети: \n");
        for (Human human: this.childList) {
            child.append(human + "\n");
        }
        return child.toString();
    }

    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate currentDate = LocalDate.now();
        LocalDate date = LocalDate.parse(dateBirth, formatter);
        return Period.between(date, currentDate).getYears();

    }

    @Override
    public String toString() {
        return this.name +  " " + this.surname + " " +
                "Пол: " + gender.name() + " " +
                "Возраст: " + getAge() + " лет " + "\n" + "Отец: " + father;
    }
// new methods
    public Human getMother() {
        return mother;
    }

    public Human getFather() {
        return father;
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
