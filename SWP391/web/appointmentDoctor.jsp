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



        <div class="page-wrapper doctris-theme toggled">
            <nav id="sidebar" class="sidebar-wrapper">
                <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
                    <div class="sidebar-brand">
                        <a href="index.html">
                            <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                            <img src="${pageContext.request.contextPath}/assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                        </a>
                    </div>


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

                <jsp:include page="admin/layout/headmenu.jsp"/>

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
                                                            function changeDoctor(id, email, code) {

                                                                window.location = "updateStatusDoctorT?appid=" + id + "&email =" + email + "&code="code;

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