window.onload = iniciarDatos;

function iniciarDatos() {
    document.getElementById("open").onclick = openNav;
    document.getElementById("sidenavBar").onclick = closeNav;
}

function openNav(){
    document.getElementById("sidenavBar").style.width = "200px";
    document.getElementById("top").style.marginleft = "200px";
    document.getElementById("left").style.marginleft = "200px";
    document.getElementById("content").style.marginleft= "200px";
}

function closeNav(){
    document.getElementById("sidenavBar").style.width = "0";
    document.getElementById("top").style.marginleft = "0";
    document.getElementById("left").style.marginleft = "0";
    document.getElementById("content").style.marginleft = "0";
}

