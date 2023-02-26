
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import rc.soop.action.ActionB;
import static rc.soop.action.Constant.bando;
import rc.soop.action.Pdf_new;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rcosco
 */
public class NewClass {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(date));
        
        
        String filePath = Pdf_new.compileAllegatoB("VGallo8881", bando, ActionB.getAllegatoA("VGallo8881"));
                
        
        
    }
}
