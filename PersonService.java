import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with ID: " + personId));
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Long personId, Person updatedPerson){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with ID: " + personId));
        person.setName(updatedPerson.getName());
        person.setSurname(updatedPerson.getSurname());
        person.setTel(updatedPerson.getTel());
        person.setAddress(updatedPerson.getAddress());
        person.setEmail(updatedPerson.getEmail());
        person.setPicture(updatedPerson.getPicture());
        return personRepository.save(person);
    }

    public void deletePerson(Long personId) {
       Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with ID: " + personId));
        personRepository.delete(person);
    }
}