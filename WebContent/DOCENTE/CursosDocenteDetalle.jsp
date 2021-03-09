<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.AlumnosXCurso" %>
<%@page import="Entidad.Curso" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="javax.servlet.RequestDispatcher"%>

<!DOCTYPE html>
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
    
    <script >
		
		function Solo_Numerico(variable){
			Numer=parseInt(variable);
			if (isNaN(Numer)){
			      return "";
			}
			    return Numer;
		}
			    
		function ValNumero(Control){
			Control.value=Solo_Numerico(Control.value);
		}
		
		
		function ventanaSecundaria (URL){ 
		   window.open(URL,"Modificar Nota Alumno","width=600,height=400,scrollbars=NO") 
		} 

		
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
               <%
               	String usaurio = " ";
               		           if (session.getAttribute("Usuario") != null){
               		                  usaurio =session.getAttribute("Usuario").toString(); }
               %> 
                <a id="cursosLink" href= "${pageContext.request.contextPath}/Servlet_DocenteMain">Cursos</a>
                <a id="perfilLink" href="${pageContext.request.contextPath}/Servlet_DocenteUsuario"> <%=usaurio%> </a>
                <a class="salir" id="salirLink" href="/TPINT_GRUPO_6_LAB_4/Login.jsp">Salir</a>

            </nav>
        </div>
    </header>


        <p align="center" style="font-size:30px">Alumnos y notas del curso</p>
        <form method="post" action= "${pageContext.request.contextPath}/Servlet_DocenteCursoDetalle" >

            <div class="container-fluid">

            </div>
      <div class="container-fluid">
            	 <%
            	 Curso unCuroset = new Curso();
               	String infoCurso = " cccc";
                if (request.getAttribute("unCurso") != null){
                	unCuroset = (Curso)request.getAttribute("unCurso"); 
                	infoCurso = "Curso: " + unCuroset.getCursoId() + "   Materia: " + unCuroset.getMateria().getNombre()+ "   Cuatrimestre: " + unCuroset.getCuatrimestre()+ "   Año: " + unCuroset.getAño() ;
                	}
               %> 
               <p style="color:blue;"> <u><%=infoCurso%></u></p>
  
         <br>      
          <div class="container" style="margin-bottom: 30px;">
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Nombre</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  aria-label="Small"  name="txtBuscarNombre">
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm" >Apellido</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtBuscarApellido" >
                    </div>
                </div>
                
                
                <div class="col-6">
                    <div class="row">
                    	<div class="col-6">
                    	 <input type="submit" value="Buscar" class="btn btn-success" name="btnBuscar"/>
                    	 <input type="hidden" name="txtId" size="12"  style="text-align:center; border: 0;" value="<%=unCuroset.getCursoId()%>" readonly>
                   		 </div>
               		 </div>
                </div>
                
            </div>
               
           </div> 
             
               <input type="hidden" name="txtLegajoProfesor"  style="text-align:center; border: 0;" value="<%=unCuroset.getProfesor().getLegajo()%>" readonly>
                <%
               	String cursoActualizado = " ";
                if (request.getAttribute("CantActualizados") != null){
                	int cant = Integer.parseInt(request.getAttribute("CantActualizados").toString()); 
                	 if (cant > 0){ %> 
                		 <p style="color:blue;"> <u>Nota Actualizada Correctamente</u></p> 
                	<%} } %> 
            </div>
    	</form>
		
		 
                        <table  id="table_id" class="table table-dark">
                            <thead>
                                <tr>
                                    <th scope="col">Legajo</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Apellido</th>
                                    <th scope="col">1º Nota</th>
                                    <th scope="col">2º Nota</th>
                                    <th scope="col">1º Recuperatorio</th>
                                    <th scope="col">2º Recuperatorio</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Guardar</th>
                                </tr>
                            </thead>
                            <tbody class="grid_Linea">
                              
                            <%
                               List<AlumnosXCurso> cursos = new ArrayList<AlumnosXCurso>();
                               if (request.getAttribute("listaCurso") != null){
                               		cursos = (List<AlumnosXCurso>) request.getAttribute("listaCurso");
                                    }
                               
                               for (AlumnosXCurso unCurso: cursos) {	 
 
                            %> 
                            
                                <tr>
                                	<form method="post" action= "${pageContext.request.contextPath}/Servlet_DocenteCursoDetalle" >
                                    <td style="text-align:center;"><%=unCurso.getLegajo()%></td>
                                    <td style="text-align:center;  font-size:25px text-align:center;"><%=unCurso.getAlumno().getNombre()%></td>
                                    <td style="text-align:center; font-size:15px text-align:center;"><%=unCurso.getAlumno().getApellido()%></td>
                                    <td style="text-align:center;"><input type="text" name="txtNota1" maxlength="2" size="3"  onkeyUp="return ValNumero(this);" style="text-align:center;" value="<%= unCurso.getNota1()%>" ></td>
                                    <td style="text-align:center;"><input type="text" name="txtNota2" maxlength="2" size="3"  onkeyUp="return ValNumero(this);" style="text-align:center;" value="<%= unCurso.getNota2()%>" ></td>
                                    <td style="text-align:center;"><input type="text" name="txtRecu1" maxlength="2" size="3"  onkeyUp="return ValNumero(this);" style="text-align:center;" value="<%= unCurso.getRecueratorio1()%>" ></td>
                                    <td style="text-align:center;"><input type="text" name="txtRecu2" maxlength="2" size="3"  onkeyUp="return ValNumero(this);" style="text-align:center;" value="<%= unCurso.getRecueratorio2()%>" ></td>
                                    <td >
                                        <select class="custom-select" id="inputGroupSelect01" name="soEstado">
                                        	 <% if (unCurso.getEstado() > 0) {%>
						                            <option value="1" <% if (unCurso.getEstado() ==1) {%>selected<%} %>>Regular</option>
						                            <option value="2" <% if (unCurso.getEstado() ==2){%>selected<%} %>>Libre</option>
						                            <%}
						                            else {%> 
						                            
						                           	 <option value="0" selected>Seleccione...</option>
                                               		 <option value="1">Regular</option>
                                                	 <option value="2">Libre</option>
						                            <%} %>
                                        	
                                        	
                                        </select>
                                    </td>
                                     <td>
                                      <input type="hidden" name="txtLegajo" size="12"  style="text-align:center; border: 0;" value="<%=unCurso.getLegajo()%>" readonly>
                                      <input type="hidden" name="txtIdCurso" size="12"  style="text-align:center; border: 0;" value="<%=unCurso.getCursoId()%>" readonly>                                    
                                     <input type="submit" name="btnGuardarUnaNota" class="btnGuardarUnaNota" value="Modificar"  /> 
                                </form>
                                </tr>
                            <% } %>

                            </tbody>
                        </table>
  		
            <br>



</body>
</html>
