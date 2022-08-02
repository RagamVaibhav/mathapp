import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addserv")
public class AddServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        int firstNumber = Integer.parseInt(req.getParameter("firstNumber"));
        int secondNumber = Integer.parseInt(req.getParameter("secondNumber"));

        // int sum = addTwoNumbers(firstNumber, secondNumber);
        HttpSession session = req.getSession();
        session.setAttribute("firstNumber", firstNumber);
        session.setAttribute("secondNumber", secondNumber);
        req.setAttribute("sum", "0");
        System.out.println(req.getAttribute("sum"));

        RequestDispatcher rq = req.getRequestDispatcher("/addFunc");
        rq.include(req, res);
        
        System.out.println(req.getAttribute("sum"));
        session.setAttribute("sum", req.getAttribute("sum"));
        out.println(session.getAttribute("sum"));

        res.sendRedirect("index.jsp");
        out.close();
    }

    public static int addTwoNumbers(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
