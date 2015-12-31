package namvc.controllers;

import namvc.framework.NaMvcPrincipal;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpParameters;
import namvc.framework.httpcontext.NaMvcHttpRequest;
import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.repositories.UsersRepository;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LoginControllerTest {
    @Test
    public void postActionCreatesNewSessionTest() throws Exception {
        //Arrange
        UsersRepository mockUsers = mockUsers(false);
        NaMvcHttpSession mockSession = mockSession();
        NaMvcHttpContext mockContext = mockContext();
        LoginController test = new LoginController(mockUsers);

        //Act
        NaMvcAction result = test.postAction(mockContext);

        //Assert
        verify(mockSession).create(any(NaMvcPrincipal.class));
        verify(mockUsers).authenticate(anyString(),anyString());
        assertNotNull(result);
    }

    @Test(expected=Exception.class)
    public void postActionThrowsExceptionTest() throws Exception {
        //Arrange
        UsersRepository mockUsers = mockUsers(true);
        NaMvcHttpSession mockSession = mockSession();
        NaMvcHttpContext mockContext = mockContext();
        LoginController test = new LoginController(mockUsers);

        //Act
        NaMvcAction result = test.postAction(mockContext);

        //Assert
        verify(mockUsers).authenticate(anyString(),anyString());
        assertNotNull(result);
    }

    private NaMvcHttpSession mockSession()
    {
        NaMvcHttpSession session = mock(NaMvcHttpSession.class);
        return session;
    }

    private NaMvcHttpContext mockContext() throws UnsupportedEncodingException {
        NaMvcHttpContext context = mock(NaMvcHttpContext.class);
        NaMvcHttpRequest request = createRequest();
        when(context.getRequest()).thenReturn(request);
        return context;
    }

    private NaMvcHttpRequest createRequest() throws UnsupportedEncodingException {
        NaMvcHttpRequest request = mock(NaMvcHttpRequest.class);
        when(request.getParameters()).thenReturn(createParameters());
        return request;
    }

    private UsersRepository mockUsers(boolean throwException) throws Exception {
        UsersRepository users = mock(UsersRepository.class);

        if (throwException)
        {
            doThrow(Exception.class).when(users.authenticate(anyString(),anyString()));
        }
        else
        {
            when(users.authenticate(anyString(),anyString())).thenReturn(createPrincipal());
        }
        return users;
    }

    private NaMvcHttpParameters createParameters() throws UnsupportedEncodingException {
        String queryString = "";
        String postString= "login=user1&password=password";
        NaMvcHttpParameters params = new NaMvcHttpParameters(queryString, postString);
        return params;
    }

    private NaMvcPrincipal createPrincipal()
    {
        List<String> roles = new ArrayList<>();
        roles.add("Role1");
        return new NaMvcPrincipal("test",roles);
    }
}
