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

public class MultiplicationServletTest {

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
        when(req.getParameter("firstNumber")).thenReturn("4");
        when(req.getParameter("secondNumber")).thenReturn("2");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(res.getWriter()).thenReturn(pw);
        when(req.getSession()).thenReturn(session);
    
        MultiplicationServlet multiplicationServlet = new MultiplicationServlet();
        assertNotNull(multiplicationServlet);
        
        multiplicationServlet.doGet(req, res);
        String result = sw.getBuffer().toString().trim();
        assertEquals(result, "8");
    }
    @Test
    public void multiplyTwoNumbers_PositiveInputs_PositiveOutput(){
        assertEquals(16,MultiplicationServlet.multiplyTwoNumbers(8, 2));
    }
    
    @Test
    public void multiplyTwoNumbers_PositiveAndNegative_NegativeOutput(){
        assertEquals(-16,MultiplicationServlet.multiplyTwoNumbers(8, -2));
    }

    @Test
    public void multiplyTwoNumbers_egativeAndPositiveN_NegativeOutput(){
        assertEquals(-16,MultiplicationServlet.multiplyTwoNumbers(-8, 2));
    }

    @Test
    public void multiplyTwoNumbers_NegativeInputs_PositiveOutput(){
        assertEquals(16,MultiplicationServlet.multiplyTwoNumbers(-8, -2));
    }

    @Test
    public void multiplyTwoNumbers_Zero_ZeroOutput(){
        assertEquals(0,MultiplicationServlet.multiplyTwoNumbers(8, 0));
    }
}
