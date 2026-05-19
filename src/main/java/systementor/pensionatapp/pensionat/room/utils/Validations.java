package systementor.pensionatapp.pensionat.room.utils;
import systementor.pensionatapp.pensionat.error.BadRequestException;
import java.time.LocalDate;


public class Validations {


    public static void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (!endDate.isAfter(startDate)) {
            throw new BadRequestException("Slutdatum måste vara efter startdatum");
        }
    }

}
