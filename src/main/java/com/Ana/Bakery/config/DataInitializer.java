package com.Ana.Bakery.config;

import com.Ana.Bakery.model.CategoriaIngrediente;
import com.Ana.Bakery.model.Ingrediente;
import com.Ana.Bakery.model.ProductoBase;
import com.Ana.Bakery.model.Usuario;
import com.Ana.Bakery.repository.IngredienteRepository;
import com.Ana.Bakery.repository.ProductoBaseRepository;
import com.Ana.Bakery.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
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
}