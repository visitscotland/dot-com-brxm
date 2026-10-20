
<!doctype html>
<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<%@ page isErrorPage="true" %>
<% response.setStatus(410); %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>VisitScotland – Website unavailable</title>
  <link rel="icon" type="image/png" href="/site/icons/icon-192-192.png">
</head>
<body>
<h1>We're having trouble loading the website</h1>
<p>We're sorry, but this website is having some technical issual and it is currently unavailable.

  Please try again later. If the problem persists, please visit the main VisitScotland website for the latest information.
</p>

<h3>For developers</h3>
<p>
  If you are seeing this page during development or testing, you may be using an outdated or incorrect version of the website application.

  Please contact the Product Engineer team or Web Operations team.
</p>
</body>
</html>