<%-- 
    Document   : LoadDBStudent
    Created on : 21-Oct-2023, 21:11:23
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
        <c:if test="${sessionScope.user.rollNo != 0}">
            <jsp:include page="Menu.jsp"></jsp:include>
        </c:if>
        
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h6 class="section-title text-center text-primary text-uppercase">EduMatch</h6>
                    <h1 class="mb-5">List <span class="text-primary text-uppercase">student</span></h1>
                </div>
                <form action="/PRJ301_SE1761_FA23_HE171380/SearchControl" method="post">
                    <div class="row g-3">

                        <div class="col-md-4">
                            <div class="form-floating">
                                <select class="form-select" name="provinceS" id="provinceSelect" onClick="loadDistricts()">
                                    <c:forEach items="${listProvince}" var="p">
                                        <option value="${p.id}">${p.name}</option>
                                    </c:forEach>
                                </select>             
                                <label for="provinceSelect">Select A Province</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-floating">
                                <select class="form-select" name="districtS" id="districtSelect" onClick="loadWards()">
                                    <c:forEach items="${listDistrict}" var="d">

                                        <option value="${d.id}">${d.name}</option>
                                    </c:forEach>
                                </select>             
                                <label for="districtSelect">Select A District</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-floating">
                                <select class="form-select" name="wardS" id="wardSelect">
                                    <c:forEach items="${listWard}" var="w">
                                        <option value="${w.id}">${w.name}</option>
                                    </c:forEach>
                                </select>             
                                <label for="wardSelect">Select A Ward</label>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-floating">
                                <select class="form-select" name="gradeS" id="gradeS">
                                    <option value="999">Select grade</option>
                                    <c:forEach items="${gradeLv}" var="g">
                                        <option value="${g}">Grade ${g}</option>
                                    </c:forEach>
                                </select>             
                                <label for="gradeS">Select grade</label>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-floating">
                                <select class="form-select" name="genderS" id="genderS">
                                    <option value="999">Select gender</option>
                                    <option value="0">Male</option>
                                    <option value="1">Female</option>
                                    <option value="2">Others</option>

                                </select>             
                                <label for="genderS">Select gender</label>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-floating">
                                <select class="form-select" name="subjectS" id="subjectS">
                                    <option value="999">Select subject</option>
                                    <c:forEach items="${subjects}" var="s">
                                        <option value="${s.id}">${s.name}</option>
                                    </c:forEach>
                                </select>             
                                <label for="subjectS">Select A subject</label>
                            </div>
                        </div>
                        <div class="col-md-4">    
                            <div class="form-floating">
                                <input type="number" class="form-control" name="moneyS" id="moneyS" min=50000 max=1000000 step=1000>
                                <label for="mơneyS">Search by price</label>
                            </div>
                        </div>

                        <div class="col-md-4">    
                            <div class="form-floating">
                                <input type="text" class="form-control" name="nameS" id="nameS">
                                <label for="nameS">Search by name</label>
                            </div>
                        </div>

                        <div class="col-12">
                            <input type="submit" class="btn btn-primary w-100 py-3" value="Search" >
                        </div>

                    </div>
                </form>
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
            <h1>There's no user</h1>
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
                                    <a href="/PRJ301_SE1761_FA23_HE171380/DetailControl?uid=${s.username}&rid=${s.id}" class="btn btn-sm btn-primary rounded py-2 px-4" >View Detail</a>

                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <form action="/PRJ301_SE1761_FA23_HE171380/LoadDBStd" method="post">

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
