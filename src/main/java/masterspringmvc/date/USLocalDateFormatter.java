package masterspringmvc.date;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 日期格式化工具类
 * 功能描述: TODO
 * @author: 康小安
 * @createDate: 18-11-19 下午1:41
 */
public class USLocalDateFormatter implements Formatter<LocalDate> {

    public static final String US_PATTERN = "MM/dd/yyyy";
    public static final String NORMAL_PATTERN = "yyyy/MM/dd";


    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(getPattern(locale)));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern(getPattern(locale)).format(object);
    }

    public static String getPattern(Locale locale) {
        return isUnitedStates(locale) ? US_PATTERN : NORMAL_PATTERN;
    }

    private static boolean isUnitedStates(Locale locale) {
        return Locale.US.getCountry().equals(locale.getCountry());
    }
}
