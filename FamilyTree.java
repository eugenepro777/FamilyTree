package FamilyTree;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree implements Writable {
    private List<Human> humanList;

//    public void save(FileHandler fileHandler) {
//        fileHandler.save(this);
//    }

    //private static final long serialVersionUID = 1L;


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

//    @Override
//    public void writeObject(ObjectOutputStream stream) {
//        try {
//            stream.defaultWriteObject();
//            System.out.println("Записано");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Override
//    public void readObject(ObjectInputStream stream) {
//        try {
//            stream.defaultReadObject();
//            System.out.println("Загружено");
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void save(Object obj, String filename) {
//        ObjectOutputStream objectOutputStream = null;
//        try {
//            objectOutputStream = new ObjectOutputStream(
//                    new FileOutputStream(filename));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            objectOutputStream.writeObject(obj);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            objectOutputStream.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void load(Object obj, String filename) {
//
//    }


    public boolean saveTree(FamilyTree tree) {
        boolean flag = false;
        File file = new File("//tree.txt");
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            if (fos != null) {
                try {
                    oos = new ObjectOutputStream(fos);
                    flag = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }

    public FamilyTree loadTree() throws InvalidObjectException {
        File file = new File("//tree.txt");
        ObjectInputStream ois = null;
        //FamilyTree tree = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            if (fis != null) {
                try {
                    ois = new ObjectInputStream(fis);
                    FamilyTree tree = (FamilyTree) ois.readObject();
                    return tree;
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new InvalidObjectException("Object fail");
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
            Writable object = (FamilyTree) ois.readObject();
            return object;
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        throw new InvalidObjectException("Object fail");
    }
}
