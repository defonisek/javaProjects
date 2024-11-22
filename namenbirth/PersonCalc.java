import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class PersonCalc{
    private final String name;
    private final String birth;

    public PersonCalc(String name,String birth){
        this.name=name;
        this.birth=birth;
    }

    public String initials(String name){
        String[] parts=name.split(" ");
        if(parts.length!=3)
            return "Неверный формат ФИО. Возможно вы ввели неполное ФИО или добавили лишний пробел"; // если split разделил не на 3 части (фамилия, имя и отчество), то что-то явно не так
        String first=parts[1].substring(0,1); // первая буква имени
        String second=parts[2].substring(0,1); // первая буква отчества
        return parts[0]+" "+first+"."+second+".";
    }

    public String gender(String name){
        String[] parts=name.split(" ");
        if(parts.length!=3)
            return "Не удалось определить";
        String patronymic=parts[2];
        if(patronymic.endsWith("ич"))
            return "М";
        else if(patronymic.endsWith("на"))
            return "Ж";
        else
            return "Не удалось определить";
    }

    public String age(String birth){
        try{
            birth=birth.replace("/", "."); // приведение к единому виду для форматтера 
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birthDate=LocalDate.parse(birth,formatter);
            LocalDate today=LocalDate.now();
            long years=ChronoUnit.YEARS.between(birthDate,today);
            long days=ChronoUnit.DAYS.between(birthDate,today);
            if(days<0)
                return "Этот человек ещё не родился"; 
            else if(years%10==1 && years%100!=11)
                return years+" год";
            else if ((years%10>=2 && years%10<=4)&&!(years%100>=12 && years%100<=14))
                return years+" года";
            else 
                return years+" лет"; 
        } 
        catch(DateTimeParseException e){ // всеобъемлющий exception для даты, парсер выкидывает её при любой некорректной информации
            return "Нечитаемая дата рождения. Проверьте её корректность и попробуйте вводить в формате дд.мм.гггг или дд/мм/гггг";
        }
    }
}
