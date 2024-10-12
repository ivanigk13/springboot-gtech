package com.example.gtech.dto;

import lombok.Data;

@Data
public class ReportResponseDto {

    private Long no;
    private Long productId;
    private String productName;
    private String productType;
    private String sellPayment;
    private String marginPayment;
}
