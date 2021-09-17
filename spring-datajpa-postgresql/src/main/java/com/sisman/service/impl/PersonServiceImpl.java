package com.sisman.service.impl;

import com.sisman.dto.PersonDto;
import com.sisman.entity.Address;
import com.sisman.entity.Person;
import com.sisman.repository.AddressRepository;
import com.sisman.repository.PersonRepository;
import com.sisman.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName("Omer");
        person.setLastName("Unal");
        final Person person1 = personRepository.save(person);

        List<Address> addressList = new ArrayList<>();
        personDto.getAddresses().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);
            address.setAddressType(Address.AddressType.OTHER);
            address.setActive(true);
            address.setPerson(person1);
            addressList.add(address);
        });
        addressRepository.saveAll(addressList);
        personDto.setId(person1.getId());
        return personDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> personList = personRepository.findAll();
        List<PersonDto> personDtoList = new ArrayList<>();
        personList.forEach(it -> {
            PersonDto personDto = new PersonDto();
            personDto.setId(it.getId());
            personDto.setFirstName(it.getFirstName());
            personDto.setLastName(it.getLastName());
            personDto.setAddresses(it.getAddresses().stream().map(Address::getAddress).collect(Collectors.toList()));
            personDtoList.add(personDto);
        });
        return personDtoList;
    }

    @Override
    public Page<PersonDto> getAll(Pageable pageable) {
        return null;
    }
}
