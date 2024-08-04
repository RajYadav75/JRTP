package in.raj.service;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import in.raj.entity.CitizenPlan;
import in.raj.repository.CitizenPlanRepository;
import in.raj.request.SearchRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private CitizenPlanRepository planrepo;

    @Override
    public List<String> getPlanNames() {
        return planrepo.getPlanNames();
    }

    @Override
    public List<String> getPlanStatuses() {
        return planrepo.getPlanStatus();

    }

    @Override
    public List<CitizenPlan> search(SearchRequest request) {
        CitizenPlan entity = new CitizenPlan();

        if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
            entity.setPlanName(request.getPlanName());
        }
        if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
            entity.setPlanStatus(request.getPlanStatus());
        }
        if (null != request.getGender() && !"".equals(request.getGender())) {
            entity.setGender(request.getGender());
        }
        if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
            String startDate = request.getStartDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //convert String to LocalDate
            LocalDate localDate = LocalDate.parse(startDate, formatter);
            entity.setPlanStartDate(localDate);
        }
        if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
            String endDate = request.getEndDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //convert String to LocalDate
            LocalDate localDate = LocalDate.parse(endDate, formatter);
            entity.setPlanEndDate(localDate);
        }

        return planrepo.findAll(Example.of(entity));
    }

    @Override
    public boolean exportExcel(HttpServletResponse response) throws Exception {
        List<CitizenPlan> record = planrepo.findAll();
//        Workbook workbook = new HSSFWorkbook();
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("plans-data");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Citizen Name");
        headerRow.createCell(2).setCellValue("Plan Name");
        headerRow.createCell(3).setCellValue("Plan Status");
        headerRow.createCell(4).setCellValue("Plan Start Date");
        headerRow.createCell(5).setCellValue("Plan End Date");
        headerRow.createCell(6).setCellValue("Benefit AMT");

        List<CitizenPlan> records = planrepo.findAll();
        int dataRowIndex = 1;
        for (CitizenPlan plan : records) {
            Row dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(plan.getCitizenId());
            dataRow.createCell(1).setCellValue(plan.getCitizenName());
            dataRow.createCell(2).setCellValue(plan.getPlanName());
            dataRow.createCell(3).setCellValue(plan.getPlanStatus());

            if (null != plan.getPlanStartDate()) {
                dataRow.createCell(4).setCellValue(plan.getPlanStartDate() + "");
            } else {
                dataRow.createCell(6).setCellValue("N/A");
            }

            if (null != plan.getPlanEndDate()) {
                dataRow.createCell(5).setCellValue(plan.getPlanEndDate() + "");
            } else {
                dataRow.createCell(6).setCellValue("N/A");
            }

            if (null != plan.getBenefitAmt()) {
                dataRow.createCell(6).setCellValue(plan.getBenefitAmt());
            } else {
                dataRow.createCell(6).setCellValue("N/A");
            }

            dataRowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return true;
    }

    @Override
    public boolean exportPdf(HttpServletResponse response) throws Exception {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("Citizen Plans info");
        document.add(p);


        PdfPTable table = new PdfPTable(6);

        table.addCell("ID");
        table.addCell("Citizen Name");
        table.addCell("Plan Name");
        table.addCell("Plan Status");
        table.addCell("Plan Start Date");
        table.addCell("Plan End Date");
//        table.addCell("Benefit AMT");

        List<CitizenPlan> plans = planrepo.findAll();
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
        return true;
    }


}
