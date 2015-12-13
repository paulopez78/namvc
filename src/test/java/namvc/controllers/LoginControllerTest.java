package namvc.controllers;

import namvc.Users;
import namvc.framework.NaMvcPrincipal;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LoginControllerTest {
    @Test
    public void postActionTest() throws Exception {
        //Arrange
        Users mockUsers = mockUsers();
        NaMvcHttpSession mockSession = mockSession();
        NaMvcHttpContext mockContext = mockContext();
        LoginController test = new LoginController(mockUsers);

        //Act
        NaMvcAction result = test.postAction(mockSession, mockContext);

        //Assert
        //verify(mockUsers).authenticate(anyString(),anyString());
        assertNotNull(result);
    }

    private NaMvcHttpSession mockSession()
    {
        NaMvcHttpSession session = mock(NaMvcHttpSession.class);

        return session;
    }

    private NaMvcHttpContext mockContext()
    {
        NaMvcHttpContext context = mock(NaMvcHttpContext.class);
        return context;
    }

    private Users mockUsers() throws Exception {
        Users users = mock(Users.class);
        when(users.authenticate(anyString(),anyString())).thenReturn(createPrincipal());
        return users;
    }

    private NaMvcPrincipal createPrincipal()
    {
        List<String> roles = new ArrayList<>();
        roles.add("Role1");

        return new NaMvcPrincipal("test",roles);
    }
}
