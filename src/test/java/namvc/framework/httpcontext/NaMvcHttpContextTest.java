package namvc.framework.httpcontext;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import namvc.framework.NaMvcPrincipal;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class NaMvcHttpContextTest {

    @Test
    public void loginTest()
    {
        //Arrange
        HttpExchange exchangeMock = createHttpExchangeMock();
        MvcHttpSession sessionMockMock = createMvcHttpSessionMock();
        NaMvcPrincipal principal = createPrincipal();
        MvcHttpContext test = new NaMvcHttpContext(exchangeMock, sessionMockMock);

        //Act
        test.login(principal);

        //Assert
        verify(sessionMockMock).create(any(NaMvcPrincipal.class));
    }

    @Test
    public void logoutTest()
    {
        //Arrange
        HttpExchange exchangeMock = createHttpExchangeMock();
        MvcHttpSession sessionMockMock = createMvcHttpSessionMock();
        MvcHttpContext test = new NaMvcHttpContext(exchangeMock, sessionMockMock);

        //Act
        test.logout();

        //Assert
        verify(sessionMockMock).kill(anyString());
    }

    private HttpExchange createHttpExchangeMock()
    {
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        try {
            when(httpExchangeMock.getRequestBody()).thenReturn(new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            });
            when(httpExchangeMock.getRequestURI()).thenReturn(new URI("http://www.test.com"));
            when(httpExchangeMock.getRequestHeaders()).thenReturn(new Headers());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpExchangeMock;
    }

    private MvcHttpSession createMvcHttpSessionMock()
    {
        MvcHttpSession sessionMock = mock(MvcHttpSession.class);
        return sessionMock;
    }

    private NaMvcPrincipal createPrincipal()
    {
        List<String> roles = new ArrayList<>();
        roles.add("Role1");

        return new NaMvcPrincipal("test",roles);
    }
}
