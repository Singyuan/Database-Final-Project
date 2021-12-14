// write/read the database (sqlite) following the rule of model
package com.lwdevelop.backend.repository;

import com.lwdevelop.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// directly use known package to write into sqlite
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
