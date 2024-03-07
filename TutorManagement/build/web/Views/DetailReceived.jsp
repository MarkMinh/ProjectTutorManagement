<%-- 
    Document   : DetailReceived
    Created on : 29-Oct-2023, 13:30:16
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>EduMatch</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">  

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/c203902cd2.js" crossorigin="anonymous"></script>

        <!-- Libraries Stylesheet -->
        <link href="/PRJ301_SE1761_FA23_HE171380/lib/animate/animate.min.css" rel="stylesheet">
        <link href="/PRJ301_SE1761_FA23_HE171380/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="/PRJ301_SE1761_FA23_HE171380/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="/PRJ301_SE1761_FA23_HE171380/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="/PRJ301_SE1761_FA23_HE171380/css/style_1.css" rel="stylesheet">
        <style>
            .rateBot{
                padding-bottom: 100px;
            }
            .rating {
                display: inline-block;
                font-size: 0;
            }

            .rating input {
                display: none;
            }

            .rating label {
                margin: 10px;
                cursor: pointer;
                width: 1em;
                font-size: 24px;
                color: #ddd;
                padding: 0 0.1em;
                display: inline-block;
            }

            .rating label:before {
                content: "★";
            }

            .rating input:checked ~ label {
                color: #f90;
            }

            .rating:not(:checked) > label:hover,
            .rating:not(:checked) > label:hover ~ label {
                color: #f90;
            }

            .rating > input:checked + label:hover,
            .rating > input:checked ~ label:hover,
            .rating > label:hover ~ input:checked ~ label,
            .rating > input:checked ~ label:hover ~ label {
                color: #ff0;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container-xxl bg-white p-0">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title text-center text-primary text-uppercase">EduMatch</h6>
                        <h1 class="mb-5">Detail <span class="text-primary text-uppercase">information</span></h1>
                    </div>
                    <div class="row g-5">
                        <div class="col-lg-6">
                            <div class="row g-3">
                                <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.1s" src="/PRJ301_SE1761_FA23_HE171380/img/${uS.img}" style="margin-top: 25%;">                                                
                        </div>

                    </div>

                    <div class="col-lg-6">

                        <div class="wow fadeInUp" data-wow-delay="0.2s">

                            <div class="row g-3">

                                <div class="d-flex justify-content-between mb-3">
                                    <h5 class="mb-0">${uS.fullName}</h5>
                                </div>
                                <div class="d-flex justify-content-between mb-3">
                                    Gender:
                                    <c:choose>
                                        <c:when test="${uS.gender == 0}">Male</c:when>
                                        <c:when test="${uS.gender == 1}">Female</c:when>
                                        <c:when test="${uS.gender == 2}">Others</c:when>
                                        <c:otherwise>Unknown</c:otherwise>
                                    </c:choose>
                                </div>


                                <div class="d-flex mb-3">

                                    <small class="border-end me-3 pe-3">${uS.ward.name}</small>
                                    <small class="border-end me-3 pe-3">${uS.district.name}</small>
                                    <small>${uS.province.name}</small>
                                </div>
                                <c:if test="${not empty listSub}">


                                    <div class="d-flex justify-content-between mb-3">
                                        <p style="color: green">
                                            Some most subject taught: 
                                            <c:forEach items="${listSub}" var="sub">
                                                |  ${sub}  
                                            </c:forEach>
                                        </p>
                                    </div>

                                </c:if>

                                <div class="d-flex mb-3">
                                    <h5>Contact</h5> <br/>
                                    PhoneNumber: ${uS.phoneNumber} <br/>
                                    Email: ${uS.email}
                                </div>
                                <div class="d-flex mb-3">
                                    <h5>Rating</h5> <br/>
                                    ${rateScore}/5 <br/>
                                    ${numRate} rated
                                </div>
                                <hr>

                                <c:if test="${rd.endTime == null}">
                                    <a href="/PRJ301_SE1761_FA23_HE171380/AcceptControl?uid=${uS.username}&rid=${rid}" class="btn btn-lg btn-primary text-uppercase"> Accept </a>
                                    <a href="/PRJ301_SE1761_FA23_HE171380/DenyControl?uid=${uS.username}" class="btn btn-lg btn-dark text-uppercase"> Deny </a>
                                </c:if>
                                <h4>Feedback</h4>
                                <c:forEach items="${listRating}" var="r">
                                    <div class="col-4"><img style="width: 100px; heigth: 100px; border-radius: 50%" src="/PRJ301_SE1761_FA23_HE171380/img/${dao.getUser(r.studentName).img}">
                                        <p>${r.studentName} </p></div>
                                    <div class="col-2">${r.rating}/5</div>
                                    <div class="col-6">${r.comment}</div>


                                </c:forEach>
                                <hr>
                                <c:if test="${listStdRate.contains(sessionScope.user.username)}"> 
                                    <form id="ratingForm" action="/PRJ301_SE1761_FA23_HE171380/DetailReceived" method="post">
                                        <div class="rating">
                                            <h4>Your rating: </h4>
                                            <input type="radio" id="star1" name="rating" value="5" />
                                            <label for="star1"></label>
                                            <input type="radio" id="star2" name="rating" value="4" />
                                            <label for="star2"></label>
                                            <input type="radio" id="star3" name="rating" value="3" />
                                            <label for ="star3"></label>
                                            <input type="radio" id="star4" name="rating" value="2" />
                                            <label for="star4"></label>
                                            <input type="radio" id="star5" name="rating" value="1" />
                                            <label for="star5"></label>
                                        </div>
                                        <input type="text" name="uid" value="${uid}" hidden/>
                                        <input type="text" name="rid" value="${rid}" hidden/>

                                        <div class="col-4"><img style="width: 100px; heigth: 100px; border-radius: 50%" src="/PRJ301_SE1761_FA23_HE171380/img/${sessionScope.user.img}">
                                            <p>${sessionScope.user.username}</p></div>
                                        <textarea style="position: relative; top: 0px; word-wrap: break-word;" class="col-12" name="comment"></textarea>
                                        <input type="submit" value="Feedback"/>
                                        <div class="rateBot"></div>
                                    </form>
                                </c:if>
                                <script>
                                    document.addEventListener("DOMContentLoaded", function () {
                                        var ratingForm = document.getElementById("ratingForm");
                                        var ratingInputs = ratingForm.querySelectorAll('input[type="radio"]');
                                        ratingForm.addEventListener("submit", function (event) {
                                            var isRatingSelected = false;

                                            // Kiểm tra xem có một đánh giá nào đã được chọn chưa
                                            ratingInputs.forEach(function (input) {
                                                if (input.checked) {
                                                    isRatingSelected = true;
                                                }
                                            });

                                            // Nếu không có đánh giá nào được chọn, ngăn chặn form submit
                                            if (!isRatingSelected) {
                                                event.preventDefault();
                                                alert("Please select a rating before submitting.");
                                            }
                                        });
                                    });
                                </script>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="/PRJ301_SE1761_FA23_HE171380/js/script.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="/PRJ301_SE1761_FA23_HE171380/lib/wow/wow.min.js"></script>
        <script src="/PRJ301_SE1761_FA23_HE171380/lib/easing/easing.min.js"></script>
        <script src="/PRJ301_SE1761_FA23_HE171380/lib/waypoints/waypoints.min.js"></script>
        <script src="/PRJ301_SE1761_FA23_HE171380/lib/counterup/counterup.min.js"></script>
        <script src="/PRJ301_SE1761_FA23_HE171380/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="/PRJ301_SE1761_FA23_HE171380/lib/tempusdominus/js/moment.min.js"></script>
        <script src="/PRJ301_SE1761_FA23_HE171380/lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="/PRJ301_SE1761_FA23_HE171380/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="/PRJ301_SE1761_FA23_HE171380/js/main.js"></script>
    </body>
</html>
