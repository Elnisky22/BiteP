// Script para abrir/fechar sidebar em tablet/smartphones
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
	 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

//Open busca
function openBusca() {
    var x = document.getElementById("buscaPet");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

//Carregar páginas via AJAX
function carregar(pagina){
	$("#content").load(pagina);
}

//Impedir números em um input
function validarNaoNumero(evt){
	var tecla = (evt.which) ? evt.which : event.keyCode
			if (tecla > 31 && (tecla < 48 || tecla > 57) || tecla == 8)
		return true;
	return false;
}