package ec.edu.ec.poo.dao;



import ec.edu.ec.poo.modelo.Carrito;
import java.util.List;

public interface CarritoDAO {

    void crear(Carrito carrito);

    Carrito buscarPorCodigo(int codigo);

    void actualizar(Carrito carrito);

    void eliminar(int codigo);

    List<Carrito> listarTodos();

}
