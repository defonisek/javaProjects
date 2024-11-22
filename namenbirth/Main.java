import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws UnsupportedEncodingException{
        Scanner scanner=new Scanner(new InputStreamReader(System.in,"Cp866")); // без cp866 кириллица превращалась в "?"
        System.out.println("Введите ФИО (например, Сергеев Сергей Сергеевич):");
        String name=scanner.nextLine();
        System.out.println("Введите дату рождения (в формате дд.мм.гггг или дд/мм/гггг):");
        String birth=scanner.nextLine();
        scanner.close();
        PersonCalc personCalc=new PersonCalc(name,birth);
        System.out.println("Инициалы: "+personCalc.initials(name));
        System.out.println("Пол: "+personCalc.gender(name));
        System.out.println("Возраст: "+personCalc.age(birth));
    }
}