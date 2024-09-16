package io.github.tuanthhtq.financemanager.repositories;

import io.github.tuanthhtq.financemanager.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author io.github.tuanthhtq
 */

public interface UserRepository extends MongoRepository<Users, String> {

	Users findByUsername(String username);

}
