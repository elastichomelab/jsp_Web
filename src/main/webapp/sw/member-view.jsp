<%@ page import="iducs.javaweb.blog202112004.model.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>등록 정보</h1>
<%--
<%= request.getParameter("fullname") %> 님 등록에 성공하였습니다.<br/>
당신의 이메일 주소는 <%= request.getParameter("email")%>
--%>
<%--
    MemberDTO m = (MemberDTO) request.getAttribute("member");
    out.println(m.getFullname());
    out.println(m.getEmail());
--%>
<%-- EL(Expression Language) : attribute 처리 전담 --%>
<%-- requestScope : EL 기본 객체, request : JSP 기본 객체 --%>
${member.fullname} - ${requestScope.member.email}

</body>
</html>
