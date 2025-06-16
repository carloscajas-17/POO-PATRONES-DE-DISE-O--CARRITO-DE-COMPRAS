package ec.edu.ec.poo.vista;

import ec.edu.ec.poo.controller.ProductoController;
import ec.edu.ec.poo.dao.ProductoDAO;
import ec.edu.ec.poo.dao.imple.ProductoDAOMemoria;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoAnadirView productoView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoDAO productoDAO = new ProductoDAOMemoria();

                new ProductoController(productoDAO, productoView, productoListaView);
            }
        });
    }
}
