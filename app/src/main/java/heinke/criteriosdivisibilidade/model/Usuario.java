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

    public Usuario(String firebase, String _nome, int _nivel, String _pontos){
        this.idFirebase = firebase;
        this.nivel = String.valueOf(_nivel);
        this.nome = _nome;
        this.pontos = _pontos;
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

    public String toString(){
        return "Nome: " + nome +"\temail: "+ email +"\nURL imagem: " + imagem + "\nnivel: " + nivel;
    }

}
