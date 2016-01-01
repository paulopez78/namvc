package namvc.framework.httpcontext;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import static org.mockito.Mockito.*;

public class NaMvcHttpResponseTest {
    @Test
    public void sendHttpCodeTest() throws IOException {
        //Arrange
        HttpExchange exchangeMock = createHttpExchangeMock();
        MvcHttpResponse test = new NaMvcHttpResponse(exchangeMock);

        //Act
        test.sendHttpCode(500, -1);

        //Assert
        verify(exchangeMock).sendResponseHeaders(anyInt(), anyInt());
        verify(exchangeMock).getResponseHeaders();
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
}
