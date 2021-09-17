package com.sisman.api;

import com.sisman.entitiy.Person;
import com.sisman.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    @PostConstruct
    public void init(){
        Person person =new Person();
        person.setFirstName("Yusuf");
        person.setLastName("Tugrul");
        person.setAddress("test");
        person.setBirthDate(Calendar.getInstance().getTime());
        person.setId("K0001");
        personRepository.save(person);
    }


    @GetMapping("/{search}")
    public ResponseEntity<List<Person>> getPeople(@PathVariable String search){

        List<Person> people = personRepository.findByFirstNameLikeOrLastNameLike(search, search);
        return ResponseEntity.ok(people);

    }

}
