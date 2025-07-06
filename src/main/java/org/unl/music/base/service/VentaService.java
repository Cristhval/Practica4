package org.unl.music.base.service;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.dynalink.linker.LinkerServices;
import org.unl.music.base.controller.dao.dao_models.DaoVenta;
import org.unl.music.base.models.MetodoPagoEnum;
import org.unl.music.base.models.Venta;
import org.w3c.dom.stylesheets.LinkStyle;

import java.awt.print.Pageable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class VentaService {
    DaoVenta daoVenta;

    private static final Double DESCUENTO = 0.005;


    public VentaService(){
        daoVenta = new DaoVenta();
    }

    public void createVenta(@NotNull Date fecha, @NotNull Double precio, @NotEmpty String nombreProducto, Integer cantidad, @NotEmpty String tipo)throws Exception{
        daoVenta.getObj().setFecha(fecha);
        daoVenta.getObj().setNombreProducto(nombreProducto);
        daoVenta.getObj().setCantidad(cantidad);
        daoVenta.getObj().setTipo(MetodoPagoEnum.valueOf(tipo));
        daoVenta.getObj().setPrecio(calcularPrecio(precio,cantidad,MetodoPagoEnum.valueOf(tipo)));
        if (!daoVenta.save()){
            throw new Exception("No se pudo crear la venta");
        }

    }

    public void updateVenta(Integer id,@NotNull Double precio, @NotNull Date fecha, @NotEmpty String nombreProducto, Integer cantidad, @NotEmpty String tipo)throws Exception{
        daoVenta.setObj(daoVenta.listAll().get(id-1));
        daoVenta.getObj().setFecha(fecha);
        daoVenta.getObj().setNombreProducto(nombreProducto);
        daoVenta.getObj().setCantidad(cantidad);
        daoVenta.getObj().setTipo(MetodoPagoEnum.valueOf(tipo));
        daoVenta.getObj().setPrecio(calcularPrecio(precio,cantidad,MetodoPagoEnum.valueOf(tipo)));
        if (!daoVenta.update(id-1)){
            throw new Exception("No se pudo actualizar la venta");
        }

    }

    public List<Venta> listAll(){
        return Arrays.stream(daoVenta.listAll().toArray()).toList();
    }

    public List<Venta> list(Pageable pageable){
        return Arrays.asList(daoVenta.listAll().toArray());
    }

    private Double calcularPrecio(Double precio, Integer cantidad, MetodoPagoEnum tipo){
        switch (tipo){
            case EFECTIVO -> precio = precio-(precio*0.5);
            case DEBITO ->  precio = precio-(precio*0.3);
        }
        if(cantidad >= 2){
            precio = precio-(precio*0.005);
        }
        return precio;
    }

    public static void main(String[] args) {
        VentaService da = new VentaService();
        try {
            da.createVenta(Date.valueOf(LocalDate.now()),15.05,"Llanta",4,"EFECTIVO");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
