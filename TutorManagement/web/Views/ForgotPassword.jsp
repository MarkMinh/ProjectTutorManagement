<%-- 
    Document   : ForgotPassword
    Created on : 27-Oct-2023, 18:39:24
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

        <jsp:include page="Menu.jsp"></jsp:include>
        <c:if test="${sessionScope.user == null}">
            <div class="container-xxl bg-white p-0">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title text-center text-primary text-uppercase">EduMatch</h6>
                        <h1 class="mb-5">Get your <span class="text-primary text-uppercase">password</span></h1>
                    </div>
                    <div class="row g-5">

                        <div class="col-lg-6">
                            <div class="wow fadeInUp" data-wow-delay="0.2s">
                                <form action="/PRJ301_SE1761_FA23_HE171380/ForgotPassword" method="post" onsubmit="return validateForm()" >
                                    <div class="row g-3">
                                        <p style="color: red">${ms}</p>
                                        <div class="col-md-12">
                                            <div class="form-floating">
                                                <input type="text" name="username" class="form-control" id="username" placeholder="Your username" required>
                                                <label for="newPass">Username</label>
                                            </div>
                                        </div>
                                        <p>Select a Security Question</p>
                                        <select class="form-select" name="questionType" id="qType">
                                            <option value="1">What is your mother's maiden name?</option>
                                            <option value="2">What was the name of your first pet?</option>
                                            <option value="3">What is the name of your favourite book?</option>
                                            <option value="4">In what city were you born?</option>
                                            <option value="5">What is your favourite movie?</option>
                                            <option value="6">What is your favourite color?</option>
                                            <option value="7">What is the name of your high school?</option>
                                            <option value="8">What was your first job?</option>
                                            <option value="9">What is the make of your first car?</option>
                                            <option value="10">Who is your favourite actor or actress?</option>
                                        </select>
                                        
                                        <div class="col-md-12">
                                            <div class="form-floating">
                                                <input type="text" name="question" class="form-control" id="qAns" placeholder="Your answer" required>
                                                <label for="newPass">Your answer</label>
                                            </div>
                                        </div>

                                        <div class="col-md-12">
                                            <div class="form-floating">
                                                <input type="password" name="newPass" class="form-control" id="newPass" placeholder="Your new password">
                                                <label for="newPass">Your new password</label>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-floating">
                                                <input type="password" name="rePass" class="form-control" id="rePass" placeholder="Rewrite new password">
                                                <label for="rePass">Rewrite new password</label>
                                            </div>
                                        </div>




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

                var newPass = document.getElementById("newPass").value;
                var rePass = document.getElementById("rePass").value;
                //            var pass = document.getElementById("password").value;
                //            var questionType = document.getElementById("questionType").value;
                //            var question = document.getElementById("question").value;
                //            var qType = document.getElementById("qType").value;
                //            var qAns = document.getElementById("qAns").value;

                if (newPass !== rePass) {
                    alert("New password and rewrite password do not match.");
                    return false;
                }
                //            if(qType !== questionType || qAns !== question){
                //                alert("Wrong security question!!");
                //                return false;
                //            }



                //            // Replace 'yourOldPassword' with the actual old password
                //            if (oldPass !== pass) {
                //                alert("Old password is incorrect.");
                //                return false;
                //            }

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

