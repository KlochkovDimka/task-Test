package com.JavaCode.taskTest.repository;

import com.JavaCode.taskTest.model.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
}
