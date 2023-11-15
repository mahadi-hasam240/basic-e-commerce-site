package com.ecommerce.userauth.repository;

import com.ecommerce.userauth.domain.common.UserDto;
import com.ecommerce.userauth.domain.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT c FROM User c WHERE (c.userIdentity = :userIdentity OR c.mobileNumber = :userIdentity) AND  c.isActive = :isActive")
    User getCustomerByUserIdentity(@Param("userIdentity") String userIdentity, @Param("isActive") boolean isActive);

    @Query("SELECT c FROM User c WHERE c.userIdentity = :userIdentity OR c.mobileNumber = :userIdentity")
    User getByUserIdentity(@Param("userIdentity") String userIdentity);

    @Query("SELECT new com.ecommerce.userauth.domain.common.UserDto(c.userIdentity) FROM User c WHERE c.id = ?1")
    UserDto getCustomerById(Long userId);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT c FROM User c WHERE (c.userIdentity = :userIdentity OR c.mobileNumber = :userIdentity) AND c.isActive = :isActive")
    User getCustomerByUserNameWithLock(@Param("userIdentity") String userIdentity, @Param("isActive") boolean isActive);

    @Query("SELECT c FROM User c WHERE (c.userIdentity = :userIdentity OR c.mobileNumber = :userIdentity)")
    User existsByIdentity(@Param("userIdentity") String userIdentity);

    @Query("SELECT count(c) > 0 from User c where c.mobileNumber = :phoneNo")
    boolean existsByPhoneNo(String phoneNo);


    Optional<User> findCustomerByUserIdentity(String userIdentity);
    boolean existsCustomerByUserIdentity(String userIdentity);

}
