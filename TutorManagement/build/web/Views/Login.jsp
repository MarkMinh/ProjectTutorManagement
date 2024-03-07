

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Login </title>
        <!-- CSS -->
        <link rel="stylesheet" href="/PRJ301_SE1761_FA23_HE171380/css/style.css">

        <!-- Boxicons CSS -->
        <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>

    </head>
    <body>
        <section class="container forms">
            <div class="form login">
                <div class="form-content">
                    <header>Login</header>
                    <form action="/PRJ301_SE1761_FA23_HE171380/LoginControl" method="post">
                        <div style="color:red"> 
                            ${msg}
                        </div>
                        <!--                        placeholder="User name"-->
                        <!--placeholder="Password"-->
                        <c:set var="cookie" value="${pageContext.request.cookies}"/>
                            <div class="field input-field">
                                <input type="text" name ="user" value="${cookie.cuser.value}"  class="input">
                        </div> 
                        <div class="field input-field">
                            <input type="password" name ="pass" value="${cookie.cpass.value}" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>                        
                        <div>
                            <input type ="checkbox" name="rem" ${(cookie.crem!=null)?'checked':''}  value="ON"/>Remember me
                        </div>    
                        <div class="form-link">
                            <a href="/PRJ301_SE1761_FA23_HE171380/ForgotPassword" class="forgot-pass">Forgot password?</a>
                        </div>
                        <div class="field button-field">
                            <input type="submit" value='Login' class="custom-button">
                        </div>
                    </form>
                    <div class="form-link">
                        <span>Don't have an account?</span> <br/>
                        <a href="/PRJ301_SE1761_FA23_HE171380/SignupControl?rollNo=1" class="link signup-link">Signup as a tutor</a><br/>
                        <span> <a href="/PRJ301_SE1761_FA23_HE171380/SignupControl?rollNo=2" class="link signup-link">Signup as a student</a></span>
                    </div>
                </div>
<!--                <div class="line"></div>
                <div class="media-options">
                    <a href="#" class="field facebook">
                        <img src="/PRJ301_SE1761_FA23_HE171380/img/facebook-icon.png" alt="" class="facebook-icon">
                        <span>Login with Facebook</span>
                    </a>
                </div>
                <div class="media-options">
                    <a href="#" class="field google">
                        <img src="/PRJ301_SE1761_FA23_HE171380/img/google-icon.png" alt="" class="google-img">
                        <span>Login with Google</span>
                    </a>
                </div>-->
            </div>          
        </section>
        <!-- JavaScript -->
        <script src="/PRJ301_SE1761_FA23_HE171380/js/script.js"></script>
    </body>
</html>