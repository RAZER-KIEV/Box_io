package software.doit.testapp.other.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class StringValidator {

    public static boolean validateEmail(String mail) {
        boolean valid;
        if (!TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }

    public static boolean validatePass(String pass) {
        return (pass.length() > 4);
    }

    public static boolean validateName(String name) {
        return (name.length() > 4);
    }
}
