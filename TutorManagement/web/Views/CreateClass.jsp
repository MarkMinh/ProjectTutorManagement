<%-- 
    Document   : CreateClass
    Created on : 22-Oct-2023, 22:16:51
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
        <style>
            .hidden-select {
                display: none; /* Ẩn selection bằng cách đặt thuộc tính display thành "none" */
            }
        </style>
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
                    <h1 class="mb-5">Create new<span class="text-primary text-uppercase">class</span></h1>
                </div>
                <div class="row g-5">

                    <div class="col-lg-6">
                        <div class="wow fadeInUp" data-wow-delay="0.2s">
                            <form action="/PRJ301_SE1761_FA23_HE171380/AcceptControl" method="post" onsubmit="return validateForm()" >
                                <p style="color: red">${ms}</p>
                                <div class="row g-3">
                                    <div class="col-md-12">
                                        <div class="form-floating">
                                            <input type="number" name="moneyS" class="form-control" id="moneyS" placeholder="The amount of money after discussion" min=50000 max = 1000000 step=1000>
                                            <label for="moneyS">The amount of money after discussion</label>
                                        </div>
                                    </div>
                                    <input type="text" name="uid" value="${uid}" hidden/>
                                    <input type="text" name="rid" value="${rid}" hidden/>
                                    <c:forEach items="${dayList}" var="d">
                                        <div class="col-md-12">
                                            <div class="form-floating">
                                                <div class="checkbox-group">
                                                    <input type="checkbox" name="dayW" value="${d}" id="checkbox${d}" class="form-check time-checkbox" />
                                                    <label for="checkbox${d}">${d}</label>
                                                    <select name="timeSlot${d}" id="timeSlot${d}" class="form-select hidden-select">
                                                        <option value="1">7:00 AM - 9:00 AM</option>
                                                        <option value="2">9:00 AM - 11:00 AM</option>
                                                        <option value="3">1:00 PM - 3:00 PM</option>
                                                        <option value="4">3:00 PM - 5:00 PM</option>
                                                        <option value="5">6:00 PM - 8:00 PM</option>
                                                        <option value="6">8:00 PM - 10:00 PM</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>





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
    <!-- JavaScript -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            // Lưu trạng thái của checkbox
            var checkboxStates = {};

            // Lấy tất cả các checkbox có class "time-checkbox"
            var checkboxes = document.querySelectorAll('.time-checkbox');

            checkboxes.forEach(function (checkbox) {
                checkbox.addEventListener('change', function () {
                    // Lấy ID của checkbox
                    var checkboxId = this.id;

                    // Lấy ID của selection tương ứng
                    var selectId = 'timeSlot' + checkboxId.substring(8);

                    // Lấy selection bằng ID
                    var select = document.getElementById(selectId);

                    // Ẩn hoặc hiện selection dựa trên trạng thái checkbox
                    if (this.checked) {
                        select.classList.remove('hidden-select');
                    } else {
                        select.classList.add('hidden-select');
                    }
                });
            });
        });
        function validateForm() {
            // Lấy giá trị của ô nhập tiền
            var moneyInput = document.getElementById("moneyS").value;

            // Kiểm tra nếu giá trị của ô nhập tiền rỗng
            if (moneyInput === "") {
                alert("Please enter the amount of money.");
                return false; // Ngăn chặn form submit
            }

            // Lấy danh sách các ô checkbox
            var checkboxes = document.querySelectorAll('input[type="checkbox"]');

            var isChecked = false; // Biến để kiểm tra xem có ít nhất một ô checkbox được chọn hay không

            // Kiểm tra từng ô checkbox
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    isChecked = true;
                    break; // Nếu có một ô được chọn, thoát ra khỏi vòng lặp
                }
            }

            // Kiểm tra nếu không có ô checkbox nào được chọn
            if (!isChecked) {
                alert("Please select at least one day.");
                return false; // Ngăn chặn form submit
            }

            return true; // Cho phép form submit nếu các điều kiện được đáp ứng
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