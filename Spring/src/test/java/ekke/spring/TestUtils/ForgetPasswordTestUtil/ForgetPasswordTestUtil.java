package ekke.spring.TestUtils.ForgetPasswordTestUtil;

public class ForgetPasswordTestUtil {

    public static final String getForgetPasswordDto(final String reminder, final String newPassword, final String email) {
        return  "{\"reminder\":" + "\"" + reminder + "\"" + ", \"newPassword\":" + "\"" + newPassword + "\"" + ", \"email\":" + "\"" + email + "\"}";
    }
}
