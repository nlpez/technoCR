window.onload = iniciarDatos;

function iniciarDatos() {
    document.getElementById("open").onclick = openNav;
    document.getElementById("close").onclick = closeNav;
}

function openNav(){
    document.getElementById("sidenavBar").style.width = "200px";
}

function closeNav(){
    document.getElementById("sidenavBar").style.width = "0";
}

/* Funcion para abrir y cerra el menu cuando se da click sobre el  */
function abrirDropMenu() {
  document.getElementById("despMenu").classList.toggle("mostrar");
}

// Cerrar el menu cuando el usuario hace click fuera de el
window.onclick = function(event) {
  if (!event.target.matches('.droplink')) {
    var dropMenu = document.getElementsByClassName("mostrar-contenido");
    var i;
    for (i = 0; i < dropMenu.length; i++) {
      var openDropdown = dropMenu[i];
      if (openDropdown.classList.contains('mostrar')) {
        openDropdown.classList.remove('mostrar');
        document.getElementById("gestEmpleado").style.backgroundcolor="#730602";
      }
    }
  }
}