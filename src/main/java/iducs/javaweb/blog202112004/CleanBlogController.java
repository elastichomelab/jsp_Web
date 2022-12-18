package iducs.javaweb.blog202112004;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

// <form action="contacts" ...  : 요청을 받음 , /는 webapp과 같은 level
@WebServlet(name = "CleanBlogController", value = "/contacts")
public class CleanBlogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // contact.jsp -> CleanBlogController : url은 contacts 임 -> list.jsp 실행 -> Web browser 에 응답 보냄
        // 지정한 페이지, result.jsp에 요청을 전달함
        request.getRequestDispatcher("list.jsp").forward(request, response);
    /*
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "contacts 실행 : </h1>");
        out.println("<h2>" + "요청 name : " + request.getParameter("name") + "</h2>");
        out.println("<h2>" + "요청 email : " + request.getParameter("email") + "</h2>");
        out.println("</body></html>");

     */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
