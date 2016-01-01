package namvc.framework.httpcontext;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NaMvcHttpRequestTest {

    @Test
    public void getPathTest()
    {
        //Arrange
        HttpExchange exchangeMock = createHttpExchangeMock();
        MvcHttpRequest test = new NaMvcHttpRequest(exchangeMock);

        //Act
        String result = test.getPath();

        //Assert
        assertTrue(result.equals("/test"));
    }

    @Test
    public void getMethodTest()
    {
        //Arrange
        HttpExchange exchangeMock = createHttpExchangeMock();
        MvcHttpRequest test = new NaMvcHttpRequest(exchangeMock);

        //Act
        String result = test.getMethod();

        //Assert
        assertTrue(result.equals("GET"));
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
            when(httpExchangeMock.getRequestURI()).thenReturn(new URI("http://localhost/test"));
            when(httpExchangeMock.getRequestHeaders()).thenReturn(new Headers());
            when(httpExchangeMock.getRequestMethod()).thenReturn("GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpExchangeMock;
    }
}
