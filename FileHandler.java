package FamilyTree;

import javax.imageio.IIOException;
import java.io.*;

public class FileHandler implements Writable {

    FamilyTree tree;
    Human human;



//    @Override
//    public void save(Writable serializable) throws IOException {
//        try (FileOutputStream fos = new FileOutputStream("out.txt");
//             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(serializable);
//        }
//        catch (IIOException ex) {
//            ex.printStackTrace(System.out);
//        }
//    }

    @Override
    public void save(Serializable serializable) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("out.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(serializable);
        }
        catch (IIOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Serializable load() throws ClassNotFoundException, InvalidObjectException {
        try (FileInputStream fis = new FileInputStream("out.txt");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
            Serializable object = (Serializable) ois.readObject();
            return object;
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        throw new InvalidObjectException("Object fail");
    }
}
