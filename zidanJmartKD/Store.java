package zidanJmartKD;

public class Store extends Recognizable implements FileParser
{
    public String name;
    public String address;
    public String phoneNumber;
    
    @Override
    public boolean read (String content){
        return false;
    }
    
    @Override
    public Object write()
    {
        return null;
    }
    
    public Object newInstance (String content)
    {
        return null;
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
}

