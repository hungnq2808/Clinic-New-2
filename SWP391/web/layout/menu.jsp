<%-- 
    Document   : menu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <header id="topnav" class="navigation sticky">
        <div class="container">

            <div>
                <a class="logo" href="home">
                    <img src="assets/images/logo-light.png" height="24" class="l-light" alt="">
                    <img src="assets/images/logo-dark.png" class="l-dark" height="24" alt="">
                </a>
            </div>

            <div class="menu-extras">
                <div class="menu-item">
                    <a class="navbar-toggle" id="isToggle" onclick="toggleMenu()">
                        <div class="lines">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                    </a>
                </div>
            </div>

            <ul class="dropdowns list-inline mb-0">

                <li class="list-inline-item mb-0 ms-1">
                    <a href="javascript:void(0)" class="btn btn-icon btn-pills btn-primary" data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop">
                        <i class="uil uil-search"></i>
                    </a>
                </li>

                <li class="list-inline-item mb-0 ms-1">
                    <div class="dropdown dropdown-primary">



                        <a class="btn btn-primary p-1" href="login">Login</a>

                        
                    </div>
                </li>

            </ul>

            <div id="navigation">

                <ul class="navigation-menu nav-left nav-light">
                    <li><a href="home" class="sub-menu-item">Trang chủ</a></li>
                    <li><a href="service?action=all" class="sub-menu-item">Dịch vụ</a></li>
                    <li><a href="aboutus" class="sub-menu-item">Giới thiệu</a></li>
                    <li><a href="blogs" class="sub-menu-item">Tin tức & chủ đề</a></li>
                </ul>

            </div>
        </div>
    </header>
</html>
