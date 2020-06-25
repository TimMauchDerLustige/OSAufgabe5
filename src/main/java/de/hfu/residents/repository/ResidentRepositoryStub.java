package de.hfu.residents.repository.;

import de.hfu.residents.domain.Resident;

import java.util.ArrayList;
import java.util.List;

public class ResidentRepositoryStub implements ResidentRepository {
    public static List<Resident> residents = new ArrayList<Resident>();

    public static void add(Resident resident) {
        residents.add(resident);
    }

    public List<Resident> getResidents() {

        return residents;
    }
}

