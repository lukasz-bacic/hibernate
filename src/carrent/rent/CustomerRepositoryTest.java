package carrent.rent;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by Lukasz on 04.10.2017.
 */
public class CustomerRepositoryTest {

    public static final String EMAIL = "test@gmail.com";
    public static final String PHONE_NUMBER = "888 333 000";

    @Test
    public void findByEmail() throws Exception {

        Optional<Customer> byEmail = CustomerRepository.findByEmail(EMAIL);
        Assert.assertTrue("shoudl return user by email", byEmail.isPresent());
    }

    @Test
    public void update() throws Exception {
        Optional<Customer> byEmail = CustomerRepository.findByEmail(EMAIL);
        if(byEmail.isPresent()){
            Customer customer = byEmail.get();
            customer.setPhoneNumber(PHONE_NUMBER);
            Assert.assertTrue("should saveOrUpdate customer", CustomerRepository.update(customer));

            Customer customerEdit = CustomerRepository.findByEmail(EMAIL).get();
            Assert.assertTrue("should phone by equals",
                    customerEdit.getPhoneNumber().equals(PHONE_NUMBER));

        }


    }

}