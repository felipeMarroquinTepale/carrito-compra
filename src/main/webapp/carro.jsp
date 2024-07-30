<%@page contentType="text/html" pageEncoding="UTF-8" import="org.marroquin.apiservlet.webapp.headers.models.*"%>
<%
Carro carro = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
    <h1>Carro de compras</h1>
    <%if(carro == null || carro.getItems().isEmpty()){%>
        <p>Lo sentimos no hay productos en el carro de compras!</p>
    <%}else{%>
 <form name="formcarro" action="<%=request.getContextPath()%>/actualizar-carro" method="post">
        <table>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Total</th>
                <th>Borrar</th>
            </tr>
            <%for(ItemCarro item: carro.getItems()){%>
                <tr>
                    <td><%=item.getProducto().getId()%></td>
                    <td><%=item.getProducto().getNombre()%></td>
                    <td><%=item.getProducto().getPrecio()%></td>
                    <td><%=item.getCantidad()%></td>
                    <td><%=item.getImporte()%></td>
                    <td><input type="checkbox" value="<%=item.getProducto().getId()%>" name="deleteProductos" /></td>
                </tr>
            <%}%>

            <tr>
                <td colspan="4" style="text-align: right">Total: <td>
                <td><%=carro.getTotal()%></td>
            </tr>
        </table>
            <a href="javascript:document.formcarro.submit();">Actualizar</a>
    <%}%>
    <p><a href="<%=request.getContextPath()%>/productos">Seguir comprando</a></p>
    <p><a href="<%=request.getContextPath()%>/index.html">Volver</a></p>
</body>
</html>