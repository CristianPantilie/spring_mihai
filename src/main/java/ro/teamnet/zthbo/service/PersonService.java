package ro.teamnet.zthbo.service;

import ro.teamnet.zthbo.dto.PersonDTO;
import ro.teamnet.zthbo.entity.Person;

import java.util.List;

public interface PersonService {

    PersonDTO savePerson(PersonDTO person);

    List<PersonDTO> savePersons(List<PersonDTO> persons);

    List<Person> findAll();

    PersonDTO getPersonById(Long id);

    boolean deletePersonById(Long id);

    List<PersonDTO> getPersonFiltered(String nume, String prenume, Integer varsta);

    List<PersonDTO> getPersonFilteredReq(String nume, String prenume, Integer varsta);

    List<PersonDTO> getPersonFilteredByAgeHql(Integer varsta);

    List<PersonDTO> getPersonFilteredByAgeNamedQuery(Integer varsta);

    PersonDTO emFind(Long id);

    PersonDTO emQuery(Long id);

    PersonDTO emTypedQuery(Long id);
}
