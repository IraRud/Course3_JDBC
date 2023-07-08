package validate_utils;

public class ValidateUtils {

    // проверка строковых значений
    public static String checkString(String str, String newStr) {
        return newStr != null && !newStr.isBlank() && !newStr.isEmpty() ? newStr : str;
    }

    // проверка интовых значений
    public static int checkInt(int number, int newNumber) {
        return newNumber >= 0 ? newNumber : number;
    }

}
