package namvc.framework;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class NaMvcAppTest {
    @Test
    public void routeTest() throws IOException {
        //Arrange
        NaMvcApp test = new NaMvcApp(8000,5);
        NaMvcController mockController = mock(NaMvcController.class);

        //Act
        test.route("/test", mockController);

        //Assert
        assertTrue(true);
    }
}
