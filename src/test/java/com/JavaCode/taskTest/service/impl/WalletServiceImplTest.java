package com.JavaCode.taskTest.service.impl;

import com.JavaCode.taskTest.exceptions.NotFoundFundsException;
import com.JavaCode.taskTest.exceptions.NotFoundWalletException;
import com.JavaCode.taskTest.model.dto.RequestWalletDto;
import com.JavaCode.taskTest.model.dto.ResponseWalletDto;
import com.JavaCode.taskTest.model.enams.OperationType;
import com.JavaCode.taskTest.model.entity.WalletEntity;
import com.JavaCode.taskTest.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
class WalletServiceImplTest {

    @Mock
    WalletRepository repository;

    @InjectMocks
    WalletServiceImpl walletService;


    @Test
    void updateWalletShouldDepositWith1000Test() {
        RequestWalletDto requestWalletDto = new RequestWalletDto(1L, OperationType.DEPOSIT, 1000.);
        WalletEntity entity = new WalletEntity(1L, 1000.);
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);

        ResponseWalletDto walletEntity = walletService.updateWallet(requestWalletDto);

        assertEquals(walletEntity.getAmount(), 2000.);
    }

    @Test
    void updateWalletShouldWithdrawWith1000Test() {
        RequestWalletDto requestWalletDto = new RequestWalletDto(1L, OperationType.WITHDRAW, 1000.);
        WalletEntity entity = new WalletEntity(1L, 1000.);
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);

        ResponseWalletDto walletEntity = walletService.updateWallet(requestWalletDto);

        assertEquals(walletEntity.getAmount(), 00.);
    }

    @Test
    void updateWalletReturnNotFoundWaller() {
        RequestWalletDto requestWalletDto = new RequestWalletDto(2L, OperationType.WITHDRAW, 1000.);
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundWalletException.class,
                () -> walletService.updateWallet(requestWalletDto));
    }

    @Test
    void updateWalletReturnNotFundsInWaller() {
        RequestWalletDto requestWalletDto = new RequestWalletDto(1L, OperationType.WITHDRAW, 2000.);
        WalletEntity entity = new WalletEntity(1L, 1000.);
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        assertThrows(NotFoundFundsException.class,
                () -> walletService.updateWallet(requestWalletDto));
    }


    @Test
    void getAccountWallet() {
        WalletEntity entity = new WalletEntity(1L, 1000.);
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        ResponseWalletDto dto = walletService.getAccountWallet(1L);

        assertEquals(dto.getAmount(), entity.getAmount());
    }

    @Test
    void getAccountWalletReturnNotFoundWaller() {
        WalletEntity entity = new WalletEntity(1L, 1000.);
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        ResponseWalletDto dto = walletService.getAccountWallet(2L);

        assertEquals(dto.getAmount(), entity.getAmount());
    }
}