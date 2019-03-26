package model.adapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class AdapterPart implements AdapterImagem {
	
	Part arquivo;
	
	public AdapterPart(Part arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public byte[] getConteudo(){
		try {
			InputStream inputStream = arquivo.getInputStream();
			ByteArrayOutputStream resultado = new ByteArrayOutputStream();
			byte[] buffer = new byte[10240];
			for (int length = 0; (length = inputStream.read(buffer)) > 0;)
				resultado.write(buffer, 0, length);
			return resultado.toByteArray();
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
