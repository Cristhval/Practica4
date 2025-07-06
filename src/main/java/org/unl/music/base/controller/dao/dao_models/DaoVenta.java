package org.unl.music.base.controller.dao.dao_models;

import org.unl.music.base.controller.dao.AdapterDao;
import org.unl.music.base.models.Album;
import org.unl.music.base.models.Venta;


public class DaoVenta extends AdapterDao<Venta> {
    private Venta obj;

    public DaoVenta() {
        super(Venta.class);

    }

    public Venta getObj() {
        if (obj == null)
            this.obj = new Venta();
        return this.obj;
    }

    public void setObj(Venta obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
        } catch (Exception e) {

            return false;

        }
    }

    public Boolean update(Integer pos) {
        try {
            this.update(obj, pos);
            return true;
        } catch (Exception e) {

            return false;

        }
    }

    public Boolean updateId(Integer id) {
        try {
            this.update(obj, id);
            return true;
        } catch (Exception e) {

            return false;

        }
    }


}
