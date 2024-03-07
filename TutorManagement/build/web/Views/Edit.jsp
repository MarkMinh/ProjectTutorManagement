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
                            <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.1s" src="/PRJ301_SE1761_FA23_HE171380/img/${sessionScope.user.img}" style="margin-top: 25%;">
                            <form action="/PRJ301_SE1761_FA23_HE171380/EditImgControl" method="post" enctype="multipart/form-data" onsubmit="return validateImg()">
                                <div class="field input-field" >
                                    <label for="imageInput">Change your avatar: </label>              
                                    <input  class="form-control" type="file" name="image" id="imageInput"/><br/> 
                                    <div class="col-6">
                                        <button class="btn btn-primary w-100 py-3" type="submit">Save</button>
                                    </div>
                                </div>
                            </form >                            
                        </div>
                        <script>

                            function validateImg() {
                                var imageInput = document.getElementById("imageInput");

                                if (imageInput.files.length === 0) {
                                    alert("Please select an image to upload.");
                                    return false; // Ngăn form gửi đi khi không có ảnh được chọn.
                                }
                                return true; // Cho phép form gửi khi có ảnh được chọn.
                            }
                        </script>
                    </div>
                    <div class="col-lg-6">
                        <div class="wow fadeInUp" data-wow-delay="0.2s">
                            <form action="/PRJ301_SE1761_FA23_HE171380/EditControl" method="post" onsubmit="return validateForm()" >
                                <div class="row g-3">
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" name="fullName" class="form-control" id="name" placeholder="Your Full Name" value="${sessionScope.user.fullName}">
                                            <label for="name">Your Name</label>
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.user.isActive == 1}">
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input type="email" name="email" class="form-control" id="email" placeholder="Your Email" value="${sessionScope.user.email}" readonly>
                                                <label for="email">Your Email</label>
                                            </div>
                                        </div>
                                    </c:if>

                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <select name="gender" class="form-select" id="select1">
                                                <option value="0" ${sessionScope.user.gender == 0 ? 'selected': ''}>Male</option>
                                                <option value="1" ${sessionScope.user.gender == 1 ? 'selected': ''}>Female</option>
                                                <option value="2" ${sessionScope.user.gender == 2 ? 'selected': ''}>Others</option>
                                            </select>
                                            <label for="select1">Your Gender</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input id="select2" class="form-control" type="date" name="dob" value="${sessionScope.user.dob}">
                                            <label for="select2">Your birthday</label>
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.user.isActive != 1}">
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" placeholder="Your Phone Number">
                                                <label for="phoneNumber">Your Phone Number</label>
                                                 <span style="color: red" id="phoneNumberError" class="error-message">
                                            </div>
                                        </div>
                                    </c:if>


                                    <div class="col-4">
                                        <div class="form-floating">
                                            <select class="form-select" name="province" id="provinceSelect" onChange="loadDistricts()">
                                                <c:forEach items="${listProvince}" var="p">
                                                    <option value="${p.id}" ${sessionScope.user.provinceId == p.id ? 'selected':''}>${p.name}</option>
                                                </c:forEach>
                                            </select>             
                                            <label for="provinceSelect">Select A Province</label>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-floating">
                                            <select class="form-select" name="district" id="districtSelect" onChange="loadWards()">
                                                <c:forEach items="${listDistrict}" var="d">
                                                    <option value="${d.id}" ${sessionScope.user.districtId == d.id ? 'selected':''}>${d.name}</option>
                                                </c:forEach>
                                            </select>             
                                            <label for="districtSelect">Select A District</label>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-floating">
                                            <select class="form-select" name="ward" id="wardSelect">
                                                <c:forEach items="${listWard}" var="w">
                                                    <option value="${w.id}" ${sessionScope.user.wardId == w.id ? 'selected':''}>${w.name}</option>
                                                </c:forEach>
                                            </select>             
                                            <label for="wardSelect">Select A Ward</label>
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
    <!-- JavaScript -->
    <script>
        function loadDistricts() {
            var selectedProvinceId = document.getElementById("provinceSelect").value;
            if (selectedProvinceId === "") {
                return; // Không thực hiện gửi yêu cầu nếu giá trị tỉnh không được chọn
            }
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "DistrictServlet?provinceId=" + selectedProvinceId, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var districtSelect = document.getElementById("districtSelect");
                    districtSelect.innerHTML = "";

                    var districts = JSON.parse(xhr.responseText);
                    for (var i = 0; i < districts.length; i++) {
                        var option = document.createElement("option");
                        option.value = districts[i].id;
                        option.text = districts[i].name;
                        districtSelect.appendChild(option);
                    }
                    loadWards();
                }
            };
            xhr.send();
        }

        function loadWards() {
            var districtId = document.getElementById("districtSelect").value;
            if (districtId === "") {
                return; // Không thực hiện gửi yêu cầu nếu giá trị quận không được chọn
            }
            // Tạo một XMLHttpRequest object để gửi yêu cầu AJAX
            var xhr = new XMLHttpRequest();

            // Xác định đường dẫn của máy chủ hoặc endpoint xử lý AJAX
            var url = "WardServlet?districtId=" + districtId; // Đặt endpoint tại đây

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Xử lý dữ liệu JSON trả về từ máy chủ
                    var response = JSON.parse(xhr.responseText);

                    // Xóa danh sách ward hiện tại
                    var wardSelect = document.getElementById("wardSelect");
                    wardSelect.innerHTML = "";

                    // Thêm danh sách các phường/xã (ward) từ response vào select box
                    response.forEach(function (ward) {
                        var option = document.createElement("option");
                        option.value = ward.id; // Đặt giá trị là id của ward
                        option.textContent = ward.name; // Đặt văn bản hiển thị
                        wardSelect.appendChild(option);
                    });
                }
            };

            xhr.open("GET", url, true);
            xhr.send();
        }

        function validateForm() {
            var selectedProvince = document.getElementById("provinceSelect").value;
            var selectedDistrict = document.getElementById("districtSelect").value;
            var selectedWard = document.getElementById("wardSelect").value;

            if (selectedProvince === "" || selectedDistrict === "" || selectedWard === "") {
                alert("Please fill in all required fields.");
                return false; // Ngăn form gửi đi nếu không đủ thông tin
            }
            var regex = /^0\d{9}$/;
            if (!regex.test(document.getElementById("phoneNumber").value)) {
                var phoneNumberError = document.getElementById("phoneNumberError");
                phoneNumberError.textContent = "Please enter the right phone number";
                return false; // Ngăn form gửi đi nếu số điện thoại không đúng định dạng
            }

            return true; // Cho phép form gửi khi đủ thông tin
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