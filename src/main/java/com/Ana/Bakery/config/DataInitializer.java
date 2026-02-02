package com.Ana.Bakery.config;

import com.Ana.Bakery.model.CategoriaIngrediente;
import com.Ana.Bakery.model.Ingrediente;
import com.Ana.Bakery.model.ProductoBase;
import com.Ana.Bakery.repository.IngredienteRepository;
import com.Ana.Bakery.repository.ProductoBaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(IngredienteRepository ingRepo, ProductoBaseRepository prodRepo) {
        return args -> {
            if (ingRepo.count() == 0) {
                // Creamos algunos ingredientes
                ingRepo.save(new Ingrediente(null,"Bizcocho de Vainilla", CategoriaIngrediente.BIZCOCHO, 0.0, true));
                ingRepo.save(new Ingrediente(null,"Bizcocho de Chocolate", CategoriaIngrediente.BIZCOCHO, 2.5, true));
                ingRepo.save(new Ingrediente(null,"Relleno de Crema Pastelera", CategoriaIngrediente.RELLENO, 1.5, true));
                ingRepo.save(new Ingrediente(null,"Relleno de Ganache de Chocolate", CategoriaIngrediente.RELLENO, 3.0, true));
                ingRepo.save(new Ingrediente(null,"Topping Frutos Rojos", CategoriaIngrediente.EXTRA, 4.0, true));
            }

            if (prodRepo.count() == 0) {

                ProductoBase tartaRedVelvet = new ProductoBase();
                tartaRedVelvet.setNombre("Línea Red Velvet");
                tartaRedVelvet.setDescripcion("Nuestra tarta más elegante y suave.");
                tartaRedVelvet.setPrecioBase(25.0);

                tartaRedVelvet.setImgPaso1("assets/img/red-velvet-1.jpeg");
                tartaRedVelvet.setImgPaso2("assets/img/red-velvet-2.jpeg");
                tartaRedVelvet.setImgPaso3("assets/img/red-velvet-3.jpeg");
                prodRepo.save(tartaRedVelvet);

                ProductoBase cheescake = new ProductoBase();
                cheescake.setNombre("Cheescake");
                cheescake.setDescripcion("La tarta de queso más deliciosa.");
                cheescake.setPrecioBase(20.0);

                cheescake.setImgPaso1("assets/img/cheescake-1.png");
                cheescake.setImgPaso2("assets/img/cheescake-2.png");
                cheescake.setImgPaso3("assets/img/cheescake-3.png");
                prodRepo.save(cheescake);

                ProductoBase lemonCake = new ProductoBase();
                lemonCake.setNombre("Tarta de limón");
                lemonCake.setDescripcion("Sorprende a los tuyos con el mejor sabor.");
                lemonCake.setPrecioBase(15.0);

                lemonCake.setImgPaso1("assets/img/lemonCake-1.jpg");
                lemonCake.setImgPaso2("assets/img/lemonCake-2.jpg");
                lemonCake.setImgPaso3("assets/img/lemonCake-3.jpg");
                prodRepo.save(lemonCake);



                ProductoBase chocolate = new ProductoBase();
                chocolate.setNombre("Tarta de Chocolate");
                chocolate.setDescripcion("Nuestra tarta más dulce.");
                chocolate.setPrecioBase(25.0);

                chocolate.setImgPaso1("assets/img/chocolate-1.jpeg");
                chocolate.setImgPaso2("assets/img/chocolate-2.jpeg");
                chocolate.setImgPaso3("assets/img/chocolate-3.jpeg");
                prodRepo.save(chocolate);

                ProductoBase whiskey = new ProductoBase();
                whiskey.setNombre("Tarta de Whiskey");
                whiskey.setDescripcion("La tarta de whiskey más alcoholica.");
                whiskey.setPrecioBase(35.0);

                whiskey.setImgPaso1("assets/img/whiskey-1.png");
                whiskey.setImgPaso2("assets/img/whiskey-2.png");
                whiskey.setImgPaso3("assets/img/whiskey-3.png");
                prodRepo.save(whiskey);

                ProductoBase vainilla = new ProductoBase();
                vainilla.setNombre("Tarta de vainilla");
                vainilla.setDescripcion("Sorprende a los tuyos con el mejor sabor de la vainilla.");
                vainilla.setPrecioBase(15.0);

                vainilla.setImgPaso1("assets/img/vainilla-1.jpg");
                vainilla.setImgPaso2("assets/img/vainilla-2.jpg");
                vainilla.setImgPaso3("assets/img/vainilla-3.jpg");
                prodRepo.save(vainilla);


            }

            System.out.println("¡Base de datos inicializada con éxito!");
        };
    }
}