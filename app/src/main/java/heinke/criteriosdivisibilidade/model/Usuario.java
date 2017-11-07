package heinke.criteriosdivisibilidade.model;

import java.io.Serializable;

/**
 * Created by heinke on 03/09/17.
 */

public class Usuario implements Serializable {

    private String nome;
    private String email;
    private String imagem;
    private String pontos;
    private String idSQl;
    private String idFirebase;
    private String nivel;
    private String pesquisa = "false";

    public Usuario(){}

    public Usuario(String SQL, String firebase, String _nome, String _email, String _imagem, String _nivel, String _pontos) {
        this.nome = _nome;
        this.email = _email;
        this.imagem = _imagem;
        this.idSQl = SQL;
        this.idFirebase = firebase;
        this.pontos = _pontos;
        this.nivel = _nivel;
    }

    public Usuario(String idSQl, String idFirebase, String nome, String email, String imagem, String nivel, String pontos, String pesquisa) {
        this.nome = nome;
        this.email = email;
        this.imagem = imagem;
        this.pontos = pontos;
        this.idSQl = idSQl;
        this.idFirebase = idFirebase;
        this.nivel = nivel;
        this.pesquisa = pesquisa;
    }

    public Usuario(String firebase, String _nome, String _email, String _imagem, String _nivel, String _pontos) {
        this.nome = _nome;
        this.email = _email;
        this.imagem = _imagem;
        this.idFirebase = firebase;
        this.pontos = _pontos;
        this.nivel = _nivel;
    }

    public Usuario(String SQL, String firebase, String _nome, String _email, String _imagem) {
        this.nome = _nome;
        this.email = _email;
        this.imagem = _imagem;
        this.idSQl = SQL;
        this.idFirebase = firebase;
    }

    public Usuario(String firebase,String _nome, String _email, String _imagem){
        this.nome = _nome;
        this.email = _email;
        this.imagem = _imagem;
        this.idFirebase = firebase;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public String getIdSQl() { return idSQl; }

    public void setIdSQl(String idSQl) { this.idSQl = idSQl; }

    public String getIdFirebase() { return idFirebase; }

    public void setIdFirebase(String idFirebase) { this.idFirebase = idFirebase; }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String toString(){
        return "Nome: " + nome +"\temail: "+ email +"\nURL imagem: " + imagem + "\nnivel: " + nivel;
    }

}
