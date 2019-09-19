package ro.teamnet.zthbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.teamnet.zthbo.dto.PersonDTO;
import ro.teamnet.zthbo.entity.Person;
import ro.teamnet.zthbo.mapper.PersonMapper;
import ro.teamnet.zthbo.repository.PersonRepository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @PersistenceContext
    private EntityManager entityManager;

    private PersonRepository personRepository;

    private PersonMapper personMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        Person person = personMapper.toPerson(personDTO);
        Person savedPerson = personRepository.save(person);
        return personMapper.toPersonDTO(savedPerson);
    }

    @Override
    public List<PersonDTO> savePersons(List<PersonDTO> personDTOs) {
        List<Person> persons = personMapper.toPersonList(personDTOs);
        List<Person> savedPersons = personRepository.saveAll(persons);

        return personMapper.toPersonDTOList(savedPersons);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return personMapper.toPersonDTO(person.orElseThrow(() -> new EntityNotFoundException("Person not found")));
    }

    @Override
    public boolean deletePersonById(Long id) {
        personRepository.deleteById(id);
        return true;
    }

    @Override
    public List<PersonDTO> getPersonFiltered(String nume, String prenume, Integer varsta) {
        List<Person> persons = personRepository.findByFirstNameOrFirstNameIsNullAndLastNameOrLastNameIsNullAndAgeOrAgeIsNull(prenume, nume, varsta);
        return persons == null ? null : personMapper.toPersonDTOList(persons);
    }

    @Override
    public List<PersonDTO> getPersonFilteredReq(String nume, String prenume, Integer varsta) {
        List<Person> persons = personRepository.findByFirstNameAndLastNameAndAge(prenume, nume, varsta);
        return persons == null ? null : personMapper.toPersonDTOList(persons);
    }

    @Override
    public List<PersonDTO> getPersonFilteredByAgeHql(Integer varsta) {
        List<Person> person = personRepository.getPersonsByAgeGreaterThanEqualHql(varsta);
        return person == null ? null : personMapper.toPersonDTOList(person);
    }

    @Override
    public List<PersonDTO> getPersonFilteredByAgeNamedQuery(Integer varsta) {
        List<Person> person = personRepository.findAllByAgeGreaterThanEqual(varsta);
        return person == null ? null : personMapper.toPersonDTOList(person);
    }

    @Override
    public PersonDTO emFind(Long id) {
        Person person = entityManager.find(Person.class, id);

        return personMapper.toPersonDTO(person);
    }

    @Override
    public PersonDTO emQuery(Long id) {
        Query query = entityManager.createQuery("select p from Person p where p.ID = :id");
        query.setParameter("id", id);
        Person person = (Person) query.getSingleResult();

        return personMapper.toPersonDTO(person);
    }

    @Override
    public PersonDTO emTypedQuery(Long id) {
        TypedQuery<Person> typedQuery = entityManager.createQuery("select p from Person p where p.ID = :id", Person.class);
        typedQuery.setParameter("id", id);
        Person person = typedQuery.getSingleResult();

        return personMapper.toPersonDTO(person);
    }

}
