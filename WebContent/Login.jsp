<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" 
    crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/TPINT_GRUPO_6_LAB_4/css/styles.css">
    <script src="scripts/mensajes.js"></script>
    
    <title>S.G.E</title>
</head>
<body class="login">
    <div class="login-box">
        <div class="logo">
            <img class="logo" src="img/logo.jpg" />
        </div>
            <form method="post" action="${pageContext.request.contextPath}/Servlet_Login"  >
                <div class="container">
                    <div class="col-12">
                        <div class="form-group has-feedback">
                            <input id="user" type="text" class="form-control" placeholder="Usuario" name="txtUsuario" runat="server" required />
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group has-feedback">
                            <input id="pass"  type="password" class="form-control" placeholder="Contraseña" name="txtContrase" runat="server" required/>
                        </div>
                    </div>

                    <div class="col-6">
                       <input type="submit" value="Ingresar" class="btn btn-success" name="btnIngresar" />
                    </div>
                </div>
            </form>
    </div>

    <div id="alert" class="alert alert-danger col-6" role="alert" style="position: absolute; left: 23%; top: 3%; display: none;">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="closeAlert()">
            <span aria-hidden="true">&times;</span>
        </button>
        <h6 id="textAlert">Datos incorrectos!</h6>
    </div>

 
 <%
 	String Resultado = "h";
 	if(request.getAttribute("Error")!= null) {  
 		Resultado = request.getAttribute("Error").toString();
	%>
		  <script>$('[id$=alert]').css('display', '');
		 </script>
	<%
 	}
 		
%>	

</body>
</html>