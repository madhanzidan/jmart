package zidanJmartKD;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store extends Recognizable implements FileParser
{
    public String name;
    public String address;
    public String phoneNumber;
    public static final String REGEX_PHONE = "^[0-9]{9, 12}\b";
    public static final String REGEX_NAME = "^[A-Z][a-z\\sa-z]{4, 19}\b";
    
    @Override //FileParser
    public boolean read (String content){
        return false;
    }
    
    public Store (int accountId, String name, String address, String phoneNumber)
    {
        super(accountId);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public Store (Account account, String name, String address, String phoneNumber)
    {
        super(account.id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
       
    public String toString(){
        return 
        "name: "+ name +
        "\naddress: " + address +
        "\nphoneNumber: " + phoneNumber ;
    }
    
    public boolean validate()
    {
        Pattern teleponPattern = Pattern.compile(REGEX_PHONE);
        Matcher teleponMatcher = teleponPattern.matcher(phoneNumber);
        
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Matcher nameMatcher = namePattern.matcher(name);
        
        return teleponMatcher.find() && nameMatcher.find();
    }
}

