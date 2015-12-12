package namvc.framework;

import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class NaMvcAuthorizationFilterTest {
    @Test
    public void doFilterWithAuthorizedRoleTest() throws IOException {
    }


    private HttpExchange createHttpExchangeMock()
    {
        HttpExchange mockHttpExchange = mock(HttpExchange.class);
        when(mockHttpExchange.getAttribute("principal")).thenReturn(createPrincipal());
        return mockHttpExchange;
    }

    private NaMvcPrincipal createPrincipal()
    {
        List<String> roles = new ArrayList<>();
        roles.add("Role1");
        return new NaMvcPrincipal("userName",roles);
    }
}