<%@page import="Entidad.Curso" %>
<%@page import="Entidad.Materia" %>
<%@page import="NegocioImpl.CursoNegocioImpl" %>
<%@page import="DAO.CursoDAO" %>
<%@page import="DAOImpl.CursoDAOImpl" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <script src="../scripts/master.js"></script>
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="/TPINT_GRUPO_6_LAB_4/css/styles.css">
    


    

    <title>Cursos</title>
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
                <a class="perfil-name" id="perfilName"><%=usuario %></a>
                <a class="salir" id="salirLink" href="/TPINT_GRUPO_6_LAB_4/Login.jsp">Salir</a>

            </nav>
        </div>
    </header>

<grid>


        
        
        
        <form action="${pageContext.request.contextPath}/Servlet_Cursos" method="post">  
        <p align="center" style="font-size:30px">CURSOS</p>
        <div class="container">
        <button type="submit" class="btn btn-primary" name="btnNuevo">Haga click aquí para ingresar un nuevo curso</button>
        </div>
        </form>
         
         <form action="${pageContext.request.contextPath}/Servlet_Cursos" method="get">
        <div class="container">      
        <br>
        <br>      

            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Materia</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtMateria">
                    </div>
                </div>
                                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Profesor</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtProfesor">
                    </div>
                </div>
            </div>

        </div>

        <div class="container" style="margin-bottom: 20px;">
        <p> Ingrese datos en las cajas de texto para buscar un curso </p>
        <button type="submit" class="btn btn-success" name="btnBuscar" >Buscar cursos</button>        
       </div>
       </form>
       


        <form method="post" action="${pageContext.request.contextPath}/Servlet_Cursos">
                	        <div class="container">  
	        	<div class="row">
	            	<div class="col-3">
	                        <button type="submit" class="btn btn-primary" name="btnListar" >Listar cursos</button>
	            	</div>
	            	</div>
	            	</div>

        </form>
        <br>

       
    
       
        
                <%
        	boolean elimino=false;
                            		
            if(request.getAttribute("eli")!=null){
			elimino = (boolean)request.getAttribute("eli");
            }
        %>
        
<% if (elimino)
	{
%>
		<div class="col-9">
			<div id="alert" class="alert alert-success col-6" role="alert">
        		<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="closeAlert()">
            		<span aria-hidden="true">&times;</span>
        		</button>
        		<h6 id="textAlert">Se eliminó el curso exitosamente.</h6>
        	</div>
		</div>


<% } %>

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
        		<h6 id="textAlert">Se agregó el curso exitosamente.</h6>
        	</div>
		</div>

<% } %>

        <%
        	boolean actualizo=false;
                            		
            if(request.getAttribute("act")!=null){
			actualizo = (boolean)request.getAttribute("act");
            }
        %>
        
<% if (actualizo)
	{
%>

		<div class="col-9">
			<div id="alert" class="alert alert-success col-6" role="alert">
        		<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="closeAlert()">
            		<span aria-hidden="true">&times;</span>
        		</button>
        		<h6 id="textAlert">Se modificó el curso exitosamente.</h6>
        	</div>
		</div>

<% } %>

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
                                <th scope="col">Modificar</th>
                                <th scope="col">Eliminar</th>
                            </tr>
                            
                      </thead>  
                   <% 
        ArrayList<Curso> listaCursos = null;            
        if(request.getAttribute("listaF")!=null){
        	listaCursos = (ArrayList<Curso>)request.getAttribute("listaF");
        	if ((listaCursos).isEmpty()){ %> <h5 style="color:red;"> No se encontraron cursos. </h5> <% }
        }
        
        else if (request.getAttribute("listaC")!=null)
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
                            	<form action="${pageContext.request.contextPath}/Servlet_Cursos"  method="post">
                            	
                                	<td><%=cur.getCursoId() %><input type="hidden" name="id" value="<%=cur.getCursoId()%>"></td>
                                    <td><%=cur.getMateria().getNombre()%></td><input type="hidden" name="materia" value="<%=cur.getMateria().getIdmateria()%>">
                                	<td><%=cur.getCuatrimestre()%></td><input type="hidden" name="cuatri" value="<%=cur.getCuatrimestre()%>">
                                	<td><%=cur.getAño()%></td><input type="hidden" name="año" value="<%=cur.getAño()%>">
                                	<td><%=cur.getProfesor().getNombre()%> <%=cur.getProfesor().getApellido()%> </td><input type="hidden" name="profe" value="<%=cur.getProfesor().getLegajo()%>">                                	                           
                                	<td>  <input type="submit" name="btnModificar" value="Modificar"></td>
			                        <td>  <input type="submit" name="btnEliminar" value="Eliminar"></td>
                                                                
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


