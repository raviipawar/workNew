/**
 * 'Jul 31, 2020
 */
package com.myapp.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Ravindra Pawar
 *
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "users")
@Component
public class Users {

	@Id
	private ObjectId id;
	private String firstName;
	private String lastName;
	private String email;
	private int age;
}
