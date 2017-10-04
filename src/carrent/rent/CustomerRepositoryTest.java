package carrent.rent;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by Lukasz on 04.10.2017.
 */
public class CustomerRepositoryTest {

    public static final String EMAIL = "test@gmail.com";

    @Test
    public void findByEmail() throws Exception {

        Optional<Customer> byEmail = CustomerRepository.findByEmail(EMAIL);
        Assert.assertTrue("shoudl return user by email", byEmail.isPresent());
    }

    @Test
    public void update() throws Exception {
    }

}