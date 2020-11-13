package com.softtek.web.app.models.entity;

import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.sql.Timestamp;
 // SE CREA POR QUE JPA NO ES COMPATIBLE CON LOCALDATETIME EN JAVA 8
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	 //Timestamp ts=new Timestamp(System.currentTimeMillis());  
     //Date date=new Date(ts.getTime());  
     //System.out.println(date);  
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return locDateTime == null ? null : Timestamp.valueOf(locDateTime);
    }
 
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime();
    }
}

