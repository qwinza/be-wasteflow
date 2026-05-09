package com.wasteflow.repository;

import com.wasteflow.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
