<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Raj Yadav
  Date: 02-08-2024
  Time: 02:50 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Raj Pages | Report App | JRTP </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">

    <h3 class="pb-3 pt-3">Report Application</h3>

    <%--<div class="form-check">
        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
        <label class="form-check-label" for="flexRadioDefault1">
            Male
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
        <label class="form-check-label" for="flexRadioDefault2">
            Fe-Male
        </label>
    </div>


    <button class="btn btn-danger">Search</button>--%>

    <form:form action="search" modelAttribute="search" method="post">
        <table>
            <tr>
                <td>Plan Name :</td>
                <td>
                    <form:select path="planName">
                        <form:option value="">--Select--</form:option>
                        <form:options items="${names}"/>
                    </form:select>
                </td>
                <td>Plan Status :</td>
                <td>
                    <form:select path="planStatus">
                        <form:option value="">--Select--</form:option>
                        <form:options items="${status}"/>
                    </form:select>
                </td>
                <td>
                    Gender:
                </td>
                <td>
                    <form:select path="gender">
                        <form:option value="">--Select--</form:option>
                        <form:option value="Male">Male</form:option>
                        <form:option value="Female">Female</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>Start Date</td>
                <td>
                    <form:input type="date" data-date-format="yyyy-MM-dd" path="startDate"/>
                </td>
                <td>End Date</td>
                <td>
                    <form:input type="date" data-date-format="yyyy-MM-dd" path="endDate"/>
                </td>
            </tr>

            <tr>

                <td>
                    <a href="/" class="btn btn-danger">Reset</a>
                </td>
                <td>
                    <input type="submit" value="Search" class="btn btn-primary"/>
                </td>
            </tr>
        </table>
    </form:form>
    <hr/>
    <table class="table table-striped table-hover table-dark">
        <thead>
        <tr>
            <th>ID</th>
            <th>Holder Name</th>
            <th>Gender</th>
            <th>Plan Name</th>
            <th>Plan Status</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Benifit Amount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${plans}" var="plan" varStatus="index">
            <tr>
                    <%--                    <td>${plan.citizenId}</td>--%>
                <td>${index.count}</td>
                <td>${plan.citizenName}</td>
                <td>${plan.gender}</td>
                <td>${plan.planName}</td>
                <td>${plan.planStatus}</td>
                <td>${plan.planStartDate}</td>
                <td>${plan.planEndDate}</td>
                <td>${plan.benefitAmt}</td>
            </tr>
        </c:forEach>

        <tr>

            <c:if test="${empty plans}">
               <td colspan="7" style="text-align: center">No Record Found</td>
            </c:if>
        </tr>


        </tbody>
    </table>


    <hr/>

    <div>
        <p>Export :
            <a href="">EXCEL</a>
            <a href="">PDF</a>

        </p>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

</body>
</html>
