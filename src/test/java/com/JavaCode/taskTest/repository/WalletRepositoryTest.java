package com.JavaCode.taskTest.repository;

import com.JavaCode.taskTest.model.entity.WalletEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class WalletRepositoryTest {

    @Autowired
    WalletRepository repository;

    @Test
    void saveWallet() {
        WalletEntity entity = WalletEntity.builder()
                .id(1L)
                .amount(1000.)
                .build();

        WalletEntity saveEntity = repository.save(entity);

        assertEquals(saveEntity.getId(), 1L);
        assertEquals(saveEntity.getAmount(), entity.getAmount());
    }

    @Test
    void findByIdWallet() {
        WalletEntity entity = repository.findById(1L).get();

        assertEquals(entity.getId(), 1L);
        assertEquals(entity.getAmount(), 1000.);
    }
}