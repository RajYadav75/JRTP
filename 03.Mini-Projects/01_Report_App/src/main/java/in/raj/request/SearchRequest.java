package in.raj.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class SearchRequest {

    private String planName;
    private String planStatus;
    private String gender;
    @DateTimeFormat(pattern = "DD-MM-YYYY")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "DD-MM-YYYY")
    private LocalDate endDate;
}
