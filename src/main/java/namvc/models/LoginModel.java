package namvc.models;

public class LoginModel {
    private final String redirectUrl;
    private final String errorMessage;
    private final String user;

    public LoginModel(String location, String user, String errorMessage)
    {
        this.redirectUrl = location;
        this.errorMessage = errorMessage;
        this.user = user;
    }


    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUser () {return user;}

    public boolean hasError()
    {
        return !getErrorMessage().equals("");
    }

    public boolean authenticated() {
        return !getUser().equals("");
    }
}
