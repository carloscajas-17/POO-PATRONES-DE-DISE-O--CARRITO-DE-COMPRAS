package ec.edu.ec.poo.controller;

import ec.edu.ec.poo.dao.CarritoDAO;
import ec.edu.ec.poo.dao.UsuarioDAO;
import ec.edu.ec.poo.modelo.Carrito;
import ec.edu.ec.poo.modelo.Rol;
import ec.edu.ec.poo.modelo.Usuario;
import ec.edu.ec.poo.utils.FormateadorUtils;
import ec.edu.ec.poo.utils.MensajeInternacionalizacionHandler;
import ec.edu.ec.poo.vista.Usuario.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final CarritoDAO carritoDAO;
    private final LoginView loginView;
    private final UsuarioRegistroView usuarioRegistroView;
    private final UsuarioEliminarView usuarioEliminarView;
    private final UsuarioListaView usuarioListaView;
    private final UsuarioModificarView usuarioModificarView;

    public UsuarioController(UsuarioDAO usuarioDAO,
                             CarritoDAO carritoDAO,
                             LoginView loginView,
                             UsuarioRegistroView usuarioRegistroView,
                             UsuarioEliminarView usuarioEliminarView,
                             UsuarioListaView usuarioListaView,
                             UsuarioModificarView usuarioModificarView) {
        this.usuarioDAO = usuarioDAO;
        this.carritoDAO = carritoDAO;
        this.loginView = loginView;
        this.usuarioRegistroView = usuarioRegistroView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioListaView = usuarioListaView;
        this.usuarioModificarView = usuarioModificarView;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        loginView.getBtnIniciar().addActionListener(e -> autenticar());

        loginView.getBtnRegistrar().addActionListener(e -> {
            usuarioRegistroView.limpiarCampos();
            usuarioRegistroView.cambiarIdioma(
                    loginView.getMensaje().getLocale().getLanguage(),
                    loginView.getMensaje().getLocale().getCountry()
            );
            usuarioRegistroView.setVisible(true);
        });

        usuarioRegistroView.getBtnRegistrar().addActionListener(e -> registrarUsuario());
        usuarioEliminarView.getBtnBuscar().addActionListener(e -> buscarUsuarioEliminar());
        usuarioEliminarView.getBtnEliminar().addActionListener(e -> eliminarUsuario());
        usuarioListaView.getBtnBuscar().addActionListener(e -> buscarUsuarioLista());
        usuarioListaView.getBtnLimpiar().addActionListener(e -> limpiarCamposLista());
        usuarioModificarView.getBtnModificar().addActionListener(e -> modificarUsuario());
        usuarioModificarView.getBtnMostrar().addActionListener(e -> mostrarContrasenia());
    }

    private void autenticar() {
        String id = loginView.getTxtUsuario().getText();
        String contrasenia = loginView.getTxtContrasenia().getText();

        usuario = usuarioDAO.autenticar(id, contrasenia);
        if (usuario == null) {
            loginView.mostrarMensaje("usuario.login.error");
        } else {
            loginView.dispose();
        }
        loginView.limpiarCampos();
    }

    public Usuario getUsuarioAutenticado() {
        return usuario;
    }

    private void registrarUsuario() {
        String id = usuarioRegistroView.getTxtId().getText();
        String nombre = usuarioRegistroView.getTxtNombre().getText();
        String password = new String(usuarioRegistroView.getTxtContrasenia().getPassword());
        String confirmarPassword = new String(usuarioRegistroView.getTxtConfirmarContrasenia().getPassword());

        if (id.isEmpty() || nombre.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
            usuarioRegistroView.mostrarMensaje("mensaje.campos.vacios");
            return;
        }

        if (usuarioDAO.buscarPorUsername(id) != null) {
            usuarioRegistroView.mostrarMensaje("mensaje.usuario.ya.existe");
            return;
        }

        if (!password.equals(confirmarPassword)) {
            usuarioRegistroView.mostrarMensaje("mensaje.contrasenia.no.coincide");
            return;
        }

        Usuario nuevoUsuario = new Usuario(id, nombre, password, Rol.USUARIO);
        usuarioDAO.crear(nuevoUsuario);
        usuarioRegistroView.mostrarMensaje("mensaje.usuario.registrado");
        usuarioRegistroView.limpiarCampos();
        usuarioRegistroView.dispose();
    }

    private void buscarUsuarioEliminar() {
        String id = usuarioEliminarView.getTxtUsuario().getText().trim();
        if (id.isEmpty()) {
            usuarioEliminarView.mostrarMensaje("mensaje.campos.vacios");
            return;
        }

        Usuario user = usuarioDAO.buscarPorUsername(id);
        if (user != null) {
            int asociados = contarCarritosUsuario(user);
            usuarioEliminarView.getTxtAsociado().setText(String.valueOf(asociados));
            usuarioEliminarView.getBtnEliminar().setEnabled(asociados == 0);
        } else {
            usuarioEliminarView.mostrarMensaje("usuario.no.encontrado");
        }
    }

    private void eliminarUsuario() {
        String id = usuarioEliminarView.getTxtUsuario().getText().trim();
        Usuario user = usuarioDAO.buscarPorUsername(id);
        if (user != null) {
            usuarioDAO.eliminar(user.getId());
            usuarioEliminarView.mostrarMensaje("usuario.eliminado");
            usuarioEliminarView.getTxtUsuario().setText("");
            usuarioEliminarView.getTxtAsociado().setText("");
        }
    }

    private void buscarUsuarioLista() {
        String id = usuarioListaView.getTxtUsuario().getText().trim();
        List<Usuario> usuarios;

        if (id.isEmpty()) {
            usuarios = usuarioDAO.listarTodos();
        } else {
            usuarios = new ArrayList<>();
            Usuario user = usuarioDAO.buscarPorUsername(id);
            if (user != null) usuarios.add(user);
        }

        cargarUsuariosEnLaTabla(usuarios);
    }

    private void cargarUsuariosEnLaTabla(List<Usuario> usuarios) {
        DefaultTableModel modelo = (DefaultTableModel) usuarioListaView.getTblDetalle().getModel();
        modelo.setRowCount(0);
        Locale locale = usuarioListaView.getMensaje().getLocale();

        for (Usuario user : usuarios) {
            List<Carrito> carritos = carritoDAO.listarPorUsuario(user.getId());
            int numCarritos = carritos.size();
            double total = calcularTotalCarritos(carritos);

            modelo.addRow(new Object[]{
                    user.getId(),
                    numCarritos,
                    FormateadorUtils.formatearMoneda(total, locale)
            });
        }
    }

    private void limpiarCamposLista() {
        usuarioListaView.getTxtUsuario().setText("");
        DefaultTableModel modelo = (DefaultTableModel) usuarioListaView.getTblDetalle().getModel();
        modelo.setRowCount(0);
    }

    private double calcularTotalCarritos(List<Carrito> carritos) {
        return carritos.stream().mapToDouble(Carrito::calcularTotal).sum();
    }

    private void modificarUsuario() {
        String id = usuarioModificarView.getTxtUsuario().getText().trim();
        String contrasenia = new String(usuarioModificarView.getTxtContrasenia().getPassword());
        String confirmacion = new String(usuarioModificarView.getTxtConfirmar().getPassword());

        if (id.isEmpty()) {
            usuarioModificarView.mostrarMensaje("usuario.vacio");
            return;
        }

        if (!contrasenia.equals(confirmacion)) {
            usuarioModificarView.mostrarMensaje("contrasenias.no.coinciden");
            return;
        }

        if (contrasenia.isEmpty()) {
            contrasenia = usuario.getContrasenia();
        } else if (!validarContrasenia(contrasenia)) {
            usuarioModificarView.mostrarMensaje("contrasenia.invalida");
            return;
        }

        usuario.setId(id);
        usuario.setContrasenia(contrasenia);

        if (usuarioDAO.actualizar(usuario)) {
            usuarioModificarView.mostrarMensaje("usuario.actualizado");
            usuarioModificarView.dispose();
        } else {
            usuarioModificarView.mostrarMensaje("error.actualizar");
        }
    }

    private void mostrarContrasenia() {
        JPasswordField contrasenia = usuarioModificarView.getTxtContrasenia();
        JPasswordField confirmacion = usuarioModificarView.getTxtConfirmar();

        if (contrasenia.getEchoChar() == '*') {
            contrasenia.setEchoChar((char) 0);
            confirmacion.setEchoChar((char) 0);
            usuarioModificarView.getBtnMostrar().setText(usuarioModificarView.getMensaje().get("ocultar"));
        } else {
            contrasenia.setEchoChar('*');
            confirmacion.setEchoChar('*');
            usuarioModificarView.getBtnMostrar().setText(usuarioModificarView.getMensaje().get("mostrar"));
        }
    }

    private boolean validarContrasenia(String contrasenia) {
        return contrasenia.length() >= 5;
    }

    private int contarCarritosUsuario(Usuario usuario) {
        List<Carrito> carritos = carritoDAO.listarPorUsuario(usuario.getId());
        return carritos.size();
    }

    public void cambiarIdiomaVistas(String lenguaje, String pais) {
        usuarioEliminarView.cambiarIdioma(lenguaje, pais);
        usuarioListaView.getMensaje().setLenguaje(lenguaje, pais);
        usuarioListaView.setTitle(usuarioListaView.getMensaje().get("usuario.lista.view"));
        usuarioModificarView.cambiarIdioma(lenguaje, pais);
        usuarioRegistroView.cambiarIdioma(lenguaje, pais);
    }
}
