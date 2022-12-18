<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Clean Blog - Start Bootstrap Theme</title>
        <link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Navigation-->
        <%@ include file="../main/nav.jsp"%>
        <header class="masthead" style="background-image: url('../assets/img/home-bg.png')">
            <div class="container position-relative px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="site-heading">
                            <h1>SH's Blog</h1>
                            <span class="subheading">${message} 환영합니다.</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Main Content-->
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-7">
                    <c:forEach items="${requestScope.blogs}" var="blog">
                    <!-- Post preview-->
                    <div class="post-preview">
                        <a href="../blogs/detail.do?id=${blog.id}">
                            <h2 class="post-title">${blog.title}</h2>
                            <h3 class="post-subtitle">${blog.content}</h3>
                        </a>
                        <p class="post-meta"> Author: ${blog.author} </p>
                        <p class="post-meta"> Email: ${blog.email} </p>
                        <p class="post-meta"> Created: ${blog.regdate} </p>
                    </div>
                    <!-- Divider-->
                    <hr class="my-4" />
                    </c:forEach>
                    <!-- Pager-->
                    <div class="d-flex justify-content-end mb-4">
                        <a class="btn btn-primary text-uppercase" href="asc.do">ASC</a>
                        <a class="btn btn-primary text-uppercase" href="desc.do">desc</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer-->
        <%@ include file="../main/footer.jsp"%>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="../js/scripts.js"></script>
    </body>
</html>
