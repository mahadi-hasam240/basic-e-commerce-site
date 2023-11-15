package com.ecommerce.userauth.repository;
import com.ecommerce.userauth.domain.entity.UserLoginLogoutHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginLogoutHistoryRepository extends JpaRepository<UserLoginLogoutHistory, Long> {

}
