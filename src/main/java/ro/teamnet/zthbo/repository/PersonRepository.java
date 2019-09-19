package ro.teamnet.zthbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.teamnet.zthbo.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByFirstNameAndLastNameAndAge(String firstName, String lastName, Integer age);

    List<Person> findByFirstNameOrFirstNameIsNullAndLastNameOrLastNameIsNullAndAgeOrAgeIsNull(String firstName, String lastName, Integer age);

    List<Person> findAllByAgeGreaterThanEqual(Integer age);

    @Query("select p from Person p where p.age >= :age")
    List<Person> getPersonsByAgeGreaterThanEqualHql(Integer age);

}
