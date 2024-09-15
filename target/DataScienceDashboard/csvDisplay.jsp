<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://jakarta.apache.org/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>CSV Display</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <style>
      .table-container {
        max-height: 400px;
        max-width: 800px;
        overflow: auto;
        border: 1px solid #ddd;
        padding: 10px;
      }
    </style>
  </head>
  <body>
    <div class="container mt-5">
      <h2 class="text-center">Uploaded CSV Data</h2>
      <div class="table-container">
        <table class="table table-striped">
          <thead>
            <tr>
              <!-- Dynamically render table headers here -->
              <c:forEach var="header" items="${headers}">
                <th>${header}</th>
              </c:forEach>
            </tr>
          </thead>
          <tbody>
            <!-- Dynamically render table rows here -->
            <c:forEach var="row" items="${rows}">
              <tr>
                <c:forEach var="cell" items="${row}">
                  <td>${cell}</td>
                </c:forEach>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
