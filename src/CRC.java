
import java.util.Scanner;

public class CRC {

    static int codeword[];
    static int divisor[];
    static int dataword[];
    static int remainder[];
    static int crc[];
    
    public static void main(String [] args)
    {
        
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the number of elements in dataword");
        int data_size=sc.nextInt();
        
        dataword=new int[data_size];
        System.out.println("Enter the dataword");
        for(int i=0;i<data_size;i++)
        {
            dataword[i]=sc.nextInt();
        }
        
        System.out.println("Enter the number of elements in divisor");
        int div_size=sc.nextInt();
        
        divisor=new int[div_size];
        System.out.println("Enter the divisor");
        for(int i=0;i<div_size;i++)
        {
            divisor[i]=sc.nextInt();
        }
        
        
        int total_length=dataword.length +divisor.length-1;
        codeword=new int[total_length];
        remainder=new int[total_length];
        crc=new int[total_length];
        
        for(int i=0;i<dataword.length;i++)
        {
            codeword[i]=dataword[i];
            remainder[i]=dataword[i];
        }
        
        //System.out.println(div);
        //System.out.println(divisor);
        //System.out.println(remainder);
        
        
        remainder=divide(codeword,divisor,remainder);
        
        for(int i=0;i<remainder.length;i++)
        {
            crc[i]=remainder[i]^codeword[i];
            
        
        }

        for(int i=0;i<remainder.length;i++)
            System.out.print(remainder[i]);
        
        System.out.println("The CRC code is");
        for(int i=0;i<crc.length;i++)
            System.out.print(crc[i]);
        
        
        System.out.println("Enter the CRC code ");
        for(int i=0;i<crc.length;i++)
            crc[i]=sc.nextInt();
        
        for(int i=0;i<crc.length;i++)
            remainder[i]=crc[i];
        
        remainder=divide(crc, divisor, remainder);
        
        int flag = 1;
        for(int i=0;i<remainder.length;i++)
        {
            if(remainder[i]!=0)
            {
                System.out.println("Error detected");
                flag = 0;
                break;
            }
        }
        if(flag == 1)
        	System.out.println("No error detected");
        
    }

    private static int[] divide(int[] codeword, int[] divisor, int[] remainder) {
        
        int count=0;
        
        while(true)
        {
            for(int i=0;i<divisor.length;i++)
            {
                remainder[count+i]=remainder[count+i]^divisor[i];
            }
            
            while(remainder[count]==0 && count!=remainder.length-1)
            {
                count++;
            }
            
            if(((remainder.length)-count)<divisor.length)
                break;
        }
        return remainder;
        
    }
}