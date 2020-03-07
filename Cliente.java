import java.util.*;
import java.io.*;


public class Cliente extends Conexion
{
    public Cliente() throws IOException
    {
		super("cliente"); //Se usa el constructor para cliente de Conexion
           System.out.println("Iniciando cliente\n");
     } 
    
    public void Escucha(BufferedReader entrada) throws IOException
    {
        		msgr= entrada.readLine();
                System.out.println(msgr);
    }
    
    public void Escribe(Scanner teclado,String msg,BufferedReader entrada) throws IOException
    {
        salidaServidor = new DataOutputStream(cs.getOutputStream());              
             do
             {               
                 Escucha(entrada);
                 msg=teclado.nextLine();
                 salidaServidor.writeUTF( msg+"\n");                
             }
               while(msg.compareTo("exit")!=0);                       

            cs.close();
    }

    public void startClient()
    { //Método para iniciar el cliente

        Scanner teclado=new Scanner(System.in);
        String msg=null;
        try
        {            
            
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
             Escribe(teclado,msg,entrada);
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

 public static void main(String[] args) { //throws IOException
     Cliente cli=null;

       try
       { 
          cli = new Cliente(); //Se crea el cliente e intenta hacer la conexion
        }catch(IOException e)
        {
            System.out.println("\nError en la conexion, Servidor ausente  ");
        }

        if( cli!=null)
          cli.startClient(); //el cliente envia mensajes al servidor
    }
	
}    
