
package apagar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kirk-wdesk
 */
public class Apagar implements Runnable{

    Thread hilo;
    boolean status = true;
    int hor, min, seg = 60;
    Menu menu;
    String param;
    
    public Apagar(Menu menu, int hor, int min, String param){
        this.menu = menu;
        hilo = new Thread(this);
        hilo.start();
        this.hor = hor;
        System.out.println(hor);
        this.min = min;
        this.param = param;
        
        if (this.min > 0) {
            seg = 60;
            this.min--;
        }
        
    }
    
    @Override
    public void run() {
        while(status){
            try {
                Thread.sleep(1000);
                if (seg == 0) {
                    if (min == 0) {
                        if (hor == 0) {
                            //JOptionPane.showMessageDialog(null, "BOOOM!!");
                            apagar();
                        }else{
                            hor--;
                            min = 59;
                            seg = 59;
                        }
                    }else{
                        min--;
                        seg = 59;
                    }
                }else{
                    seg--;
                }
                
                menu.getLabelSegundos().setText(seg+"s");
                menu.getLabelMinutos3().setText(min+"m");
                menu.getLabelHoras2().setText(hor+"h");
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Apagar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cancelar(){
        status = false;
        hilo.stop();
        //hilo.destroy();
    }
    
    public void apagar(){
        try {
            // Execute a command without arguments
            System.out.println("apagando..");
            String [] cmd = {"shutdown", param}; //Comando de apagado en windows
            Runtime.getRuntime().exec(cmd);
            cancelar();
        
        } catch (IOException e) {
        
        }
        
    }
    
}
