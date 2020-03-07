import java.io.*;
import java.util.Scanner;

public class Servidor extends Conexion { //Se hereda de conexi�n para hacer uso de los sockets

            Scanner teclado=new Scanner(System.in);
            String msg=null;   


    public Servidor() throws IOException{
          super("servidor"); //Se usa el constructor para servidor de Conexion
          System.out.println("Iniciando servidor\n");
    }

      public void Escribe(Scanner teclado,String msg,BufferedReader entrada) throws IOException{

        salidaCliente = new DataOutputStream(cs.getOutputStream());

             do{                
                 msg=teclado.nextLine();
                 salidaCliente.writeUTF( msg+"\n");
                 Escucha(entrada);
               }while(msg.compareTo("exit")!=0);                       

            cs.close();//Fin de la conexi�n
    }
    

    public void Escucha(BufferedReader entrada) throws IOException{
      
        mensajeServidor = entrada.readLine();
        System.out.println(mensajeServidor);  
    }
    
   

    public void startServer() 
    {   //M�todo para iniciar el servidor   
        try
        {
          System.out.println("Esperando..."); //Esperando conexi�n

          cs = ss.accept(); //Accept comienza el socket y espera una conexi�n desde un cliente
          System.out.println("Cliente en l�nea");

            //Se obtiene el flujo entrante desde el cliente
           BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            
          try
          {
            
             Escribe(teclado,msg,entrada);
          }
          catch (Exception e)
          {
          System.out.println(e.getMessage());
          }
            
            
            System.out.println("Fin de la conexi�n");
            
            ss.close();//Se finaliza la conexi�n con el cliente
          }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

     public static void main(String[] args) throws IOException{        
        Servidor serv=null;

          
          try
          {
           serv= new Servidor(); //Se crea el servidor
          }
          catch(IOException e)
          {
               System.out.println("Error al iniciar el Servidor");
          }
           if(serv!=null)
          serv.startServer(); 
    }
}
