package ec.edu.ec.poo.vista.Carrito;
import ec.edu.ec.poo.utils.MensajeInternacionalizacionHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoAnadirView extends JInternalFrame {
    private JButton btnBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JComboBox cbxCantidad;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;
    public CarritoAnadirView(MensajeInternacionalizacionHandler mensajeInternacionalizacion){
        super("Carrito de Compras", true, true, false, true);
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatos();

    }
    private void actualizarTextos() {
        // Actualizar título y labels
        setTitle(mensajeInternacionalizacion.get("carrito.anadir.titulo"));
        //lblTitulo.setText(mensaje.get("carrito.anadir.titulo"));t
        txtCodigo.setText(mensajeInternacionalizacion.get("codigo"));
        txtNombre.setText(mensajeInternacionalizacion.get("nombre"));
        txtPrecio.setText(mensajeInternacionalizacion.get("precio"));
        cbxCantidad.setSelectedItem(mensajeInternacionalizacion.get("cantidad"));
        txtSubtotal.setText(mensajeInternacionalizacion.get("subtotal"));
        txtIva.setText(mensajeInternacionalizacion.get("iva"));
        txtTotal.setText(mensajeInternacionalizacion.get("total"));

        // Actualizar botones
        btnBuscar.setText(mensajeInternacionalizacion.get("buscar"));
        btnAnadir.setText(mensajeInternacionalizacion.get("anadir"));
        btnGuardar.setText(mensajeInternacionalizacion.get("guardar"));
        btnLimpiar.setText(mensajeInternacionalizacion.get("limpiar"));
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mensajeInternacionalizacion.setLenguaje(lenguaje, pais);
        actualizarTextos();

    }

    private void cargarDatos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
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

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}

