package test.java;

import main.java.namvc.framework.NaMvcHttpSession;
import main.java.namvc.framework.NaMvcPrincipal;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class NaMvcHttpSessionTest {
    @Test
    public void createSessionTest() {
        //Arrange
        NaMvcHttpSession testee = new NaMvcHttpSession(5);
        NaMvcPrincipal principal = createPrincipal();

        //Act
        String sessionId = testee.create(principal);

        //Assert
        assertEquals(principal.getUserName(), testee.getPrincipal(sessionId).getUserName());
    }

    @Test
    public void killSessionTest() {
        //Arrange
        NaMvcHttpSession testee = new NaMvcHttpSession(5);
        NaMvcPrincipal principal = createPrincipal();
        String sessionId = testee.create(principal);

        //Act
        testee.kill(sessionId);

        //Assert
        assertNull(testee.getPrincipal(sessionId));
    }

    private NaMvcPrincipal createPrincipal()
    {
        List<String> roles = new ArrayList<>();
        roles.add("Role1");

        return new NaMvcPrincipal("test",roles);
    }
}
