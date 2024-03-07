<%-- 
    Document   : Signup
    Created on : 13-Oct-2023, 00:03:10
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Sign up </title>
        <!-- CSS -->
        <link rel="stylesheet" href="/PRJ301_SE1761_FA23_HE171380/css/style.css">

        <!-- Boxicons CSS -->
        <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>

    </head>
    <body>
        <section class="container forms">       
            <!-- Signup Form -->
            <div class="form login">
                <div class="form-content">
                    <header>Sign up</header>
                    <form action="/PRJ301_SE1761_FA23_HE171380/SignupControl" method="post" onsubmit="return validateForm()">
                        <input type="text" name="rollNo" value="${rollNo}" hidden>
                        <p style="color: red">${ms}</p>
                        <div class="field input-field">
                            <input type="text" name="user" placeholder="User name"  class="input">
                        </div> 
                        <div class="field input-field">
                            <input type="password" name="pass" placeholder="Password" class="password">
                        </div>
                        <div class="field input-field">
                            <input type="password" name="repass" placeholder="Confirm password" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>

                        <div class="field input-field">
                            <input type="email" name="email" placeholder="Email" class="input">                        
                        </div>
                        Select a Security Question
                            <select name="questionType">
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
                            <input type="text" name="question" placeholder="Your Answer" required>

                       
                        <!--                        <span style="color: red" id="phoneNumberError" class="error-message"></span>-->

                        <div class="field button-field">
                            <input type="submit" value='Sign up' class="custom-button">
                        </div>
                    </form>
                    <div class="form-link">
                        <span>You've ready have an account? <a href="/PRJ301_SE1761_FA23_HE171380/LoginControl" class="link signup-link">Login</a></span>
                    </div>
                </div>
                <!--                <div class="line"></div>
                                <div class="media-options">
                                    <a href="#" class="field facebook">
                                        <img src="/PRJ301_SE1761_FA23_HE171380/img/facebook-icon.png" alt="" class="facebook-icon">
                                        <span>Sign up with Facebook</span>
                                    </a>
                                </div>
                                <div class="media-options">
                                    <a href="#" class="field google">
                                        <img src="/PRJ301_SE1761_FA23_HE171380/img/google-icon.png" alt="" class="google-img">
                                        <span>Sign up with Google</span>
                                    </a>
                                </div>-->
            </div>
        </section>
        <!-- JavaScript -->
        <script>
            function validateForm() {
                var password = document.getElementsByName("pass")[0].value;
                var repassword = document.getElementsByName("repass")[0].value;

                if (password.length < 6) {
                    alert("Mật khẩu phải ít nhất 6 ký tự.");
                    return false;
                }

                if (password !== repassword) {
                    alert("Mật khẩu và xác nhận mật khẩu không trùng khớp.");
                    return false;
                }
//                var regex = /^0\d{9}$/;
//                if (!regex.test(document.getElementById("phoneNumber").value)) {
//                    var phoneNumberError = document.getElementById("phoneNumberError");
//                    phoneNumberError.textContent = "Please enter the right phone number";
//                    return false; // Ngăn form gửi đi nếu số điện thoại không đúng định dạng
//                }

                return true; // Cho phép form gửi khi đủ thông tin
            }
        </script>
        <script src="/PRJ301_SE1761_FA23_HE171380/js/script.js"></script>
    </body>

</html>