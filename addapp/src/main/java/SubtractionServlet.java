import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/subserv")
public class SubtractionServlet extends HttpServlet{
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int firstNumber = Integer.parseInt(req.getParameter("firstNumber"));
        int secondNumber = Integer.parseInt(req.getParameter("secondNumber"));
        int diff = subtractTwoNumbers(firstNumber,secondNumber);
        System.out.println(diff);
        out.print(diff);
        HttpSession session = req.getSession();
        session.setAttribute("firstNumber", firstNumber);
        session.setAttribute("secondNumber", secondNumber);
        session.setAttribute("diff", diff);
        res.sendRedirect("index.jsp");
        out.close();
    }

    public static int subtractTwoNumbers(int firstNumber,int secondNumber){
        return firstNumber-secondNumber;
    }
}
