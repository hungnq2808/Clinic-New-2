

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <style>
            .Choicefile{
                display: block;
                background: #396CF0;
                border: 1px solid #fff;
                color: #fff;
                width: 150px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                padding: 5px 0px;
                border-radius: 5px;
                font-weight: 500;
                align-items: center;
                justify-content: center;
            }

            .Choicefile:hover {
                text-decoration: none;
                color: white;
            }

            #uploadfile,
            .removeimg {
                display: none;
            }

            #thumbbox {
                position: relative;
                width: 100%;
                margin-bottom: 20px;
            }

            .removeimg {
                height: 25px;
                position: absolute;
                background-repeat: no-repeat;
                top: 5px;
                left: 5px;
                background-size: 25px;
                width: 25px;
                border-radius: 50%;

            }

            .removeimg::before {
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                content: '';
                border: 1px solid red;
                background: red;
                text-align: center;
                display: block;
                margin-top: 11px;
                transform: rotate(45deg);
            }

            .removeimg::after {
                content: '';
                background: red;
                border: 1px solid red;
                text-align: center;
                display: block;
                transform: rotate(-45deg);
                margin-top: -2px;
            }
        </style>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-lg-12">
                                <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded" id="pills-tab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link rounded active" id="pills-cloud-tab" data-bs-toggle="pill" href="#info" role="tab" aria-controls="infor" aria-selected="false">
                                            <div class="text-center pt-1 pb-1">
                                                <h4 class="title font-weight-normal mb-0">Thông tin</h4>
                                            </div>
                                        </a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link rounded" id="pills-smart-tab" data-bs-toggle="pill" href="#edit" role="tab" aria-controls="edit" aria-selected="false">
                                            <div class="text-center pt-1 pb-1">
                                                <h4 class="title font-weight-normal mb-0">Chỉnh sửa</h4>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane show active row" id="info" role="tabpanel" aria-labelledby="info">
                                <div class="col-lg-12 col-md-12 mt-60">
                                    <div class="bg-white rounded shadow overflow-hidden">
                                        <div class="p-4 border-bottom">
                                            <h5 class="mb-0">Thông tin Dịch Vụ</h5>
                                        </div>
                                        <br><br><br><br><br>

                                        <div class="d-flex align-items-center mt-2">
                                            <i class="uil uil-book-open align-text-bottom text-primary h5 mb-0 me-2"></i>
                                            <h6 class="mb-0">Tên dịch vụ</h6>
                                            <p class="text-muted mb-0 ms-2">${service.name}</p>
                                        </div>

                                        <div class="d-flex align-items-center mt-2">
                                            <i class="uil uil-book-open align-text-bottom text-primary h5 mb-0 me-2"></i>
                                            <h6 class="mb-0">Giá dịch vụ</h6>
                                            <p class="text-muted mb-0 ms-2">${service.price}vnđ</p>
                                        </div>
                                        
                                        <div class="d-flex align-items-center mt-2">
                                            <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                            <h6 class="mb-0">Trạng thái</h6>
                                            <c:if test="${service.status == true}">
                                                <p class="text-muted mb-0 ms-2">Hoạt động</p>
                                            </c:if>
                                            <c:if test="${service.status == false}">
                                                <p class="text-muted mb-0 ms-2">Khóa</p>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="tab-pane fade" id="edit" role="tabpanel" aria-labelledby="edit">
                            <div class="card border-0 shadow overflow-hidden">
                                <div class="tab-content p-4" id="pills-tabContent">
                                    <form action="servicemanage?action=update_info&id=${service.id}" method="POST" class="mt-4" onSubmit="document.getElementById('submit').disabled = true;">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Tên dịch vụ</label>
                                                    <input name="title" oninvalid="CheckTitle(this);" oninput="CheckTitle(this);" id="name" type="text" class="form-control" value="${service.name}">
                                                </div>
                                            </div>

                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Giá dịch vụ</label>
                                                    <input name="fee" oninvalid="CheckPrice(this);" oninput="CheckPrice(this);"  id="number" type="text" class="form-control" value="${service.price}">
                                                </div>
                                            </div>

                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Trạng thái <span class="text-danger"></span></label>
                                                    <table>
                                                        <tbody>
                                                            <tr>
                                                                <td><input id="credit" name="status" ${service.status==true?"checked":""} value="true" type="radio" class="form-check-input"
                                                                           checked required ></td>
                                                                <td><label class="form-check-label">Hoạt động</label></td>
                                                                <td></td>
                                                                <td><input id="debit" name="status" ${service.status==false?"checked":""} value="false" type="radio" class="form-check-input"
                                                                           required></td>
                                                                <td><label class="form-check-label">Khóa</label></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <input type="submit" id="submit" name="send" class="btn btn-primary"
                                                       value="Cập nhật">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>

        <jsp:include page="../admin/layout/footer.jsp"/>
    </main>
</div>

<script src="assets/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/simplebar.min.js"></script>
<script src="assets/js/feather.min.js"></script>
<script src="assets/js/app.js"></script>
<script>

                                    function readURL(input, thumbimage) {
                                        if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
                                            var reader = new FileReader();
                                            reader.onload = function (e) {
                                                $("#thumbimage").attr('src', e.target.result);
                                            }
                                            reader.readAsDataURL(input.files[0]);
                                        } else { // Sử dụng cho IE
                                            $("#thumbimage").attr('src', input.value);

                                        }
                                        $("#thumbimage").show();
                                        $('.filename').text($("#uploadfile").val());
                                        $(".Choicefile").hide();
                                        $(".Update").show();
                                        $(".removeimg").show();
                                    }
                                    $(document).ready(function () {
                                        $(".Choicefile").bind('click', function () {
                                            $("#uploadfile").click();

                                        });
                                        $(".removeimg").click(function () {
                                            $("#thumbimage").attr('src', '').hide();
                                            $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
                                            $(".removeimg").hide();
                                            $(".Choicefile").show();
                                            $(".Update").hide();
                                            $(".filename").text("");
                                        });
                                    })
</script>
</body>

</html>

