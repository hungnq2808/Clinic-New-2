<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />

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
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    </head>

    <body>
        <jsp:include page="layout/preloader.jsp"/>

        <jsp:include page="layout/menu.jsp"/>

        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="layout/sidebar.jsp"/>

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
                        </div>



                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-info">
                                        <thead>
                                            <tr>
                                                <th>Số thứ tự</th>
                                                <th>Bệnh nhân </th>
                                                <th>Bác sĩ</th>
                                                <th>Ngày</th>
                                                <th>Triệu chứng</th>
                                                <th>Slot</th>
                                                <th>Status</th>
                                                <th>Action<th/>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set value="${0}" var="stt"></c:set>

                                            <c:forEach items="${data}" var="x">
                                                <c:set value="${x.ua.specialization.name}" var="spe"></c:set>

                                                    <tr>
                                                        <td>${stt=stt+1}</td> 
                                                    <td>${x.pd.patientName}</td> 
                                                    <td>${x.ua.name}</td> 

                                                    <td>${x.date}</td> 

                                                    <td>${x.symptom}</td> 
                                                    <td>${x.slot.date_schedule}</td> 
                                                    <td>
                                                        <c:if test="${x.managerAccept == true}">
                                                            Đã xử lí
                                                        </c:if>
                                                        <c:if test="${x.managerAccept == false}">
                                                            Chưa xử lí
                                                        </c:if>
                                                    </td> 
                                                    <td>

                                                        <button type = "submit" class="btn btn-primary" onclick="changeDoctor('${x.getId()}')">Đổi bác sĩ</button>
                                                        <script type="text/javascript">
                                                            function changeDoctor(id) {

                                                                window.location = "listdoctor?id=" + id;

                                                            }
                                                        </script>





                                                    </td>

                                                    <td>

                                                        <button type = "submit" class="btn btn-primary" onclick="updateT('${x.getId()}')">Duyệt đơn</button>
                                                        <script type="text/javascript">
                                                            function updateT(id) {

                                                                window.location = "updateStatus?appid=" + id;

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



















                        <div class="col-lg-8 col-md-6 mt-4 mt-sm-0 pt-2 pt-sm-0">
                            <div class="card border-0 shadow overflow-hidden">
                                <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded-0 shadow overflow-hidden bg-light mb-0" id="pills-tab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link rounded-0" id="overview-tab" data-bs-toggle="pill" href="#pills-overview" role="tab" aria-controls="pills-overview" aria-selected="false">
                                            <div class="text-center pt-1 pb-1">
                                                <h4 class="title fw-normal mb-0">Reexam</h4>
                                            </div>
                                        </a><!--end nav link-->
                                    </li><!--end nav item-->

                                    <li class="nav-item">
                                        <a class="nav-link rounded-0 active" id="experience-tab" data-bs-toggle="pill" href="#pills-experience" role="tab" aria-controls="pills-experience" aria-selected="true">
                                            <div class="text-center pt-1 pb-1">
                                                <h4 class="title fw-normal mb-0">Doctor denied</h4>
                                            </div>
                                        </a><!--end nav link-->
                                    </li><!--end nav item-->
                                </ul>

                                <div class="tab-content p-4" id="pills-tabContent">
                                    <div class="tab-pane fade" id="pills-overview" role="tabpanel" aria-labelledby="overview-tab">

                                        <div class="row">
                                            <div class="container">

                                                <table class="table table-bordered table-info">
                                                    <thead>
                                                        <tr>
                                                            <th>Số thứ tự</th>
                                                            <th>Bệnh nhân </th>
                                                            <th>Bác sĩ</th>
                                                            <th>Ngày</th>
                                                            <th>Triệu chứng</th>
                                                            <th>Slot</th>
                                                            <th></th>


                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:set value="${0}" var="stt"></c:set>

                                                        <c:forEach items="${requestScope.lsser}" var="ls">

                                                            <tr>
                                                                <td>${stt=stt+1}</td> 
                                                                <td>${ls.getPd().getPatientName()}</td> 
                                                                <td>${ls.ua.name}</td> 

                                                                <td>${ls.date}</td> 

                                                                <td>${ls.symptom}</td> 
                                                                <td>${ls.slot.date_schedule}</td> 
                                                                <td>

                                                                    <button type = "submit" class="btn btn-primary" onclick="updateTrue('${x.getId()}')">Duyệt đơn</button>
                                                                    <script type="text/javascript">
                                                                        function updateTrue(id) {

                                                                            window.location = "updateStatus?appid=" + id;

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

                                    <div class="tab-pane fade active show" id="pills-experience" role="tabpanel" aria-labelledby="experience-tab">
                                        <div class="container">

                                            <table class="table table-bordered table-info">
                                                <thead>
                                                    <tr>
                                                        <th>Số thứ tự</th>
                                                        <th>Bệnh nhân </th>
                                                        <th>Bác sĩ</th>
                                                        <th>Ngày</th>
                                                        <th>Triệu chứng</th>
                                                        <th>Slot</th>
                                                        <th></th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set value="${0}" var="stt"></c:set>

                                                    <c:forEach items="${ls}" var="l">

                                                        <tr>
                                                            <td>${stt=stt+1}</td> 
                                                            <td>${l.pd.patientName}</td> 
                                                            <td>${l.ua.name}</td> 

                                                            <td>${l.date} </td> 

                                                    <td>${l.symptom}</td> 
                                                    <td>${l.slot.date_schedule}</td> 
                                                    <td><button type = "submit" class="btn btn-primary" onclick="updateTr('${x.getId()}')">Duyệt đơn</button>
                                                        <script type="text/javascript">
                                                            function updateTr(id) {

                                                                window.location = "updateStatus?appid=" + id;

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