<%@page import="DAO.CursoDAO" %>
<%@page import="DAOImpl.CursoDAOImpl" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Entidad.Curso" %>
<%@page import="Entidad.Materia" %>
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
   <script src="../scripts/master.js"></script>
    <title>Reportes</title>
</head>
<body>
<%
         String usuario =   application.getAttribute("var").toString();
           %>
    <header>
        <div class="contenedor">
            <div class="icon">
                <a id="logoLink" href=""><h1>S.G.E</h1></a>
            </div>

          <nav class="menu">
                <a id="alumnosLink" href="${pageContext.request.contextPath}/ADMIN/AlumnosMain.jsp">Alumnos</a>
                <a id="profesoresLink" href="${pageContext.request.contextPath}/ADMIN/Profesores.jsp">Profesores</a>
                <a id="cursosLink" href="${pageContext.request.contextPath}/ADMIN/Cursos.jsp">Cursos</a>
                <a id="usuariosLink" href="${pageContext.request.contextPath}/ADMIN/UsuariosMain.jsp">Usuarios</a>
                <a id="reportesLink" href="${pageContext.request.contextPath}/ADMIN/Reportes.jsp">Reportes</a>
                <a class="perfil-name" id="perfilName"><%=usuario %></a>
                <a class="salir" id="salirLink" href="/TPINT_GRUPO_6_LAB_4/Login.jsp">Salir</a>

            </nav>
        </div>
    </header>

    <grid>
        <p align="center" style="font-size:30px">REPORTES</p>
		<form action="${pageContext.request.contextPath}/Servlet_Reportes" method="post">
		
                <div class="container">  
	        	<div class="row">
	            	<div class="col-3">
	                        <button type="submit" class="btn btn-primary" name="btnListar" >Listar cursos</button>
	            	</div>
	            	</div>
	            	</div>

        </form>
        <br>
        
		        <div class="container-fluid">
            <div class="row">
                <div class="col">
                
                    <table id="table_id" class="table table-dark">
                    
                       <thead> 
                            <tr>
                                <th scope="col">Id curso</th>
                                <th scope="col">Materia</th>
                                <th scope="col">Cuatrimestre</th>
                                <th scope="col">Año</th>
                                <th scope="col">Profesor</th>
                                <th scope="col">Reporte</th>
                                
                            </tr>
                            
                      </thead>  
                   <% 
        ArrayList<Curso> listaCursos = null;            
		if (request.getAttribute("listaC")!=null)
        {
        	listaCursos = (ArrayList<Curso>)request.getAttribute("listaC");	
        }
        %>
						<% if (listaCursos!=null)
                        	for(Curso cur : listaCursos)
                        	{

                        %>
                          
                        <tbody class="grid_Linea">
                            <tr>
                            	<form action="${pageContext.request.contextPath}/Servlet_Reportes"  method="post">
                            	
                                	<td><%=cur.getCursoId() %><input type="hidden" name="id" value="<%=cur.getCursoId()%>"></td>
                                    <td><%=cur.getMateria().getNombre()%></td><input type="hidden" name="materia" value="<%=cur.getMateria().getIdmateria()%>">
                                	<td><%=cur.getCuatrimestre()%></td><input type="hidden" name="cuatri" value="<%=cur.getCuatrimestre()%>">
                                	<td><%=cur.getAño()%></td><input type="hidden" name="año" value="<%=cur.getAño()%>">
                                	<td><%=cur.getProfesor().getNombre()%> <%=cur.getProfesor().getApellido()%> </td><input type="hidden" name="profe" value="<%=cur.getProfesor().getLegajo()%>">                                	                           
                                	<td>  <input type="submit" name="btnReporte" value="Ver Reporte"></td>
                                                                
                                </form>
                            </tr>
                          <% } %>
                        </tbody>                        
                    </table>                    
                </div>
            </div>
        </div>
    </grid>
</body>
</html>

