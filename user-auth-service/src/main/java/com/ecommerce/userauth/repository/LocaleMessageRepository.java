package com.ecommerce.userauth.repository;
import com.ecommerce.userauth.domain.entity.LocaleMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleMessageRepository extends JpaRepository<LocaleMessage, Long> {
    LocaleMessage findLocaleMessageByLocale(String locale);
}
