package ec.edu.ec.poo.vista;

import javax.swing.*;

public class ProductoModificarView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar;
    private JButton btnModificar;

    public ProductoModificarView() {
        setContentPane(panelPrincipal);
        setTitle("Modificar Producto");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
