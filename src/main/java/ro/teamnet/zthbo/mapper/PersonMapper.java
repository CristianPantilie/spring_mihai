package ro.teamnet.zthbo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.teamnet.zthbo.dto.PersonDTO;
import ro.teamnet.zthbo.entity.City;
import ro.teamnet.zthbo.entity.Person;
import ro.teamnet.zthbo.service.CityService;
import ro.teamnet.zthbo.util.Cache;
import ro.teamnet.zthbo.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    @Autowired
    private CityService cityService;

    @Autowired
    private Cache cache;

    public Person toPerson(PersonDTO personDTO) {
        if (personDTO == null) {
            return null;
        }
        Person person = new Person();
        person.setFirstName(personDTO.getPrenume());
        person.setLastName(personDTO.getNume());
        person.setAge(Utils.stringToInt(personDTO.getVarsta().split(" ani")[0]));

        List<City> cities = (List<City>) cache.get("cities");
        if (cities == null) {
            cache.put("cities", cityService.findAll());
            cities = (List<City>) cache.get("cities");
        }
        if (!cities.isEmpty()) {
            person.setCity(cities.get((int) (Math.random() * cities.size())));
        }

        return person;
    }

    public PersonDTO toPersonDTO(Person person) {
        if (person == null) {
            return null;
        }
        PersonDTO personDTO = PersonDTO.builder()
                .nume(person.getLastName())
                .prenume(person.getFirstName())
                .varsta(person.getAge() + " ani")
                .oras(person.getCity().getName())
                .build();

        return personDTO;
    }

    public List<Person> toPersonList(List<PersonDTO> personDTOs) {
        return personDTOs.stream().map(personDTO -> toPerson(personDTO)).collect(Collectors.toList());
    }

    public List<PersonDTO> toPersonDTOList(List<Person> persons) {
        return persons.stream().map(this::toPersonDTO).collect(Collectors.toList());
    }

}
