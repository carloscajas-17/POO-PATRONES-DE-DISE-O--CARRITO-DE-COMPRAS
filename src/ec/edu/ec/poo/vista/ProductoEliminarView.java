package ec.edu.ec.poo.vista;
import javax.swing.*;

public class ProductoEliminarView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JButton btnEliminar;

    public ProductoEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Producto");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
    }
}

