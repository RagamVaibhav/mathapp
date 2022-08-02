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

import org.junit.Test;
import org.mockito.Mock;

public class AdditionFunctionTest {

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    @Mock
    HttpServletResponse res = mock(HttpServletResponse.class);
    @Mock
    HttpSession session = mock(HttpSession.class);

    @Test
    public void testDoGet_PositiveInputs_PositiveOutputs() throws IOException, ServletException {

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("firstNumber")).thenReturn(1);
        when(session.getAttribute("secondNumber")).thenReturn(2);
        req.setAttribute("sum", 0);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(res.getWriter()).thenReturn(pw);

        AdditionFunctionServlet addFuncServlet = new AdditionFunctionServlet();
        assertNotNull(addFuncServlet);

        addFuncServlet.doGet(req, res);
        String result = sw.getBuffer().toString().trim();
        assertEquals(result, "3");
    }
}
