<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%
int v1 = Integer.parseInt(request.getParameter("v1"));
int v2 = Integer.parseInt(request.getParameter("v2"));
String op = request.getParameter("op");
switch (op) { // jsp writer 객체인 out을 사용
  case "+": out.print(v1 + v2); break;
  case "-": out.print(v1 - v2); break;
  case "*": out.print(v1 * v2); break;
  case "/": out.print(v1 / v2); break;
  default: out.print("NAN"); // Not A Number
}
%>