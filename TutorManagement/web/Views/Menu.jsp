<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<!-- Header Start -->
<div class="container-fluid bg-dark px-0">
    <div class="row gx-0">
        <div class="col-lg-3 bg-dark d-none d-lg-block">
            <c:if test="${sessionScope.user.rollNo == 0}">
                <a href="/PRJ301_SE1761_FA23_HE171380/AdminControl" class="navbar-brand w-100 h-100 m-0 p-0 d-flex align-items-center justify-content-center">
                <h1 class="m-0 text-primary text-uppercase">EduMatch</h1>
            </a>
            </c:if>
            <c:if test="${sessionScope.user.rollNo != 0}">
            <a href="/PRJ301_SE1761_FA23_HE171380/HomeControl" class="navbar-brand w-100 h-100 m-0 p-0 d-flex align-items-center justify-content-center">
                <h1 class="m-0 text-primary text-uppercase">EduMatch</h1>
            </a>
            </c:if>
        </div>
        <div class="col-lg-9">
            <div class="row gx-0 bg-white d-none d-lg-flex">
                <div class="col-lg-7 px-5 text-start">
                    <div class="h-100 d-inline-flex align-items-center py-2 me-4">
                        <i class="fa fa-envelope text-primary me-2"></i>
                        <p class="mb-0">info@example.com</p>
                    </div>
                    <div class="h-100 d-inline-flex align-items-center py-2">
                        <i class="fa fa-phone-alt text-primary me-2"></i>
                        <p class="mb-0">+012 345 6789</p>
                    </div>
                </div>
                <div class="col-lg-5 px-5 text-end">
                    <div class="d-inline-flex align-items-center py-2">
                        <a class="me-3" href=""><i class="fab fa-facebook-f"></i></a>
                        <a class="me-3" href=""><i class="fab fa-twitter"></i></a>
                        <a class="me-3" href=""><i class="fab fa-linkedin-in"></i></a>
                        <a class="me-3" href=""><i class="fab fa-instagram"></i></a>
                        <a class="" href=""><i class="fab fa-youtube"></i></a>
                    </div>
                </div>
            </div>
            <nav class="navbar navbar-expand-lg bg-dark navbar-dark p-3 p-lg-0">
                <a href="index.html" class="navbar-brand d-block d-lg-none">
                    <h1 class="m-0 text-primary text-uppercase">EduMatch</h1>
                </a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <c:if test="${sessionScope.user.rollNo == 0}">
                            <a href="/PRJ301_SE1761_FA23_HE171380/AdminControl" class="nav-item nav-link active">Home</a>
                        </c:if>
                        <c:if test="${sessionScope.user.rollNo != 0}">
                            <a href="/PRJ301_SE1761_FA23_HE171380/HomeControl" class="nav-item nav-link active">Home</a>
                        </c:if>
                            <a href="/PRJ301_SE1761_FA23_HE171380/LoadDBStd" class="nav-item nav-link ">Students</a>
                        <c:if test="${sessionScope.user.rollNo == 2}">
                            
                            <a href="/PRJ301_SE1761_FA23_HE171380/MyRequest" class="nav-item nav-link">Requested</a>
                        </c:if>

                        <a href="/PRJ301_SE1761_FA23_HE171380/ClassControl" class="nav-item nav-link">Classes</a>

                    </div>
                    <c:if test="${sessionScope.user == null}">
                        <a style="position: relative; left:250px" class="btn btn-sm btn-primary rounded py-2 px-4" href="/PRJ301_SE1761_FA23_HE171380/LoginControl">Login</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle btn btn-sm btn-primary rounded py-2 px-4" data-bs-toggle="dropdown">Sign up</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="/PRJ301_SE1761_FA23_HE171380/SignupControl?rollNo=1" class="dropdown-item">Sign up with tutor role</a>
                                <a href="/PRJ301_SE1761_FA23_HE171380/SignupControl?rollNo=2" class="dropdown-item">Sign up with student role</a>
                            </div>
                        </div>

                    </c:if>

                    <c:if test="${sessionScope.user != null}">
                        <div class="nav-item ">
                            <a style="position: relative; left: 150px" href="#" class="nav-link" data-bs-toggle="dropdown"><i class="fa-regular fa-bell"></i></a>

                            <div style="position: absolute;left:150px; right: 0px;" class="subnav dropdown-menu rounded-0 m-0">

                                <c:forEach items="${sessionScope.listNoti}" var="n">
                                    <a href="${n.link}" class="dropdown-item">${n.message} <span style="position: absolute; right:0; color: green" class="small"> ${n.diff}</span></a>                              
                                </c:forEach>
                            </div>
                        </div>

                        <div class="nav-item dropdown">

                            <a href="#" class="nav-link" data-bs-toggle="dropdown"><img style="border-radius: 50%" width="50px" height="50px" src="/PRJ301_SE1761_FA23_HE171380/img/${sessionScope.user.img}" alt=""></a>
                            <div style="position: absolute; right: 0px; top: 68px" class="dropdown-menu rounded-0 m-0">

                                <a href="/PRJ301_SE1761_FA23_HE171380/EditControl" class="dropdown-item">Edit your information</a>
                                <a href="/PRJ301_SE1761_FA23_HE171380/PasswordControl" class="dropdown-item">Change password</a>
                                <c:if test="${sessionScope.user.rollNo == 2}">
                                    
                                    <a href="/PRJ301_SE1761_FA23_HE171380/MyRequest" class="dropdown-item">Your request</a>
                                </c:if>
                                <a href="/PRJ301_SE1761_FA23_HE171380/ClassControl" class="dropdown-item">Your classes</a>    

                                <a href="/PRJ301_SE1761_FA23_HE171380/LogOutControl" class="dropdown-item">Log Out</a>
                            </div>
                        </div>
                    </c:if>
                </div>
            </nav>
        </div>
    </div>
</div>
<script>
    var subnav = document.querySelector(".subnav");

    function toggleSubnav() {
        if (subnav.style.display === "block") {
            subnav.style.display = "none";
        } else {
            subnav.style.display = "block";
        }
    }
</script>
<!-- Header End -->

<!--end of menu-->