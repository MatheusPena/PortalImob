package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ServletImagemConstrutora extends HttpServlet {
	
	protected void processarRequisicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String url = request.getParameter("url");
						
			if(url!=null) {
				//url = url.replaceAll("\\\\/", "\\");
				System.out.println("SERVLET DE IMAGENS DA CONSTRUTORA INICIANDO..."+url);
				//String url = request.getPathInfo();
				ServletOutputStream out = response.getOutputStream();
				File file = new File(url);
				response.setContentLength((int) file.length());
				FileInputStream in = new FileInputStream(file);
				byte[] buf = new byte[1024];
				int cont = 0;
				while ((cont = in.read(buf)) >= 0) {
					out.write(buf, 0, cont);
				}
				out.close();
				in.close();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendError(404);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Breve descrição";
	}
}