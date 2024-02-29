package com.nhnacademy.certificateissuingsystem.entity;

import javax.persistence.Column;
import java.util.Date;

public class BirthDeathReportResident {

    private Pk pk;
    @Column(name = "resident_serial_number", nullable = false)
    private Integer residentNum;

    @Column(name = "birth_death_report_date", nullable = false)
    private Date reportDate;

    @Column(name = "birth_report_qualifications_code")
    private String birthQualificationsCode;

    @Column(name = "death_report_qualifications_code")
    private String deathQualificationsCode;

    @Column(name = "email_address")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;


    private class Pk {
        @Column(name = "birth_death_type_code", nullable = false)
        private String id;
        @Column(name = "report_resident_serial_number", nullable = false)
        private Integer reportResidentNum;
    }
}
