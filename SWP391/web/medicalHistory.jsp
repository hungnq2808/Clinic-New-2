

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


    </head>
    <body>
        <header>
            <a href="index.jsp">
                <img src="assets/images/svg/../logo-dark.png" alt="" class="logo">

            </a>
            <style>
                .logo {
                    display : flex;
                    height: 50px;
                }
            </style>

        </header>

    <center>
        <div class="banner">
            <div class="text-banner">
                <h3>Medical history</h3>
            </div>
            <style>
                .banner {
                    color: blue;

                }
            </style>
        </div>



    </center>






    <div class="container">


        <table class="table">
            <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>Bệnh nhân</th>
                    <th>Bệnh</th>
                    <th>Ngày</th>
                    <th>Ghi chú</th>
                    <th>Tái khám</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.list}" var="a">
                    <tr>
                        <th>${a.getId()}</th>
                        <th>${a.getPatient_id().getPatientName()}</th>
                        <th>${a.getDisease()}</th>
                        <th>${a.getDate()}</th>
                        <th>${a.getNote()}</th>
                        <th>
                            <c:if test="${a.reexam_medical == true}">
                                Tái khám
                            </c:if>
                            <c:if test="${a.reexam_medical == false}">
                                Không tái khám
                            </c:if>
                        </th>
                        <th>
                            <a href="UpdateMedicalHistory.jsp?mhid=${a.id}" class="btn btn-primary">Sửa bệnh án</a>

                        </th>
                        <th>
                            <a href="doctorbooking?pid=${a.patient_id.id}" type="button" class="btn btn-primary">Hẹn tái khám</a>


                        </th>

                    </tr>

                </c:forEach>
            </tbody>
        </table>
    </div>




</body>
</html>
