/**
 * 'Jul 31, 2020
 */
package com.myapp.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myapp.model.Users;


/**
 * @author Ravindra Pawar
 *
 */
@Repository
public interface UserRepository extends MongoRepository<Users, ObjectId> {

	List<Users> findByEmail(String email);

}
 