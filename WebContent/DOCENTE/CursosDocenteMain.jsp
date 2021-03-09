<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Curso" %>
<%@page import="NegocioImpl.CursoNegocioImpl" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="/TPINT_GRUPO_6_LAB_4/css/styles.css">
    
	
	<script type="text/javascript">
		$(document).ready( function () {
	    $('#table_id').DataTable();
		} );
	</script>
	
	 <script src="../scripts/funciones.js"></script>
	 
    <title>Cursos</title>
</head>
<body>
    <header>
        <div class="contenedor">
            <div class="icon">
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
    <form  method="post" action="${pageContext.request.contextPath}/Servlet_DocenteCurso">

        <div class="container-fluid">
            <div class="contenedor">
                <br><br>
                <h1>Bienvenido/a</h1> <h2 id="user"></h2>
                <h3>al Sistema de Gestión Educativa</h3>
            </div>
            <p align="center" style="font-size:30px">CURSOS</p>
        </div>
        <div class="container-fluid">
            <u> <p>Haga click en el curso del que desea más informacion</p> </u>
            <div class="row">
                <div class="col">
                    <table id="table_id" class="display">
                        <thead>
                            <tr>
                                <th scope="col">Curso</th>
                                <th scope="col">Materia</th>
                                <th scope="col">Cuatrimestre</th>
                                <th scope="col">Año</th>
                                <th scope="col">Calificar y ver Alumnos</th>
                            </tr>
                        </thead>
                        <tbody class="grid_Linea">
                        	
                        <% 
                        List<Curso> cursos  = new ArrayList<Curso>();
               	  		if (request.getAttribute("listaCursoXProfesor") != null){
               	  			cursos = (List<Curso>) request.getAttribute("listaCursoXProfesor");
               	  		}
                       	 for (Curso unCurso : cursos) {	
                        %> 
                        	
                            <tr>
                            	<td><%=unCurso.getCursoId()%></td>
                                <td><%=unCurso.getMateria().getNombre()%></td>
                                <td><%=unCurso.getCuatrimestre()%></td>
                                <td><%=unCurso.getAño()%></td>
                                <td><input type="submit" name="btnSeleccionar" class="DocenteMainbtnSeleccionar" value="<%=unCurso.getCursoId()%>" /> 
                                </td>
                            </tr>
                        <%
                       	}
                        %>
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </form>
</body>
</html>