<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>


        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="${pageContext.request.contextPath}/assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="${pageContext.request.contextPath}/assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flatpickr.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/jquery.timepicker.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


    </head>

    <body>
        <jsp:include page="layout/preloader.jsp"/>

        <jsp:include page="layout/menu.jsp"/>

        <div class="page-wrapper doctris-theme toggled">
            <nav id="sidebar" class="sidebar-wrapper">
                <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
                    <div class="sidebar-brand">
                        <a href="index.html">
                            <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                            <img src="${pageContext.request.contextPath}/assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                        </a>
                    </div>

                    <ul class="sidebar-menu pt-3">
                        <li><a href="index.html"><i class="uil uil-dashboard me-2 d-inline-block"></i>Dashboard</a></li>
                        <li><a href="appointment.html"><i class="uil uil-stethoscope me-2 d-inline-block"></i>Appointment</a></li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)"><i class="uil uil-user me-2 d-inline-block"></i>Doctors</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="doctors.html">Doctors</a></li>
                                    <li><a href="add-doctor.html">Add Doctor</a></li>
                                    <li><a href="dr-profile.html">Profile</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)"><i class="uil uil-wheelchair me-2 d-inline-block"></i>Patients</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="patients.html">All Patients</a></li>
                                    <li><a href="add-patient.html">Add Patients</a></li>
                                    <li><a href="patient-profile.html">Profile</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)"><i class="uil uil-apps me-2 d-inline-block"></i>Apps</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="chat.html">Chat</a></li>
                                    <li><a href="email.html">Email</a></li>
                                    <li><a href="calendar.html">Calendar</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)"><i class="uil uil-shopping-cart me-2 d-inline-block"></i>Pharmacy</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="shop.html">Shop</a></li>
                                    <li><a href="product-detail.html">Shop Detail</a></li>
                                    <li><a href="shopcart.html">Shopcart</a></li>
                                    <li><a href="checkout.html">Checkout</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)"><i class="uil uil-flip-h me-2 d-inline-block"></i>Blogs</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="blogs.html">Blogs</a></li>
                                    <li><a href="blog-detail.html">Blog Detail</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)"><i class="uil uil-file me-2 d-inline-block"></i>Pages</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="faqs.html">FAQs</a></li>
                                    <li><a href="review.html">Reviews</a></li>
                                    <li><a href="invoice-list.html">Invoice List</a></li>
                                    <li><a href="invoice.html">Invoice</a></li>
                                    <li><a href="terms.html">Terms & Policy</a></li>
                                    <li><a href="privacy.html">Privacy Policy</a></li>
                                    <li><a href="error.html">404 !</a></li>
                                    <li><a href="blank-page.html">Blank Page</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="sidebar-dropdown">
                            <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Authentication</a>
                            <div class="sidebar-submenu">
                                <ul>
                                    <li><a href="login.html">Login</a></li>
                                    <li><a href="signup.html">Signup</a></li>
                                    <li><a href="forgot-password.html">Forgot Password</a></li>
                                    <li><a href="lock-screen.html">Lock Screen</a></li>
                                    <li><a href="thankyou.html">Thank you${pageContext.request.contextPath}.!</a></li>
                                </ul>
                            </div>
                        </li>

                        <li><a href="components.html"><i class="uil uil-cube me-2 d-inline-block"></i>Components</a></li>

                        <li><a href="${pageContext.request.contextPath}/landing/index-two.html" target="_blank"><i class="uil uil-window me-2 d-inline-block"></i>Landing page</a></li>
                    </ul>
                    <!-- sidebar-menu  -->
                </div>
                <!-- sidebar-content  -->
                <ul class="sidebar-footer list-unstyled mb-0">
                    <li class="list-inline-item mb-0 ms-1">
                        <a href="#" class="btn btn-icon btn-pills btn-soft-primary">
                            <i class="uil uil-comment icons"></i>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">



                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="banner">
                            <div class="text-banner">
                                <center><h3>Manager appointment</h3></center>
                            </div>
                            <style>
                                .banner {
                                    color: blue;

                                }
                            </style>


                        </div




                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-info" >
                                        <thead>
                                            <tr>

                                                <th>Bệnh nhân </th>
                                                <th>Bác sĩ</th>
                                                <th>Ngày</th>
                                                <th>Triệu chứng</th>
                                                <th>Slot</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                                <th></th>
                                                

                                            </tr>
                                        </thead>
                                        <tbody>


                                            <c:forEach items="${sessionScope.lst}" var="l">

                                                <tr>
                                                    <td><a href="medicalHistory?pid=${l.getPd().getId()}">${l.getPd().getPatientName()}</a></td>
                                                    
                                                    
                                                    
                                                    


                                                    <td>${l.ua.name}</td> 

                                                    <td>${l.date}</td> 

                                                    <td>${l.symptom}</td> 
                                                    <td>${l.slot.date_schedule}</td> 
                                                    <td>
                                                        <c:if test="${l.doctorAccept == true}">
                                                            Đã xử lí
                                                        </c:if>
                                                        <c:if test="${l.doctorAccept == false}">
                                                            Chưa xử lí
                                                        </c:if>
                                                    </td> 
                                                    <td>


                                                        <a type = "submit" class="btn btn-primary small" href="updateStatusDoctorT?appid=${l.getId()}&email=${l.getPd().getPatientEmail()}&code=${l.getClinicCode()}">Duyệt đơn</a>
                                                        <script type="text/javascript">
                                                            function changeDoctor(id,email,code) {

                                                                window.location = "updateStatusDoctorT?appid=" + id +"&email =" +email +"&code="code;

                                                            }
                                                        </script>
                                                    </td>
                                                    
                                                    <td>
                                                        <button type = "submit" class="btn btn-primary small" onclick="create('${l.getPd().getId()}')">Tạo bệnh án</button>
                                                        <script type="text/javascript">
                                                            function create(id) {

                                                                window.location = "addMedical?pid=" + id;

                                                            }
                                                        </script>
                                                        
                                                        




                                                    </td>


                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>













                    </div>
                </div><!--end row-->
                <jsp:include page="layout/footer.jsp"/>


        </div>

    </div><!--end container-->


</main>
<!--End page-content" -->
</div>
<!-- page-wrapper -->



<!-- Modal start -->
<!-- Add New Appointment Start -->

<!-- Add New Appointment End -->


<!-- View Appintment End -->

<!-- Accept Appointment Start -->

<!-- Accept Appointment End -->

<!-- Cancel Appointment Start -->

<!-- Cancel Appointment End -->
<!-- Modal end -->

<!-- javascript -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
<!-- simplebar -->
<script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/select2.init.js"></script>
<!-- Datepicker -->
<script src="${pageContext.request.contextPath}/assets/js/flatpickr.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/flatpickr.init.js"></script>
<!-- Datepicker -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.timepicker.min.js"></script> 
<script src="${pageContext.request.contextPath}/assets/js/timepicker.init.js"></script> 
<!-- Icons -->
<script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
<!-- Main Js -->
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

</body>

</html>