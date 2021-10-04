package zidanJmartKD;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Recognizable implements FileParser
{
    public String name;
    public String email;
    public String password;
    public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$(?=\\S+$)";
    
    public Account (int id, String name, String email, String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    /*
    public String toString(){
        return 
        "name: "+ name +
        "\nemail: " + email +
        "\npassword: " + password ;
    }*/
    
    @Override //FileParser
    public boolean read (String content)
    {
        return false;
    }
    
    public boolean validate()
    {
        Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
        Matcher emailMatcher = emailPattern.matcher(email);
        
        Pattern passwordPattern = Pattern.compile(REGEX_PASSWORD);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        
        return emailMatcher.find() && passwordMatcher.find();
    }
}
