import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdditionServletTest {

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);

    @Mock
    HttpServletResponse res = mock(HttpServletResponse.class);

    @Mock
    HttpSession session = mock(HttpSession.class);

    @Mock
    RequestDispatcher rd = req.getRequestDispatcher("/addFunc");

    HashMap<String,String> map = new HashMap<>();
    @Before
    public void mockInit() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoGet_PositiveInputs_PositiveOutputs() throws IOException, ServletException {
        when(req.getParameter("firstNumber")).thenReturn("1");
        when(req.getParameter("secondNumber")).thenReturn("2");
        when(req.getRequestDispatcher(anyString())).thenReturn(rd);
        map.put("firstNumber","1");
        map.put("secondNumber","2");
        Integer sum = Integer.parseInt(map.get("firstNumber"))+Integer.parseInt(map.get("secondNumber"));
        map.put("sum",sum.toString());

        when(session.getAttribute("sum")).thenReturn(map.get("sum"));
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(res.getWriter()).thenReturn(pw);
        when(req.getSession()).thenReturn(session);

        AddServlet addServlet = new AddServlet();
        assertNotNull(addServlet);

        addServlet.doGet(req, res);
        String result = sw.getBuffer().toString().trim();
        assertEquals(result, "3");
    }

    @Test
    public void addTwoNumbers_Positive_Positive() {
        assertEquals(10, AddServlet.addTwoNumbers(8, 2));
    }

    @Test
    public void addTwoNumbers_PositiveAndNegative_Positive() {
        assertEquals(6, AddServlet.addTwoNumbers(8, -2));
    }

    @Test
    public void addTwoNumbers_PositiveAndNegative_Negative() {
        assertEquals(-6, AddServlet.addTwoNumbers(-8, 2));
    }

    @Test
    public void addTwoNumbers_Negative_Negative() {
        assertEquals(-10, AddServlet.addTwoNumbers(-8, -2));
    }

}
