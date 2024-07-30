package org.marroquin.apiservlet.webapp.headers.service;

import org.marroquin.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"notebook","computacion",175000),
                new Producto(2L,"mesa escritorio","oficina",100000),
                new Producto(3L,"Teclado mecanico", "computacion",4000));
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(p->p.getId().equals(id)).findAny();
    }

    /*
    @Override
    public Optional<Producto> buscarProducto(String nombre) {

        return (listar().stream().filter(p -> {
            if (nombre == null || nombre.isBlank()){
                return false;
            }
            return p.getNombre().contains(nombre);
        }).findFirst()
        );
    }
     */
}
