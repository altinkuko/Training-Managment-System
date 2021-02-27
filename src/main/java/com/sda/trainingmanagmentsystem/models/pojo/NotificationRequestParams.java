package com.sda.trainingmanagmentsystem.models.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationRequestParams {
    private LocalDate date;
    private String subject;
    private String content;
}
