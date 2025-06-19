package ec.edu.ec.poo.vista;

import ec.edu.ec.poo.controller.ProductoController;
import ec.edu.ec.poo.dao.ProductoDAO;
import ec.edu.ec.poo.dao.imple.ProductoDAOMemoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Instanciamos DAO
                ProductoDAO productoDAO = new ProductoDAOMemoria();

                // Instanciamos las vistas
                MenuPrincipalView principalView = new MenuPrincipalView();
                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoModificarView productoModificarView = new ProductoModificarView();
                ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                CarritoAnadirView carritoAnadirView = new CarritoAnadirView();

                // Instanciamos el controlador
                ProductoController productoController = new ProductoController(
                        productoDAO,
                        productoAnadirView,
                        productoListaView,
                        productoEliminarView,
                        productoModificarView,
                        carritoAnadirView
                );

                // Listeners de menú
                principalView.getMenuItemCrear().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!productoAnadirView.isVisible()) {
                            principalView.getjDesktopPane().add(productoAnadirView);
                            productoAnadirView.setVisible(true);
                            productoAnadirView.toFront();
                        }
                    }
                });

                principalView.getMenuItemBuscar().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!productoListaView.isVisible()) {
                            principalView.getjDesktopPane().add(productoListaView);
                            productoListaView.setVisible(true);
                            productoListaView.toFront();
                        }
                    }
                });

                principalView.getMenuItemEliminar().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!productoEliminarView.isVisible()) {
                            principalView.getjDesktopPane().add(productoEliminarView);
                            productoEliminarView.setVisible(true);
                            productoEliminarView.toFront();
                        }
                    }
                });

                principalView.getMenuItemActualizar().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!productoModificarView.isVisible()) {
                            principalView.getjDesktopPane().add(productoModificarView);
                            productoModificarView.setVisible(true);
                            productoModificarView.toFront();
                        }
                    }
                });

                principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!carritoAnadirView.isVisible()) {
                            principalView.getjDesktopPane().add(carritoAnadirView);
                            carritoAnadirView.setVisible(true);
                            carritoAnadirView.toFront();
                        }
                    }
                });

                // MOSTRAR LA VENTANA PRINCIPAL
                principalView.setVisible(true);
            }
        });
    }
}
