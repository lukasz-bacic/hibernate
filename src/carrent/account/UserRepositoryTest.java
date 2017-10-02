package carrent.account;



import carrent.rent.Option;
import org.junit.Assert;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Lukasz on 02.10.2017.
 */
public class UserRepositoryTest {
    private static final String EMAIL = "test@gmail.com";
    public static final String TAJNE_HASLO = "tajneHaslo";


    @org.junit.Test
    public void findUserByEmailAndPassword() throws Exception {
        Optional<User> user = UserRepository.findUserByEmailAndPassword(EMAIL, TAJNE_HASLO);
        Assert.assertTrue("find User", user.isPresent());
    }

    @org.junit.Test
    public void save() throws Exception {

        User user = new User(EMAIL, TAJNE_HASLO);

        Assert.assertTrue("add User",UserRepository.save(user));
    }



}
