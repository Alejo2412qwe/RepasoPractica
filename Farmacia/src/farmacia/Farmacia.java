/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package farmacia;

import Controlador.ControllerMedicamento;
import Modelo.ModelMedicamento;
import VIsta.PantallaPrincipal;

/**
 *
 * @author LaptopSA
 */
public class Farmacia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PantallaPrincipal pp = new PantallaPrincipal();
        Modelo.ModelMedicamento mm = new ModelMedicamento();
        ControllerMedicamento cm = new ControllerMedicamento(pp,mm);

        cm.inicio();
    }
    
}
