package systementor.pensionatapp.pensionat.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import systementor.pensionatapp.pensionat.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
