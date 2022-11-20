
import java.util.*;
import java.io.*;

public class macropro {
    static String mnt[][]=new String[5][3];     //assuming 5 macro in one program
    static String ala[][]=new String[10][2];    //assuming 2 arguments in each macro
    static String mdt[][]=new String[20][1];    //assuming 4 LOC  in each macro 
    static int mdtc=0,mntc=0,alac=0;
    public static void main(String[] args) {
        pass1();
        System.out.println("***********PASS 1***************");
        System.out.println("MACRO NAME TABLE");
        display(mnt,mntc,3);
        System.out.println("ARGUMENT LIST ARRAY TABLE");
        display(ala,alac,2);
        System.out.println("MACRO DEFINITION TABLE");
        display(mdt,mdtc,1);
        pass2();
        System.out.println("ARGUMENT ARRAY FOR PASS2");
        display(ala,alac,2);
    }
    static void pass1(){
        int index=0,i;
        String s,prev="",substring;
        try{
            BufferedReader ip= new BufferedReader(new FileReader("D:\\LP-1\\MacroProcessor\\input.txt"));
            File op = new File("D:\\LP-1\\MacroProcessor\\pass1_output.txt");
            if(!op.exists()){
                op.createNewFile();
                BufferedWriter output=new BufferedWriter(new FileWriter(op.getAbsoluteFile()));
            
            while((s=ip.readLine())!=null){
                if(s.equalsIgnoreCase("MACRO")){
                    prev=s;
                    for(;!(s=ip.readLine()).equalsIgnoreCase("MEND");mdtc++,prev=s){
                        if(prev.equalsIgnoreCase("MACRO")){
                            StringTokenizer st=new StringTokenizer(s);      //for creating tokens on the string
                            String []str = new String[st.countTokens()];
                            for ( i = 0; i < str.length; i++) {
                                str[i]=st.nextToken();
                            }
                            mnt[mntc][0]=(mntc+1)+"";   //mnt formation
                            mnt[mntc][1]=str[0];
                            mnt[mntc++][2]=(++mdtc)+"";

                            st=new StringTokenizer(str[1],",");
                            String string[]=new String[st.countTokens()];
                            for (i = 0; i < string.length; i++) {
                                string[i]=st.nextToken();
                                ala[alac][0]=alac+"";
                                index=string[i].indexOf("=");           //read upto = and taken and returns the index of it and consider it as one of the substring
                                if(index!=-1){
                                    ala[alac++][1]=string[i].substring(0,index);
                                }
                                else{
                                    ala[alac++][1]=string[i];
                                }
                            }
                        }
                        else{
                            index=s.indexOf("&");       
                            substring=s.substring(index);
                            for(i=0; i<alac; i++){
                                if(ala[i][1].equals(substring)){
                                    s=s.replaceAll(substring,"#"+ala[i][0]);
                                }
                            }

                        }
                        mdt[mdtc-1][0]=s;
                    }
                    mdt[mdtc-1][0]=s;
                }
                else{
                    output.write(s);
                    output.newLine();
                }
            }output.close();
            
        }

        }catch(FileNotFoundException e){
            System.out.println("Unavailabe file");
        }
        catch(IOException e){
            e.printStackTrace();;
        }
    }
   
    static void pass2(){
        String s,temp;
        int flag=0,alap=0,index,mdtp,i,j;
        try{
            BufferedReader ip=new BufferedReader(new FileReader("D:\\LP-1\\MacroProcessor\\pass1_output.txt"));
            File op = new File("D:\\LP-1\\MacroProcessor\\pass2_output.txt");
            if(!op.exists()){
                op.createNewFile();
                BufferedWriter output=new BufferedWriter(new FileWriter(op.getAbsoluteFile()));
            
            for(;(s=ip.readLine())!=null;flag=0){
                StringTokenizer st=new StringTokenizer(s);
                String str[]=new String[st.countTokens()];
                for ( i = 0; i < str.length; i++) {
                    str[i]=st.nextToken();
                }
                for(j=0; j<mntc; j++){
                    if(str[0].equals(mnt[j][1])){
                        mdtp=Integer.parseInt(mnt[j][2]);
                        st=new StringTokenizer(str[1],",");
                        String arg[]=new String[st.countTokens()];
                        for(i=0; i<arg.length; i++){
                            arg[i]=st.nextToken();
                            ala[alap++][1]=arg[i];
                        }
                        for(i=mdtp; !(mdt[i][0].equalsIgnoreCase("MEND")); i++){
                            index=mdt[i][0].indexOf("#");
                            temp=mdt[i][0].substring(0,index);
                            temp+=ala[Integer.parseInt("" +mdt[i][0].charAt(index+1))][1];
                            output.write(temp);
                            output.newLine();
                        }
                        flag=1;
                    }
                }
                if(flag==0){
                    output.write(s);
                    output.newLine();
                }
            }
            output.close();
        }
           
        
    }catch(FileNotFoundException e){
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();;
    }
}
static void display(String a[][],int n,int m){
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            System.out.print(a[i][j]+" ");
            
        }
        System.out.println();
        
    }
}
    
}
