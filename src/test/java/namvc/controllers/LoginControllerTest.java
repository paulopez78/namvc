package namvc.controllers;

import namvc.framework.NaMvcPrincipal;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpcontext.MvcHttpContext;
import namvc.framework.httpcontext.MvcHttpRequest;
import namvc.framework.httpcontext.NaMvcHttpParameters;
import namvc.repositories.UsersRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LoginControllerTest {
    @Test
    public void getActionAlreadyLoggedInTest() throws Exception {
        //Arrange
        UsersRepository mockUsers = mockUsers(false);
        MvcHttpContext mockContext = mockContext(true);
        LoginController test = new LoginController(mockUsers);

        //Act
        NaMvcAction result = test.getAction(mockContext);

        //Assert
        verify(mockContext).authenticated();
        verify(mockContext).getPrincipal();
        assertTrue(result instanceof RenderAction);
    }

    @Test
    public void getActionNotAuthenticatedTest() throws Exception {
        //Arrange
        UsersRepository mockUsers = mockUsers(false);
        MvcHttpContext mockContext = mockContext(false);
        LoginController test = new LoginController(mockUsers);

        //Act
        NaMvcAction result = test.getAction(mockContext);

        //Assert
        verify(mockContext).authenticated();
        verify(mockContext).getRequest();
        assertTrue(result instanceof RenderAction);
    }

    @Test
    public void postActionLogInTest() throws Exception{
        //Arrange
        UsersRepository mockUsers = mockUsers(false);
        MvcHttpContext mockContext = mockContext(false);
        LoginController test = new LoginController(mockUsers);

        //Act
        NaMvcAction result = test.postAction(mockContext);

        //Assert
        verify(mockUsers).authenticate(anyString(),anyString());
        verify(mockContext).login(any(NaMvcPrincipal.class));
        assertTrue(result instanceof RedirectAction);
    }

    @Test(expected=Exception.class)
    public void postActionThrowsExceptionTest() throws Exception {
        //Arrange
        UsersRepository mockUsers = mockUsers(true);
        MvcHttpContext mockContext = mockContext(false);
        LoginController test = new LoginController(mockUsers);

        //Act
        NaMvcAction result = test.postAction(mockContext);

        //Assert
        verify(mockUsers).authenticate(anyString(),anyString());
        verify(mockContext, never()).login(any(NaMvcPrincipal.class));
        assertTrue(result instanceof RenderAction);
    }

    private MvcHttpContext mockContext(boolean authenticated)  {
        MvcHttpContext context = mock(MvcHttpContext.class);
        MvcHttpRequest request = createRequest();
        when(context.getRequest()).thenReturn(request);
        when(context.getPrincipal()).thenReturn(createPrincipal());
        when(context.authenticated()).thenReturn(authenticated);
        return context;
    }

    private MvcHttpRequest createRequest()  {
        MvcHttpRequest request = mock(MvcHttpRequest.class);
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

    private NaMvcHttpParameters createParameters()  {
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
