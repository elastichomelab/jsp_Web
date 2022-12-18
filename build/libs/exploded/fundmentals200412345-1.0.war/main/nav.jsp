<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light"  id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/main/index.jsp">
            <c:choose>
                <c:when test="${sessionScope.logined != null}">
                    ${sessionScope.author}'s Blog
                </c:when>
                <c:otherwise>
                    Blog
                </c:otherwise>
            </c:choose>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <%-- JSTL은 EL이나 HTML 태그 사용이 편리하다 --%>
            <c:choose>
                    <c:when test="${sessionScope.logined == null}">
                    <!-- 로그인 전 -->
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/create-form.jsp">회원 등록</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/login-form.jsp">로그인</a></li>
                    </c:when>
                    <c:otherwise>
                    <!-- 로그인 후 : session -->
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/get.do">블로그목록</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do">블로그 작성</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/logout.do">로그아웃</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/detail.do?email=${sessionScope.logined}">(상세조회)<br/> 회원정보 수정/삭제 </a></li>
                     </c:otherwise>
            </c:choose>
            <c:choose>
               <c:when test="${sessionScope.author == 'sw202112004'}">
                    <!-- 관리자 모드 -->
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/list.do">목록조회</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/delete-form.jsp">블로그 삭제</a></li>
               </c:when>
            </c:choose>

                    <%--블로그--%>
            </ul>
        </div>
    </div>
</nav>