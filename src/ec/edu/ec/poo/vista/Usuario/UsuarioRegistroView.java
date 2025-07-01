package ec.edu.ec.poo.vista.Usuario;

import ec.edu.ec.poo.utils.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class UsuarioRegistroView extends JDialog {
    private JPanel panelPrincipal;
    private JTextField txtId;
    private JTextField txtNombre;
    private JPasswordField txtContrasenia;
    private JPasswordField txtConfirmarContrasenia;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private MensajeInternacionalizacionHandler mensaje;

    public UsuarioRegistroView(MensajeInternacionalizacionHandler mensaje) {
        this.mensaje = mensaje;
        initComponents();
        actualizarTextos();
    }

    private void initComponents() {
        setContentPane(panelPrincipal);
        setTitle("Registro de Usuario");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void actualizarTextos() {
        setTitle(mensaje.get("usuario.registro.titulo"));
        btnRegistrar.setText(mensaje.get("registrar"));
        btnCancelar.setText(mensaje.get("cancelar"));
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mensaje.setLenguaje(lenguaje, pais);
        actualizarTextos();
    }

    // Getters y Setters necesarios
    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JPasswordField getTxtConfirmarContrasenia() {
        return txtConfirmarContrasenia;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public MensajeInternacionalizacionHandler getMensaje() {
        return mensaje;
    }

    public void mostrarMensaje(String keyMensaje) {
        JOptionPane.showMessageDialog(this, mensaje.get(keyMensaje));
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtContrasenia.setText("");
        txtConfirmarContrasenia.setText("");
    }
}
