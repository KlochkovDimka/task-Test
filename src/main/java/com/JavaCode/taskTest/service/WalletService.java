package com.JavaCode.taskTest.service;

import com.JavaCode.taskTest.model.dto.RequestWalletDto;
import com.JavaCode.taskTest.model.dto.ResponseWalletDto;

public interface WalletService {

    ResponseWalletDto updateWallet(RequestWalletDto walletDto);

    ResponseWalletDto getAccountWallet(Long walletId);
}
