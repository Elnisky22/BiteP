function pswCheck(){
	var senha1 = document.getElementById("psw1").value;
	var senha2 = document.getElementById("psw2").value;
	
	if (senha2.value != ""){
		if (senha1.value != senha2.value){
			alert("Senhas não correspondem");
			return false;
		}
		else
			return true;
	}
	return true;
}