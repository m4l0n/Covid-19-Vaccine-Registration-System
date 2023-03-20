package system.service;

import system.common.fileIO.ReadNWrite;
import system.model.Vaccine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VaccineService implements ReadNWrite<Vaccine> {
    public Optional<Vaccine> searchVaccine(String vaccineName) {
        return readFile().stream()
                .filter(vaccine -> vaccine.getVaccineName().equals(vaccineName)).findFirst();
    }

    @Override
    public void update(List<Vaccine> arrayList) {

    }

    @Override
    public List<Vaccine> readFile() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(Files.newInputStream(Paths.get(Vaccine.getDataVaccine())))) {
            List<Vaccine> vaccineList = new ArrayList<>();
            readFileHelper(ois, vaccineList);
            return vaccineList;
        }  catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

    }
}

