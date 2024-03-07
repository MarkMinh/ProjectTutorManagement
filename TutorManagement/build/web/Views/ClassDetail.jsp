<%-- 
    Document   : Detail
    Created on : 22-Oct-2023, 00:31:47
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
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container-xxl bg-white p-0">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title text-center text-primary text-uppercase">EduMatch</h6>
                        <h1 class="mb-5">Class <span class="text-primary text-uppercase">information</span></h1>
                    </div>
                    
                    <div class="col-lg-6">

                        <div class="wow fadeInUp" data-wow-delay="0.2s">

                            <div class="row g-3">

                                <div class="d-flex justify-content-between mb-3">
                                    <h5 class="mb-0">Class ID : ${c.id}</h5>
                                </div>


                                <div class="d-flex justify-content-between mb-3">
                                    Grade: ${c.reqDetail.gradeLevel}
                                </div>

                                <div class="d-flex justify-content-between mb-3">
                                    Subject: ${c.reqDetail.subjectName}
                                </div>
                                <div class="d-flex justify-content-between mb-3">
                                    Learning type: ${c.reqDetail.learningType == 0 ? 'Online':'Offline'}
                                </div>

                                <div class="d-flex justify-content-between mb-3">
                                    <span class="num">
                                       Amount one hour:  <fmt:formatNumber type="number" value="${c.amountOneHour}" currencyCode="VND" />
                                       VND
                                    </span>
                                </div>
                                
                                <div class="d-flex justify-content-between mb-3">
                                    <c:forEach items="${listSchedule}" var="s">
                                        ${s.dayOfWeek}
                                        <c:choose>
                                            <c:when test="${s.timeSlot == '1'}">
                                                7:00 AM - 9:00 AM
                                            </c:when>
                                            <c:when test="${s.timeSlot == '2'}">
                                                9:30 AM - 11:30 AM
                                            </c:when>
                                            <c:when test="${s.timeSlot == '3'}">
                                                1:00 PM - 3:00 PM
                                            </c:when>
                                            <c:when test="${s.timeSlot == '4'}">
                                                3:30 PM - 5:30 PM
                                            </c:when>
                                            <c:when test="${s.timeSlot == '5'}">
                                                6:00 PM - 8:00 PM
                                            </c:when>
                                            <c:when test="${s.timeSlot == '6'}">
                                                8:30 AM - 10:30 AM
                                            </c:when>
                                        </c:choose>
                                                <br/>
                                    </c:forEach>
                                </div>
                                <hr>
                                <div class="d-flex justify-content-between">
                                    <a href="/PRJ301_SE1761_FA23_HE171380/EndClass?cid=${c.id}" class="btn btn-sm btn-dark rounded py-2 px-4" >End this class</a>                                    
                                </div>

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
