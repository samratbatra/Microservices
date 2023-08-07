package com.buddycode.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class FraudCheckHistoryService {


    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;


    public boolean isFraudulentCustomer(Integer customerId){

        FraudCheckHistory fraudCheckHistory = FraudCheckHistory.builder().customerId(customerId).isFraudster(false).createdAt(LocalDateTime.now()).build();
        try{
            fraudCheckHistoryRepository.save(fraudCheckHistory);
        } catch( Exception e){
            e.printStackTrace();
            log.info(" EXCEPTION--------" +e.getMessage());
        }


        return false;

    }

}
