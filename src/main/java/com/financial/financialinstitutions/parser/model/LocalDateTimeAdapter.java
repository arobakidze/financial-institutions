package com.financial.financialinstitutions.parser.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(value);
    }

    @Override
    public String marshal(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}