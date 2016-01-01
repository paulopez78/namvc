package namvc.controllers;

import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;
import namvc.framework.httpcontext.MvcHttpContext;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LogoutControllerTest {
    @Test
    public void postActionLogOutTest() throws Exception {
        //Arrange
        MvcHttpContext mockContext = mockContext();
        LogoutController test = new LogoutController();

        //Act
        NaMvcAction result = test.postAction(mockContext);

        //Assert
        verify(mockContext).logout();
        assertTrue(result instanceof RedirectAction);
    }

    private MvcHttpContext mockContext() throws IOException {
        MvcHttpContext context = mock(MvcHttpContext.class);
        return context;
    }
}
