<%-- 
    Document   : Edit
    Created on : 13-Oct-2023, 01:03:13
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
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
        <c:if test="${sessionScope.user == null}">
            <h1> Access denied </h1>
        <li class="nav-item">
            <a class="nav-link" href="/PRJ301_SE1761_FA23_HE171380/HomeControl">Click here to return home</a>
        </li>
    </c:if>
    <jsp:include page="Menu.jsp"></jsp:include>
    <c:if test="${sessionScope.user != null}">
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h6 class="section-title text-center text-primary text-uppercase">EduMatch</h6>
                    <h1 class="mb-5">Edit your <span class="text-primary text-uppercase">information</span></h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-6">
                        <div class="row g-3">
                            <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.1s" src="/PRJ301_SE1761_FA23_HE171380/img/${uS.img}" style="margin-top: 25%;">

                        </div>
                        <script>

                        </script>
                    </div>
                    <div class="col-lg-6">
                        <div class="wow fadeInUp" data-wow-delay="0.2s">
                            <form action="/PRJ301_SE1761_FA23_HE171380/DeleteAccount" method="post"  >
                                <div class="row g-3">

                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" name="username" class="form-control" id="name"  value="${uS.username}" readonly>
                                            <label for="name">Username</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" name="fullName" class="form-control" id="fullName" placeholder="Your Full Name" value="${uS.fullName}" readonly>
                                            <label for="fullName">Full name</label>
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.user.isActive == 1}">
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input type="email" name="email" class="form-control" id="email" placeholder="Your Email" value="${uS.email}" readonly>
                                                <label for="email">Email</label>
                                            </div>
                                        </div>
                                    </c:if>


                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" class="form-control" id="genderInput" name="gender" value="${uS.gender == 0 ? 'Male' : uS.gender == 1 ? 'Female' : 'Others'}" readonly>
                                            <label for="genderInput">Gender</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input id="select2" class="form-control" type="date" name="dob" value="${uS.dob}" readonly>
                                            <label for="select2">Your birthday</label>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" value="${uS.phoneNumber}" readonly>
                                            <label for="phoneNumber">Phone Number</label>
                                        </div>
                                    </div>



                                    <div class="col-4">
                                        <div class="form-floating">
                                            <div id="ward" class="form-control">${uS.ward.name}</div>
                                              <label for="ward">Ward</label>

                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-floating">

                                            <div id="district" class="form-control">${uS.district.name}</div>
                                            <label for="district">District</label>

                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-floating">
                                            <div id="province" class="form-control">${uS.province.name}</div>
                                            <label for="province">Province</label>
                                        </div>
                                    </div>


                                    <div class="col-12">
                                        <button class="btn btn-dark w-100 py-3" type="submit">Delete this account</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Booking End -->   
    </c:if>
    <!-- JavaScript -->



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