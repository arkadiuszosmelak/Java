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
class Odbior extends Thread
{
    Boolean polaczenie = true;
    Socket sock;
    BufferedReader sockReader;
    String str;
 
   public Odbior(Socket sock) throws IOException                                         
   {   
      this.sockReader=new BufferedReader(new InputStreamReader(sock.getInputStream()));
      this.sock=sock; 
   }                                                                                    
 
   public void run() 
   {   
     while(polaczenie)
     {
        try
        {
           str = sockReader.readLine();
           System.out.println("<Nadeszlo:> " + str);
           if( "koniec".equals(str))
               {
                   
                   polaczenie = false;
                   sock.close();
                   sockReader.close();
               }



           //zamykanie polaczenia                                                 
           //sockReader.close();                                                           
        }
        catch( IOException e )
        {
           System.err.println("Problem z polaczeniem!");
           System.exit(1);
        }
     }
 
   };                 
}



