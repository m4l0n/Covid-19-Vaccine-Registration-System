package system.service;

import java.util.ArrayList;

public interface ReadNWrite<T> {
    public void update(ArrayList<T> arrayList) throws Exception;
    public ArrayList<T> readFile() throws Exception;
}
