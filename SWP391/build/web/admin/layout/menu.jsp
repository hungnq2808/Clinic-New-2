<%-- 
    Document   : menu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="sidebar" class="sidebar-wrapper">
    <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
        <div class="sidebar-brand">
            <a href="home">
                <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
            </a>
        </div>

        <ul class="sidebar-menu pt-3">

                <li><a href="dashboard?action=default"><i class="uil uil-dashboard me-2 d-inline-block"></i>Bảng điều khiển</a></li>
                <li><a href="accountmanage?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Tài khoản</a></li>
                <li><a href="doctormanage?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Bác sĩ</a></li>
                <li><a href="patient?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Bệnh nhân</a></li>
                <li><a href="servicemanage?action=all"><i class="uil uil-apps me-2 d-inline-block"></i>Quản lý Dịch vụ</a></li>

        </ul>
    </div>
</nav>
