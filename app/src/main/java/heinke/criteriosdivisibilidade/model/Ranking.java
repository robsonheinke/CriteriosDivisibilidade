package heinke.criteriosdivisibilidade.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import heinke.criteriosdivisibilidade.repositorio.Database;

/**
 * Created by heinke on 26/09/17.
 */

public class Ranking {
    private Usuario usuario;
    private String pos;

    public Ranking (Usuario _usuario, String _pos){
        this.usuario = _usuario;
        this.pos = _pos;
    }

    public Ranking (){ }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario _usuario) {
        this.usuario = _usuario;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String _pos) {
        this.pos = _pos;
    }

    @Override
    public String toString(){
        return "pos: "+this.pos+"\tnome: "+ this.usuario.getNome() + "\tpontos: "+this.usuario.getPontos();
    }
}
