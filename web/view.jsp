<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practise JAXB Web</title>

        <script type="text/javascript" src="FetchRepresentatives.js"></script>
        <script type="text/javascript">
            const loadRepresentatives = (select) => {
                console.log("Start: loadRepresenatives");

                var fr = new FetchRepresentatives();
                fr.loadRepresentatives();
            }
        </script>


    </head>
    <body>
        <h1>Practise JAXB Web</h1>

        <form method="POST">

            <select id = "countrySelect" name = "countrySelect"  style = "margin-right: 50px" size = "10" onclick="loadRepresentatives(this)">
                <c:forEach var="country" items="${countries}">
                    <option>${country.name}</option>
                </c:forEach>

            </select>

            <select style = "margin-right: 50px" size="10" name="representativeSelect" id="representativeSelect" >
                <option></option>
                <option></option>
            </select>

            <select style = "margin-right: 50px" size="10" name="customerSelect" id = "customerSelect">
                <option></option>
                <option></option>
            </select>

        </form>

    </body>
</html>
