package org.marroquin.apiservlet.webapp.headers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    //define una instancia items de tipo List<ItemCarro>
    private List<ItemCarro> items;

    public Carro(){
        this.items = new ArrayList<>();
    }

    public void addItemCarro(ItemCarro itemcarro){
        //si la lista de items ya contiene el itemcarro por agregar
        if(items.contains(itemcarro)){
            //Busca el itemcarro en la lista usando un Stream
           Optional<ItemCarro> optionalItemCarro = items.stream()
                   .filter(i -> i.equals(itemcarro))
                   .findAny();
           //Si se encuentra el itemcarro en la lista, incrementa su cantidad de producto
           if(optionalItemCarro.isPresent()){
               ItemCarro i = optionalItemCarro.get();
               i.setCantidad(i.getCantidad()+1);
           }
        }else {
            //Si el itemcarro por agregar no esta en la lista, simplemente lo agrega a la lista
            this.items.add(itemcarro);
        }
    }

    public List<ItemCarro> getItems(){
        return items;
    }
    //Calcula el total de los importes de todos los itemsCarro en la lista de items
    public int getTotal(){
        //La lista items se convierte en un Stream
        //Realiza un mapeo y convierte en un int cada metodo getImporte de cada itemCarro, para despues sumar todos lo importe de cada itemCarro
        return items.stream().mapToInt(ItemCarro:: getImporte).sum();
    }

    //eliminar productos
    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
              productoIds.forEach(productoId -> removeProducto(productoId));
        }
    }

    //Eliminar un producto especifico por id
    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    //actualiza la cantidad de un producto
    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    //encontrar producto por id
    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
