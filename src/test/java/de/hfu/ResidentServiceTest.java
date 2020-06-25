package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ResidentServiceTest {

    @Test
    public void testGetUniqueResident(){
        ResidentRepositoryStub testRepository = new ResidentRepositoryStub();

        Resident resident1 = new Resident("Tim","Mauch","Winkelstrasse","Schwanau", new GregorianCalendar(1989, Calendar.JULY,16).getTime());
        Resident resident2 = new Resident("Dagobert","Duck","Geldspeicherstrasse","Entenhausen", new GregorianCalendar(1950, Calendar.SEPTEMBER,27).getTime());
        Resident resident3 = new Resident("Donald","Duck","Entenstrasse","Entenhausen", new GregorianCalendar(1950,Calendar.JUNE,10).getTime());

        testRepository.add(resident1);
        testRepository.add(resident2);
        testRepository.add(resident3);

        BaseResidentService service = new BaseResidentService();
        service.setResidentRepository(testRepository);

        Resident filterResident = new Resident();
        filterResident.setGivenName("Tim");

        try {
            assertEquals(resident1,service.getUniqueResident(filterResident));
        } catch (ResidentServiceException e) {
            assert false;
        }
    }

    @Test(expected = ResidentServiceException.class)
    public void testGetUniqueResidentExceptionNotUnique() throws ResidentServiceException {
        ResidentRepositoryStub testRepository = new ResidentRepositoryStub();

        Resident resident1 = new Resident("Tim","Mauch","Winkelstrasse","Schwanau", new GregorianCalendar(1989, Calendar.JULY,16).getTime());
        Resident resident2 = new Resident("Dagobert","Duck","Geldspeicherstrasse","Entenhausen", new GregorianCalendar(1950, Calendar.SEPTEMBER,27).getTime());
        Resident resident3 = new Resident("Donald","Duck","Entenstrasse","Entenhausen", new GregorianCalendar(1950,Calendar.JUNE,10).getTime());

        testRepository.add(resident1);
        testRepository.add(resident2);
        testRepository.add(resident3);

        BaseResidentService service = new BaseResidentService();
        service.setResidentRepository(testRepository);

        Resident filterResident = new Resident();
        filterResident.setGivenName("Geigle");
        service.getUniqueResident(filterResident);
    }

    @Test(expected = ResidentServiceException.class)
    public void testGetUniqueResidentExceptionWildcard() throws ResidentServiceException {
        ResidentRepositoryStub testRepository = new ResidentRepositoryStub();

        Resident resident1 = new Resident("Tim","Mauch","Winkelstrasse","Schwanau", new GregorianCalendar(1989, Calendar.JULY,16).getTime());
        Resident resident2 = new Resident("Dagobert","Duck","Geldspeicherstrasse","Entenhausen", new GregorianCalendar(1950, Calendar.SEPTEMBER,27).getTime());
        Resident resident3 = new Resident("Donald","Duck","Entenstrasse","Entenhausen", new GregorianCalendar(1950,Calendar.JUNE,10).getTime());

        testRepository.add(resident1);
        testRepository.add(resident2);
        testRepository.add(resident3);

        BaseResidentService service = new BaseResidentService();
        service.setResidentRepository(testRepository);

        Resident filterResident = new Resident();
        filterResident.setGivenName("*");
        service.getUniqueResident(filterResident);
    }

    @Test
    public void testFilteredResidentWildcard() {
        ResidentRepositoryStub testRepository = new ResidentRepositoryStub();

        Resident resident1 = new Resident("Tim","Mauch","Winkelstrasse","Schwanau", new GregorianCalendar(1989, Calendar.JULY,16).getTime());
        Resident resident2 = new Resident("Dagobert","Duck","Geldspeicherstrasse","Entenhausen", new GregorianCalendar(1950, Calendar.SEPTEMBER,27).getTime());
        Resident resident3 = new Resident("Donald","Duck","Entenstrasse","Entenhausen", new GregorianCalendar(1950,Calendar.JUNE,10).getTime());

        testRepository.add(resident1);
        testRepository.add(resident2);
        testRepository.add(resident3);

        BaseResidentService service = new BaseResidentService();
        service.setResidentRepository(testRepository);

        Resident filterResident = new Resident();
        filterResident.setFamilyName("*");
        assertEquals(testRepository.getResidents(),service.getFilteredResidentsList(filterResident));
    }

    @Test
    public void testFilteredResidentByDate() {
        ResidentRepositoryStub testRepository = new ResidentRepositoryStub();

        Resident resident1 = new Resident("Tim","Mauch","Winkelstrasse","Schwanau", new GregorianCalendar(1989, Calendar.JULY,16).getTime());
        Resident resident2 = new Resident("Dagobert","Duck","Geldspeicherstrasse","Entenhausen", new GregorianCalendar(1950, Calendar.SEPTEMBER,27).getTime());
        Resident resident3 = new Resident("Donald","Duck","Entenstrasse","Entenhausen", new GregorianCalendar(1950,Calendar.JUNE,10).getTime());

        testRepository.add(resident1);
        testRepository.add(resident2);
        testRepository.add(resident3);

        BaseResidentService service = new BaseResidentService();
        service.setResidentRepository(testRepository);

        Resident filterResident = new Resident();
        filterResident.setDateOfBirth(new GregorianCalendar(1989, Calendar.JULY, 16).getTime());
        assertEquals(1, service.getFilteredResidentsList(filterResident).size());
        assertEquals(service.getFilteredResidentsList(filterResident).get(0),resident1);
    }

    @Test
    public void testFilteredResidentByCity() {
        ResidentRepositoryStub testRepository = new ResidentRepositoryStub();

        Resident resident1 = new Resident("Tim","Mauch","Winkelstrasse","Schwanau", new GregorianCalendar(1989, Calendar.JULY,16).getTime());
        Resident resident2 = new Resident("Dagobert","Duck","Geldspeicherstrasse","Entenhausen", new GregorianCalendar(1950, Calendar.SEPTEMBER,27).getTime());
        Resident resident3 = new Resident("Donald","Duck","Entenstrasse","Entenhausen", new GregorianCalendar(1950,Calendar.JUNE,10).getTime());

        testRepository.add(resident1);
        testRepository.add(resident2);
        testRepository.add(resident3);

        BaseResidentService service = new BaseResidentService();
        service.setResidentRepository(testRepository);

        Resident filterResident = new Resident();
        filterResident.setStreet("Winkelstrasse");
        assertEquals(1, service.getFilteredResidentsList(filterResident).size());
        assertEquals(service.getFilteredResidentsList(filterResident).get(0),resident1);

    }
}