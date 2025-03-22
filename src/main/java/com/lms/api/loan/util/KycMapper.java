package com.lms.api.loan.util;

import com.lms.api.loan.dto.CustomerInfoDTO;
import com.lms.generated.Customer;
import com.lms.generated.CustomerResponse;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

@Component
public class KycMapper {

    public CustomerInfoDTO toDto(Customer info) {
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.setCustomerNumber(info.getCustomerNumber());
        dto.setFirstName(info.getFirstName());
        dto.setLastName(info.getLastName());
        dto.setDateOfBirth(convertDate(info.getDob()));
        dto.setNationalId(info.getIdNumber());
        dto.setEmail(info.getEmail());
        dto.setPhoneNumber(info.getMobile());
        return dto;
    }

    private LocalDate convertDate(XMLGregorianCalendar calendar) {
        return calendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }
}

