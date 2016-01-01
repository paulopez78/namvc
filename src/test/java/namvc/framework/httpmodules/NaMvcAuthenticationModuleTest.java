package namvc.framework.httpmodules;

import namvc.framework.httpcontext.MvcHttpRequest;
import namvc.framework.httpcontext.MvcHttpResponse;
import namvc.framework.httpcontext.MvcHttpContext;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NaMvcAuthenticationModuleTest {
    @Test
    public void authenticatedTest() throws IOException {
        boolean result = executeAuthentication(true);

        //Assert
        assertTrue(result);
    }

    @Test
    public void notAuthenticatedTest() throws IOException {
        boolean result = executeAuthentication(false);

        //Assert
        assertFalse(result);
    }

    private boolean executeAuthentication(boolean authenticated) throws IOException {
        //Arrange
        NaMvcModule test = new NaMvcAuthenticationModule();
        MvcHttpContext contextMock = createContext(authenticated);

        //Act
        boolean result =test.execute(contextMock);

        //Assert
        verify(contextMock).authenticated();
        return result;
    }

    private MvcHttpContext createContext(boolean authenticated) {
        MvcHttpContext context = mock(MvcHttpContext.class);
        when(context.authenticated()).thenReturn(authenticated);
        when(context.getResponse()).thenReturn(mock(MvcHttpResponse.class));
        when(context.getRequest()).thenReturn(mock(MvcHttpRequest.class));

        return context;
    }
}