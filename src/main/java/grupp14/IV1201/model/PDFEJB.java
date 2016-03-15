/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.model;

import com.lowagie.text.DocumentException;
import grupp14.IV1201.DTO.ApplicationViewDTO;
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
     * @param dto
     * @throws IOException
     * @throws DocumentException
     */
    public void createPDF(ApplicationViewDTO dto) throws IOException, DocumentException
    {        
        StringBuilder html = new StringBuilder("<?xml version='1.0' encoding='UTF-8'?>");
        html.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' "
                + "'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
        html.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
        html.append("<body>");
        html.append("<h2 style='color: #353535;font-size: 16px;margin: 0px;padding:"
                + " 20px 0px;font-weight: bold;'>");
        html.append("Application");
        html.append("</h2>");
        html.append("<div>");
        html.append("<div>");
        html.append("<p> username: ");
        html.append(dto.getPerson().getUsername());
        html.append("</p>");
        html.append("<p> name: ");
        html.append(dto.getPerson().getName());
        html.append("</p>");
        html.append("<p> surname: ");
        html.append(dto.getPerson().getSurname());
        html.append("</p>");
        html.append("<p> ssn: ");
        html.append(dto.getPerson().getSsn());
        html.append("</p>");
        html.append("<p> mail: ");
        html.append(dto.getPerson().getMail());
        html.append("</p>");
        html.append("<p> Expertise: ");
        html.append(dto.getExpertise().getExpertise());
        html.append("</p>");
        html.append("<p> Years of experience: ");
        html.append(Float.toString(dto.getApp().getYearsOfExperience()));
        html.append("</p>");
        html.append("<p> Available from:  ");
        html.append(dto.getApp().getDateFrom().toString());
        html.append("</p>");
        html.append("<p> Available to: ");
        html.append(dto.getApp().getDateTo().toString());   
        html.append("</p>");
        html.append("</div></div>");
        html.append("</body></html>");
        
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html.toString());
        renderer.layout();
        HttpServletResponse response = (HttpServletResponse)
                FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"" + "application" + dto.getApp().getId().toString() + "\"");
        OutputStream browserStream = response.getOutputStream();
        renderer.createPDF(browserStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}