<!doctype html>
<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<%@ page isErrorPage="true" %>
<% response.setStatus(500); %>

<html lang="en">
  <head>
    <meta charset="utf-8"/>
    <title>500 - Internal CMS Error</title>
  </head>
  <body>
      <h1>Temporary Service Unavailable</h1>
      <p>We're sorry, but an internal server error has occurred and the site is temporarily unavailable.</p>
      <p>Please try again later.</p>
      <strong>Developer Information</strong>
      <p>If this error appeared following a code release, you can enable the Diagnosis.jsp page to assist with troubleshooting and root cause analysis.</p>

      <% out.println("<!-- An unexcepted error occurred. The name of the exception is:"); %>
      <%= exception %>
      <% out.println("-->"); %>
  </body>
</html>