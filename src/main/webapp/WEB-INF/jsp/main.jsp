<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
<html>
<head>
    <style>
        .button {
        display: inline-block;
        border-radius: 4px;
        background-color: #4CAF50;
        border: none;
        color: #FFFFFF;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        text-align: center;
        font-size: 15px;
        font-weight: bold
        padding: 10px;
        width: 100px;
        transition: all 0.5s;
        cursor: pointer;
        margin: 5px;
        align:left
        }

        .button span {
        cursor: pointer;
        display: inline-block;
        position: relative;
        transition: 1s;
        }

        .button span:after {
        content: '+add';
        font-size: 25px;
        position: relative;
        opacity: 0;
        top: 0;
        right: -100px;
        transition: 0.5s;
        }

        .button:hover span {
        padding-right: 200px;
        }

        .button:hover span:after {
        opacity: 1;
        right: 0;
        }

        #customers {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
        }

        #customers td, #customers th {
        border: 1px solid #ddd;
        padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #4CAF50;
        color: white;
        }
        #top {
        font-size: 25px;
        }
    </style>
</head>
<body>
    <form action="/biblioteka/MainServlet">
    <table id="customers">
        <tr id="top">
            <th>
                <button id="top" class="button" style="vertical-align:middle">
                    <span>Book</span>
                </button>
            </th>
            <th>
                <span>Author</span>
            </th>
            <th>
                <span>Publishing</span>
            </th>
            <th>
                Release Date
            </th>
        </tr>
        <c:forEach var="item" items="${books}">
            <tr>
                <td>${item.name}</td>
                <td>
                    <c:forEach var="author" items="${item.author}" varStatus="loop">
                        ${author.name} ${author.secondName} ${not loop.last ? ', ' : ''}
                    </c:forEach>
                </td>
                <td>${item.publishing.name}</td>
                <td>${item.dateOfRealise}</td>

            </tr>
        </c:forEach>
    </table>

</body>
</html>
