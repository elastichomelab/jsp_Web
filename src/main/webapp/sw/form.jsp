<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>회원가입</h1>
<form action="../members" method="post">
    이 메 일 : <input type="text" name="email" />
    암   호 : <input type="password" name="pw"/>
    이   름 : <input type="text" name="name" />
    전화번호 : <input type="text" name="phone" />
    주   소 : <input type="text" name="address" />
  <input type="submit" value="등록"/>
</form>
</body>
</html>
