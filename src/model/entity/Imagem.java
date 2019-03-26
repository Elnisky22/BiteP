package model.entity;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Entity implementation class for Entity: Imagem
 *
 */
@Entity
public class Imagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer idImagem;
	@Column(nullable = false)
	@Lob
	private byte[] imagem;


	public Imagem() {
		super();
	}
	public Imagem(byte[] imagem) {
		this.imagem = imagem;
	}   
	public Integer getIdImagem() {
		return this.idImagem;
	}

	public void setIdImagem(Integer idImagem) {
		this.idImagem = idImagem;
	}   
	public byte[] getImagem() {
		return this.imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}   
	
	public static void recuperaImagem(byte[] b,String s) {
		OutputStream out = null;
		try {
		    out = new BufferedOutputStream(new FileOutputStream(s));
		    out.write(b);
		} catch (IOException e) {e.printStackTrace();}
	}
   
}
