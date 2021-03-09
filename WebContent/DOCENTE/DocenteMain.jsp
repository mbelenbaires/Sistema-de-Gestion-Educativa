<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" 
    crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="/TPINT_GRUPO_6_LAB_4/css/styles.css">
    
     <script src="../scripts/funciones.js"></script>
    
    <title>Sistema de Gestión Educativa</title>
</head>
<body>
    <header>
        <div class="contenedor">
            <div class="icon" >
                <a id="logoLink" href=""><h1>S.G.E</h1></a>
            </div>
          <nav class="menu">
               <%  String usaurio = " ";
		           if (session.getAttribute("Usuario") != null){
		                  usaurio =session.getAttribute("Usuario").toString(); }
               %> 
                <a id="cursosLink" href="${pageContext.request.contextPath}/Servlet_DocenteMain">Cursos</a>
                <a id="perfilLink" href="${pageContext.request.contextPath}/Servlet_DocenteUsuario"> <%=usaurio%> </a>
                <a class="salir" id="salirLink" href="/TPINT_GRUPO_6_LAB_4/Login.jsp">Salir</a>

            </nav>
        </div>
    </header>

    <cartel>
        <div class="contenedor">
            <h1>Bienvenido/a</h1> <h2 id="user"></h2>
            <h3>al Sistema de Gestión Educativa</h3>
        </div>
    </cartel>
</body>
</html>
