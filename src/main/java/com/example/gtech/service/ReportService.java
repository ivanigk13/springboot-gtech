package com.example.gtech.service;

import com.example.gtech.dto.ReportResponseDto;
import com.example.gtech.model.ProductEntity;
import com.example.gtech.model.TransactionEntity;
import com.example.gtech.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TransactionRepository transactionRepository;

    public List<ReportResponseDto> getReport(String startDate, String endDate){
        LocalDateTime start = LocalDateTime.of(LocalDate.parse(startDate), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.parse(endDate), LocalTime.MAX);
        List<TransactionEntity> transactions = transactionRepository.findByTransactionDateBetween(start, end);

        return constructResponse(transactions);
    }

    private List<ReportResponseDto> constructResponse(List<TransactionEntity> transactions){
        List<ReportResponseDto> response = new ArrayList<>();
        long no = 1L;
        for(TransactionEntity entity : transactions){
            ReportResponseDto reportDto = new ReportResponseDto();
            ProductEntity product = entity.getProduct();
            reportDto.setNo(no);
            reportDto.setProductId(product.getId());
            reportDto.setProductName(product.getProductName());
            reportDto.setProductType(product.getProductType());
            reportDto.setSellPayment(entity.getTotalCost().toString());
            reportDto.setMarginPayment(marginPayment(entity));
            response.add(reportDto);
            no++;
        }
        return response;
    }

    private String marginPayment(TransactionEntity entity){
        BigDecimal price = entity.getProduct().getProductPrice().multiply(new BigDecimal(entity.getQuantity()));
        return price.multiply(new BigDecimal("0.05")).toString();
    }
}
