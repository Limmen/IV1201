/*
 * Classname: LocaleManager
 * Version: 0.1
 * Date: 14-3-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.util;

import com.lowagie.text.DocumentException;
import grupp14.IV1201.entities.Application;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * This class handles creation of PDF-files generated from specific applications.
 * @author kim
 */
public class PDFManager {
    
    /**
     * Produces a PDF-file http response of a application.
     * @param application Application Entity that is the source of the pdf-file.
     * @throws IOException thrown when the specified URL cannot be found for the 
     * redirection.
     * @throws DocumentException Thrown when a error occurs in the creation of the pdf document.
     */
    public static void createPDF(Application application) throws IOException, DocumentException
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
        html.append(application.getPerson().getUsername());
        html.append("</p>");
        html.append("<p> name: ");
        html.append(application.getPerson().getName());
        html.append("</p>");
        html.append("<p> surname: ");
        html.append(application.getPerson().getSurname());
        html.append("</p>");
        html.append("<p> ssn: ");
        html.append(application.getPerson().getSsn());
        html.append("</p>");
        html.append("<p> mail: ");
        html.append(application.getPerson().getMail());
        html.append("</p>");
        html.append("<p> Expertise: ");
        html.append(application.getExpertise().getExpertise());
        html.append("</p>");
        html.append("<p> Years of experience: ");
        html.append(Float.toString(application.getYearsOfExperience()));
        html.append("</p>");
        html.append("<p> Available from:  ");
        html.append(application.getDateFrom().toString());
        html.append("</p>");
        html.append("<p> Available to: ");
        html.append(application.getDateTo().toString());
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
        response.setHeader("Content-Disposition", "inline; filename=\"" + "application" + application.getId().toString() + "\"");
        OutputStream browserStream = response.getOutputStream();
        renderer.createPDF(browserStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
