package ec.edu.ec.poo.dao;



import ec.edu.ec.poo.modelo.Rol;
import ec.edu.ec.poo.modelo.Usuario;

import java.util.List;

public interface UsuarioDAO {

    Usuario autenticar(String username, String contrasenia);

    void crear(Usuario usuario);

    Usuario buscarPorUsername(String username);

    void eliminar(String username);

    boolean actualizar(Usuario usuario);
    //void guardar(Usuario usuario);

    List<Usuario> listarTodos();

    List<Usuario> listarPorRol(Rol rol);

}