package iducs.javaweb.blog202112004.controller;

import iducs.javaweb.blog202112004.model.Member;
import iducs.javaweb.blog202112004.repository.MemberDAO;
import iducs.javaweb.blog202112004.repository.MemberDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MemberController", urlPatterns = {"/members/create.do", "/members/detail.do", "/members/list.do",
        "/members/update.do", "/members/delete.do", "/members/login.do", "/members/logout.do"})
public class MemberController extends HttpServlet {
    MemberDAO memberDAOImpl = new MemberDAOImpl();
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // request 객체의 인코딩을 설정, ISO-8859-1 기본
        HttpSession session = request.getSession(); // 세션 객체를 가져옮
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf('/') + 1);
        if(action.equals("create.do")) { // member creating
            int ret = 0;
            Member m = new Member();
            m.setEmail(request.getParameter("email"));
            m.setPw(request.getParameter("pw"));
            m.setName(request.getParameter("name"));
            m.setPhone(request.getParameter("phone"));
            m.setAddress(request.getParameter("address"));

            if((ret = memberDAOImpl.create(m)) > 0) {
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "회원 등록 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if(action.equals("detail.do")) {
            String email = request.getParameter("email");
            //String email = (String) session.getAttribute("logined");
            Member member = new Member();
            member.setEmail(email);
            Member retMember = null;
            if((retMember = memberDAOImpl.read(member)) != null) {
                request.setAttribute("member", retMember); // DB로 부터 가져온 정보를 Member 객체로 반환 후 속성으로 설정
                request.getRequestDispatcher("../members/detail-form.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "회원정보 상세보기에 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if(action.equals("list.do")) { // member listing
            List<Member> memberList = new ArrayList<Member>();
            if((memberList = memberDAOImpl.readList()) != null) {//정상
                request.setAttribute("members", memberList);
                request.getRequestDispatcher("../members/list.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "회원 목록 조회 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
            request.setAttribute("list", memberList);
        }
        else if(action.equals("update.do")){
            int ret = 0;
            Member m = new Member();
            m.setEmail(request.getParameter("email"));
            m.setName(request.getParameter("name"));
            m.setPhone(request.getParameter("phone"));
            m.setAddress(request.getParameter("address"));
            if((ret = memberDAOImpl.update(m)) > 0) {
                request.setAttribute("member", m);
                request.getRequestDispatcher("../members/detail-form.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "회원정보 수정에 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if(action.equals("delete.do")) {
            int ret = 0;
            Member m = new Member();
            m.setEmail(request.getParameter("email"));
            m.setPw(request.getParameter("pw"));
            if((ret = memberDAOImpl.delete(m)) > 0) {
                session.invalidate();
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "회원 탈퇴 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if(action.equals("login.do")) {
            String email = request.getParameter("email");
            String pw = request.getParameter("pw");
            Member member = new Member();
            member.setEmail(email);
            member.setPw(pw);
            Member retMember = null;
            if((retMember = memberDAOImpl.login(member)) != null) {
                session.setAttribute("logined", retMember.getEmail()); // 로그인 상태 유지
                session.setAttribute("author", retMember.getName()); // 로그인 상태 유지
                request.setAttribute("message", retMember.getName()); // 출력 정보를 전달
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "로그인에 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if(action.equals("logout.do")) {
            session.invalidate(); // session 객체에 저장된 모든 속성들을 무효화됨
            response.sendRedirect("../main/index.jsp"); // request, response 객체를 전달하지 않고 페이지 이동
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);

    }
}
