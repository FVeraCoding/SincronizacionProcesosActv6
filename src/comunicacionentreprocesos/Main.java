package comunicacionentreprocesos;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        File proceso1 = new File("C:\\Users\\Fernando\\Desktop\\Procesos", "proceso1.txt");
        String[] datosProceso1 = leerArchivo(proceso1);
        
        File proceso2 = new File("C:\\Users\\Fernando\\Desktop\\Procesos", "proceso2.txt");
        String[] datosProceso2 = leerArchivo(proceso2);
        
        Process procesoUno;
        Process procesoDos;
        try {
            procesoUno = Runtime.getRuntime().exec(datosProceso1);
            int valorRetornoProceso1 = procesoUno.waitFor();
            System.out.println(valorRetornoProceso1);
            
            if(valorRetornoProceso1 == 0){
                System.out.println("El primer proceso ha terminado correctamente. Lanzando el segundo proceso.");
                procesoDos = Runtime.getRuntime().exec(datosProceso2);
            }else{
                System.out.println("El primer proceso no ha terminado de la manera esperada. El segundo proceso no se lanzará.");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public static String[] leerArchivo(File file){
        String[] datosProceso = new String[1];
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String linea = br.readLine();
            int contador = 0;
            
            
            while(linea != null){
                System.out.println(linea);
                datosProceso[0] = linea;
                linea = br.readLine();
            }
            
            
            
        } catch(EOFException e){
            System.out.println("Se ha llegado al final del documento.");
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datosProceso;
    }

}
