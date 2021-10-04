package zidanJmartKD;

public class Jmart
{
    public static void main(String[] args)
    {
        Account betaTest = new Account(456, "Gae Hwi - 456", "gaehwi@gmail.com", "Gaehwi456");
        System.out.println(betaTest.validate());
        
        Complaint betaComplaint = new Complaint(99, "Terlambat datang");
        System.out.println(betaComplaint);
    }
}