package com.vti.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
    private int id;
    private String name;
    private String type;
    private Integer totalMembers;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;
    private List<AccountDTO> accounts;

    @Data
    @NoArgsConstructor
    public static class AccountDTO extends RepresentationModel<AccountDTO>{
        @JsonProperty("accountId")
        private int id;
        private String username;
    }
}
