package ec.edu.ec.poo.vista.Producto;
import ec.edu.ec.poo.utils.MensajeInternacionalizacionHandler;

import javax.swing.*;
public class ProductoEliminarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar;
    private JButton btnEliminar;

    private MensajeInternacionalizacionHandler mensaje;

    public ProductoEliminarView(MensajeInternacionalizacionHandler mensaje) {
        this.mensaje = mensaje;
        initComponents();
        actualizarTextos();
    }

    private void initComponents() {
        setContentPane(panelPrincipal);
        setSize(500, 200);
        //setLocationRelativeTo(null);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
    }

    private void actualizarTextos() {
        setTitle(mensaje.get("producto.eliminar.titulo"));
        //lblTitulo.setText(mensaje.get("producto.eliminar.titulo"));
        txtCodigo.setText(mensaje.get("codigo"));
        txtNombre.setText(mensaje.get("nombre"));
        txtPrecio.setText(mensaje.get("precio"));

        btnBuscar.setText(mensaje.get("buscar"));
        btnEliminar.setText(mensaje.get("eliminar"));
        //btnListar.setText(mensaje.get("listar"));
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mensaje.setLenguaje(lenguaje, pais);
        actualizarTextos();
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

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public MensajeInternacionalizacionHandler getMensaje() {
        return mensaje;
    }

    public void setMensaje(MensajeInternacionalizacionHandler mensaje) {
        this.mensaje = mensaje;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean confirmarEliminacion() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar este producto?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        return opcion == JOptionPane.YES_OPTION;
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}


