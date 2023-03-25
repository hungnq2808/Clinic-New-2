

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css2/style.css">
    </head>
    <body>
        <form action="updatemedical" method ="post">
            <div class="create">
                <div class="row">
                    <div class="col col-sm-3 col-md-3 col-lg-3"></div>
                    <div class="col col-sm-6 col-md-6 col-lg-6" id="bet">
                        <div class="text-center" id="up">
                            <h3>Update Medical History </h3>
                        </div>

                        

                        <div>
                            Enter disease:<input type="text" class="form-control" name="disease"/><br/>
                            Enter date:<input type="text" class="form-control" placeholder="YYYY-MM-DD" name="date"><br/>
                            Enter note:<input type="text" class="form-control"  name="note"><br/>

                        </div>

                        Reexam_medical:
                        <div class="col">
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="customRadio" name="reexam" value="True" >
                                <label class="custom-control-label" for="customRadio">YES</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="customRadio2" name="reexam" value ="False" >
                                <label class="custom-control-label" for="customRadio2">NO</label>
                            </div>
                        </div>
                        <div class="sub">

                            <center><button type="submit" class="btn btn-primary btn-lg">Save</button></center>
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <h1>${requestScope.mess}</h1>


    </body>
</html>
