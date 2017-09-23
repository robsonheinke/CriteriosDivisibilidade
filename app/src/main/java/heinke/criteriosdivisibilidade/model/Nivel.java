package heinke.criteriosdivisibilidade.model;

import java.util.ArrayList;

/**
 * Created by heinke on 19/09/17.
 */

public class Nivel {
    private String id;
    private String ordem;
    private String nivel;
    private String criterio;

    public Nivel(){}

    public Nivel(String _ordem, String _nivel, String _criterio){
        this.nivel = _nivel;
        this.ordem = _ordem;
        this.criterio = _criterio;
    }

    public Nivel(String _id, String _ordem, String _nivel, String _criterio){
        this.id = _id;
        this.ordem = _ordem;
        this.nivel = _nivel;
        this.criterio = _criterio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String toString(){
        return "ordem: "+this.ordem + " " + this.nivel + "\tnivel = " +this.nivel ;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public ArrayList<Nivel> adicionaNiveis(){
        int aux = 2;
        String ordem = "Localize todos os números divisíveis por ";
        ArrayList<Nivel> niveis = new ArrayList<>();

        for(;aux < 13;aux++){
            niveis.add(new Nivel(ordem+aux+".",String.valueOf(aux-1),String.valueOf(aux)));
        }
        return niveis;
    }
}
