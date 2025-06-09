package PatronDeDiseño;

public class ItemDeLCarro {
    private Producto p;
    private int cantidad;
    public ItemDeLCarro(Producto p, int cantidad) {
        this.p = p;
        this.cantidad = cantidad;

    }

    public Producto getP() {
        return p;
    }

    public void setP(Producto p) {
        this.p = p;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
