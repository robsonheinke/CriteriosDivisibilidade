package heinke.criteriosdivisibilidade.model;

import java.util.ArrayList;

import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;

/**
 * Created by heinke on 06/10/17.
 */

public class Formulario {
    private String escolaridade;
    private String aparencia;
    private String usabilidade;
    private String estrutura;
    private String recursos;
    private String dificuldade;
    private ArrayList<String> criterioDificuldade;
    private String enfoque;
    private String beneficiaUsuario;
    private String recomendaria;
    private String sugestao;
    private String idUsuario;

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getAparencia() {
        return aparencia;
    }

    public void setAparencia(String aparencia) {
        this.aparencia = aparencia;
    }

    public String getUsabilidade() {
        return usabilidade;
    }

    public void setUsabilidade(String usabilidade) {
        this.usabilidade = usabilidade;
    }

    public String getEstrutura() {
        return estrutura;
    }

    public void setEstrutura(String estrutura) {
        this.estrutura = estrutura;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public ArrayList<String> getCriterioDificuldade() {
        return criterioDificuldade;
    }

    public void setCriterioDificuldade(ArrayList<String> criterioDificuldade) {
        this.criterioDificuldade = criterioDificuldade;
    }

    public String getEnfoque() {
        return enfoque;
    }

    public void setEnfoque(String enfoque) {
        this.enfoque = enfoque;
    }

    public String getBeneficiaUsuario() {
        return beneficiaUsuario;
    }

    public void setBeneficiaUsuario(String beneficiaUsuario) {
        this.beneficiaUsuario = beneficiaUsuario;
    }

    public String getRecomendaria() {
        return recomendaria;
    }

    public void setRecomendaria(String recomendaria) {
        this.recomendaria = recomendaria;
    }

    public String getSugestao() {
        return sugestao;
    }

    public void setSugestao(String sugestao) {
        this.sugestao = sugestao;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString(){
        String string = "ID: " + this.idUsuario + "\n" +
                "ESCOLARIDADE: " + this.escolaridade + "\n" +
                "APARENCIA: "+ this.aparencia + "\n" +
                "USABILIDADE: " + this.usabilidade + "\n" +
                "ESTRUTURA: " + this.estrutura + "\n" +
                "RECURSOS: " + this.recursos + "\n" +
                "DIFICULDADE: " + this.dificuldade + "\n" +
                "NIVEL: ";
        int size = this.criterioDificuldade.size() - 1;
        int i = 0;
        for(String a: this.criterioDificuldade){
            if(i == size){
                string += a;
            }
            else{
                string += (a + ",");
            }
            i++;
        }
        string += "\nENFOQUE: " + this.enfoque + "\n" +
                "BENEFICIA USUARIO: " + this.beneficiaUsuario + "\n" +
                "RECOMENDARIA: " + this.recomendaria + "\n" +
                "SUGESTAO: " + this.sugestao;
        return string;
    }
}
