package dat.backend.control;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "createOrder", value = "/createOrder")
public class CreateOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String msg = "Din forespørgsel er nu blevet oprette - se status her på din profil side.";
        request.setAttribute("msg",msg);
        request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);

    }
}
