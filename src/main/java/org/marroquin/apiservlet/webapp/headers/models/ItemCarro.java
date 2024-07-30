package org.marroquin.apiservlet.webapp.headers.models;

import java.util.Objects;

public class ItemCarro {
    private int cantidad;
    private Producto producto; //define una instancia "producto" que es un objeto de la clase Producto

    public ItemCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    // Este método se usa para comparar si dos objetos son iguales en términos de su contenido
    public boolean equals(Object o) {
        //Si el objeto actual es igual al objeto pasado como parametro "o", retorna un verdadero
        if (this == o) return true;
        //Verifica si el objeto pasado como parametro "o" es nulo o si el de una clase diferente retorna falso
        if (o == null || getClass() != o.getClass()) return false;
        //el objeto o lo convierte al tipo ItemCarro.
        ItemCarro itemCarro = (ItemCarro) o;

        //es usado para comparar dos instancias de ItemCarro para determinar si son iguales por su id y su nombre
        return Objects.equals(producto.getId(), itemCarro.producto.getId())
                && Objects.equals(producto.getNombre(), itemCarro.producto.getNombre());
    }

    //Scamos el importe. Es cantidad por el precio del producto
    public int getImporte(){
        return cantidad * producto.getPrecio();
    }
}
