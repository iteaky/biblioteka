<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
<head>
<form action="/biblioteka/Book">
<style>
    * {
    box-sizing: border-box;
    }
    .button {
    display: inline-block;
    border-radius: 4px;
    background-color: #f4511e;
    border: none;
    color: #FFFFFF;
    text-align: center;
    font-size: 18px;
    padding: 10px;
    width: 300px;
    transition: all 0.5s;
    cursor: pointer;
    margin: 5px;
    }

    .button span {
    cursor: pointer;
    display: inline-block;
    position: relative ;
    transition: 0.5s;
    }

    .button span:after {
    content: '\00bb';
    position: relative ;
    opacity: 0;
    top: 0;
    right: -20px;
    transition: 0.5s;
    }

    .button:hover span {
    padding-right: 25px;
    }

    .button:hover span:after {
    opacity: 1;
    right: 0;
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
<script>
    function cloneLink(form) {

    var el = document.getElementById('test');
    var a = form.elements.a.value;
    var b = form.elements.b.value;
    var newA = cloneAndAddHandler(el, a, b);
    newA.innerHTML = 'test' + a + ',' + b;
    document.body.appendChild( document.createElement('br') );
    }

</script>
</head>
<body>

<h2>Adding new Book</h2>
<p>You can choose Author or Publishing FROM \"biblioteka\".extending, or you should previously create new one</p>

<div class="container">
    <form action="/action_page.php">
        <div class="row">
            <div class="col-25">
                <label for="fname">Book name</label>
            </div>
            <div class="col-75">
                <input type="text" id="fname" name="firstname" placeholder="What is it?"></input>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="lname">Release date</label>
            </div>
            <div class="col-75">
                <input type="text" id="lname" name="releaseDate" placeholder="DD-MM-YYYY"></input>
            </div>
        </div>


        <div class="row">
            <div class="col-25">
                <label for="country">Author</label>
            </div>
            <div class="col-75">
                <select id="country" name="author"  multiple>
                    <c:forEach var="author" items="${authors}" >
                        <option value="${author.id}">${author.name} ${author.secondName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="country">Publishing
                </label>
            </div>
            <div class="col-75">
                <select id="country" name="publishing">
                    <c:forEach var="publishing" items="${publishings}">
                        <option value="${publishing.id}">${publishing.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <input type="submit" value="Submit"></input>
        </div>
    </form>
    <form action="/biblioteka/Author">
        <button name="addAuthor" value="true" class="button" style="vertical-align:middle"><span>Add Author </span></button>
    </form>
    <form action="/biblioteka/Publishing">
        <button name="addPublishing" value="true" class="button" style="vertical-align:middle"><span>Add Publishing </span></button>
    </form>
</div>

</body>
        </html>
