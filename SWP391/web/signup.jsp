<%-- 
    Document   : sign up
    Created on : Mar 18, 2023, 2:20:30 AM
    Author     : Quang Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Doctris - Doctor Appointment Booking System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="https://shreethemes.in" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />

    </head>

    <body>
        <!-- Loader -->
        <div id="preloader">
            <div id="status">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
            </div>
        </div>
        <!-- Loader -->

        <div class="back-to-home rounded d-none d-sm-block">
            <a href="index.html" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->
        <section class="bg-half-150 d-table w-100 bg-light" style="background: url('${pageContext.request.contextPath}/assets/images/bg/bg-lines-one.png') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <img src="${pageContext.request.contextPath}/assets/images/logo-dark.png" height="24" class="mx-auto d-block" alt="">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Sign Up</h4>  
                                <form action="signup" class="login-form mt-4" method="post">
                                    <div class="row">

                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Name: <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" placeholder="Your Name" required="" name="name">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Your Email <span class="text-danger">*</span></label>
                                                <input type="email" class="form-control" placeholder="Email" name="email" required="">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Password <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" placeholder="Password" required="" name="password">
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">                                               
                                                <label class="form-label">Phone:  <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" placeholder="Your Phone" name="phone" required="" >
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">                                                
                                                <label class="form-label">Gender: <span class="text-danger">*</span></label><br>
                                                <select name="gender" class="form-control doctor-name select2input">
                                                    <option value="male">Male</option>
                                                    <option value="female">Female</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Address: <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control" placeholder="Your address" required="" name="address">
                                            </div>
                                        </div>

                                        <div class="col-md-12">
                                            <div class="mb-3">                                               
                                                <label class="form-label">Date of birth:  <span class="text-danger">*</span></label>
                                                <input type="date" class="form-control" placeholder="Date of birth" name="dob" required="" >
                                            </div>
                                        </div>
                                        
                                           <div class="col-md-6">
                                            <div class="mb-3">                                               
                                                <label class="form-label">Location:  <span class="text-danger">*</span></label>
                                                <select name="locationid" class="form-control doctor-name select2input">
                                                    <c:forEach items="${requestScope.lsld}" var="ld">
                                                        <option value="${ld.getId()}">${ld.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">                                                
                                                <label class="form-label">Specialization: <span class="text-danger">*</span></label><br>
                                                <select name="speid" class="form-control doctor-name select2input">
                                                    <c:forEach items="${requestScope.lssp}" var="lsp">
                                                        <option value="${lsp.getId()}">${lsp.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-md-12">
                                            <div class="md-3">
                                                <div class="custom-file">
                                                    <input type="file" class="form-control doctor-name select2inputt" id="customFile" name="img">
                                                    <label class="custom-file-label" for="customFile"></label>
                                                </div>
                                            </div>
                                        </div>
                                        
                                         <div class="col-md-12">
                                            <div class="mb-6">                                                
                                                <label class="form-label">Permision: <span class="text-danger">*</span></label><br>
                                                <select name="speid" class="form-control doctor-name select2input">
                                                    <c:forEach items="${requestScope.lsper}" var="lsper">
                                                        <option value="${lsper.getId()}">${lsper.getPermision()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <script>
// Add the following code if you want the name of the file appear on select
                                            $(".custom-file-input").on("change", function () {
                                                var fileName = $(this).val().split("\\").pop();
                                                $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                                            });
                                        </script>

                                        <div class="col-md-12">
                                            <div class="d-grid">
                                                <button class="btn btn-primary">Register</button>
                                            </div>
                                        </div>


                                        <div class="mx-auto">
                                            <p class="mb-0 mt-3"><small class="text-dark me-2">Already have an account ?</small> <a href="login" class="text-dark fw-bold">Sign in</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        
                        </div><!---->
                    </div> <!--end col-->
                </div><!--end row-->
            </div> <!--end container-->
        </section><!--end section-->
        <!-- Hero End -->

        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
        <!-- Icons -->
        <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

    </body>

</html>
