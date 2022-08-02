import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/divserv")
public class DivisionServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int firstNumber = Integer.parseInt(req.getParameter("firstNumber"));
        int secondNumber = Integer.parseInt(req.getParameter("secondNumber"));
        double div = divideTwoNumbers(firstNumber, secondNumber);
        HttpSession session = req.getSession();
        out.print(session.getAttribute("div"));
        session.setAttribute("firstNumber", firstNumber);
        session.setAttribute("secondNumber", secondNumber);
        session.setAttribute("div", div);
        res.sendRedirect("index.jsp");
        out.close();
    }

    public static double divideTwoNumbers(int firstNumber, int secondNumber) {
        return (double) firstNumber / secondNumber;
    }
}
