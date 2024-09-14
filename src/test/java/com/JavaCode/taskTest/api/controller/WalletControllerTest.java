package com.JavaCode.taskTest.api.controller;

import com.JavaCode.taskTest.service.WalletService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class WalletControllerTest {

    public static final String API = "http://localhost:8080/api/v1/wallet";

    public static final String JSON = """
            {
              "id": 1,
              "operationType": "DEPOSIT",
              "amount": 100.50
            }""";
    @MockBean
    WalletService walletService;

    @Autowired
    MockMvc mockMvc;


    @Test
    @SneakyThrows
    void postUpdateWallet() {
        mockMvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk());
        verify(walletService).updateWallet(any());
    }

    @Test
    @SneakyThrows
    void postUpdateWalletWithNegativeId() {
        String wallet = """
                {
                  "id": -1,
                  "operationType": "DEPOSIT",
                  "amount": 100.50
                }""";
        mockMvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(wallet)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(not(emptyOrNullString())));
    }

    @Test
    @SneakyThrows
    void postUpdateWalletWithNullId() {
        String wallet = """
                {
                  "id": 0,
                  "operationType": "DEPOSIT",
                  "amount": 100.50
                }""";
        mockMvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(wallet)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(not(emptyOrNullString())));
    }

    @Test
    @SneakyThrows
    void postUpdateWalletWithUnknownOperatorOfType() {
        String wallet = """
                {
                  "id": 1,
                  "operationType": "NULL",
                  "amount": 100.50
                }""";

        mockMvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(wallet)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(not(emptyOrNullString())));
    }

    @Test
    @SneakyThrows
    void postUpdateWalletWithNegativedAmount() {
        String wallet = """
                {
                  "id": 1,
                  "operationType": "DEPOSIT",
                  "amount": -100.50
                }""";
        mockMvc.perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(wallet)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(not(emptyOrNullString())));
    }

    @Test
    @SneakyThrows
    void getAccountWeller() {
        mockMvc.perform(get(API + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk());
        verify(walletService).getAccountWallet(anyLong());
    }

    @Test
    @SneakyThrows
    void getAccountWellerWithNullId() {
        mockMvc.perform(get(API + "/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void getAccountWellerWithNegativeId() {
        mockMvc.perform(get(API + "/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void getAccountWellerWithCharId() {
        mockMvc.perform(get(API + "/f")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest());
    }
}