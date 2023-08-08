package com.buddycode.customer;

import com.buddycode.clients.fraud.FraudCheckResponse;
import com.buddycode.clients.fraud.FraudClient;
import com.buddycode.clients.notification.NotificationClient;
import com.buddycode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw  new IllegalStateException("fraudster");
        }

        notificationClient.sendNotification(new NotificationRequest("Hi Welcome to BuddyCode", customer.getEmail(), customer.getId()));
        customerRepository.save(customer);

    }
}
