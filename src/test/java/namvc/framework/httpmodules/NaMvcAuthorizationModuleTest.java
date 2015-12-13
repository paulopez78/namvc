package namvc.framework.httpmodules;

import namvc.framework.NaMvcPrincipal;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpResponse;
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
        assertTrue(result);
    }

    @Test
    public void notAllowedRoleTest() throws IOException {
        boolean result = executeAuthorization("Role1", "RoleX");
        assertFalse(result);
    }

    private boolean executeAuthorization(String userRole, String allowedRole) throws IOException {
        //Arrange
        NaMvcAuthorizationModule test = new NaMvcAuthorizationModule(allowedRole);
        NaMvcPrincipal principal = createPrincipal("user", userRole);
        NaMvcHttpContext contextMock = createContext(principal);

        //Act
        return test.execute(contextMock);
    }

    private NaMvcHttpContext createContext(NaMvcPrincipal principal) throws IOException {
        NaMvcHttpContext context = mock(NaMvcHttpContext.class);
        when(context.getPrincipal()).thenReturn(principal);
        when(context.getResponse()).thenReturn(mock(NaMvcHttpResponse.class));
        return context;
    }

    private NaMvcPrincipal createPrincipal(String user, String role)
    {
        List<String> roles = new ArrayList<>();
        roles.add(role);
        return new NaMvcPrincipal(user,roles);
    }
}