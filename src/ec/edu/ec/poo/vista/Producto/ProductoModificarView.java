package ec.edu.ec.poo.vista.Producto;
import ec.edu.ec.poo.utils.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class ProductoModificarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar;
    private JButton btnModificar;

    private MensajeInternacionalizacionHandler mensaje;

    public ProductoModificarView(MensajeInternacionalizacionHandler mensaje) {
        this.mensaje = mensaje;
        initComponents();
        actualizarTextos();
    }
    private void initComponents() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 200);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setLocationRelativeTo(null);
    }



    private void actualizarTextos() {
        setTitle(mensaje.get("producto.modificar.titulo"));
        //txtNombre.setText(mensaje.get("producto.modificar.titulo"));
        txtCodigo.setText(mensaje.get("codigo"));
        txtNombre.setText(mensaje.get("nombre"));
        txtPrecio.setText(mensaje.get("precio"));

        btnBuscar.setText(mensaje.get("buscar"));
        btnBuscar.setText(mensaje.get("listar"));
        btnModificar.setText(mensaje.get("actualizar"));
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

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}
