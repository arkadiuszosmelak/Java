import java.io.*;
import java.net.*;

public class Serwer {

public static final int PORT=50007;
 
      public static void main(String args[]) throws IOException                  
   {      
        boolean polaczenie = true;
        boolean zmiana = true ;
        ServerSocket serv; 
        Socket sock; 
        BufferedReader inp; 
        BufferedReader klaw;
        PrintWriter outp;
        String str; 
        
        try
        {
            //tworzenie gniazda serwerowego                                                                                            
            serv=new ServerSocket(PORT);   

            //oczekiwanie na polaczenie i tworzenie gniazda sieciowego             
            System.out.println("Nasluchuje: "+serv);                                                                                         
            sock=serv.accept();                                                    
            System.out.println("Jest polaczenie: "+sock); 

            //tworzenie strumienia danych pobieranych z gniazda sieciowego     
            klaw=new BufferedReader(new InputStreamReader(System.in));                                                                                              
            outp=new PrintWriter(sock.getOutputStream()); 
            inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));


          while(polaczenie)
          {

              if(zmiana)
              {
                //komunikacja - czytanie danych ze strumienia
                str=inp.readLine();                                                    
                System.out.println("<Nadeszlo:> " + str); 
                zmiana = false;
              }

              else
              {
                //komunikacja - czytanie danych z klawiatury i przekazywanie ich do strumienia   
                System.out.print("<Wysylamy:> ");                                                
                str=klaw.readLine();                                                      
                outp.println(str);                                                               
                outp.flush();
                zmiana = true;
              }

            if( str.equalsIgnoreCase("koniec"))
            {
                System.out.println("koniec połączenia");
                inp.close();
                klaw.close();                                                                    
                outp.close();  
                sock.close();
                serv.close();
                polaczenie = false;
            } else {
            }

          }
        }
        catch(IOException e)
        {
            System.err.println("Problem z polaczeniem!");
            System.exit(1);
        }
   }                                                               
}

