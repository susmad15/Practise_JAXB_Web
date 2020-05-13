package adapters;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date>{

  @Override
  public Date convertToDatabaseColumn(LocalDate ld) {
    if (ld != null) {
      return Date.valueOf(ld);
    }
    return null;
  }

  @Override
  public LocalDate convertToEntityAttribute(Date date) {
    if (date != null) {
      return date.toLocalDate();
    }
    return null;
  }
  
}