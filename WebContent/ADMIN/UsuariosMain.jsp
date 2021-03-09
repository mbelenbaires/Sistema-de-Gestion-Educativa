<%@page import="Entidad.Usuario" %>
<%@page import="NegocioImpl.UsuarioNegocioImpl" %>
<%@page import="DAO.UsuarioDAO" %>
<%@page import="java.util.ArrayList" %>
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
   <script src="scripts/mensajes.js"></script>
    <title>Usuarios</title>
   
   
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
    

	        <p align="center" style="font-size:30px">USUARIOS</p>

	    
	    <form action="${pageContext.request.contextPath}/Servlet_Usuarios" method="get">
	    	<div class="container">      
	    		<br>
	    		<br>      
	        	<div class="row">
	            	<div class="col-6">
	                	<div class="input-group input-group-sm mb-3">
	                    	<div class="input-group-prepend">
	                        	<span class="input-group-text" id="inputGroup-sizing-sm">Nombre de Usuario</span>
	                    	</div>
	                    	<input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtNombreU">
	                	</div>
	            	</div>
	        	</div>
	    	</div>
	
	    	<div class="container" style="margin-bottom: 20px;">
	    		<p> Ingrese datos en las cajas de texto para buscar un usuario </p>
	    		<button type="submit" class="btn btn-success" name="btnBuscar" >Buscar usuarios</button>        
	   		</div>
	   	</form>
	   	
	   	<% 
        	ArrayList<Usuario> listaUsuarios = null;
            
        	if(request.getAttribute("listaF")!=null){
        		listaUsuarios = (ArrayList<Usuario>)request.getAttribute("listaF");
        		if((listaUsuarios).isEmpty()){
        			%> <h5 style="color:red;"> No se encontraron usuarios. </h5> <%
        		}
        	}else if(request.getAttribute("listaA")!=null){
        		listaUsuarios = (ArrayList<Usuario>)request.getAttribute("listaA");	
        	}
        %>
        	
        <form method="post" action="${pageContext.request.contextPath}/Servlet_Usuarios">
	        <div class="container">  
	        	<div class="row">
	            	<div class="col-3">
	                	<button type="submit" class="btn btn-primary" name="btnListar" >Listar usuarios</button>
	            	</div>
        
        
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
			<div id="alert" class="alert alert-danger col-6" role="alert">
        		<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="closeAlert()">
            		<span aria-hidden="true">&times;</span>
        		</button>
        		<h6 id="textAlert">Usuario eliminado correctamente.</h6>
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
        		<h6 id="textAlert">Usuario agregado correctamente.</h6>
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
        		<h6 id="textAlert">Usuario modificado correctamente.</h6>
        	</div>
		</div>

<% } %>
</div>
</div>
</form>
<br>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                
                    <table id="table_id" class="table table-dark">
                       <thead> 
                            <tr>
                                <th scope="col">usuario</th>
                                <th scope="col">contrasena</th>
                                <th scope="col">Id Perfil</th>
                                <th scope="col">Modificar</th>
                                <th scope="col">Eliminar</th>
                            </tr>
                      </thead>  
                                            
                        <% if (listaUsuarios!=null)
                        	for(Usuario user : listaUsuarios)
                        	{
                        %>
                          
                        <tbody class="grid_Linea">
                            <tr>
                            	<form action="${pageContext.request.contextPath}/Servlet_Usuarios"  method="post">
                            	
                                	<td><%=user.getUsuario() %><input type="hidden" name="user" value="<%=user.getUsuario() %>"></td>
                                	<td><%=user.getContraseña()%></td><input type="hidden" name="contra" value="<%=user.getContraseña()%>">
                                	<td><%=user.getPerfilId()%></td><input type="hidden" name="perfil" value="<%=user.getPerfilId()%>">                          
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