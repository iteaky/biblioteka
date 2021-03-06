<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
<head>
<form action="/biblioteka/AddPublishing">
<style>
    * {
    box-sizing: border-box;
    }

    input[type=text], select, textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
    }

    label {
    padding: 12px 12px 12px 0;
    display: inline-block;
    }

    input[type=submit] {
    background-color: #4CAF50;
    margin-top: 30px;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
    }

    input[type=submit]:hover {
    background-color: #45a049;
    }

    .container {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
    }

    .col-25 {
    float: left;
    width: 25%;
    margin-top: 6px;
    }

    .col-75 {
    float: left;
    width: 75%;
    margin-top: 6px;
    }

    /* Clear floats after the columns */
    .row:after {
    content: "";
    display: table;
    clear: both;
    }

    /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other
    instead of next to each other */
    @media screen and (max-width: 600px) {
    .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
    }
    }
</style>
</head>
<body>

<h2>Adding new Publishing</h2>

<div class="container">
    <form action="/action_page.php">
        <div class="row">
            <div class="col-25">
                <label for="fname">Name</label>
            </div>
            <div class="col-75">
                <input type="text" id="fname" name="name" placeholder="What is it?"></input>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="lname">City</label>
            </div>
            <div class="col-75">
                <input type="text" id="lname" name="city" placeholder="Where is theirs hood?"></input>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="lname">Phone number</label>
            </div>
            <div class="col-75">
                <input type="text" id="lname" name="phone" placeholder="+1 234 567 89 10"></input>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="lname">Mail</label>
            </div>
            <div class="col-75">
                <input type="text" id="lname" name="email" placeholder="mail@mail.com"></input>
            </div>
        </div>



        <div class="row">
            <input type="submit" value="Submit"></input>
        </div>
    </form>
</div>

</body>
        </html>
