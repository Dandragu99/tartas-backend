package com.Ana.Bakery.producto.productoBaseService;

import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import com.Ana.Bakery.producto.productoBaseDTO.ProductoBaseDTO;
import com.Ana.Bakery.producto.productoModel.ProductoBase;
import com.Ana.Bakery.producto.productoRepository.ProductoBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoBaseService {

    private final ProductoBaseRepository productoBaseRepository;

    // Mapper entidad -> DTO
    private ProductoBaseDTO toDTO(ProductoBase p) {
        return new ProductoBaseDTO(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecioBase(),
                p.getImgPaso1(),
                p.getImgPaso2(),
                p.getImgPaso3(),
                p.getImgPaso4(),
                p.getImgPaso5(),
                p.getIngredientesCompatibles()
        );
    }

    public List<ProductoBaseDTO> getAll() {
        return productoBaseRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<ProductoBaseDTO> getById(Long id) {
        return productoBaseRepository.findById(id).map(this::toDTO);
    }

    public List<Ingrediente> getIngredientes(Long id) {
        return productoBaseRepository.findById(id)
                .map(ProductoBase::getIngredientesCompatibles)
                .orElse(List.of());
    }

    public ProductoBaseDTO create(ProductoBase producto) {
        return toDTO(productoBaseRepository.save(producto));
    }

    public Optional<ProductoBaseDTO> update(Long id, ProductoBase productoActualizado) {
        return productoBaseRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecioBase(productoActualizado.getPrecioBase());
                    producto.setImgPaso1(productoActualizado.getImgPaso1());
                    producto.setImgPaso2(productoActualizado.getImgPaso2());
                    producto.setImgPaso3(productoActualizado.getImgPaso3());
                    producto.setImgPaso4(productoActualizado.getImgPaso4());
                    producto.setImgPaso5(productoActualizado.getImgPaso5());
                    return toDTO(productoBaseRepository.save(producto));
                });
    }

    public boolean delete(Long id) {
        return productoBaseRepository.findById(id)
                .map(p -> {
                    productoBaseRepository.delete(p);
                    return true;
                })
                .orElse(false);
    }
}