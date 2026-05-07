package systementor.pensionatapp.pensionat.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotBlank(message = "Förnamn måste anges")
    private String firstName;

    @NotBlank(message = "Efternamn måste anges")
    private String lastName;

    @NotBlank(message = "E-post måste anges")
    @Email(message = "E-post måste vara giltig")
    private String email;

    private String phone;


    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "Förnamn måste anges") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "Förnamn måste anges") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Efternamn måste anges") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Efternamn måste anges") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "E-post måste anges") @Email(message = "E-post måste vara giltig") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "E-post måste anges") @Email(message = "E-post måste vara giltig") String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
