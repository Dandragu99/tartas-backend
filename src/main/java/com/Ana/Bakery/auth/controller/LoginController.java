package com.Ana.Bakery.auth.controller;


import com.Ana.Bakery.auth.dto.AuthResponse;
import com.Ana.Bakery.auth.dto.LoginRequest;
import com.Ana.Bakery.auth.dto.RegisterRequest;
import com.Ana.Bakery.auth.service.JwtService;
import com.Ana.Bakery.usuario.usuarioModel.Usuario;
import com.Ana.Bakery.usuario.usuarioRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Password incorrecta");
        }

        String token = jwtService.generateToken(usuario);
        return ResponseEntity.ok(new AuthResponse(token));

    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setTelefono(request.getTelefono());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol("ROLE_CLIENTE");

        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
