package ec.edu.ec.poo.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Carritoview extends JFrame {

    private JPanel panelPrincipal;
    private JTable tableCarrito;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnPagar;
    private JLabel labelTotal;
    private JTextField txtDescuento;
    private JButton btnAplicarDescuento;

    private double total = 0.0;
    private double descuento = 0.0;

    public void CarritoView() {
        setContentPane(panelPrincipal);
        setTitle("Carrito de Compras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);


        tableCarrito.setFont(new Font("Courier New", Font.PLAIN, 14));
        tableCarrito.getTableHeader().setFont(new Font("Courier New", Font.BOLD, 15));
        tableCarrito.setRowHeight(28);


        Font fontBtn = new Font("Courier New", Font.BOLD, 14);
        btnAgregar.setFont(fontBtn);
        btnEliminar.setFont(fontBtn);
        btnPagar.setFont(fontBtn);
        btnAplicarDescuento.setFont(fontBtn);


        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Subtotal"}, 0
        );
        tableCarrito.setModel(model);

        labelTotal.setText("Total: $0.00");


        btnAgregar.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this, "Código:");
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            double precio = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio:"));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Cantidad:"));
            double subtotal = precio * cantidad;
            model.addRow(new Object[]{codigo, nombre, precio, cantidad, subtotal});
            recalcularTotal(model);
        });


        btnEliminar.addActionListener(e -> {
            int fila = tableCarrito.getSelectedRow();
            if (fila != -1) {
                model.removeRow(fila);
                recalcularTotal(model);
            }
        });


        btnAplicarDescuento.addActionListener(e -> {
            try {
                descuento = Double.parseDouble(txtDescuento.getText());
                recalcularTotal(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Descuento inválido");
            }
        });


        btnPagar.addActionListener(e -> {
            imprimirCarritoEnConsola(model);
            JOptionPane.showMessageDialog(this,
                    "¡Gracias por su compra!\n" + labelTotal.getText(),
                    "Compra Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    // Método para recalcular el total y aplicar descuento
    private void recalcularTotal(DefaultTableModel model) {
        total = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            total += Double.parseDouble(model.getValueAt(i, 4).toString());
        }
        if (descuento > 0) {
            total = total * (1 - descuento / 100.0);
        }
        labelTotal.setText("Total: $" + String.format("%.2f", total));
    }

    // Método para imprimir el carrito en la consola
    private void imprimirCarritoEnConsola(DefaultTableModel model) {
        System.out.println("\n======== CARRITO DE COMPRAS ========");
        for (int i = 0; i < model.getRowCount(); i++) {
            String codigo = model.getValueAt(i, 0).toString();
            String nombre = model.getValueAt(i, 1).toString();
            String precio = model.getValueAt(i, 2).toString();
            String cantidad = model.getValueAt(i, 3).toString();
            String subtotal = model.getValueAt(i, 4).toString();
            System.out.println("- " + nombre + " (Código: " + codigo + "), Precio: $" + precio +
                    ", Cantidad: " + cantidad + ", Subtotal: $" + subtotal);
        }
        System.out.println("-------------------------------------");
        System.out.println(labelTotal.getText());
    }


}
