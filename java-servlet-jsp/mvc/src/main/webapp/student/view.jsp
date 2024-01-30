<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
  <title>학생-조회</title>
  <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
  <tbody>
  <!-- todo view 구현 -->

    <tr>
      <th>아이디</th>
      <th>${student.id}</th>
    </tr>
    <tr>
      <th>이름</th>
      <th>${student.name}</th>
    </tr>
    <tr>
      <th>성별</th>
      <th>${student.gender}</th>
    </tr>
    <tr>
      <th>나이</th>
      <th>${student.age}</th>
    </tr>
    <tr>
      <th>cmd</th>
      <th>${cfmt:formatDate(student.createdAt, 'yyyy-MM-dd HH:mm:ss')}</th>
    </tr>
  </tbody>
</table>
<ul>
  <li><a href="/student/list.do">리스트</a></li>
  <li>
    <!-- todo ${update_link} 설정 c:url -->
    <c:url var="update_link" value="/student/update.do">
      <c:param name="id" value="${student.id}"></c:param>
    </c:url>
    <a href="${update_link}">수정</a>
  </li>
  <li>
    <!-- todo 삭제버튼 구현, method=post -->
<form method="post" action="/student/delete.do">
  <input type="hidden" name="id" value="${student.id}">
  <button type="submit">삭제 </button>
</form>
 </li>

</ul>

</body>
</html>