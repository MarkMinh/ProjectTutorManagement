<%-- 
    Document   : Received
    Created on : 29-Oct-2023, 13:02:54
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
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title text-center text-primary text-uppercase">EduMatch</h6>
                        <h1 class="mb-5">List <span class="text-primary text-uppercase">student</span></h1>
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
        <c:if test="${empty listStd}">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h1>There's no user</h1>
            </div>
        </c:if>
        <c:if test="${not empty listStd}">
            <div class="row g-4">
                <c:forEach items="${listStd}" var="s" begin='${page.begin}' end = '${page.end-1}'>
                    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="room-item shadow rounded overflow-hidden">
                            <!--                        <div class="position-relative">
                                                        <img class="img-fluid" src="img/room-1.jpg" alt="">
                                                        <small class="position-absolute start-0 top-100 translate-middle-y bg-primary text-white rounded py-1 px-3 ms-4">$100/Night</small>
                                                    </div>-->

                            <div class="p-4 mt-2">

                                <div class="d-flex justify-content-between mb-3">
                                    <h5 class="mb-0">${(dao.getUser(s.username)).username}</h5>                                
                                </div>

       
                                <div class="d-flex justify-content-between">
                                    <a href="/PRJ301_SE1761_FA23_HE171380/DetailReceived?uid=${(dao.getUser(s.username)).username}" class="btn btn-sm btn-primary rounded py-2 px-4" >View Detail</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <form action="/PRJ301_SE1761_FA23_HE171380/ReceivedControl" method="post">

                <select name ="nrpp">
                    <c:forEach items="${nrppArr}" var = "nr">
                        <option  value="${nr}" ${nr==page.nrpp?"selected":""}>${nr}</option>
                    </c:forEach>
                </select>


                <input type ='text' name="index" value="${page.index}" hidden>
                <input type ='text' name="totalPage" value="${page.totalPage}" hidden>
                <c:if test="${page.index!=0 }">
                    <input type="submit" class="btn" name ="btnHome" value="Home">
                    <input type="submit" class="btn" name ="btnPre" value="Pre">
<!--                    <span class ='btn'><a class = 'btn' href ="LoadDB02?index=${0}">Home</a></span>
                    <span class ='btn'><a class = 'btn' href ="LoadDB02?index=${page.index-1}">Pre</a></span>-->
                </c:if>

                <c:forEach var="i" begin = '${page.pageStart}' end ='${page.pageEnd}'>
                    <input type="submit" class="btn" name ="btn${i}" value="${i+1}">
                </c:forEach>

                <c:if test='${page.index<page.totalPage-1}'>
<!--                    <span class ='btn'><a class = 'btn' href ="LoadDB01?index=${page.index+1}">Next</a></span>
                    <span class ='btn'><a class = 'btn' href ="LoadDB01?index=${page.totalPage-1}">End</a></span>-->
                    <input type="submit" class="btn" name ="btnNext" value="Next">
                    <input type="submit" class="btn" name ="btnEnd" value="End">
                </c:if>
            </form>

        </div>
    </div>
</div>                                    



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
                var option = document.createElement("option");
                option.value = 0;
                option.text = "Select a district";
                districtSelect.appendChild(option);
                for (var i = 0; i < districts.length; i++) {
                    var option = document.createElement("option");
                    option.value = districts[i].id;
                    option.text = districts[i].name;
                    districtSelect.appendChild(option);
                }

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

                var option = document.createElement("option");
                option.value = 0;
                option.text = "Select a ward";
                wardSelect.appendChild(option);
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

