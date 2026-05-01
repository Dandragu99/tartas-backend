package com.Ana.Bakery.ingrediente.ingredienteRepository;

import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    
}