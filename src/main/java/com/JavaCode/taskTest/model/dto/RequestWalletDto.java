package com.JavaCode.taskTest.model.dto;

import com.JavaCode.taskTest.model.enams.OperationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestWalletDto {
    @NotNull
    @Min(1)
    private Long id;
    @NotNull
    private OperationType operationType;
    @NotNull
    @Positive
    private Double amount;
}
