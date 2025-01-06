<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>아이디 찾기 결과</title>
  </head>
  <body>
    <% String email = request.getParameter("email"); String foundId =
    "example_id"; %>

    <h2>아이디 찾기 결과</h2>

    <% if (foundId != null) { %>
    <p>찾으시는 아이디는 <%= foundId %>입니다.</p>
    <% } else { %>
    <p>해당 계정으로 등록된 아이디가 없습니다.</p>
    <% } %>
  </body>
</html>
