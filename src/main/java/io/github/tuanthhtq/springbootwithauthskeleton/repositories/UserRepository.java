package io.github.tuanthhtq.springbootwithauthskeleton.repositories;

import io.github.tuanthhtq.springbootwithauthskeleton.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author io.github.tuanthhtq
 */

public interface UserRepository extends JpaRepository<Users, Long> {
}
