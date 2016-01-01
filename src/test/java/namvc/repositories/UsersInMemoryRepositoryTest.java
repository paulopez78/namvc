package namvc.repositories;

import namvc.framework.NaMvcPrincipal;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsersInMemoryRepositoryTest {
    @Test
    public void authenticationPassedTest() throws Exception {
        UsersRepository test = new UsersInMemoryRepository();
        NaMvcPrincipal result = test.authenticate("user1","password");

        assertTrue(result.getUserName().equals("user1"));
    }

    @Test(expected=Exception.class)
    public void authenticationFailedTest() throws Exception {
        UsersRepository test = new UsersInMemoryRepository();
        NaMvcPrincipal result = test.authenticate("user1","wrongpassword");

        assertNull(result);
    }
}