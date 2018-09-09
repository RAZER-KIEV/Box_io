package software.doit.boxio.other.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class StringValidator {

    public static boolean validateEmail(String mail) {
        return  !TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    public static boolean validatePass(String pass) {
        return (pass.length() > 4);
    }

    public static boolean validateName(String name) {
        return (name.length() > 4);
    }
}
