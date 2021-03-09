<%@page import="Entidad.Materia" %>
<%@page import="Entidad.Curso" %>
<%@page import="Entidad.Profesor" %>
<%@page import="Entidad.Alumno" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" 
    crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <script src="../scripts/master.js"></script>
    <link rel="stylesheet" href="/TPINT_GRUPO_6_LAB_4/css/styles.css">
    <title>Alumnos del Curso</title>
</head>
<body>
<%
         String usuario =   application.getAttribute("var").toString();
           %>
    <header>
        <div class="contenedor">
          <div class="icon" >
              <a id="logoLink" href=""><h1>S.G.E</h1></a>
          </div>
          
          <nav class="menu">
                <a id="alumnosLink" href="${pageContext.request.contextPath}/ADMIN/AlumnosMain.jsp">Alumnos</a>
                <a id="profesoresLink" href="${pageContext.request.contextPath}/ADMIN/Profesores.jsp">Profesores</a>
                <a id="cursosLink" href="${pageContext.request.contextPath}/ADMIN/Cursos.jsp">Cursos</a>
                <a id="usuariosLink" href="${pageContext.request.contextPath}/ADMIN/UsuariosMain.jsp">Usuarios</a>
                <a id="reportesLink" href="${pageContext.request.contextPath}/ADMIN/Reportes.jsp">Reportes</a>
                <a class="perfil-name" id="perfilName">Perfil</a>
                <a class="perfil-name" id="perfilName"><%=usuario %></a>
                <a class="salir" id="salirLink" href="/TPINT_GRUPO_6_LAB_4/Login.jsp">Salir</a>

            </nav>
        </div>
    </header>
    
		<form method="post" action="${pageContext.request.contextPath}/Servlet_Cursos">
        <p align="center" style="font-size:30px">LISTADO DE ALUMNOS</p>
                <div class="container">      
        <br>      
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Legajo</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtLegajo" required>
                      
                    </div>
                </div>
            </div>

        </div>
        <br>
        <%Curso cur = (Curso)request.getAttribute("curso"); %>
        <div class="container">
            <div class="row">
                <div class="col-6">
                    <input type="submit" class="btn btn-success" name="btnAgregarAlu" value="Agregar alumno"/>
                    <input type="hidden" name="id" value="<%=cur.getCursoId()%>">
                </div>
            </div>
        </div>
        <br>
        </form>
        
        <%
        	boolean insert=false;
                            		
            if(request.getAttribute("ins")!=null){
			insert = (boolean)request.getAttribute("ins");
            }
        %>
        
<% if (insert)
	{
%>

		<div class="col-9">
			<div id="alert" class="alert alert-success col-6" role="alert">
        		<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="closeAlert()">
            		<span aria-hidden="true">&times;</span>
        		</button>
        		<h6 id="textAlert">Se agregó el alumno al curso exitosamente.</h6>
        	</div>
		</div>

<% } %>

        <%
        	boolean eliminoalu=false;
                            		
            if(request.getAttribute("elialu")!=null){
			eliminoalu = (boolean)request.getAttribute("elialu");
            }
        %>
        
<% if (eliminoalu)
	{
%>

		<div class="col-9">
			<div id="alert" class="alert alert-success col-6" role="alert">
        		<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="closeAlert()">
            		<span aria-hidden="true">&times;</span>
        		</button>
        		<h6 id="textAlert">Se eliminó el alumno del curso exitosamente.</h6>
        	</div>
		</div>



<% } %>




        <div class="container-fluid">
            <div class="row">
                <div class="col">
                    <table id="dataGrid" class="table table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Legajo</th>
                                <th scope="col">Apellido</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Eliminar</th>                            
                            </tr>
                        </thead>
                        
       <%ArrayList<Alumno> listaAlumnos = null;   
		if (request.getAttribute("listaAxC") != null){
			if (request.getAttribute("curso") != null){				
            		listaAlumnos = (ArrayList<Alumno>)request.getAttribute("listaAxC");
            		Materia m = new Materia();
            		
            		
                    for(Alumno alu : listaAlumnos)
                    { %>
                                
                         <tbody class="grid_Linea">
						<form action="${pageContext.request.contextPath}/Servlet_Cursos"  method="post">
                            	
						<td><%=alu.getLegajo()%><input type="hidden" name="lega" value="<%=alu.getLegajo()%>"></td>
						<td><%=alu.getApellido()%><input type="hidden" name="id" value="<%=cur.getCursoId()%>"></td> 
                        <td><%=alu.getNombre()%></td>                  
			            <td>  <input type="submit" name="btnEliminarAlu" value="Eliminar Alumno"></td>
                                                                
                        </form>                        
                        </tr>
                     <% } %>
                    </tbody>
                	</table>

            <%	}
		}
		
        	             

				if (listaAlumnos.isEmpty()) { %>
				<br>
				<p> Este curso no posee alumnos aún</p>
                        <% } %>
                </div>
            </div>
        </div>


</body>
</html>