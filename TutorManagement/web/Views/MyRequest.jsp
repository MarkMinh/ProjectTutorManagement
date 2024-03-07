<%-- 
    Document   : MyRequest
    Created on : 29-Oct-2023, 14:29:44
    Author     : ADMIN
--%>

<%@page import="DAL.*" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title text-center text-primary text-uppercase">EduMatch</h6>
                        <h1 class="mb-5">List <span class="text-primary text-uppercase">request</span></h1>
                    </div>
                    
            </div>
        </div>
    </div>
</div>
</div>

<div class="container-xxl py-5">
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title text-center text-primary text-uppercase">Our Students</h6>
            <h1 class="mb-5">Explore Our <span class="text-primary text-uppercase">Students</span></h1>
        </div>
        <c:if test="${empty listR}">
            <h1>There's no request</h1>
        </c:if>
        <c:if test="${not empty listR}">
            <div class="row g-4">
                <c:forEach items="${listR}" var="s">
                    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="room-item shadow rounded overflow-hidden">
                            <!--                        <div class="position-relative">
                                                        <img class="img-fluid" src="img/room-1.jpg" alt="">
                                                        <small class="position-absolute start-0 top-100 translate-middle-y bg-primary text-white rounded py-1 px-3 ms-4">$100/Night</small>
                                                    </div>-->
                            <div class="position-relative">
                                ${s.learningType == 0 ? 'Online':'Offline'}
                            </div>
                            <div class="p-4 mt-2">

                                <div class="d-flex justify-content-between mb-3">
                                    <h5 class="mb-0">${s.userR.fullName}</h5>
                                    <div class="ps-2">
                                        Grade: ${s.gradeLevel}
                                    </div>
                                </div>

                                <div class="d-flex mb-3">
                                    <small class="border-end me-3 pe-3">${s.userR.ward.name}</small>
                                    <small class="border-end me-3 pe-3">${s.userR.district.name}</small>
                                    <small>${s.userR.province.name}</small>
                                </div>
                                <p class="text-body mb-3">
                                    ${s.hourlyMinRate} - ${s.hourlyMaxRate} VND / hour
                                </p>
                                <div class="d-flex justify-content-between">
                                    <a href="/PRJ301_SE1761_FA23_HE171380/EditRequest?rid=${s.id}" class="btn btn-sm btn-primary rounded py-2 px-4" >Edit Request</a>
                                    <a href="/PRJ301_SE1761_FA23_HE171380/DeleteRequest?rid=${s.id}" class="btn btn-sm btn-dark rounded py-2 px-4" >Delete Request</a>
                                </div>
                                    
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            

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
