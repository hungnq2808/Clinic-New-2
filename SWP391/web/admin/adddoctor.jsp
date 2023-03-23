<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>

    <body>
        <jsp:include page="../layout/preloader.jsp"/>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Add New Doctor</h5>                         
                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="index.html">Doctris</a></li>
                                    <li class="breadcrumb-item"><a href="doctors.html">Doctors</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Add Doctor</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-lg-12 mt-4">
                                <div class="card border-0 p-4 rounded shadow">                                    
                                    <form action="doctormanage?action=submit" method="post" class="login-form mt-4" onSubmit="document.getElementById('submit').disabled = true;">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">                                               
                                                    <label class="form-label">Họ Tên <span class="text-danger">*</span></label>
                                                    <input value="${requestScope.name}"type="text" oninvalid="CheckFullName(this);" oninput="CheckFullName(this);" class="form-control" name="name" required="">
                                                </div>
                                            </div>                                         
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Email <span class="text-danger">*</span></label>
                                                    <input value="" type="email" oninvalid="CheckEmail(this);" oninput="CheckEmail(this);" class="form-control" name="email" required="">
                                                </div>
                                            </div>                    
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                                    <input value="" type="password" id="password" oninvalid="CheckPassword(this);" oninput="CheckPassword(this);" class="form-control" name="password" required="">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Nhập lại mật khẩu <span class="text-danger">*</span></label>
                                                    <input value="" type="password" oninvalid="CheckRePassword(this);" oninput="CheckRePassword(this);" class="form-control" name="repassword" required="">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="mb-3">                                               
                                                    <label class="form-label">Số điện thoại <span class="text-danger">*</span></label>
                                                    <input value="" type="text" oninvalid="CheckPhone(this);" oninput="CheckPhone(this);" class="form-control" name="phone" required="">
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Chuyên môn</label>
                                                    <select name="speciality" class="form-select">
                                                        <c:forEach items="${speciality}" var="s">
                                                            <option value="${s.id}">${s.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div><!--end col-->

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
                                                    <label class="form-label">Permision: <span class="text-danger">*</span></label><br>
                                                    <select name="perid" class="form-control doctor-name select2input">
                                                        <c:forEach items="${requestScope.lsper}" var="lsper">
                                                            <option value="${lsper.getId()}">${lsper.getPermision()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                           
                                                                                                                          
                                               <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Address:  <span class="text-danger">*</span></label>
                                                    <input name="dob" type="text" onkeydown="return false" min="1923-01-01" max="2004-01-01" class="form-control" required=""></input>
                                                </div>
                                            </div>                                                                                  
                                                                                                                          
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Ngày sinh <span class="text-danger">*</span></label>
                                                    <input name="dob" type="date" onkeydown="return false" min="1923-01-01" max="2004-01-01" class="form-control" required=""></input>
                                                </div>
                                            </div>
                                            

                                            <div class="col-md-6">
                                                <div class="md-3">
                                                    <div class="custom-file">
                                                        <input type="file" class="form-control doctor-name select2inputt" id="customFile" name="img" placeholder="Choose your image...." required="">
                                                        <label class="custom-file-label" for="customFile"></label>
                                                    </div>
                                                </div>
                                            </div>   
                                                                                                                          
                                              <div class="col-md-6">
                                                <div class="mb-3">                                               
                                                    <label class="form-label">Giới tính <span class="text-danger">*</span></label>
                                                    <div class="my-3">
                                                        <div class="form-check">
                                                            <input ${(requestScope.gender) ? "checked=\"checked\"" : ""} id="credit" name="gender" value="true" type="radio" class="form-check-input"
                                                                                                                         checked required>
                                                            <label class="form-check-label">Nam</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input ${(!requestScope.gender) ? "checked=\"checked\"" : ""} id="debit" name="gender" value="false" type="radio" class="form-check-input"
                                                                                                                          required>
                                                            <label class="form-check-label">Nữ</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                                                                    

                                            <script>
// Add the following code if you want the name of the file appear on select
                                                $(".custom-file-input").on("change", function () {
                                                    var fileName = $(this).val().split("\\").pop();
                                                    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                                                });
                                            </script>                                                                              
                                                               
                                        </div>

                                        <button type="submit" class="btn btn-primary">Add Doctor</button>
                                    </form>
                                    <p style="color: red; align-content: center;">
                                        ${requestScope.error}
                                    </p>
                                </div>
                            </div><!--end col-->
                        </div><!--end row-->
                    </div>
                </div><!--end container-->
                <jsp:include page="../admin/layout/footer.jsp"/>

                <script src="assets/js/jquery.min.js"></script>
                <script src="assets/js/bootstrap.bundle.min.js"></script>
                <script src="assets/js/simplebar.min.js"></script>
                <script src="assets/js/select2.min.js"></script>
                <script src="assets/js/select2.init.js"></script>
                <script src="assets/js/flatpickr.min.js"></script>
                <script src="assets/js/flatpickr.init.js"></script>
                <script src="assets/js/jquery.timepicker.min.js"></script> 
                <script src="assets/js/timepicker.init.js"></script> 
                <script src="assets/js/feather.min.js"></script>
                <script src="assets/js/app.js"></script>

                <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">-->
                <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


                </body>

                </html>
