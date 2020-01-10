package com.img;
import java.io.File;
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

@WebServlet("/FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet 
{
    public static String A="";
    public static String B="";
	private static final long serialVersionUID = 1L;
	@Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException 
    {
   response.setContentType("text/html;charset=UTF-8");
   final String path = "C:/Users/Trainee/workspace/1st/com.imgUpl/WebContent/image";
   final Part filePart = request.getPart("fname");
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
       A= fileName;
       
       B= path + File.separator + fileName;
       App a=new App();
        a.main123(B,A);
      
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


  
}
