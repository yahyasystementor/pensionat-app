package systementor.pensionatapp.pensionat.customer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import systementor.pensionatapp.pensionat.booking.repository.BookingRepository;
import systementor.pensionatapp.pensionat.customer.model.Customer;
import systementor.pensionatapp.pensionat.customer.repository.CustomerRepository;
import org.mockito.Mockito.*;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {


    @Mock
    private CustomerRepository customerRepository;


    @Mock private BookingRepository bookingRepository;


    private CustomerService customerService;

    private Customer customer1;
    private Customer customer2;

    private List<Customer> fakeCustomers ;


    @BeforeEach
    void setUp() {

        customerService = new CustomerService(
                customerRepository,
                bookingRepository
        );


         customer1 = new Customer(
                "Yahya",
                "Hussein",
                "yahya@test.se",
                "070000000"
        );

        customer2 = new Customer(
                "Rebecca",
                "Eriksson",
                "Rebecca@test.se",
                "070000001"
        );

        fakeCustomers = List.of(
                customer1,
                customer2
        );
    }




    @Test
    void getAllCustomers_ShouldReturnAllCustomersFromRepository() {

        // Arrange

        when(customerRepository.findAll())
                .thenReturn(fakeCustomers);

        // Act
        List<Customer> result = customerService.getAllCustomers();


        // Assert
        assertThat(result)
                .hasSize(2);
        assertThat(result)
                .containsExactly(customer1,customer2);

        verify(customerRepository)
                .findAll();
        verifyNoMoreInteractions(customerRepository);

        verifyNoMoreInteractions(bookingRepository);

    }



    // TODO CREATE TEST FOR createUser Method

    // Method saves users

    // Method saves correct user



}





// Mock fejkat objekt

// Stub förbereder ett svar (when(repo.findAll()).then....

// Fake implementation av fejkade objektet
