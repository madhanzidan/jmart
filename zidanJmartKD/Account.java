package zidanJmartKD;

public class Account extends Recognizable implements FileParser
{
    public String name;
    public String email;
    public String password;
    
    public Account (int id, String name, String email, String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        return 
        "Name: "+ name +
        "\nemail: " + email +
        "\npassword: " + password ;
    }
    
    @Override
    public boolean read (String readAccount)
    {
        return false;
    }
}
