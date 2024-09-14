package com.JavaCode.taskTest.service.impl;

import com.JavaCode.taskTest.exceptions.NotFoundFundsException;
import com.JavaCode.taskTest.exceptions.NotFoundWalletException;
import com.JavaCode.taskTest.mapper.WalletMapper;
import com.JavaCode.taskTest.model.dto.RequestWalletDto;
import com.JavaCode.taskTest.model.dto.ResponseWalletDto;
import com.JavaCode.taskTest.model.entity.WalletEntity;
import com.JavaCode.taskTest.repository.WalletRepository;
import com.JavaCode.taskTest.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public ResponseWalletDto updateWallet(RequestWalletDto walletDto) {
        WalletEntity walletEntity = findWalletId(walletDto.getId());
        switch (walletDto.getOperationType()) {
            case DEPOSIT -> depositAmount(walletDto, walletEntity);
            case WITHDRAW -> withdrawAmount(walletDto, walletEntity);
        }
        return WalletMapper.toResponseWalletDto(walletRepository.save(walletEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseWalletDto getAccountWallet(Long walletId) {
        WalletEntity walletEntity = findWalletId(walletId);
        return WalletMapper.toResponseWalletDto(walletEntity);
    }

    private void depositAmount(RequestWalletDto walletDto, WalletEntity entity) {
        entity.setAmount(entity.getAmount() + walletDto.getAmount());
    }

    private void withdrawAmount(RequestWalletDto walletDto, WalletEntity entity) {
        if (entity.getAmount() < walletDto.getAmount()) {
            throw new NotFoundFundsException("there are not enough funds in the wallet");
        }
        entity.setAmount(entity.getAmount() - walletDto.getAmount());
    }

    private WalletEntity findWalletId(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() ->
                new NotFoundWalletException("Not found wallet"));
    }
}
