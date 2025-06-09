package PatronDeDiseño;

import java.util.ArrayList;
import java.util.List;

public class CarroDeCompra {
    private List<ItemDeLCarro> items = new ArrayList<>();

    public void agregarProducto(Producto p , int cantidad) {
        for (ItemDeLCarro item : items) {
            if(item.getP().getCodigo().equals(p.getCodigo())){
                item.setCantidad(item.getCantidad() + cantidad);

            }
        }



    }
    public void CalcularPrecio() {

    }

}
