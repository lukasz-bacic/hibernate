<%@ page language="java" contentType="text/html; harset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="carrent.rent.CarRepository" %>
<%@ page import="carrent.rent.Car" %>
<%@ page import="carrent.rent.CarSegment" %>
<%@ page import="carrent.rent.Make" %>
<%@ page import="carrent.rent.Color" %>
<%@ page import="carrent.rent.EngineType" %>
<%@ page import="carrent.rent.GearBox" %>
<%@ page import="java.util.Optional" %>
<%
    String carId = request.getParameter("carId");
    Integer id = null;
    try {
        id = Integer.valueOf(carId);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    if (id != null) {
        Optional<Car> car = CarRepository.findCar(id);
        if (car.isPresent())
            pageContext.setAttribute("car", car.get());
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Item - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-item.css" rel="stylesheet">

</head>

<body>
<!-- Page Content -->
<div class="container">

    <div class="card card-container">
        <form class="form-signin" action="/editCar" method="post">

            <input type="hidden" value="${car.id}" id="id" name="id"
                   class="form-control">

            <input type="text" value="${car.model}" id="model" name="model" class="form-control"
                   placeholder="Car model" required autofocus>

            <input type="number" value="${car.insuranceCost}" id="insuranceCost" name="insuranceCost"
                   class="form-control"
                   placeholder="Insurance Cost" required autofocus>

            <input type="number" value="${car.capacity}" id="capacity" name="capacity" class="form-control"
                   placeholder="capacity" required autofocus>

            <input type="number" value="${car.basePrice}" id="basePrice" name="basePrice" class="form-control"
                   placeholder="Base Price" required autofocus>

            <select class="form-control" name="segment">
                <c:forEach var="segment" items="${CarSegment.values()}">
                    <c:if test="${car.carSegment.equals(segment)}">
                        <option value="${segment}" selected="selected">${segment}</option>
                    </c:if>
                    <c:if test="${! car.carSegment.equals(segment)}">
                        <option value="${segment}">${segment}</option>
                    </c:if>
                </c:forEach>
            </select>

            <select class="form-control" name="make">
                <c:forEach var="make" items="${Make.values()}">
                    <c:if test="${car.make.equals(make)}">
                        <option value="${make}" selected="selected">${make}</option>
                    </c:if>
                    <c:if test="${!car.make.equals(make)}">
                        <option value="${make}">${make}</option>
                    </c:if>
                </c:forEach>
            </select>

            <select class="form-control" name="color">
                <c:forEach var="color" items="${Color.values()}">
                    <c:if test="${car.color.equals(color)}">
                        <option value="${color}" selected="selected">${color}</option>
                    </c:if>
                    <c:if test="${! car.color.equals(color)}">
                        <option value="${color}">${color}</option>
                    </c:if>
                </c:forEach>
            </select>

            <select class="form-control" name="gearBox">
                <c:forEach var="gearBox" items="${GearBox.values()}">
                    <c:if test="${car.engine.gearBox.equals(gearBox)}">
                        <option value="${gearBox}" selected>${gearBox}</option>
                    </c:if>
                    <c:if test="${! car.engine.gearBox.equals(gearBox)}">
                        <option value="${gearBox}">${gearBox}</option>
                    </c:if>
                </c:forEach>
            </select>

            <select class="form-control" name="engineType">
                <c:forEach var="engineType" items="${EngineType.values()}">
                    <c:if test="${car.engine.engineType.equals(engineType)}">
                        <option value="${engineType}" selected="selected">${engineType}</option>
                    </c:if>
                    <c:if test="${! car.engine.engineType.equals(engineType)}">
                        <option value="${engineType}">${engineType}</option>
                    </c:if>

                </c:forEach>
            </select>

            <input type="number" value="${car.engine.torque}" id="torque" name="torque" class="form-control"
                   placeholder="Torque" required autofocus>

            <input type="number" value="${car.engine.horsePower}" id="horsePower" name="horsePower" class="form-control"
                   placeholder="HorsePower" required autofocus>

            <input type="number" value="${car.engine.fuelConsumption}" id="fuelConsumption" name="fuelConsumption"
                   class="form-control"
                   placeholder="FuelConsumption" required autofocus>

            <input type="number" value="${car.engine.engineCapacity}" id="engineCapacity" name="engineCapacity"
                   class="form-control"
                   placeholder="Engine Capacity" required autofocus>


            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign up</button>

        </form><!-- /form -->
    </div><!-- /card-container -->

</div>
<!-- /.col-lg-9 -->


</div>
<!-- /.container -->
<jsp:include page="footer.jsp"/>