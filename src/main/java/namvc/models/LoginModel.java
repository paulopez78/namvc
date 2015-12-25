package namvc.models;

public class LoginModel {
    private String redirectUrl;
    private String errorMessage;

    public LoginModel(String location, String errorMessage)
    {
        this.redirectUrl = location;
        this.errorMessage = errorMessage;
    }

    public LoginModel(String location)
    {
        this(location, "");
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean hasError()
    {
        return !getErrorMessage().equals("");
    }
}
