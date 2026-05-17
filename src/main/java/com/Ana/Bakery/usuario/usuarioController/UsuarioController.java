package com.Ana.Bakery.usuario.usuarioController;

import com.Ana.Bakery.usuario.dto.UpdatePerfilDto;
import com.Ana.Bakery.usuario.usuarioModel.Usuario;
import com.Ana.Bakery.usuario.ususarioService.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getPerfil(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarPerfil(
            @PathVariable Long id,
            @RequestBody UpdatePerfilDto dto) {
        return ResponseEntity.ok(usuarioService.actualizarPerfil(id, dto));
    }
}
