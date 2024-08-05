package in.raj.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import in.raj.entity.CitizenPlan;
import in.raj.repository.CitizenPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class PDFGenerator {
    @Autowired
    private CitizenPlanRepository planrepo;
    public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        PdfWriter.getInstance(document,new FileOutputStream(f));

        document.open();
        // Creating Font
        //Setting Font Style and size
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph p = new Paragraph("Citizen Plans info",fontTitle);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);


        PdfPTable table = new PdfPTable(6);
        table.setSpacingBefore(5);



        table.addCell("ID");
        table.addCell("Citizen Name");
        table.addCell("Plan Name");
        table.addCell("Plan Status");
        table.addCell("Plan Start Date");
        table.addCell("Plan End Date");
//        table.addCell("Benefit AMT");


        for (CitizenPlan plan : plans) {
            table.addCell(String.valueOf(plan.getCitizenId()));
            table.addCell(plan.getCitizenName());
            table.addCell(plan.getPlanName());
            table.addCell(plan.getPlanStatus());
            table.addCell(plan.getPlanStartDate()+"");
            table.addCell(plan.getPlanEndDate()+"");
        }

        document.add(table);
        document.close();

    }
}
