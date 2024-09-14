package com.JavaCode.taskTest.mapper;

import com.JavaCode.taskTest.model.dto.ResponseWalletDto;
import com.JavaCode.taskTest.model.entity.WalletEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class WalletMapperTest {

    @Test
    void toResponseWalletDto() {
        WalletEntity walletEntity = WalletEntity.builder()
                .id(1L)
                .amount(1000.)
                .build();

        ResponseWalletDto responseWalletDto = ResponseWalletDto.builder()
                .amount(1000.)
                .build();

        ResponseWalletDto dto = WalletMapper.toResponseWalletDto(walletEntity);
        assertEquals(dto.getAmount(), responseWalletDto.getAmount());
    }
}