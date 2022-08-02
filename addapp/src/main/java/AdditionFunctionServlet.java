import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addFunc")
public class AdditionFunctionServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        int sum = ((int) session.getAttribute("firstNumber") + (int) session.getAttribute("secondNumber"));
        req.setAttribute("sum", sum);
        System.out.println(sum);
        out.print(sum);
    }
}
