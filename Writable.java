package FamilyTree;

import java.io.*;

public interface Writable extends Serializable {
//    void writeObject(ObjectOutputStream stream);

//    void readObject(ObjectInputStream stream);

   // void save(Serializable serializable) throws IOException;

    void save(Writable serializable) throws IOException;

    Object load() throws ClassNotFoundException, InvalidObjectException;
//    void load(Object obj, String filename);
}

