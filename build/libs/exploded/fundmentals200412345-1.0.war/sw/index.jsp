<%-- JSP 주석(comment)
  Created by IntelliJ IDEA.
  User: IN302
  Date: 2022-10-31
  Time: 오후 2:11
  To change this template use File | Settings | File Templates.
--%>
<!-- HTML 주석
page
include
taglib
-->
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // 자바 문법에 따라 작성함. JSP 기본 객체를 사용할 수 있음 : out - JSP 기본 객체중 하나
    // 객체를 생성해서 사용해야 함.
    String name = new String("인덕");
    String param = request.getParameter("iducs");
    out.println("스크립틀릿(scriptlet)" + param );
    String[] arrStr = {"인덕", "컴소", "Java", "Web", "2A"};
    request.setAttribute("attr", arrStr); // Attribute는 EL, JSTL로 처리하면 효과적임
    for(String str : arrStr) {
%>
    <h1> <% out.println(str); %> </h1>
<%
    }
%>
</h1>
<br/>
<%= "표현식 (Expression) : 스클립틀릿에서 출력 편리성 향상 " + param %><br/>
<c:out value="EL & JSTL 활용 : EL은 Expression의 기능을 보강, attribute 접근 편리성 제공, 다양한 연산 제공"/>
<c:forEach items="${attr}" var="item" >
    <img src="../img/micol.png" width="100px"/>
    <h2>${item}</h2>
</c:forEach>


<jsp:include page="about.jsp"></jsp:include>
</body>
</html>
