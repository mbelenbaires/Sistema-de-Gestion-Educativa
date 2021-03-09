function Ingresar(){

    var usr = $('[id$=user]').val();
    var pass = $('[id$=pass]').val();

    if( pass == "" || usr == ""){
        $('#alert').fadeIn(1000);
        setTimeout(function() {
            $('#alert').fadeOut(1000);       
        },3000);
    }else{
        if(usr == "Administrador"){
            window.location = "ADMIN/AdministradorMain.jsp?user=" + usr;
        }else{
            if(usr == "Docente"){
                window.location = "DOCENTE/DocenteMain.jsp?user=" + usr;
            }else{
                $('[id$=textAlert]').text("Datos incorrectos!");
                $('#alert').fadeIn(1000);
                setTimeout(function() {
                    $('#alert').fadeOut(1000);       
                },3000);
            }
        }
    }   
}

function closeAlert(){
    $('#alert').fadeOut(1000);
}