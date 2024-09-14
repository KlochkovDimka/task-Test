package com.JavaCode.taskTest.api.controller;

import com.JavaCode.taskTest.model.dto.RequestWalletDto;
import com.JavaCode.taskTest.model.dto.ResponseWalletDto;
import com.JavaCode.taskTest.service.WalletService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wallet")
@RequiredArgsConstructor
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseWalletDto postUpdateWeller(@Validated @RequestBody RequestWalletDto walletDto) {
        return walletService.updateWallet(walletDto);
    }

    @GetMapping("/{WALLET_UUID}")
    public ResponseWalletDto getAccountWeller(@Positive @PathVariable Long WALLET_UUID) {
        return walletService.getAccountWallet(WALLET_UUID);
    }
}
