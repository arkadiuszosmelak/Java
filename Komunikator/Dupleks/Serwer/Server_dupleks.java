/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_dupleks;

import java.io.*;
import java.net.*;

/**
 *
 * @author dredi
 */

 
public class Server_dupleks
{
   public static final int PORT=50007;
   
 
   public static void main(String args[]) throws IOException      
   {      
       Boolean polaczenie = true;
        BufferedReader klaw;
        PrintWriter outp;  
        String str;
        
        //tworzenie gniazda serwerowego                            
        ServerSocket serv;                                         
        serv=new ServerSocket(PORT);                               

        //oczekiwanie na polaczenie i tworzenie gniazda sieciowego 
        System.out.println("Nasluchuje: "+serv);                   
        Socket sock;                                               
        sock=serv.accept();
        System.out.println("Jest polaczenie: "+sock); 
        
        klaw=new BufferedReader(new InputStreamReader(System.in));
        outp = new PrintWriter(sock.getOutputStream());

        //tworzenie watka odbierajacego                            
        Odbior o = new Odbior(sock);
        o.start();

        while(o.isAlive())
        {
            try
          {     
                System.out.println(Boolean.toString(o.isAlive()));
               //System.out.print("<Wysylamy:> ");                                                
               str=klaw.readLine(); 
               outp.println(str);                                                               
               outp.flush();
               
               if( "koniec".equals(str))
               {
                   klaw.close();                                                                    
                   outp.close();                                                                    
                   sock.close(); 
                   serv.close();
                   break;
               }
          }
          catch (SocketException err) 
           {
            System.err.println("Polaczenie zostalo przerwane z powodu bledu zwiazanego z TCP");
           }
          catch( IOException e )
           {
              System.err.println("Problem z polaczeniem!");
              System.exit(1);
           }
            
        }
 

        //zamykanie polaczenia  

        serv.close();                                              
        sock.close(); 
        System.out.println(" koniec połączenia! ");
     
                  
      
    
 
 
 
   }
}