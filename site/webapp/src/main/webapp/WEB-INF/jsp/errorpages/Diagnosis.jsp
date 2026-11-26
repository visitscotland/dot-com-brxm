<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<!doctype html>
<%@ page isErrorPage="true" %>
<% response.setStatus(500); %>

<%!
  private String escapeHtml(String value) {
    if (value == null) return "";
    return value.replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
  }
%>

<html lang="en">
<head>
  <meta charset="utf-8"/>
  <title>500 error</title>
</head>
<body>
<h1>CMS Internal Server error</h1>
<% out.println("<!-- An unexcepted error occurred. The name of the exception is:"); %>
<%= exception %>
<% out.println("Stacktrace:"); %>
<%
  if (exception != null) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    exception.printStackTrace(pw);
    pw.flush();
    out.print(escapeHtml(sw.toString()));
  } else {
    out.print("No exception available.");
  }
%>

<% out.println("-->"); %>
</body>
</html>
