<%@page import="Entidad.Alumno" %>
<%@page import="DAOImpl.AlumnoDAOImpl" %>
<%@page import="DAO.AlumnoDao" %>
<%@page import="Entidad.Provincia" %>
<%@page import="Entidad.Localidad" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html5>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <script src="../scripts/master.js"></script>
    <link rel="stylesheet" href="/TPINT_GRUPO_6_LAB_4/css/styles.css">
    <title>Alumnos</title>
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
             <form method="post" action="${pageContext.request.contextPath}/Servlet_Alumnos">   

    
        <p align="center" style="font-size:30px">ALUMNOS</p>
        
                <%
        	Alumno alu=null;
                            		
            if(request.getAttribute("alumno")!=null){
			alu = (Alumno)request.getAttribute("alumno");
            }
        %>
        


        <div class="container" style="margin-bottom: 30px;">
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Legajo</span>
                        </div>
                        <% if (alu!=null)
							{Alumno aux = new Alumno();
							%>

							<input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" value="<%=alu.getLegajo() %>" placeholder="<%=alu.getLegajo() %>" readonly="true" name="txtLegajo">

							<% } 
							else { %>
                        <input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Nuevo" readonly="true" name="txtLegajo">
                        <% } %>
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">DNI</span>
                        </div>
                            <% if (alu!=null)
							{
							%>

							<input type="number" name="txtDni" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=alu.getDni() %>">

							<% } 
							else { %>
                        <input type="number" name="txtDni" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                        <% } %>
                        
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Nombre</span>
                        </div>
                                                                        <% if (alu!=null)
							{
							%>

							<input type="text" name="txtNombre" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=alu.getNombre() %>">

							<% } 
							else { %>
                        <input type="text" name="txtNombre" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                        <% } %>
                        
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Apellido</span>
                        </div>
                                                                                                <% if (alu!=null)
							{
							%>

							<input type="text" name="txtApellido" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=alu.getApellido() %>">

							<% } 
							else { %>
                        <input type="text" name="txtApellido" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                        <% } %>
                        
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Fecha Nacimiento</span>
                        </div>
                            <% if (alu!=null)
							{
							%>

							<input type="text" name="txtFechanac" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=alu.getFechanac() %>">

							<% } 
							else { %>
                        <input type="text" name="txtFechanac" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="dd-mm-aaaa" required>
                        <% } %>
                        
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Dirección</span>
                        </div>
                           <% if (alu!=null)
							{
							%>

							<input type="text" name="txtDir" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=alu.getDireccion() %>">

							<% } 
							else { %>
                        <input type="text" name="txtDir" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                        <% } %>
                        
                    </div>
                </div>
            </div>
            <div class="row">
            
                <div class="col-6">
                    <div class="input-group mb-3">
                    
                    

                    
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Provincia</label>
                        </div>
                        
                        	<%

								ArrayList<Provincia> listaProv = new ArrayList<Provincia>();
								if (request.getAttribute("listaP") != null) {
								listaProv = (ArrayList<Provincia>) request.getAttribute("listaP");
								}
							%>
                                                  
                            
                        <select class="custom-select" id="inputGroupSelect01" name=selProvincia required> 
                        <option value="0">Seleccione la provincia...</option>
                        
                        <% if (alu!=null) {                   
						
							for (Provincia p : listaProv) {
						%>
						<option value="<%=p.getId()%>"<%if((alu.getProvincia().getNombre()).toString().equals(p.getNombre())){%>selected<%}%>> <%=p.getNombre()%></option> 
						
						<%
							}			
						}
                        
                        
                        else {  
                        
							for (Provincia p : listaProv) {
						%>
						<option value="<%=p.getNombre()%>"><%=p.getNombre()%></option> 
						
						<%
							}}
						%>
						
				</select>          
 
                </div>
                </div>


                <div class="col-6">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                                                
                            <label class="input-group-text" for="inputGroupSelect01">Localidad</label>
                        </div>
                        <%
								ArrayList<Localidad> listaLoc = new ArrayList<Localidad>();
								if (request.getAttribute("listaL") != null) {
								listaLoc = (ArrayList<Localidad>) request.getAttribute("listaL");
								}
							%>
                        <select class="custom-select" id="inputGroupSelect01" name=selLocalidad required>  
                        
                        <option value="">Seleccione la localidad...</option>
                        
                        <% if (alu!=null)
							{
							for (Localidad l : listaLoc) {
						%>
						<option value="<%=l.getId()%>"<%if((alu.getLocalidad().getNombre()).toString().equals(l.getNombre())){%>selected<%}%>> <%=l.getNombre()%></option> 
						
						<%
							}
							
							 } 
                        
                        
                        
							else { %>
							
													<%
							for (Localidad l : listaLoc) {
						%>
						<option value="<%=l.getNombre()%>"><%=l.getNombre()%></option> 
						<%
							}
						%>
						
                        <% } %>
                        


				</select>
                    </div>
                </div>
                
            </div>

            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Email</span>
                        </div>
                            <% if (alu!=null)
							{
							%>

							<input type="text" name="txtEmail" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=alu.getMail() %>">

							<% } 
							else { %>
                        <input type="email" name="txtEmail" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                        <% } %>
                        
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Teléfono</span>
                        </div>
                            <% if (alu!=null)
							{
							%>

							<input type="text" name="txtTel" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  value="<%=alu.getTelefono() %>">

							<% } 
							else { %>
                        <input type="number" name="txtTel" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required>
                        <% } %>
                        
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-6">
                
                <% if (alu!=null)
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