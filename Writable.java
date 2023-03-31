package FamilyTree;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface Writable extends Serializable {
    void writeObject(ObjectOutputStream stream);

    void readObject(ObjectInputStream stream);
}

