package namvc.framework.httpmodules;

import namvc.framework.httpcontext.MvcHttpRequest;
import namvc.framework.httpcontext.MvcHttpResponse;
import namvc.framework.httpcontext.MvcHttpContext;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NaMvcStaticFilesModuleTest {
    @Test
    public void staticFileDetectedTest() throws IOException {
        boolean result = executeStaticFilesModule(true);

        //Assert
        assertFalse(result);
    }

    @Test
    public void staticFileNotDetectedTest() throws IOException {
        boolean result = executeStaticFilesModule(false);

        //Assert
        assertTrue(result);
    }

    private boolean executeStaticFilesModule(boolean staticFileDetected) throws IOException {
        //Arrange
        NaMvcModule test = new NaMvcStaticFilesModule();
        MvcHttpContext contextMock = createContext(staticFileDetected);

        //Act
        boolean result =test.execute(contextMock);

        //Assert
        verify(contextMock).getRequest();
        return result;
    }

    private MvcHttpContext createContext(boolean staticFileDetected) {
        MvcHttpContext context = mock(MvcHttpContext.class);
        MvcHttpRequest request = createRequest(staticFileDetected);
        when(context.getResponse()).thenReturn(mock(MvcHttpResponse.class));
        when(context.getRequest()).thenReturn(request);

        return context;
    }

    private MvcHttpRequest createRequest (boolean staticFileDetected)
    {
        MvcHttpRequest request = mock(MvcHttpRequest.class);
        if (staticFileDetected)
        {
            when(request.getPath()).thenReturn("/file.txt");
        }
        else
        {
            when(request.getPath()).thenReturn("/route");
        }

        return request;
    }
}