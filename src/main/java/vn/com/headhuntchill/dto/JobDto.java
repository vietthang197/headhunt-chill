package vn.com.headhuntchill.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class JobDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private String salaryFrom;
    private String salaryTo;
    private String reward;
    private String tag;
}
