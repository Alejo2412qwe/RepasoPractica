/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ModelMedicamento;
import VIsta.Factura;
import VIsta.PantallaPrincipal;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author LaptopSA
 */
public class ControllerMedicamento {

    private PantallaPrincipal pp;
    private ModelMedicamento ModelMed;
    Factura f = new Factura();

    public ControllerMedicamento() {
    }

    public ControllerMedicamento(PantallaPrincipal pp, ModelMedicamento ModelMed) {
        this.pp = pp;
        this.ModelMed = ModelMed;
        pp.setVisible(true);
    }

    public void inicio() {
        pp.getBtnAceptar().addActionListener(l -> Registrar());
        cargarCombo();
        f.getBtnCerrar().addActionListener(l -> f.dispose());
        f.getBtnEnviar().addActionListener(l -> Mensaje());
        pp.setTitle("REGISTRO DE PEDIDO");
    }

    public void Mensaje() {

        JOptionPane.showMessageDialog(pp, "PEDIDO ACEPTADO", "ACEPTACION DE PEDIDO", JOptionPane.INFORMATION_MESSAGE, imagen("/modelo/aceptar.png", 40, 40));
        f.dispose();
    }

    public Icon imagen(String ruta, int alto, int ancho) {

        Icon img = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(alto, ancho, java.awt.Image.SCALE_SMOOTH));

        return img;
    }

    public void Registrar() {

        if (Validar()) {

            int response = JOptionPane.showConfirmDialog(pp, "¿Agregar Medicamento?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                String nombre = pp.getTxtNombre().getText();
                String tipo = pp.getComboTipoMed().getSelectedItem().toString();
                int cant = Integer.parseInt(pp.getTxtCantidad().getText());
                String distribuidor = "";
                if (pp.getRadio1().isSelected() == true) {
                    distribuidor = "COFARMA";
                }
                if (pp.getRadio2().isSelected() == true) {
                    distribuidor = "EMPSEPHAR";
                }
                if (pp.getRadio3().isSelected() == true) {
                    distribuidor = "CEMEFAR";
                }

                String sucursal = "";
                if (pp.getCheckPrincipal().isSelected() == true) {
                    sucursal = "PRINCIPAL - Calle de la Rosa n. 28";
                }
                if (pp.getCheckSecundaria().isSelected() == true) {
                    sucursal = "SECUNDARIA - Calle Alcazabilla n. 3";
                }
                if (pp.getCheckPrincipal().isSelected() == true && pp.getCheckSecundaria().isSelected() == true) {
                    sucursal = "Para la sucursal PRINCIPAL - Calle de la Rosa n. 28 y SECUNDARIA - Calle Alcazabilla n. 3";
                }

                ModelMedicamento medicamento = new ModelMedicamento();
                medicamento.setNombre(nombre);
                medicamento.setTipo(tipo);
                medicamento.setCantidad(cant);
                medicamento.setDistribuidor(distribuidor);
                medicamento.setSucursal(sucursal);

                if (medicamento.setMedicamento()) {

                    JOptionPane.showMessageDialog(pp, "Medicamento agregado correctamente");
                    f.setVisible(true);
                    f.setTitle("FARMACEUTICA "+medicamento.getDistribuidor());
                    f.getTitulo().setText(medicamento.getDistribuidor());
                    f.getTxtMensaje().setText(String.valueOf(medicamento.getCantidad()) + " unidades del " + medicamento.getTipo() + " " + medicamento.getNombre());
                    f.getTxtMensaje1().setText("SUCURSAL ELEGIDA: " + medicamento.getSucursal());
                    LimpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(pp, "No se pudo agregar el medicamento");
                }

            }
        }
    }

    public void cargarCombo() {

        ArrayList<String> lista = new ArrayList<>();
        lista.add("SELECCIONE UN TIPO DE MEDICAMENTO");
        lista.add("Analgesico");
        lista.add("Analeptico");
        lista.add("Anestesico");
        lista.add("Antiacido");
        lista.add("Antidepresivo");
        lista.add("Antibiotico");
        for (String datos : lista) {
            pp.getComboTipoMed().addItem(datos);
        }
    }

    public boolean Validar() {

        boolean ban = true;

        if (pp.getTxtNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(pp, "EL NOMBRE ESTA VACIO");
            ban = false;
        }

        if (!pp.getTxtNombre().getText().matches("[a-zA-Z]*")) {
            JOptionPane.showMessageDialog(pp, "NO SE PERMITE ESOS DATOS EN EL NOMBRE");
            ban = false;
        }

        if (pp.getTxtCantidad().getText().isEmpty()) {
            JOptionPane.showMessageDialog(pp, "LA CANTIDAD ESTA VACIA");
            ban = false;
        }

        if (pp.getComboTipoMed().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(pp, "SELECCIONE UN TIPO CORRECTO");
            ban = false;
        }

        if (!pp.getTxtCantidad().getText().matches("[0-9]*")) {
            JOptionPane.showMessageDialog(pp, "CANTIDAD INVALIDA");
            ban = false;
        }

        return ban;
    }

    public void LimpiarCampos() {

        pp.getTxtNombre().setText("");
        pp.getComboTipoMed().setSelectedIndex(0);
        pp.getTxtCantidad().setText("");

        if (pp.getCheckPrincipal().isSelected()) {
            pp.getCheckPrincipal().setSelected(false);
        }
        if (pp.getCheckSecundaria().isSelected()) {
            pp.getCheckSecundaria().setSelected(false);
        }

        pp.getRadio1().setSelected(false);

        pp.getRadio2().setSelected(false);

        pp.getRadio3().setSelected(false);

    }
    
}
