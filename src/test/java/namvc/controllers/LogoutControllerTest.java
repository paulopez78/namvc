package namvc.controllers;

import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpResponse;
import namvc.framework.httpcontext.NaMvcHttpSession;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LogoutControllerTest {
    @Test
    public void postActionKillsSessionTest() throws Exception {
        //Arrange
        NaMvcHttpSession mockSession = mockSession();
        NaMvcHttpContext mockContext = mockContext();
        LogoutController test = new LogoutController();

        //Act
        NaMvcAction result = test.postAction(mockContext);

        //Assert
        verify(mockSession).kill(anyString());
        assertNotNull(result);
    }

    private NaMvcHttpSession mockSession()
    {
        NaMvcHttpSession session = mock(NaMvcHttpSession.class);
        //when(session.getTimeout()).thenReturn(1);
        return session;
    }

    private NaMvcHttpContext mockContext() throws IOException {
        NaMvcHttpResponse response = createResponse();
        NaMvcHttpContext context = mock(NaMvcHttpContext.class);
        when(context.getResponse()).thenReturn(response);
        //when(context.getSessionId()).thenReturn("1");
        return context;
    }

    private NaMvcHttpResponse createResponse() throws IOException {
        NaMvcHttpResponse response = mock(NaMvcHttpResponse.class);
        doNothing().when(response).addHeader(anyString(), anyList());
        doNothing().when(response).sendHttpCode(anyInt(), anyInt());
        return response;
    }
}
