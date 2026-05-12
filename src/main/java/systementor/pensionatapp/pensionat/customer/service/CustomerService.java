package systementor.pensionatapp.pensionat.customer.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import systementor.pensionatapp.pensionat.booking.repository.BookingRepository;
import systementor.pensionatapp.pensionat.customer.model.CreateCustomerRequest;
import systementor.pensionatapp.pensionat.customer.model.Customer;
import systementor.pensionatapp.pensionat.customer.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public CustomerService(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(request.firstName(), request.lastName(), request.email(), request.phone());
        return customerRepository.save(customer);
    }
}
