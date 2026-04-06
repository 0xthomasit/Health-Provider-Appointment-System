package com.ion.auth.repositories;

import com.ion.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Thomas Ng
 * User:0xthomasit
 * Date:06-04-2026
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}