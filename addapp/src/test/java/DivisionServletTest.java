import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DivisionServletTest extends HttpServlet{

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);

    @Mock
    HttpServletResponse res = mock(HttpServletResponse.class);

    @Mock
    HttpSession session = mock(HttpSession.class);

    HashMap<String,String> map = new HashMap<>();

    @Before
    public void mockInit() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoGet_PositiveInputs_PositiveOutputs() throws IOException, ServletException {
        when(req.getParameter("firstNumber")).thenReturn("10");
        when(req.getParameter("secondNumber")).thenReturn("2");
        map.put("firstNumber","10");
        map.put("secondNumber","2");
        Integer division = (Integer.parseInt(map.get("firstNumber"))/Integer.parseInt(map.get("secondNumber")));
        map.put("div",division.toString());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(res.getWriter()).thenReturn(pw);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("div")).thenReturn(map.get("div"));

        DivisionServlet divisionServlet = new DivisionServlet();
        assertNotNull(divisionServlet);

        divisionServlet.doGet(req, res);
        String result = sw.getBuffer().toString().trim();

        when(session.getAttribute("div")).thenReturn(result);
        assertEquals(result, "5");
    }

    @Test
    public void divideTwoNumbers_NumbersDivisible_IntegerOutput() {
        assertEquals(4.0, DivisionServlet.divideTwoNumbers(8, 2), 0.01);
    }

    @Test
    public void divideTwoNumbers_NumbersNotDivisible_DecimalOutput() {
        assertEquals(5.5, DivisionServlet.divideTwoNumbers(11, 2), 0.01);
    }

    @Test
    public void divideTwoNumbers_PositiveAndNegative_NegativeOutput() {
        assertEquals(-4.0, DivisionServlet.divideTwoNumbers(-8, 2), 0.01);
    }

    @Test
    public void divideTwoNumbers_NegativeAndPositive_NegativeOutput() {
        assertEquals(-4.0, DivisionServlet.divideTwoNumbers(8, -2), 0.01);
    }

    @Test
    public void divideTwoNumbers_NegativeInputs_PositiveOutput() {
        assertEquals(4.0, DivisionServlet.divideTwoNumbers(-8, -2), 0.01);
    }

}
