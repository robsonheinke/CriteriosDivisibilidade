package heinke.criteriosdivisibilidade.model;

/**
 * Created by heinke on 26/09/17.
 */

public class Ranking {
    private String id;
    private String idFirebase;
    private String nome;
    private String nivel;
    private String pontos;

    public Ranking(String idFirebase, String nome, String nivel, String pontos) {
        this.idFirebase = idFirebase;
        this.nome = nome;
        this.nivel = nivel;
        this.pontos = pontos;
    }

    public Ranking (){ }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFirebase() {
        return idFirebase;
    }

    public void setIdFirebase(String idFirebase) {
        this.idFirebase = idFirebase;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }
}
