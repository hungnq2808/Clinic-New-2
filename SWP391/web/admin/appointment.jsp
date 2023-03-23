<%-- 
    Document   : appointment
    Created on : Feb 18, 2023, 2:59:04 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    
    <center>
        <h1> List of Appointment </h1>
        <h3><a href="add">Create new appointment</a></h3>
        <table border="1px" width="60%">
            <tr>
                <th>ID</th>
                <th>Bệnh nhân </th>
                <th>Bác sĩ</th>
                <th>Dịch vụ</th>
                <th>Ngày</th>
                <th>Giờ</th>
                <th>Trạng thái</th>
            </tr>
            <c:forEach items="${data}" var="x">
                <tr>
                    <td>${x.id}</td> 
                    <td>${x.patients.firstname}</td> 
                    <td>${x.user.firstname}</td> 
                    <td>${x.service.name}</td> 
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${x.date}" /></td> 
                    
                    <td>${x.time}</td> 
                    <td>
                        <c:if test="${x.status == 1}">
                            Đã xử lí
                        </c:if>
                            <c:if test="${x.status == 0}">
                            Chưa xử lí
                        </c:if>
                    </td> 
                    <td>
                        <a href="#">CHỉnh sửa</a>
                        
                        
                        
                    </td>
                    <td>
                        <a href="#">Đồng ý</a>
                        
                        
                        
                    </td>
                    <td>
                        
                        <a href="#">Hủy bỏ</a>
                        
                        
                    </td>
                    <td>
                        
                        <a href="#">Chi tiết</a>
                        
                        
                    </td>
                    
                    
                </tr>
            </c:forEach>
        </table>
    </center>
    
    
   
</body>
</html>
