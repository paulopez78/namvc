package namvc.framework.httpmodules;

import namvc.framework.NaMvcPrincipal;
import namvc.framework.httpcontext.MvcHttpResponse;
import namvc.framework.httpcontext.MvcHttpContext;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NaMvcAuthorizationModuleTest {
    @Test
    public void allowedRoleTest() throws IOException {
        boolean result = executeAuthorization("Role1", "Role1");

        //Assert
        assertTrue(result);
    }

    @Test
    public void notAllowedRoleTest() throws IOException {
        boolean result = executeAuthorization("Role1", "RoleX");

        //Assert
        assertFalse(result);
    }

    private boolean executeAuthorization(String userRole, String allowedRole) throws IOException {
        //Arrange
        NaMvcAuthorizationModule test = new NaMvcAuthorizationModule(allowedRole);
        NaMvcPrincipal principal = createPrincipal("user", userRole);
        MvcHttpContext contextMock = createContext(principal);

        //Act
        boolean result =test.execute(contextMock);

        //Assert
        verify(contextMock).getPrincipal();
        return result;
    }

    private MvcHttpContext createContext(NaMvcPrincipal principal) throws IOException {
        MvcHttpContext context = mock(MvcHttpContext.class);
        when(context.getPrincipal()).thenReturn(principal);
        when(context.getResponse()).thenReturn(mock(MvcHttpResponse.class));
        return context;
    }

    private NaMvcPrincipal createPrincipal(String user, String role)
    {
        List<String> roles = new ArrayList<>();
        roles.add(role);
        return new NaMvcPrincipal(user,roles);
    }
}