/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.model;

import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.io.OutputStream;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author kim
 */
@Stateless
public class PDFEJB {
    
    /**
     * Produces a PDF-file http response of a application.
     * @param application
     * @throws IOException
     * @throws DocumentException
     */
    public void createPDF(String application) throws IOException, DocumentException{        
        StringBuilder html = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>");
        html.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
        html.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
        html.append("<body>");
        html.append("<h2 style='color: #353535;font-size: 16px;margin: 0px;padding: 20px 0px;font-weight: bold;'>");
        html.append(application);
        html.append("</h2>");
        html.append("<div>");
        html.append("<div>");
        html.append("Application Description");
        html.append("</div></div>");
        html.append("</body></html>");
        
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html.toString());
        renderer.layout();
        HttpServletResponse response = (HttpServletResponse)
                FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"" + application + "\"");
        OutputStream browserStream = response.getOutputStream();
        renderer.createPDF(browserStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
