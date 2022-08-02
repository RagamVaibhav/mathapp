import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SubstractionServletTest {

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);

    @Mock
    HttpServletResponse res = mock(HttpServletResponse.class);

    @Mock
    HttpSession session = mock(HttpSession.class);

    @Before
    public void mockInit() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoGet_PositiveInputs_PositiveOutputs() throws IOException, ServletException {
        when(req.getParameter("firstNumber")).thenReturn("1");
        when(req.getParameter("secondNumber")).thenReturn("2");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(res.getWriter()).thenReturn(pw);
        when(req.getSession()).thenReturn(session);
    
        SubtractionServlet subtractionServlet = new SubtractionServlet();
        assertNotNull(subtractionServlet);
        
        subtractionServlet.doGet(req, res);
        String result = sw.getBuffer().toString().trim();
        assertEquals(result, "-1");
    }

    @Test
    public void subtractTwoNumbers_PositiveInputs_PositiveOutput() {
        assertEquals(6, SubtractionServlet.subtractTwoNumbers(8, 2));
    }

    @Test
    public void subtractTwoNumbers_PositiveAndNegative_PositiveOutput() {
        assertEquals(10, SubtractionServlet.subtractTwoNumbers(8, -2));
    }

    @Test
    public void subtractTwoNumbers_NegativeAndNegative_NegativeOutput() {
        assertEquals(-6, SubtractionServlet.subtractTwoNumbers(-8, -2));
    }

    @Test
    public void subtractTwoNumbers_NegativeAndNegative_PositiveOutput() {
        assertEquals(6, SubtractionServlet.subtractTwoNumbers(-2, -8));
    }

    @Test
    public void subtractTwoNumbers_NegativeAndPostitive_NegativeOutput() {
        assertEquals(-10, SubtractionServlet.subtractTwoNumbers(-8, 2));
    }
}
