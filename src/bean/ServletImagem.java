package bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ImagemDB;
import model.entity.Imagem;

import javax.inject.Inject;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.BufferedInputStream;

@WebServlet("/images/*")
public class ServletImagem extends HttpServlet {

@Inject
private ImagemDB imagemDb;

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String imageId = String.valueOf(request.getPathInfo().substring(1)); // obtem toda string depois de "/images/".
    //System.out.println("parametro recebido : "+imageId);
    Imagem image = imagemDb.consultarID(Imagem.class,Integer.parseInt(imageId)); // pega imagem do banco

    response.setHeader("Content-Type", getServletContext().getMimeType("" + image.getIdImagem()));
    response.setHeader("Content-Disposition", "inline; filename=\"" + image.getIdImagem() + "\"");

    BufferedInputStream input = null;
    BufferedOutputStream output = null;

    try {
        input = new BufferedInputStream(new ByteArrayInputStream(image.getImagem())); // Creates buffered input stream.
        output = new BufferedOutputStream(response.getOutputStream());
        byte[] buffer = new byte[10240];
        for (int length = 0; (length = input.read(buffer)) > 0;) {
            output.write(buffer, 0, length);
        }
    } finally {
        if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
        if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
    }
}
}