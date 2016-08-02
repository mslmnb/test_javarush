<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page session="false" %>
<html>
<head>
    <style>
        <%@include file="/home.css" %>
    </style>
    <title>Список дел</title>
</head>

<a href="../../index.jsp">Вернуться</a>

<br/>
<br/>

<h1>Список делишек</h1>

<c:url var="seleAction" value="/tests/filter"/>
<c:set var="filterMsg" value=""/>

<form:form action="${seleAction}" commandName="filter">
    <select name="filter"  id="filter">
        <option value="all">все</option>
        <option value="ready">готов</option>
        <option value="unready">не готов</option>
    </select>
    <input type="submit"
           value="<spring:message text="Фильтр"/>"/>
    <br>
    <c:if test="${filter=='ready'}">
        <br>
        <c:set var="filterMsg" value="Фильтр: выполненные дела"/>
    </c:if>
    <c:if test="${filter=='unready'}">
        <br>
        <c:set var="filterMsg" value="Фильтр: невыполненные дела"/>
    </c:if>

    <c:if test="${empty filterMsg}">
            <br>
    </c:if>
    <c:out value="${filterMsg}"/>
    <br>
</form:form>


<c:if test="${!empty listTests}">
    <table class="tg">
        <!-- заголовок списка -->
        <tr>
            <th width="80">Номер</th>
            <th width="120">Наименование</th>
            <th width="150">Дата создания</th>
            <th width="120">Выполнено</th>
            <th width="150">Дата выполнения</th>

            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <!-- тело списка -->
        <c:forEach items="${listTests}" var="test">
            <tr>
                <td align="right">${test.id}</td>
                <td>${test.name}</td>
                <td align="center">
                    <fmt:formatDate value="${test.createddate}"/>
                </td>
                <td align="center">
                    <c:if test="${test.isready}">
                        <p class="greenText">
                            <c:out value="+"/>
                        </p>
                    </c:if>
                    <c:if test="${!test.isready}">
                        <p class="redText">
                            <c:out value="-"/>
                        </p>
                    </c:if>
                </td>
                <td align="center">
                     <c:if test="${test.isready}">
                        <fmt:formatDate value="${test.readydate}"/>
                    </c:if>
                    <c:if test="!${test.isready}">
                        <c:out value=" "/>
                    </c:if>
                </td>
                <td><a href="<c:url value='/edit/${test.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${test.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<!-- Блок для добавления новой записи -->
<c:if test="${empty test.name}">
    <h1>Добавить новое дельце</h1>
</c:if>
<c:if test="${!empty test.name}">
    <h1>Редактировать дельце</h1>
</c:if>

<c:url var="addAction" value="/tests/add"/>

<form:form action="${addAction}" method="post" commandName="test">
    <table>
        <c:if test="${!empty test.name}">
            <tr>  <!-- номер id -->
                <td>Номер:</td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>  <!-- дата создания -->
                <td>Дата создания:</td>
                <td>
                    <form:input  type="date" path="createddate" />
                </td>
            </tr>
            <tr>
                <td>Выполнено:</td>
                <td>
                    <form:checkbox path="isready"/>
                </td>
            </tr>
            <tr>
                <td>Дата выполнения:</td>
                <td>
                    <form:input  type="date" path="readydate" />
                </td>
            </tr>
        </c:if>
        <tr>   <!-- Наименование -->
            <td>Наименование:</td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty test.name}">
                    <input type="submit"
                           value="<spring:message text="Изменить"/>"/>
                </c:if>
                <c:if test="${empty test.name}">
                    <input type="submit"
                           value="<spring:message text="Добавить"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>



</body>
</html>
