package org.unl.music.base.controller.dao.dao_models;

import org.unl.music.base.controller.dao.AdapterDao;
import org.unl.music.base.models.Album;
import org.unl.music.base.models.Usuario;


public class DaoUsuario extends AdapterDao<Usuario> {
    private Usuario obj;

    public DaoUsuario() {
        super(Usuario.class);

    }

    public Usuario getObj() {
        if (obj == null)
            this.obj = new Usuario();
        return this.obj;
    }

    public void setObj(Usuario obj) {
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
