<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Profesor" %>
<%@page import="Entidad.Localidad" %>
<%@page import="Entidad.Provincia" %>
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
     
    <title>Perfil</title>
</head>
<body>
    <header>
        <div class="contenedor">
          <div class="icon" >
            <a id="logoLink" href=""><h1>S.G.E</h1></a>
          </div>
        
		    <nav class="menu">
               <%  String inUsaurio = " ";
		           if (session.getAttribute("Usuario") != null){
		        	   inUsaurio =session.getAttribute("Usuario").toString(); }
               %> 
                <a id="cursosLink" href="${pageContext.request.contextPath}/Servlet_DocenteMain">Cursos</a>
                <a id="perfilLink" href="${pageContext.request.contextPath}/Servlet_DocenteUsuario"> <%=inUsaurio%> </a>
                <a class="salir" id="salirLink" href="/TPINT_GRUPO_6_LAB_4/Login.jsp">Salir</a>
            </nav>
        </div>
    </header>

    <form method="post" action="${pageContext.request.contextPath}/Servlet_DocenteUsuario">
    	<br><br>
    	<% Profesor unProfesor = new Profesor();
    	   Provincia unaProvincia = new Provincia();
    	   Localidad unaLocalidad = new Localidad();
    	   if (request.getAttribute("PerfilProfesor") != null){
    		   unProfesor = (Profesor)request.getAttribute("PerfilProfesor");
    		   unaProvincia = unProfesor.getProvincia();
    		   unaLocalidad = unProfesor.getLocalidad();
     	   }
    	   
    	   String resu = "";
    	   if (request.getAttribute("ResuContrasenaDocente") != null){
    		   resu = request.getAttribute("ResuContrasenaDocente").toString();
    	 %>
    	 		 <p style="color:blue;"><%=resu%></p>
    	  <% }	%>
        <p align="center" style="font-size:30px">MI PERFIL</p>
        <div class="container" style="margin-bottom: 30px;">
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Nombre</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtNombre" value="<%=unProfesor.getNombre()%>" readonly>
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm" >Apellido</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtApellido" value="<%=unProfesor.getApellido()%>" readonly>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Mail</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtMail" value="<%=unProfesor.getMail()%>" readonly>
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Teléfono</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtTelefono" value="<%=unProfesor.getTelefono()%>" readonly>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-6" >
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm" >Localidad</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtLocalidad" value="<%=unaLocalidad.getNombre()%>" readonly>
                    </div>
                </div>
                
                 <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Provincia</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtProvincia" value="<%=unaProvincia.getNombre()%>" readonly>
                    </div>
                </div>
                
            </div>

     </div>        
	 <div class="container">
		<div class="row">
             <div class="col-6">
                  <div class="input-group input-group-sm mb-3">
                      <div class="input-group-prepend">
                          <span class="input-group-text" id="inputGroup-sizing-sm">Contraseña</span>
                      </div>
                      <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtContrasena1" required >
                  </div>
            </div>

            <div class="col-6">
              <div class="input-group input-group-sm mb-3">
                 <div class="input-group-prepend">
                      <span class="input-group-text" id="inputGroup-sizing-sm">Repetir Contraseña</span>
                 </div>
                 <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtContrasena2" required>
              </div>
            </div>
        </div>
      </div>      
        <div class="container">
            <div class="row">
                <div class="col-6">
                	 <input type="submit" value="Guardar" class="btn btn-success" name="btnGuardar" />
                </div>
            </div>
        </div>
    </form>
</body>
</html>
