<%@page import="Entidad.Materia" %>
<%@page import="Entidad.Curso" %>
<%@page import="Entidad.Profesor" %>
<%@page import="Entidad.Alumno" %>
<%@ page import="java.util.ArrayList"%>
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
    <link rel="stylesheet" href="/TPINT_GRUPO_6_LAB_4/css/styles.css">
    <title>Cursos</title>
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
     	
        <p align="center" style="font-size:30px">Información del Curso</p>
        
        <%
        	Curso cur = null;       
            if(request.getAttribute("curso")!=null)
			{   
            	
			cur = (Curso)request.getAttribute("curso");
			
            }
            
        %>
        
        <form method="post" action="${pageContext.request.contextPath}/Servlet_Cursos">  
        <div class="container">
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                        
                            <span class="input-group-text" id="inputGroup-sizing-sm">Id Curso</span>                            
                        </div>                        
                        <% if (cur!=null)
							{ 
							%>

							<input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" value="<%=cur.getCursoId()%>" placeholder="<%=cur.getCursoId()%>" readonly="true" name="txtIdcurso">

							<% } 
							else { %>
                        <input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Nuevo" readonly="true" name="txtIdcurso">
                        <% } %>
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Materia</span>
                        </div>                     
                        
                        	<%
								ArrayList<Materia> listaMat = new ArrayList<Materia>();
								if (request.getAttribute("listaM") != null) {
								listaMat = (ArrayList<Materia>) request.getAttribute("listaM");
								}
							%>
                        
                        <select class="custom-select" id="inputGroupSelect01" name="selMateria" required>  
                        
                        <option value="0">Seleccione la materia...</option>
						<% if (cur!=null){
                        
							for (Materia m : listaMat) {
						%>
						<option value="<%=m.getIdmateria()%>"<%if(cur.getMateria().getIdmateria() == m.getIdmateria()){%>selected<%}%>><%=m.getNombre()%></option> 
						
						<%
							}						
						}                                              
                        else { 							
													
                        	for (Materia m : listaMat) {
						%>
						<option value="<%=m.getIdmateria()%>"><%=m.getNombre()%></option> 
						<%
							}				
                       } %>

				</select>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Cuatrimestre</span>
                        </div>

                        <select class="custom-select" id="selCuatri" name="selCuatri" required>
                            <option>Seleccione...</option>
                            
                            <% if (cur!=null) {%>
                            <option value="1" <% if (cur.getCuatrimestre()==1) {%>selected<%} %>>Primero</option>
                            <option value="2" <% if (cur.getCuatrimestre()==2){%>selected<%} %>>Segundo</option>
                            <%}
                            else {%> 
                            
                            <option value="1">Primero</option>
                            <option value="2">Segundo</option>
                            <%} %>
                        </select>
 
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Año</span>
                        </div>
                        <select class="custom-select" id="inputGroupSelect02" name="selAño" required>
                        <option selected>Seleccione...</option>
                        
                            <% if (cur!=null) {%>
                            <option value="2019"<% if (cur.getAño()==2019) {%>selected<%} %>>2019</option>
                            <option value="2020"<% if (cur.getAño()==2020) {%>selected<%} %>>2020</option>
                            <option value="2021"<% if (cur.getAño()==2021) {%>selected<%} %>>2021</option>
                            <option value="2022"<% if (cur.getAño()==2022) {%>selected<%} %>>2022</option>
                            <option value="2023"<% if (cur.getAño()==2023) {%>selected<%} %>>2023</option>
                            <option value="2024"<% if (cur.getAño()==2024) {%>selected<%} %>>2024</option>
                            <option value="2025"<% if (cur.getAño()==2025) {%>selected<%} %>>2025</option>
                            <option value="2026"<% if (cur.getAño()==2026) {%>selected<%} %>>2026</option>
                            <option value="2027"<% if (cur.getAño()==2027) {%>selected<%} %>>2027</option>
                            <option value="2028"<% if (cur.getAño()==2028) {%>selected<%} %>>2028</option>                         
                            
                            <%}
                            else {%> 
                            <option value="2020">2020</option>
                            <option value="2021">2021</option>
                            <option value="2022">2022</option>
                            <option value="2023">2023</option>
                            <option value="2024">2024</option>
                            <option value="2025">2025</option>
                            <option value="2026">2026</option>
                            <option value="2027">2027</option>
                            <option value="2028">2028</option>
                            <%} %>
                            

                        </select>

                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-me">Profesor</span>
                        </div>
                            <%
								ArrayList<Profesor> listaProf = new ArrayList<Profesor>();
								if (request.getAttribute("listaP") != null) {
								listaProf = (ArrayList<Profesor>) request.getAttribute("listaP");
								}
							%>
                        
                        <select class="custom-select" id="inputGroupSelect01" name="selProfesor" required>  
                        
                        <option value="">Seleccione el profesor...</option>
                        
                         <% if (cur!=null)
							{
                        	 for (Profesor p : listaProf) {
						%>
						<option value="<%=p.getLegajo()%>"<%if(cur.getProfesor().getLegajo() == p.getLegajo()){%>selected<%}%>> <%=p.getNombre()%> <%=p.getApellido()%></option> 
						
						<%
							}							
						} 
                                               
                        
 						else {
 							for (Profesor p : listaProf) {
 							%>
 							<option value="<%=p.getLegajo()%>"><%=p.getNombre()%> <%=p.getApellido()%></option> 
 							<%
 								}				
 	                       } %>

				</select>
			</div>
            </div>
			</div>
			
	</div>            

                  <% if (cur!=null)
					{ %>
                     <div class="container">
                             <div class="row">
                            	<div class="col-6">                                 	
                            		 <input type="submit" class="btn btn-success" name="btnActualizar" value="Modificar Curso">
                            	 </div>
                             </div>
                     </div>	
                     
                     				 <div class="container">
                	<div class="row">
                    	<div class="col-6">
                    		<input type="hidden" name="id" value="<%=cur.getCursoId()%>">
                    		<input type="submit" class="btn btn-primary" name="btnAlumnos" value="Ver listado de alumnos del curso">
                 		</div>
                	</div>
            	</div>
                     					
					<% 	} else { %>
				<div class="container">
                	<div class="row">
                    <div class="col-6">                    	
                        <input type="submit" class="btn btn-success" name="btnGuardar" value="Guardar Curso">
                    </div>
               	 </div>
            	</div>
					<%
					} %>




            </form>
            </grid>

    
</body>
</html>

