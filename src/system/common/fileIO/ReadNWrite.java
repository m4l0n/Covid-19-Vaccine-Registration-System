package system.common.fileIO;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public interface ReadNWrite<T> {
    public void update(List<T> arrayList);
    public List<T> readFile();

    default void readFileHelper(ObjectInputStream ois, List<T> pplList) {
        try {
            Object obj = ois.readObject();
            if (obj != null) {
                pplList.add((T) obj);
                readFileHelper(ois, pplList);
            }
        } catch (EOFException ex) {
            // End of file reached
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
