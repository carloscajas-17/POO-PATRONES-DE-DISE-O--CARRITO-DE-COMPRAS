package ec.edu.ec.poo.controller;

import ec.edu.ec.poo.dao.ProductoDAO;
import ec.edu.ec.poo.modelo.Producto;
import ec.edu.ec.poo.vista.ProductoAnadirView;
import ec.edu.ec.poo.vista.ProductoListaView;
import ec.edu.ec.poo.vista.ProductoModificarView;
import ec.edu.ec.poo.vista.ProductoEliminarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final ProductoModificarView productoModificarView;
    private final ProductoEliminarView productoEliminarView;
    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoModificarView productoModificarView,
                              ProductoEliminarView productoEliminarView) {
        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoModificarView = productoModificarView;
        this.productoEliminarView = productoEliminarView;
        configurarEventos();
    }

    private void configurarEventos() {

        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });


        productoModificarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
                    Producto producto = productoDAO.buscarPorCodigo(codigo);
                    if (producto != null) {
                        productoModificarView.getTxtNombre().setText(producto.getNombre());
                        productoModificarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
                    } else {
                        productoModificarView.mostrarMensaje("Producto no encontrado");
                    }
                } catch (NumberFormatException ex) {
                    productoModificarView.mostrarMensaje("Código inválido");
                }
            }
        });


        productoModificarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
                    String nombre = productoModificarView.getTxtNombre().getText();
                    double precio = Double.parseDouble(productoModificarView.getTxtPrecio().getText());

                    Producto producto = new Producto(codigo, nombre, precio);
                    productoDAO.actualizar(producto); // método void
                    productoModificarView.mostrarMensaje("Producto modificado correctamente");
                } catch (NumberFormatException ex) {
                    productoModificarView.mostrarMensaje("Datos inválidos");
                }
            }
        });


        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
                    productoDAO.eliminar(codigo); // método void
                    productoEliminarView.mostrarMensaje("Producto eliminado correctamente");
                    productoEliminarView.limpiarCampos();
                } catch (NumberFormatException ex) {
                    productoEliminarView.mostrarMensaje("Código inválido");
                }
            }
        });
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
}
