/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LaptopSA
 */
public class ModelMedicamento extends Medicamento{
    
    Conexion c = new Conexion();

    public ModelMedicamento() {
    }

    public ModelMedicamento(String nombre, String tipo, String sucursal, String distribuidor, int cantidad) {
        super(nombre, tipo, sucursal, distribuidor, cantidad);
    }
   
    
    public boolean setMedicamento() {       
        String sql="INSERT INTO public.medicamento(\n" +
"	med_nombre, med_tipo, med_sucursal, med_distribuidor, med_cantidad)\n" +
"	VALUES ('"+getNombre()+"', '"+getTipo()+"', '"+getSucursal()+"', '"+getDistribuidor()+"', "+getCantidad()+");";
        return c.accion(sql);//EJECUTAMOS EN INSERT
    }
}
