window.onload = iniciarDatos;

function iniciarDatos() {
    //document.getElementById("open").onclick = openNav;
    //document.getElementById("close").onclick = closeNav;
}

function openNav() {
    document.getElementById("sidenavBar").style.width = "200px";
}

function closeNav() {
    document.getElementById("sidenavBar").style.width = "0";
}

/* Funcion para abrir y cerra el menu cuando se da click sobre los links <a> */
function abrirDropMenu() {
    var emp = document.getElementById("gestProyectoE");
    var cli = document.getElementById("gestProyectoC");
    var art = document.getElementById("gestProyectoA");
    var pro = document.getElementById("gestProyectoP");

    emp.onclick = function () {
        document.getElementById("despMenuE").classList.toggle("mostrar");
    };

    cli.onclick = function () {
        document.getElementById("despMenuC").classList.toggle("mostrar");
    };

    art.onclick = function () {
        document.getElementById("despMenuA").classList.toggle("mostrar");
    };
    pro.onclick = function () {
        document.getElementById("despMenuP").classList.toggle("mostrar");
    };
}

// Cerrar el menu cuando el usuario hace click fuera de el
window.onclick = function (event) {
    if (!event.target.matches('.droplink')) {
        var dropMenu = document.getElementsByClassName("mostrar-contenido");
        var i;
        for (i = 0; i < dropMenu.length; i++) {
            var openDropdown = dropMenu[i];
            if (openDropdown.classList.contains('mostrar')) {
                openDropdown.classList.remove('mostrar');
            }
        }
    }
};