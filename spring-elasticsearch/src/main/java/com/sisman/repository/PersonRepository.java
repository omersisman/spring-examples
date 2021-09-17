package com.sisman.repository;

import com.sisman.entitiy.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"first_name\": \"?0\"}}]}}")
    List<Person> getByCustomQuery(String search);

    List<Person> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);

}
