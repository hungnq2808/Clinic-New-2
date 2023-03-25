<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-md-6 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Services</h5>
                                </div>
                                <div class="col-md-7">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="servicemanage?action=search" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input type="text" class="form-control border rounded-pill" name="txt" id="s" placeholder="Tìm kiếm tên dịch vụ...">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            
                            <div class="col-md-2">
                                <a href="servicemanage?action=add"><button class="btn btn-primary">Thêm mới</button></a>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive bg-white shadow rounded">
                                    <table class="table mb-0 table-center">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" >ID</th>
                                                <th class="border-bottom p-3" >Tên dịch vụ</th>
                                                <th class="border-bottom p-3" >Phí</th>
                                                <th class="border-bottom p-3" >Trạng thái</th>
                                                <th class="border-bottom p-3 text-center" >Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${service}" var="s">
                                                <tr>                                                   
                                                    <td class="p-3">${s.id}</td>
                                                    <td class="p-3">${s.name}</td>
                                                    <td class="p-3"><fmt:formatNumber pattern="#,###,###,###" value="${s.price}"/> đ</td>
                                                    <c:if test="${s.status == true}">
                                                        <td class="p-3">Active</td>
                                                    </c:if>
                                                    <c:if test="${s.status == false}">
                                                        <td class="p-3">Disable</td>
                                                    </c:if>
                                                    <td class=" text-center p-3">
                                                        
                                                        <a href="servicemanage?action=detail&id=${s.id}" type="button"class="btn btn-info">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <c:set var="page" value="${page}"/>
                        <div class="row text-center">
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center justify-content-between">
                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                        <c:forEach begin="${1}" end="${num}" var="i">
                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>

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
        <script src="assets/js/sweetalert.min.js"></script>

        <script>
                                                        $(document).ready(jQuery(function () {
                                                            jQuery(".disable").click(function () {
                                                                swal({
                                                                    title: "Cảnh báo",
                                                                    text: "Bạn có chắc chắn muốn khóa dịch vụ này?",
                                                                    buttons: ["Hủy bỏ", "Đồng ý"],
                                                                })
                                                                        .then((willDelete) => {
                                                                            if (willDelete) {
                                                                                window.location = "servicemanage?action=update_status&id=" + $(this).attr("value") + "&status=false";
                                                                                swal("Đã khóa thành công.!", {
                                                                                });
                                                                            }
                                                                        });
                                                            });

                                                            jQuery(".active").click(function () {
                                                                swal({
                                                                    title: "Cảnh báo",
                                                                    text: "Bạn có chắc chắn muốn kích hoạt dịch vụ này?",
                                                                    buttons: ["Hủy bỏ", "Đồng ý"],
                                                                })
                                                                        .then((willDelete) => {
                                                                            if (willDelete) {
                                                                                window.location = "servicemanage?action=update_status&id=" + $(this).attr("value") + "&status=true";
                                                                                swal("Đã kích hoạt thành công.!", {
                                                                                });
                                                                            }
                                                                        });
                                                            });
                                                        }));
        </script>

    </body>

</html>

