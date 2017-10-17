<%@ page import="carrent.rent.CustomerRepository" %>
<%@ page import="carrent.rent.Customer" %>
<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; harset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  Integer userId = (Integer) request.getSession().getAttribute("userId");
  if(userId != null){
    Optional<Customer> customer = CustomerRepository.findById(userId);
    if(customer.isPresent()){
        pageContext.setAttribute("customer", customer.get());
    }
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
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Janusz rent</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">

            <li class="nav-item">
              <c:if test="${empty customer}">
                <a class="nav-link btn btn-danger" href="/login.jsp">Zaloguj </a>
              </c:if>
              <c:if test="${not empty customer}">
                <a class="nav-link btn btn-danger" href="/wyloguj">Wyloguj </a>
              </c:if>

            </li>

            <li class="nav-item">${customer.firstName} ${customer.lastName}</li>

            <li class="nav-item active">
              <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/myRent.jsp">Historia</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Contact</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>