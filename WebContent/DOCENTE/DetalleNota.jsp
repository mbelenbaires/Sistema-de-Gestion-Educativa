<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="Entidad.AlumnosXCurso" %>
<%@page import="Entidad.Alumno" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    
    <script type="text/javascript">
		
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
		
	</script>
    
<title>Insert title here</title>
</head>
<body>
	<form  method="post" action= "${pageContext.request.contextPath}/Servlet_DocenteCursoDetalle" >
			 <% 
			    AlumnosXCurso unCurso = new AlumnosXCurso();
			    Alumno unAlum = new Alumno();
			    if (request.getAttribute("unCursoModificar") != null){
			    	unCurso = (AlumnosXCurso)request.getAttribute("unCursoModificar");
			    }
			 	int nota1 = unCurso.getNota1();
			 	int nota2 = unCurso.getNota2();
			 	int recu1 = unCurso.getRecueratorio1();
			 	int recu2 = unCurso.getRecueratorio2();
			 	int estado = unCurso.getEstado();
			 	unAlum.setApellido(unCurso.getAlumno().getApellido().toString());
			 	unAlum.setNombre(unCurso.getAlumno().getNombre());
			  %>
			  <br> <br>
		<div class="container" style="margin-bottom: 30px;">
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Alumno</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  aria-label="Small"  name="txtLegajo" value="<%=unCurso.getLegajo()%>" readonly>
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm" >Apellido</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtApellido"  value="<%=unAlum.getApellido()%>" readonly>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Nombre</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtNombre"  value="<%=unAlum.getNombre()%>" readonly>
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Nota 1</span>
                        </div>
                         <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtNota1" maxlength="2" onkeyUp="return ValNumero(this);"  value="<%=nota1%>"  >
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm" >Nota 2</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="txtNota2" maxlength="2" onkeyUp="return ValNumero(this);" value="<%=nota2%>" >
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Recuperatorio 1</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtRecu1" maxlength="2" onkeyUp="return ValNumero(this);" value="<%=recu1%>" >
                    </div>
                </div>
            </div>

        <div class="row">
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Recuperatorio 2</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtRecu2" maxlength="2" onkeyUp="return ValNumero(this);" value="<%=recu2%>" >
                    </div>
                </div>
                        		
                <div class="col-6">
                    <div class="input-group input-group-sm mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">Estado</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtEstado"  value="<%=estado%>" >
                    </div>
                </div>
				
            </div>
        </div>        
		
		   <div class="container">
                <div class="row">
                    <div class="col-6">
                    	 <input type="submit" value="Guardar" class="btn btn-success" name="btnGuardarUnaNota"  />
						<input type="hidden" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"  name="txtCursoId"  value="<%=unCurso.getCursoId()%>" >	
                    </div>
                </div>
            </div>	
			
	</form>
</body>
</html>