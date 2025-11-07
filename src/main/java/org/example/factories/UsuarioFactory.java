package org.example.factories;

import org.example.entities.Usuario;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public UsuarioFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public Usuario criarUsuario(String nome, String tipo, String email, String senha) {
        Usuario usuario = new Usuario(nome, tipo, email, senha);
        usuario.setId(getNextID());
        return usuario;
    }
}