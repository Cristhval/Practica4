package org.unl.music.base.service;

import jakarta.validation.constraints.NotEmpty;
import org.unl.music.base.controller.dao.dao_models.DaoUsuario;
import org.unl.music.base.models.Usuario;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;

public class UsuarioService {
    DaoUsuario daoUsuario;
    public UsuarioService(){
        daoUsuario = new DaoUsuario();
    }

    public void createUsuario( @NotEmpty String nombre, @NotEmpty String apellido,
                              @NotEmpty String correo, @NotEmpty String cedula)throws Exception {
        daoUsuario.getObj().setNombre(nombre);
        daoUsuario.getObj().setApellido(apellido);
        daoUsuario.getObj().setCedula(cedula);
        daoUsuario.getObj().setCorreo(correo);

        if (!daoUsuario.save()){
            throw new Exception("NO se pudo crear");
        }
    }

    public void updateUsuario(Integer id, @NotEmpty String nombre, @NotEmpty String apellido,
                              @NotEmpty String correo, @NotEmpty String cedula)throws Exception{
        daoUsuario.setObj(daoUsuario.listAll().get(id-1));
        daoUsuario.getObj().setId(id);
        daoUsuario.getObj().setNombre(nombre);
        daoUsuario.getObj().setApellido(apellido);
        daoUsuario.getObj().setCedula(cedula);
        daoUsuario.getObj().setCorreo(correo);

        if (!daoUsuario.update(id-1)){
            throw new Exception("NO se pudo actualizar");
        }
    }

    public List<Usuario> listAll(){

        return Arrays.stream(daoUsuario.listAll().toArray()).toList();
    }

    public List<Usuario> list(Pageable pageable){

        return Arrays.asList(daoUsuario.listAll().toArray());
    }



}
