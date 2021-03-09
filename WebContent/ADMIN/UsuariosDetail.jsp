<%@page import="Entidad.Usuario" %>
<%@page import="DAOImpl.UsuarioDAOImpl" %>
<%@page import="DAO.UsuarioDAO" %>
<%@ page import="java.util.ArrayList"%>
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
    <title>Usuarios</title>
</head>
<body>
<%
         String usuario =   application.getAttribute("var").toString();
           %>
    <header>
        <div class="contenedor">
          <div class="icon" >
            <h1> S.G.E</h1>
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
             <form method="post" action="${pageContext.request.contextPath}/Servlet_Usuarios">   

    
        <p align="center" style="font-size:30px">USUARIOS</p>
        
                <%
        	Usuario user=null;
                            		
            if(request.getAttribute("usuario")!=null){
            	user = (Usuario)request.getAttribute("usuario");
            }
        %>
        


        <div class="container" style="margin-bottom: 30px;">
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Nombre Usuario</span>
                        </div>
                                                                        <% if (user!=null)
							{
							%>

							<input type="text" name="txtNombreU" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=user.getUsuario() %>" readonly="true">

							<% } 
							else { %>
                        <input type="text" name="txtNombreU" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                        <% } %>
                        
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Contraseña</span>
                        </div>
                                                                                                <% if (user!=null)
							{
							%>

							<input type="text" name="txtContra" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=user.getContraseña() %>">

							<% } 
							else { %>
                        <input type="text" name="txtContra" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                        <% } %>
                        
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-6">
                
                <% if (user!=null)
							{
							%>
							
							<input type="submit" class="btn btn-success" name="btnActualizar" value="Actualizar">

							<% } 
							else { %>
                        <input type="submit" class="btn btn-success" name="btnGuardar" value="Guardar">
                        <% } %>

                </div>
            </div>
        </div>
       </form> 
</grid>
</body>
</html>