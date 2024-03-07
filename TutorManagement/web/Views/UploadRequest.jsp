<%-- 
    Document   : UploadRequest
    Created on : 27-Oct-2023, 23:16:31
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h1 class="mb-5">Upload your <span class="text-primary text-uppercase">request</span></h1>
                </div>
                <div class="row g-5">

                    <div class="col-lg-6">
                        <div class="wow fadeInUp" data-wow-delay="0.2s">
                            <form action="/PRJ301_SE1761_FA23_HE171380/UploadRequest" method="post" onsubmit="return validateForm()" >
                                <div class="row g-3">
                                    <p style="color: red">${ms}</p>
                                    <c:set value="${dao.getRequestDetailById(rid)}" var="rd"></c:set>
                                    <input type="text" name="rid" value="${rid}" hidden/>
                                    <div class="col-md-4"> 
                                        <select class="form-select" name="gradeLevel">
                                            <c:forEach var="i" begin="1" end="12">
                                                <option value="${i}" ${(rd.gradeLevel == i)?'selected':''}>Grade ${i}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <select class="form-select" name="subject">
                                            <c:forEach items="${subjects}" var ="s">
                                                <option value="${s.id}" ${(rd.subjectId == s.id)?'selected':''}>${s.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <select class="form-select" name="learningType">

                                            <option value="0" ${(rd.learningType == 0)?'selected':''}>Online</option>
                                            <option value="1" ${(rd.learningType == 1)?'selected':''}>Offline</option>

                                        </select>
                                    </div>

                                    <div class="col-md-6">    
                                        <div class="form-floating">
                                            <input type="number" value="${rd.hourlyMinRate}" class="form-control" name="minRate" id="minRateInput" min=50000 max=1000000 step=1000 required>
                                            <label for="minRateInput">Min Rate</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="number" value="${rd.hourlyMaxRate}" class="form-control" name="maxRate" id="maxRateInput" min=50000 max=1000000 step=1000 required>
                                            <label for="maxRateInput">Max Rate</label>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-floating">
                                            <input type="text" value="${rd.description}" name="description" class="form-control" id="description" placeholder="Describe about you" required>
                                            <label for="description">Describe about you</label>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-floating">
                                            <input type="text" value="${rd.requirement}" name="requirement" class="form-control" id="requirement" placeholder="Your requirement" required>
                                            <label for="requirement">Your requirement</label>
                                        </div>
                                    </div>


<!--                                    <input type="text" id="questionType" value="${sessionScope.user.questionType}" hidden>
<input type="text" id="question" value="${sessionScope.user.question}" hidden>-->



                                    <div class="col-12">
                                        <button class="btn btn-primary w-100 py-3" type="submit">Save</button>
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
    <script>

        function validateForm() {

            const minRateInput = document.getElementById("minRateInput");
            const maxRateInput = document.getElementById("maxRateInput");

            const minRateValue = parseInt(minRateInput.value);
            const maxRateValue = parseInt(maxRateInput.value);

            // Kiểm tra nếu maxRate bé hơn minRate, hiển thị cảnh báo và ngăn chặn việc gửi form
            if (maxRateValue < minRateValue) {
                alert("maxRate must be greater than or equal to minRate");
                return false; // Ngăn chặn việc gửi form
            }

            return true;
        }
    </script>
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

