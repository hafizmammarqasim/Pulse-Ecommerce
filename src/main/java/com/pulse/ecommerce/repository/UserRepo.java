package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserRecord,Long> {

    Optional<UserRecord> findByEmailOrPhoneNumber(String email,String phoneNumber);
}
