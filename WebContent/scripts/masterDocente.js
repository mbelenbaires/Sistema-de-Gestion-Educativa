var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
var usr = urlParams.get('user');
if(usr != null){
    $('[id$=user]').text(usr);
    $('[id$=logoLink]').attr('href', 'DocenteMain.jsp?user=' + usr);
    $('[id$=cursosLink]').attr('href', 'CursosDocenteMain.jsp?user=' + usr);
    $('[id$=perfilLink]').attr('href', 'PerfilDocente.jsp?user=' + usr);
    $('[id$=alumnosNota]').attr('href', 'CursosDocenteDetalle.jsp?user=' + usr);
    $('[id$=salirLink]').attr('href', '../Login.jsp');
    $('[id$=perfilName]').text(usr);
}else{
    window.location = "../Login.jsp";
}

$("#perfilName").hover(function(){
    $('[id$=subMenu]').css('display', 'block');
    $("#subMenu").hover(function(){
            $('[id$=subMenu]').css('display', 'block');
        }, function(){
            $('[id$=subMenu]').css('display', 'none');
        });
    }, function(){
    $('[id$=subMenu]').css('display', 'none');
});

$(document).ready( function () {
    $('#dataGrid').DataTable();
} );

$(document).ready(function() {
  $("#dataGrid td:has(h6)").mouseover(function(e) {
    $(this).css("cursor", "pointer");
  });
  $("#dataGrid td:has(h6)").click(function(e) {
    var clickedCell= $(e.target).closest("td");
    window.location = "AlumnosDetail.jsp?user="+usr+"&Legajo=" + clickedCell.text();
  });
});

function Nuevo(page){
    window.location = page + "?user=" + usr;
}

function Volver(page){
    window.location = page + "?user=" + usr;
}

function Deslog(){
    window.location = "../Login.jsp";
}