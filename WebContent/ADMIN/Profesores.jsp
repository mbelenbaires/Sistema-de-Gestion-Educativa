<%@page import="Entidad.Profesor" %>
<%@page import="NegocioImpl.ProfesorNegocioImpl" %>
<%@page import="DAO.ProfesorDAO" %>    
<%@page import="java.util.ArrayList" %>  
<%@page import="DAOImpl.LocalidadDAOImpl" %>
<%@page import="DAOImpl.ProvinciaDAOImpl" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <script src="../scripts/master.js"></script>
    <script src="scripts/mensajes.js"></script>
    <script type="text/javascript" charset="utf8"	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="/TPINT_GRUPO_6_LAB_4/css/styles.css">

    <title>Profesores</title>
    
</head>
<body>
<%
         String usuario =   application.getAttribute("var").toString();
           %>
    <header>
        <div class="contenedor">
          <div class="icon" >
             <a id="logoLink" href=""><h1> S.G.E</h1></a>
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
       <form action="${pageContext.request.contextPath}/Servlet_Profesor" method="post">  
        <p align="center" style="font-size:30px">PROFESORES</p>
        <div class="container">
        <button type="submit" class="btn btn-primary" name="btnNuevo">Haga click aquí para ingresar un nuevo Profesor</button>
        </div>
        </form>
         
         <form action="${pageContext.request.contextPath}/Servlet_Profesor" method="get">
        <div class="container">      
        <br>
        <br>      
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Legajo</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtLegajo">
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">DNI</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtDni">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Nombre</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtNombre">
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Apellido</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtApellido">
                    </div>
                </div>
            </div>
        </div>

        <div class="container" style="margin-bottom: 20px;">
        <p> Ingrese datos en las cajas de texto para buscar un Profesor </p>
        <button type="submit" class="btn btn-success" name="btnBuscar" >Buscar Profesores</button>        
       </div>
       </form>
       
                   <% 
        ArrayList<Profesor> listaProfesor = null;
            
        if(request.getAttribute("listaF")!=null){
        	listaProfesor = (ArrayList<Profesor>)request.getAttribute("listaF");
        	if ((listaProfesor).isEmpty()){ %> <h5 style="color:red;"> No se encontraron Profesores. </h5> <% }
        }
        
        else if (request.getAttribute("listaA")!=null)
        {
        	listaProfesor = (ArrayList<Profesor>)request.getAttribute("listaA");	

        }
        %>


        <form method="post" action="${pageContext.request.contextPath}/Servlet_Profesor">
                	        <div class="container">  
	        	<div class="row">
	            	<div class="col-3">
	                <button type="submit" class="btn btn-primary" name="btnListar" >Listar Profesores</button>
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
        		<h6 id="textAlert">Se eliminó el profesor exitosamente.</h6>
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
        		<h6 id="textAlert">Se agregó el profesor exitosamente.</h6>
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
        		<h6 id="textAlert">Se modificó el profesor exitosamente.</h6>
        	</div>
		</div>

<% } %>

        <div class="container-fluid">
            <div class="row">
                <div class="col">
                
                    <table id="table_id" class="table table-dark">
                    
                       <thead> 
                            <tr>
                                <th scope="col">Legajo</th>
                                <th scope="col">DNI</th>
                                <th scope="col">Apellido</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Fecha Nacimiento</th>
                                <th scope="col">Dirección</th>
                                <th scope="col">Localidad</th>
                                <th scope="col">Provincia</th>
                                <th scope="col">Email</th>
                                <th scope="col">Teléfono</th>
                                <th scope="col">Modificar</th>
                                <th scope="col">Eliminar</th>
                            </tr>
                            
                      </thead>  
                                            
                        <% if (listaProfesor!=null)
                        	for(Profesor prof : listaProfesor)
                        	{

                        %>
                          
                          
                        <tbody class="grid_Linea">
                            <tr>
                            	<form action="${pageContext.request.contextPath}/Servlet_Profesor"  method="post">
                            	
                                	<td><%=prof.getLegajo() %><input type="hidden" name="lega" value="<%=prof.getLegajo() %>"></td>
                                	<td><%=prof.getDni()%></td><input type="hidden" name="dni" value="<%=prof.getDni()%>">
                                	<td><%=prof.getApellido()%></td><input type="hidden" name="ape" value="<%=prof.getApellido()%>">
                                	<td><%=prof.getNombre()%></td><input type="hidden" name="nom" value="<%=prof.getNombre()%>">
                                	<td><%=prof.getFechanac()%></td><input type="hidden" name="fechanac" value="<%=prof.getFechanac()%>">
                                	<td><%=prof.getDireccion()%></td><input type="hidden" name="dir" value="<%=prof.getDireccion()%>">
                                	<td><%=prof.getLocalidad().getNombre()%> </td> <input type="hidden" name="loc" value="<%=prof.getLocalidad().getId()%>"> 
                                	<td><%=prof.getProvincia().getNombre()%> </td> <input type="hidden" name="prov" value="<%=prof.getProvincia().getId()%>">
                                	<td><%=prof.getMail()%></td><input type="hidden" name="mail" value="<%=prof.getMail()%>">
                                	<td><%=prof.getTelefono()%></td>   <input type="hidden" name="tel" value="<%=prof.getTelefono()%>">                             
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
</html></html>