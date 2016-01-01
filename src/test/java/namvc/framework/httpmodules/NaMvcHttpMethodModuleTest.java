package namvc.framework.httpmodules;

import namvc.framework.NaMvcController;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpactions.RenderAction;
import namvc.framework.httpcontext.MvcHttpRequest;
import namvc.framework.httpcontext.MvcHttpResponse;
import namvc.framework.httpcontext.MvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpContext;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NaMvcHttpMethodModuleTest {
    @Test
    public void executeGetMethodTest() throws IOException {
        boolean result = executeHttpMethod("GET");

        //Assert
        assertTrue(result);
    }

    @Test
    public void executePostMethodTest() throws IOException {
        boolean result = executeHttpMethod("POST");

        //Assert
        assertTrue(result);
    }

    private boolean executeHttpMethod(String method) throws IOException {
        //Arrange
        NaMvcController controller = createController();
        NaMvcModule test = new NaMvcHttpMethodModule(controller);
        MvcHttpContext contextMock = createContext(method);

        //Act
        boolean result =test.execute(contextMock);

        //Assert
        if (method.equals("GET"))
        {
            verify(controller).getAction(any(NaMvcHttpContext.class));
        }
        if (method.equals("POST"))
        {
            verify(controller).postAction(any(NaMvcHttpContext.class));
        }

        return result;
    }

    private NaMvcController createController()
    {
        NaMvcController controller = mock(NaMvcController.class);
        when(controller.getAction(any(MvcHttpContext.class)))
                .thenReturn(new RenderAction(""));

        when(controller.postAction(any(MvcHttpContext.class)))
                .thenReturn(new RedirectAction(""));
        return controller;
    }

    private MvcHttpContext createContext(String method) {
        MvcHttpContext context = mock(MvcHttpContext.class);
        MvcHttpRequest request = createRequest(method);
        when(context.getResponse()).thenReturn(mock(MvcHttpResponse.class));
        when(context.getRequest()).thenReturn(request);

        return context;
    }

    private MvcHttpRequest createRequest (String method)
    {
        MvcHttpRequest request = mock(MvcHttpRequest.class);
        when(request.getMethod()).thenReturn(method);
        return request;
    }
}