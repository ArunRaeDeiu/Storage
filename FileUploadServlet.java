package Tutorial1;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;


import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
  
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/FileUploadServlet"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	@Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException 
    {
   response.setContentType("text/html;charset=UTF-8");
//final String path = request.getParameter("destination");
   final String path = "C:/Users/Trainee/workspace/1st/Tutorial/WebContent/image/";
   final Part filePart = request.getPart("file");
    final String fileName = getFileName(filePart); 
   OutputStream out = null;
    InputStream filecontent = null; 
        out = new FileOutputStream(new File(path + File.separator + fileName));
        filecontent = filePart.getInputStream();
        int read = 0;
        final byte[] bytes = new byte[1024];
        while ((read = filecontent.read(bytes)) != -1) 
        {
            out.write(bytes, 0, read);
        }
        PrintWriter pw=response.getWriter(); 
        pw.write(fileName);
       // String filename=path + File.separator+ fileName;
        /*response.setContentType("image/jpeg");
        ServletOutputStream out1;
        out1 = response.getOutputStream();
        FileInputStream flinp = new FileInputStream(filename);
        BufferedInputStream buffinp = new BufferedInputStream(flinp);
        BufferedOutputStream buffoup = new BufferedOutputStream(out1);
        int ch=0;
        while ((ch=buffinp.read()) != -1) 
        {
   
            buffoup.write(ch);

        }
        buffinp.close();
        flinp.close();
        buffoup.close();
        out1.close(); */
}
    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

/*private String getFileName(final Part part)
{
    final String partHeader = part.getHeader("content-disposition");
    LOGGER.log(Level.INFO,"Part Header = {0}",partHeader);
    for (String content : part.getHeader("content-disposition").split(";"))
    {
        if (content.trim().startsWith("filename")) 
        {
            return content.substring
            		(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}*/
  
}
