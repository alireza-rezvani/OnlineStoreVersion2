package ir.maktab32.java.projects.onlinestoreversion2.utilities;

public class Utilities {
    public static boolean isNumeric(String string){
        try {
            Long.parseLong(string);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isEmailValid(String email){
        int atSignCounter = 0;
        int dotCounter = 0;
        for (int i = 0; i < email.length(); i++){
            if (email.substring(i, i + 1).equals("@")){
                atSignCounter++;
            }
            else if (email.substring(i, i + 1).equals(".")){
                dotCounter++;
            }
        }

        if (atSignCounter == 1 && dotCounter > 0)
            return true;
        else
            return false;
    }
}
