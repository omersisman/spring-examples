package com.sisman.entitiy;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "people")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private String id;

    @Field(name = "first_name", type = FieldType.Text)
    private String firstName;

    @Field(name = "last_name", type = FieldType.Text)
    private String lastName;

    @Field(name = "address", type = FieldType.Text)
    private String address;

    @Field(name = "birth_date", type = FieldType.Date)
    private Date birthDate;

}
