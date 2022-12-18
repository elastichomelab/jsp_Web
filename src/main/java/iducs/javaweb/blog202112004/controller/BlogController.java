package iducs.javaweb.blog202112004.controller;

import iducs.javaweb.blog202112004.model.Blog;
import iducs.javaweb.blog202112004.model.Member;
import iducs.javaweb.blog202112004.repository.BlogDAOImpl;
import iducs.javaweb.blog202112004.repository.MemberDAOImpl;
import iducs.javaweb.blog202112004.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "post", urlPatterns = {"/blogs/post-form.do", "/blogs/post.do",
        "/blogs/get.do", "/blogs/detail.do",
        "/blogs/updateForm.do", "/blogs/update.do",
        "/blogs/delete.do", "/blogs/asc.do", "/blogs/desc.do" }) //urlPatterns : 다수의 url 을 기술할 수 있다.
public class BlogController extends HttpServlet {
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do, blog/list.do가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1); // post.do, list.do 반환

        BlogDAOImpl dao = new BlogDAOImpl();

        if (action.equals("post-form.do")) {
            Member member = new Member();
            Blog blog = new Blog();
            member.setEmail(request.getParameter("logined"));
            member.setName(request.getParameter("name"));

            request.setAttribute("loginedEmail", member.getEmail()); //email로 조회한 회원 정보 객체를 member라는 키로 request에 attribute로 저장
            request.setAttribute("loginedName", member.getName()); //email로 조회한 회원 정보 객체를 member라는 키로 request에 attribute로 저장

            request.getRequestDispatcher("../blogs/post-form.jsp").forward(request, response);

        }
        else if (action.equals("post.do")) {
            Blog blog = new Blog();
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            blog.setAuthor(request.getParameter("author"));
            blog.setEmail(request.getParameter("email"));
            //데이터베이스 처리 요청 또는 서비스 요청 코드 추가
            if (dao.create(blog) > 0) {
                request.setAttribute("blog", blog);
                //처리 결과를 view에 전달
                request.setAttribute("work", "블로그 작성 성공");
                request.getRequestDispatcher("../status/success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if (action.equals("get.do")) {
            List<Blog> blogList = new ArrayList<Blog>(); //처리결과 한개 이상의 블로그를 저장하는 객체
            if ((blogList = (ArrayList<Blog>) dao.readList()) != null) {
                request.setAttribute("blogs", blogList);
                request.getRequestDispatcher("../blogs/list-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 목록 조회 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if (action.equals("delete.do")) {
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if (dao.delete(blog) > 0) {
                request.setAttribute("blog", blog);
                request.setAttribute("work", "블로그 삭제를");
                // 처리 결과를 view에 전달한다. message.jsp -> processok.jsp
                request.getRequestDispatcher("../status/success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if (action.equals("update.do")) {
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            blog.setAuthor(request.getParameter("author"));
            blog.setEmail(request.getParameter("email"));

            if(dao.update(blog) > 0){
                request.setAttribute("blog", blog);
                request.setAttribute("work", "블로그 수정을");
                request.getRequestDispatcher("../status/success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if (action.equals("detail.do")) {
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            Blog retBlog = null;
            if ((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog); // key -> blog
                //session.setAttribute("blog", "로그인정보");
                request.getRequestDispatcher("../blogs/detail-form.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 조회를");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response); // 오류
            }
        }
        else if (action.equals("asc.do")){
            List<Blog> blogList = new ArrayList<Blog>(); //처리결과 한개 이상의 블로그를 저장하는 객체
            if ((blogList = (ArrayList<Blog>) dao.asc()) != null) {
                request.setAttribute("blogs", blogList);
                request.getRequestDispatcher("../blogs/list-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 목록 조회 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if (action.equals("desc.do")){
            List<Blog> blogList = new ArrayList<Blog>(); //처리결과 한개 이상의 블로그를 저장하는 객체
            if ((blogList = (ArrayList<Blog>) dao.readList()) != null) {
                request.setAttribute("blogs", blogList);
                request.getRequestDispatcher("../blogs/list-view.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "블로그 목록 조회 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }
}
