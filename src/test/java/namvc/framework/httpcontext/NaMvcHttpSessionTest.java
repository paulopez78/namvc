package namvc.framework.httpcontext;

import namvc.framework.NaMvcPrincipal;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class NaMvcHttpSessionTest {
    @Test
    public void createSessionTest() {
        //Arrange
        NaMvcHttpSession test = new NaMvcHttpSession(5);
        NaMvcPrincipal principal = createPrincipal();

        //Act
        String result = test.create(principal);

        //Assert
        assertEquals(principal.getUserName(), test.getPrincipal(result).getUserName());
    }

    @Test
    public void killSessionTest() {
        //Arrange
        NaMvcHttpSession test = new NaMvcHttpSession(5);
        NaMvcPrincipal principal = createPrincipal();
        String sessionId = test.create(principal);

        //Act
        test.kill(sessionId);

        //Assert
        assertNull(test.getPrincipal(sessionId));
    }

    private NaMvcPrincipal createPrincipal()
    {
        List<String> roles = new ArrayList<>();
        roles.add("Role1");

        return new NaMvcPrincipal("test",roles);
    }
}
