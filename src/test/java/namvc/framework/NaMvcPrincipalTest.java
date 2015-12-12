package namvc.framework;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class NaMvcPrincipalTest {
    @Test
    public void isInRoleTest() {
        //Arrange
        NaMvcPrincipal test = createPrincipal();

        //Act
        boolean result = test.isInRole("Role1");

        //Assert
        assertTrue(result);
    }

    @Test
    public void isNotInRoleTest() {
        //Arrange
        NaMvcPrincipal test = createPrincipal();

        //Act
        boolean result = test.isInRole("RoleX");

        //Assert
        assertFalse(result);
    }

    @Test
    public void getUserNameTest() {
        //Arrange
        NaMvcPrincipal test = createPrincipal();

        //Act
        String result = test.getUserName();

        //Assert
        assertEquals(result, "user1");
    }

    private NaMvcPrincipal createPrincipal()
    {
        List<String> roles = new ArrayList<String>()
        {
            {
                add("Role1");
                add("Role2");
            }
        };

        return new NaMvcPrincipal("user1",roles);
    }
}
