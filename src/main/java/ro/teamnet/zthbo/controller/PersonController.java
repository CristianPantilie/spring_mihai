package ro.teamnet.zthbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.teamnet.zthbo.dto.PersonDTO;
import ro.teamnet.zthbo.responseentity.GenericResponseEntity;
import ro.teamnet.zthbo.responseentity.PersonResponseEntity;
import ro.teamnet.zthbo.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/saveOne")
    public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO person) {
        PersonDTO savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
        PersonDTO person = personService.getPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends GenericResponseEntity> deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/saveMany")
    public ResponseEntity<List<PersonDTO>> savePersons(@RequestBody List<PersonDTO> persons) {
        List<PersonDTO> savedPersons = personService.savePersons(persons);
        return new ResponseEntity<>(savedPersons, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PersonDTO>> getPersonsFiltered(
            @RequestParam(name = "nume_de_familie") String nume,
            @RequestParam(name = "prenume", required = false) String prenume,
            @RequestParam(required = false) Integer varsta) {
        List<PersonDTO> personDTOs = personService.getPersonFiltered(nume, prenume, varsta);
        return new ResponseEntity<>(personDTOs, HttpStatus.OK);
    }

    @GetMapping("/filterReq")
    public ResponseEntity<List<PersonDTO>> getPersonsFilteredReq(
            @RequestParam(name = "nume_de_familie") String nume,
            @RequestParam(name = "prenume", required = false) String prenume,
            @RequestParam(required = false) Integer varsta) {
        List<PersonDTO> personDTOs = personService.getPersonFilteredReq(nume, prenume, varsta);
        return new ResponseEntity<>(personDTOs, HttpStatus.OK);
    }

    @GetMapping("/filterAgeNamedQuery")
    public ResponseEntity<List<PersonDTO>> getPersonsByAgeNamedQuery(@RequestParam Integer varsta) {
        List<PersonDTO> personDTOs = personService.getPersonFilteredByAgeNamedQuery(varsta);
        return new ResponseEntity<>(personDTOs, HttpStatus.OK);
    }

    @GetMapping("/filterAgeHql")
    public ResponseEntity<List<PersonDTO>> getPersonsByAgeHql(@RequestParam Integer varsta) {
        List<PersonDTO> personDTOs = personService.getPersonFilteredByAgeHql(varsta);
        return new ResponseEntity<>(personDTOs, HttpStatus.OK);
    }


}
