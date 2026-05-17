package com.Ana.Bakery.usuario.ususarioService;

import com.Ana.Bakery.usuario.dto.UpdatePerfilDto;
import com.Ana.Bakery.usuario.usuarioModel.Usuario;
import com.Ana.Bakery.usuario.usuarioRepository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario actualizarPerfil(Long id, UpdatePerfilDto dto) {
        Usuario usuario = findById(id);
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setTelefono(dto.getTelefono());
        return usuarioRepository.save(usuario);
    }
}
