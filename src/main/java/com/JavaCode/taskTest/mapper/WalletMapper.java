package com.JavaCode.taskTest.mapper;

import com.JavaCode.taskTest.model.dto.ResponseWalletDto;
import com.JavaCode.taskTest.model.entity.WalletEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WalletMapper {
    public static ResponseWalletDto toResponseWalletDto(WalletEntity walletEntity) {
        if (walletEntity == null) {
            return null;
        }
        return ResponseWalletDto.builder()
                .amount(walletEntity.getAmount())
                .build();
    }
}
