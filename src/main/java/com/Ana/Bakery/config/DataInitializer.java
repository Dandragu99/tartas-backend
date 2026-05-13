package com.Ana.Bakery.config;

import com.Ana.Bakery.ingrediente.categoriaIngrediente.CategoriaIngrediente;
import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import com.Ana.Bakery.pedido.estadoPedido.EstadoPedido;
import com.Ana.Bakery.pedido.pedidoModel.Pedido;
import com.Ana.Bakery.pedido.pedidoRepository.PedidoRepository;
import com.Ana.Bakery.producto.productoModel.ProductoBase;
import com.Ana.Bakery.usuario.usuarioModel.Usuario;
import com.Ana.Bakery.ingrediente.ingredienteRepository.IngredienteRepository;
import com.Ana.Bakery.producto.productoRepository.ProductoBaseRepository;
import com.Ana.Bakery.usuario.usuarioRepository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    @Order(1)
    CommandLineRunner initUsers(UsuarioRepository usuarioRepository,
                                PasswordEncoder passwordEncoder){
        return args -> {
            if (usuarioRepository.count() == 0){

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@bakery.com");
                admin.setNombreCompleto("Administrador");
                admin.setRol("ROLE_ADMIN");

                usuarioRepository.save(admin);


                Usuario cliente = new Usuario();
                cliente.setUsername("cliente");
                cliente.setPassword(passwordEncoder.encode("cliente123"));
                cliente.setEmail("cliente@bakery.com");
                cliente.setNombreCompleto("Cliente Normal");
                cliente.setRol("ROLE_CLIENTE");

                usuarioRepository.save(cliente);
            }
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner initDatabase(IngredienteRepository ingRepo, ProductoBaseRepository prodRepo) {
        return args -> {
            if (ingRepo.count() == 0) {

                // Bizcochos
                Ingrediente bizVainilla = ingRepo.save(Ingrediente.builder().nombre("Bizcocho de Vainilla").tipo(CategoriaIngrediente.BIZCOCHO).precioAdicional(0.0).disponible(true).build());
                Ingrediente bizChocolate = ingRepo.save(Ingrediente.builder().nombre("Bizcocho de Chocolate").tipo(CategoriaIngrediente.BIZCOCHO).precioAdicional(2.5).disponible(true).build());

                // Rellenos
                Ingrediente relCrema     = ingRepo.save(Ingrediente.builder().nombre("Relleno de Crema Pastelera").tipo(CategoriaIngrediente.RELLENO).precioAdicional(1.5).disponible(true).build());
                Ingrediente relChocolate = ingRepo.save(Ingrediente.builder().nombre("Relleno de Ganache de Chocolate").tipo(CategoriaIngrediente.RELLENO).precioAdicional(3.0).disponible(true).build());
                Ingrediente relFresa     = ingRepo.save(Ingrediente.builder().nombre("Relleno de Fresa").tipo(CategoriaIngrediente.RELLENO).precioAdicional(4.0).disponible(true).build());
                Ingrediente relVainilla  = ingRepo.save(Ingrediente.builder().nombre("Relleno de Vainilla").tipo(CategoriaIngrediente.RELLENO).precioAdicional(1.5).disponible(true).build());
                Ingrediente relLimon     = ingRepo.save(Ingrediente.builder().nombre("Relleno de Limón").tipo(CategoriaIngrediente.RELLENO).precioAdicional(2.0).disponible(true).build());

                // Coberturas
                Ingrediente cobChocolate  = ingRepo.save(Ingrediente.builder().nombre("Cobertura Chocolate").tipo(CategoriaIngrediente.COBERTURA).precioAdicional(2.0).disponible(true).build());
                Ingrediente cobRedVelvet  = ingRepo.save(Ingrediente.builder().nombre("Cobertura Red Velvet").tipo(CategoriaIngrediente.COBERTURA).precioAdicional(3.5).disponible(true).build());
                Ingrediente cobCheesecake = ingRepo.save(Ingrediente.builder().nombre("Cobertura Cheesecake").tipo(CategoriaIngrediente.COBERTURA).precioAdicional(2.5).disponible(true).build());
                Ingrediente cobLimon      = ingRepo.save(Ingrediente.builder().nombre("Cobertura Limón").tipo(CategoriaIngrediente.COBERTURA).precioAdicional(1.5).disponible(true).build());

                // Extras
                Ingrediente extFrutos    = ingRepo.save(Ingrediente.builder().nombre("Topping Frutos Rojos").tipo(CategoriaIngrediente.EXTRA).precioAdicional(4.0).disponible(true).build());
                Ingrediente extChocolate = ingRepo.save(Ingrediente.builder().nombre("Topping Chocolate").tipo(CategoriaIngrediente.EXTRA).precioAdicional(3.0).disponible(true).build());
                Ingrediente extLimon     = ingRepo.save(Ingrediente.builder().nombre("Topping Limón").tipo(CategoriaIngrediente.EXTRA).precioAdicional(2.0).disponible(true).build());

                if (prodRepo.count() == 0) {

                    // Red Velvet → bizcocho vainilla/chocolate, relleno crema/fresa, cobertura red velvet, extras frutos/chocolate
                    ProductoBase redVelvet = new ProductoBase();
                    redVelvet.setNombre("Línea Red Velvet");
                    redVelvet.setDescripcion("Nuestra tarta más elegante y suave.");
                    redVelvet.setPrecioBase(25.0);
                    redVelvet.setImgPaso1("assets/img/red-velvet-1.png");
                    redVelvet.setImgPaso2("assets/img/red-velvet-2.png");
                    redVelvet.setImgPaso3("assets/img/red-velvet-3.png");
                    redVelvet.setImgPaso4("assets/img/red-velvet-4.png");
                    redVelvet.setImgPaso5("assets/img/red-velvet-5.png");

                    redVelvet.setIngredientesCompatibles(List.of(bizVainilla, bizChocolate, relCrema, relFresa, cobRedVelvet, extFrutos, extChocolate));
                    prodRepo.save(redVelvet);

                    // Cheesecake → bizcocho vainilla, relleno crema/vainilla, cobertura cheesecake, extras frutos/limón
                    ProductoBase cheesecake = new ProductoBase();
                    cheesecake.setNombre("Cheescake");
                    cheesecake.setDescripcion("La tarta de queso más deliciosa.");
                    cheesecake.setPrecioBase(20.0);
                    cheesecake.setImgPaso1("assets/img/cheescake-1.png");
                    cheesecake.setImgPaso2("assets/img/cheescake-2.png");
                    cheesecake.setImgPaso3("assets/img/cheescake-3.png");
                    cheesecake.setIngredientesCompatibles(List.of(bizVainilla, relCrema, relVainilla, cobCheesecake, extFrutos, extLimon));
                    prodRepo.save(cheesecake);

                    // Limón → bizcocho vainilla, relleno limón/crema, cobertura limón, extras limón/frutos
                    ProductoBase limon = new ProductoBase();
                    limon.setNombre("Tarta de limón");
                    limon.setDescripcion("Sorprende a los tuyos con el mejor sabor.");
                    limon.setPrecioBase(15.0);
                    limon.setImgPaso1("assets/img/tarta-limon-1.png");
                    limon.setImgPaso2("assets/img/tarta-limon-2.png");
                    limon.setImgPaso3("assets/img/tarta-limon-3.png");
                    limon.setIngredientesCompatibles(List.of(bizVainilla, relLimon, relCrema, cobLimon, extLimon, extFrutos));
                    prodRepo.save(limon);

                    // Chocolate → bizcocho chocolate/vainilla, relleno chocolate/crema, cobertura chocolate, extras chocolate/frutos
                    ProductoBase chocolate = new ProductoBase();
                    chocolate.setNombre("Tarta de Chocolate");
                    chocolate.setDescripcion("Nuestra tarta más dulce.");
                    chocolate.setPrecioBase(25.0);
                    chocolate.setImgPaso1("assets/img/chocolate-1.png");
                    chocolate.setImgPaso2("assets/img/chocolate-2.jpeg");
                    chocolate.setImgPaso3("assets/img/chocolate-3.png");
                    chocolate.setIngredientesCompatibles(List.of(bizChocolate, bizVainilla, relChocolate, relCrema, cobChocolate, extChocolate, extFrutos));
                    prodRepo.save(chocolate);

                    ProductoBase carrot = new ProductoBase();
                    carrot.setNombre("Carrot Cake");
                    carrot.setDescripcion("Bizcocho jugoso de zanahoria con frosting de queso.");
                    carrot.setPrecioBase(28.0);
                    carrot.setImgPaso1("assets/img/carrot-1.png");
                    carrot.setImgPaso2("assets/img/carrot-2.png");
                    carrot.setImgPaso3("assets/img/carrot-3.png");
                    carrot.setIngredientesCompatibles(List.of(
                            bizVainilla,
                            relCrema,
                            relVainilla,
                            cobCheesecake,
                            extFrutos
                    ));
                    prodRepo.save(carrot);

                    ProductoBase fresa = new ProductoBase();
                    fresa.setNombre("Tarta de Fresa");
                    fresa.setDescripcion("Clásica tarta de fresas con nata fresca.");
                    fresa.setPrecioBase(22.0);
                    fresa.setImgPaso1("assets/img/fresa-1.png");
                    fresa.setImgPaso2("assets/img/fresa-2.png");
                    fresa.setImgPaso3("assets/img/fresa-3.png");
                    fresa.setIngredientesCompatibles(List.of(
                            bizVainilla,
                            relFresa,
                            relCrema,
                            cobCheesecake,
                            extFrutos
                    ));
                    prodRepo.save(fresa);
                }
            }


            System.out.println("¡Base de datos inicializada con éxito!");
        };
    }

    @Bean
    @Order(3)
    CommandLineRunner initPedidos(UsuarioRepository usuarioRepository,
                                  ProductoBaseRepository prodRepo,
                                  PedidoRepository pedidoRepository,
                                  PasswordEncoder passwordEncoder) {
        return args -> {
            // Usuarios extra
            if (usuarioRepository.count() == 2) {
                Usuario u1 = new Usuario();
                u1.setUsername("maria");
                u1.setPassword(passwordEncoder.encode("maria123"));
                u1.setEmail("maria@bakery.com");
                u1.setNombreCompleto("María García");
                u1.setRol("ROLE_CLIENTE");
                usuarioRepository.save(u1);

                Usuario u2 = new Usuario();
                u2.setUsername("carlos");
                u2.setPassword(passwordEncoder.encode("carlos123"));
                u2.setEmail("carlos@bakery.com");
                u2.setNombreCompleto("Carlos López");
                u2.setRol("ROLE_CLIENTE");
                usuarioRepository.save(u2);
            }

            if (pedidoRepository.count() == 0 && prodRepo.count() > 0) {
                Usuario cliente = usuarioRepository.findByUsername("cliente")
                        .orElseThrow();
                Usuario maria = usuarioRepository.findByUsername("maria")
                        .orElseThrow();

                List<ProductoBase> productos = prodRepo.findAll();
                ProductoBase redVelvet  = productos.get(0);
                ProductoBase cheesecake = productos.get(1);
                ProductoBase limon      = productos.get(2);
                ProductoBase chocolate  = productos.get(3);

                // cliente — 4 pedidos en distintos estados para ver el timeline completo
                Pedido p1 = new Pedido();
                p1.setUsuario(cliente);
                p1.setProductoBase(redVelvet);
                p1.setIngredientesSeleccionados(List.of());
                p1.setEstado(EstadoPedido.ENTREGADO);
                p1.setPrecioTotal(32.5);
                p1.setFechaEntrega(LocalDateTime.now().minusDays(10));
                pedidoRepository.save(p1);

                Pedido p2 = new Pedido();
                p2.setUsuario(cliente);
                p2.setProductoBase(cheesecake);
                p2.setIngredientesSeleccionados(List.of());
                p2.setEstado(EstadoPedido.ENVIADO);
                p2.setPrecioTotal(24.0);
                p2.setFechaEntrega(LocalDateTime.now().plusDays(1));
                pedidoRepository.save(p2);

                Pedido p3 = new Pedido();
                p3.setUsuario(cliente);
                p3.setProductoBase(limon);
                p3.setIngredientesSeleccionados(List.of());
                p3.setEstado(EstadoPedido.EN_PROCESO);
                p3.setPrecioTotal(17.0);
                p3.setFechaEntrega(LocalDateTime.now().plusDays(2));
                pedidoRepository.save(p3);

                Pedido p4 = new Pedido();
                p4.setUsuario(cliente);
                p4.setProductoBase(chocolate);
                p4.setIngredientesSeleccionados(List.of());
                p4.setEstado(EstadoPedido.PENDIENTE);
                p4.setPrecioTotal(28.0);
                p4.setFechaEntrega(LocalDateTime.now().plusDays(4));
                pedidoRepository.save(p4);

                // maria — 2 pedidos
                Pedido p5 = new Pedido();
                p5.setUsuario(maria);
                p5.setProductoBase(chocolate);
                p5.setIngredientesSeleccionados(List.of());
                p5.setEstado(EstadoPedido.EN_PROCESO);
                p5.setPrecioTotal(31.0);
                p5.setFechaEntrega(LocalDateTime.now().plusDays(3));
                pedidoRepository.save(p5);

                Pedido p6 = new Pedido();
                p6.setUsuario(maria);
                p6.setProductoBase(redVelvet);
                p6.setIngredientesSeleccionados(List.of());
                p6.setEstado(EstadoPedido.PENDIENTE);
                p6.setPrecioTotal(27.5);
                p6.setFechaEntrega(LocalDateTime.now().plusDays(5));
                pedidoRepository.save(p6);

                System.out.println("Pedidos de prueba creados.");
            }
        };
    }
}