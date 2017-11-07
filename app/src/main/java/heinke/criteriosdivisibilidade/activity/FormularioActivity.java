package heinke.criteriosdivisibilidade.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.Normalizer;
import java.util.ArrayList;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Formulario;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.repositorio.Database_Firebase;

public class FormularioActivity extends Utilitarios implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioButton ensinoFundamental, ensinoMedio, ensinoSuperior, posGraduacao, naoEstudante;
    private ImageButton aparenciaMRuim, aparenciaRuim, aparenciaRazoavel, aparenciaBom, aparenciaMBom;
    private ImageButton usabilidadeMRuim, usabilidadeRuim, usabilidadeRazoavel, usabilidadeBom, usabilidadeMBom;
    private ImageButton orgMRuim, orgRuim, orgRazoavel, orgBom, orgMBom;
    private ImageButton recursosMRuim, recursosRuim, recursosRazoavel, recursosBom, recursosMBom;
    private RadioButton muitoFacil, facil, medio, dificil, muitoDificil;
    private CheckBox nivel2, nivel3, nivel4, nivel5, nivel6, nivel7, nivel8, nivel9, nivel10, nivel11, nivel12;
    private RadioButton didaticoEducativo, ludicoDivertido;
    private RadioButton infantil, juvenil, adulto, todos;
    private RadioButton sim, nao;
    private EditText mensagem;
    private Button enviar;

    private Boolean apaMRuim = false
                   ,apaRuim = false
                   ,apaRazoavel = false
                   ,apaBom = false
                   ,apaMBom = false
                   ,usaMRuim = false
                   ,usaRuim = false
                   ,usaRazoavel = false
                   ,usaBom = false
                   ,usaMBom = false
                   ,orMRuim = false
                   ,orRuim = false
                   ,orRazoavel = false
                   ,orBom = false
                   ,orMBom = false
                   ,recMRuim = false
                   ,recRuim = false
                   ,recRazoavel = false
                   ,recBom = false
                   ,recMBom = false;

    private RadioGroup escolaridade, dificuldade, enfoque, aprendizado, recomendacao;

    private Formulario formulario;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        formulario = new Formulario();

        inicializaTela();

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");

        formulario.setIdUsuario(usuario.getIdFirebase());

        usuario.setPesquisa("true");

        alertAgradecimento(this);
    }

    private void inicializaTela(){
        ensinoFundamental = (RadioButton) findViewById(R.id.estudanteEF);
        ensinoMedio = (RadioButton) findViewById(R.id.estudanteEM);
        ensinoSuperior = (RadioButton) findViewById(R.id.estudanteES);
        posGraduacao = (RadioButton) findViewById(R.id.estudantePG);
        naoEstudante = (RadioButton) findViewById(R.id.estudadeNE);

        aparenciaMRuim = (ImageButton) findViewById(R.id.aparenciaMRUIM);
            aparenciaMRuim.setOnClickListener(this);
        aparenciaRuim = (ImageButton) findViewById(R.id.aparenciaRUIM);
            aparenciaRuim.setOnClickListener(this);
        aparenciaRazoavel = (ImageButton) findViewById(R.id.aparenciaRAZOAVEL);
            aparenciaRazoavel.setOnClickListener(this);
        aparenciaBom = (ImageButton) findViewById(R.id.aparenciaBOM);
            aparenciaBom.setOnClickListener(this);
        aparenciaMBom = (ImageButton) findViewById(R.id.aparenciaMBOM);
            aparenciaMBom.setOnClickListener(this);

        usabilidadeMRuim = (ImageButton) findViewById(R.id.usabilidadeMRUIM);
            usabilidadeMRuim.setOnClickListener(this);
        usabilidadeRuim = (ImageButton) findViewById(R.id.usabilidadeRUIM);
            usabilidadeRuim.setOnClickListener(this);
        usabilidadeRazoavel = (ImageButton) findViewById(R.id.usabilidadeRAZOAVEL);
            usabilidadeRazoavel.setOnClickListener(this);
        usabilidadeBom = (ImageButton) findViewById(R.id.usabilidadeBOM);
            usabilidadeBom.setOnClickListener(this);
        usabilidadeMBom = (ImageButton) findViewById(R.id.usabilidadeMBOM);
            usabilidadeMBom.setOnClickListener(this);

        orgMRuim = (ImageButton) findViewById(R.id.orgMRUIM);
            orgMRuim.setOnClickListener(this);
        orgRuim = (ImageButton) findViewById(R.id.orgRUIM);
            orgRuim.setOnClickListener(this);
        orgRazoavel = (ImageButton) findViewById(R.id.orgRAZOAVEL);
            orgRazoavel.setOnClickListener(this);
        orgBom = (ImageButton) findViewById(R.id.orgBOM);
            orgBom.setOnClickListener(this);
        orgMBom = (ImageButton) findViewById(R.id.orgMBOM);
            orgMBom.setOnClickListener(this);

        recursosMRuim = (ImageButton) findViewById(R.id.recursosMRUIM);
            recursosMRuim.setOnClickListener(this);
        recursosRuim = (ImageButton) findViewById(R.id.recursosRUIM);
            recursosRuim.setOnClickListener(this);
        recursosRazoavel = (ImageButton) findViewById(R.id.recursosRAZOAVEL);
            recursosRazoavel.setOnClickListener(this);
        recursosBom = (ImageButton) findViewById(R.id.recursosBOM);
            recursosBom.setOnClickListener(this);
        recursosMBom = (ImageButton) findViewById(R.id.recursosMBOM);
            recursosMBom.setOnClickListener(this);

        muitoFacil = (RadioButton) findViewById(R.id.MFACIL);
        facil = (RadioButton) findViewById(R.id.FACIL);
        medio = (RadioButton) findViewById(R.id.MEDIO);
        dificil = (RadioButton) findViewById(R.id.DIFICIL);
        muitoDificil = (RadioButton) findViewById(R.id.MDIFICIL);

        nivel2 = (CheckBox) findViewById(R.id.nvl2);
        nivel3 = (CheckBox) findViewById(R.id.nvl3);
        nivel4 = (CheckBox) findViewById(R.id.nvl4);
        nivel5 = (CheckBox) findViewById(R.id.nvl5);
        nivel6 = (CheckBox) findViewById(R.id.nvl6);
        nivel7 = (CheckBox) findViewById(R.id.nvl7);
        nivel8 = (CheckBox) findViewById(R.id.nvl8);
        nivel9 = (CheckBox) findViewById(R.id.nvl9);
        nivel10 = (CheckBox) findViewById(R.id.nvl10);
        nivel11 = (CheckBox) findViewById(R.id.nvl11);
        nivel12 = (CheckBox) findViewById(R.id.nvl12);

        didaticoEducativo = (RadioButton) findViewById(R.id.didatico);
        ludicoDivertido = (RadioButton) findViewById(R.id.ludico);

        infantil = (RadioButton) findViewById(R.id.infantil);
        juvenil = (RadioButton) findViewById(R.id.juvenil);
        adulto = (RadioButton) findViewById(R.id.adulto);
        todos = (RadioButton) findViewById(R.id.todos);

        sim = (RadioButton) findViewById(R.id.sim);
        nao = (RadioButton) findViewById(R.id.nao);

        mensagem = (EditText) findViewById(R.id.mensagem);

        escolaridade = (RadioGroup) findViewById(R.id.escolaridade);
            escolaridade.setOnCheckedChangeListener(this);
        dificuldade = (RadioGroup) findViewById(R.id.dificuldade);
            dificuldade.setOnCheckedChangeListener(this);
        enfoque = (RadioGroup) findViewById(R.id.enfoque);
            enfoque.setOnCheckedChangeListener(this);
        aprendizado = (RadioGroup) findViewById(R.id.aprendizado);
            aprendizado.setOnCheckedChangeListener(this);
        recomendacao = (RadioGroup) findViewById(R.id.recomedacao);
            recomendacao.setOnCheckedChangeListener(this);

        enviar = (Button) findViewById(R.id.enviar);
            enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            /*aparencia*/
            case R.id.aparenciaMRUIM:
                if(apaMRuim){
                    aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRuim.setImageResource(R.drawable.estrelacrua);
                    aparenciaRazoavel.setImageResource(R.drawable.estrelacrua);
                    aparenciaBom.setImageResource(R.drawable.estrelacrua);
                    aparenciaMBom.setImageResource(R.drawable.estrelacrua);
                    apaRuim = false;
                    apaRazoavel = false;
                    apaBom = false;
                    apaMBom = false;
                    formulario.setAparencia("1");
                }
                else{
                    aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                    apaMRuim = true;
                    formulario.setAparencia("1");
                }
                break;
            case R.id.aparenciaRUIM:
                if(apaRuim){
                    aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRazoavel.setImageResource(R.drawable.estrelacrua);
                    aparenciaBom.setImageResource(R.drawable.estrelacrua);
                    aparenciaMBom.setImageResource(R.drawable.estrelacrua);
                    apaRazoavel = false;
                    apaBom = false;
                    apaMBom = false;
                    formulario.setAparencia("2");
                }
                else {
                    aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRuim.setImageResource(R.drawable.estreladourada);
                    apaMRuim = true;
                    apaRuim = true;
                    formulario.setAparencia("2");
                }
                break;
            case R.id.aparenciaRAZOAVEL:
                if(apaRazoavel){
                    aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRazoavel.setImageResource(R.drawable.estreladourada);
                    aparenciaBom.setImageResource(R.drawable.estrelacrua);
                    aparenciaMBom.setImageResource(R.drawable.estrelacrua);
                    apaBom = false;
                    apaMBom = false;
                    formulario.setAparencia("3");
                }
                else {
                    aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRazoavel.setImageResource(R.drawable.estreladourada);
                    apaMRuim = true;
                    apaRuim = true;
                    apaRazoavel = true;
                    formulario.setAparencia("3");
                }
                break;
            case R.id.aparenciaBOM:
                if(apaBom){
                    aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRazoavel.setImageResource(R.drawable.estreladourada);
                    aparenciaBom.setImageResource(R.drawable.estreladourada);
                    aparenciaMBom.setImageResource(R.drawable.estrelacrua);
                    apaMBom = false;
                    formulario.setAparencia("4");
                }
                else {
                    aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRuim.setImageResource(R.drawable.estreladourada);
                    aparenciaRazoavel.setImageResource(R.drawable.estreladourada);
                    aparenciaBom.setImageResource(R.drawable.estreladourada);
                    apaMRuim = true;
                    apaRuim = true;
                    apaRazoavel = true;
                    apaBom = true;
                    formulario.setAparencia("4");
                }
                break;
            case R.id.aparenciaMBOM:
                aparenciaMRuim.setImageResource(R.drawable.estreladourada);
                aparenciaRuim.setImageResource(R.drawable.estreladourada);
                aparenciaRazoavel.setImageResource(R.drawable.estreladourada);
                aparenciaBom.setImageResource(R.drawable.estreladourada);
                aparenciaMBom.setImageResource(R.drawable.estreladourada);
                apaMRuim = true;
                apaRuim = true;
                apaRazoavel = true;
                apaBom = true;
                apaMBom = true;
                formulario.setAparencia("5");
                break;

            /*usabilidade*/
            case R.id.usabilidadeMRUIM:
                if(usaMRuim){
                    usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRuim.setImageResource(R.drawable.estrelacrua);
                    usabilidadeRazoavel.setImageResource(R.drawable.estrelacrua);
                    usabilidadeBom.setImageResource(R.drawable.estrelacrua);
                    usabilidadeMBom.setImageResource(R.drawable.estrelacrua);
                    usaRuim = false;
                    usaRazoavel = false;
                    usaBom = false;
                    usaMBom = false;
                    formulario.setUsabilidade("1");
                }
                else{
                    usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                    usaMRuim = true;
                    formulario.setUsabilidade("1");
                }
                break;
            case R.id.usabilidadeRUIM:
                if(usaRuim){
                    usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRazoavel.setImageResource(R.drawable.estrelacrua);
                    usabilidadeBom.setImageResource(R.drawable.estrelacrua);
                    usabilidadeMBom.setImageResource(R.drawable.estrelacrua);
                    usaRazoavel = false;
                    usaBom = false;
                    usaMBom = false;
                    formulario.setUsabilidade("2");
                }
                else {
                    usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRuim.setImageResource(R.drawable.estreladourada);
                    usaMRuim = true;
                    usaRuim = true;
                    formulario.setUsabilidade("2");
                }
                break;
            case R.id.usabilidadeRAZOAVEL:
                if(usaRazoavel){
                    usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRazoavel.setImageResource(R.drawable.estreladourada);
                    usabilidadeBom.setImageResource(R.drawable.estrelacrua);
                    usabilidadeMBom.setImageResource(R.drawable.estrelacrua);
                    usaBom = false;
                    usaMBom = false;
                    formulario.setUsabilidade("3");
                }
                else {
                    usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRazoavel.setImageResource(R.drawable.estreladourada);
                    usaMRuim = true;
                    usaRuim = true;
                    usaRazoavel = true;
                    formulario.setUsabilidade("3");
                }
                break;
            case R.id.usabilidadeBOM:
                if(usaBom){
                    usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRazoavel.setImageResource(R.drawable.estreladourada);
                    usabilidadeBom.setImageResource(R.drawable.estreladourada);
                    usabilidadeMBom.setImageResource(R.drawable.estrelacrua);
                    usaMBom = false;
                    formulario.setUsabilidade("4");
                }
                else {
                    usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRuim.setImageResource(R.drawable.estreladourada);
                    usabilidadeRazoavel.setImageResource(R.drawable.estreladourada);
                    usabilidadeBom.setImageResource(R.drawable.estreladourada);
                    usaMRuim = true;
                    usaRuim = true;
                    usaRazoavel = true;
                    usaBom = true;
                    formulario.setUsabilidade("4");
                }
                break;
            case R.id.usabilidadeMBOM:
                usabilidadeMRuim.setImageResource(R.drawable.estreladourada);
                usabilidadeRuim.setImageResource(R.drawable.estreladourada);
                usabilidadeRazoavel.setImageResource(R.drawable.estreladourada);
                usabilidadeBom.setImageResource(R.drawable.estreladourada);
                usabilidadeMBom.setImageResource(R.drawable.estreladourada);
                usaMRuim = true;
                usaRuim = true;
                usaRazoavel = true;
                usaBom = true;
                usaMBom = true;
                formulario.setUsabilidade("5");
                break;

            /*organização e estrutura*/
            case R.id.orgMRUIM:
                if(orMRuim){
                    orgMRuim.setImageResource(R.drawable.estreladourada);
                    orgRuim.setImageResource(R.drawable.estrelacrua);
                    orgRazoavel.setImageResource(R.drawable.estrelacrua);
                    orgBom.setImageResource(R.drawable.estrelacrua);
                    orgMBom.setImageResource(R.drawable.estrelacrua);
                    orRuim = false;
                    orRazoavel = false;
                    orBom = false;
                    orMBom = false;
                    formulario.setEstrutura("1");
                }
                else{
                    orgMRuim.setImageResource(R.drawable.estreladourada);
                    orMRuim = true;
                    formulario.setEstrutura("1");
                }
                break;
            case R.id.orgRUIM:
                if(orRuim){
                    orgMRuim.setImageResource(R.drawable.estreladourada);
                    orgRuim.setImageResource(R.drawable.estreladourada);
                    orgRazoavel.setImageResource(R.drawable.estrelacrua);
                    orgBom.setImageResource(R.drawable.estrelacrua);
                    orgMBom.setImageResource(R.drawable.estrelacrua);
                    orRazoavel = false;
                    orBom = false;
                    orMBom = false;
                    formulario.setEstrutura("2");
                }
                else {
                    orgMRuim.setImageResource(R.drawable.estreladourada);
                    orgRuim.setImageResource(R.drawable.estreladourada);
                    orMRuim = true;
                    orRuim = true;
                    formulario.setEstrutura("2");
                }
                break;
            case R.id.orgRAZOAVEL:
                if(orRazoavel){
                    orgMRuim.setImageResource(R.drawable.estreladourada);
                    orgRuim.setImageResource(R.drawable.estreladourada);
                    orgRazoavel.setImageResource(R.drawable.estreladourada);
                    orgBom.setImageResource(R.drawable.estrelacrua);
                    orgMBom.setImageResource(R.drawable.estrelacrua);
                    orBom = false;
                    orMBom = false;
                    formulario.setEstrutura("3");
                }
                else {
                    orgMRuim.setImageResource(R.drawable.estreladourada);
                    orgRuim.setImageResource(R.drawable.estreladourada);
                    orgRazoavel.setImageResource(R.drawable.estreladourada);
                    orMRuim = true;
                    orRuim = true;
                    orRazoavel = true;
                    formulario.setEstrutura("3");
                }
                break;
            case R.id.orgBOM:
                if(orBom){
                    orgMRuim.setImageResource(R.drawable.estreladourada);
                    orgRuim.setImageResource(R.drawable.estreladourada);
                    orgRazoavel.setImageResource(R.drawable.estreladourada);
                    orgBom.setImageResource(R.drawable.estreladourada);
                    orgMBom.setImageResource(R.drawable.estrelacrua);
                    orMBom = false;
                    formulario.setEstrutura("4");
                }
                else {
                    orgMRuim.setImageResource(R.drawable.estreladourada);
                    orgRuim.setImageResource(R.drawable.estreladourada);
                    orgRazoavel.setImageResource(R.drawable.estreladourada);
                    orgBom.setImageResource(R.drawable.estreladourada);
                    orMRuim = true;
                    orRuim = true;
                    orRazoavel = true;
                    orBom = true;
                    formulario.setEstrutura("4");
                }
                break;
            case R.id.orgMBOM:
                orgMRuim.setImageResource(R.drawable.estreladourada);
                orgRuim.setImageResource(R.drawable.estreladourada);
                orgRazoavel.setImageResource(R.drawable.estreladourada);
                orgBom.setImageResource(R.drawable.estreladourada);
                orgMBom.setImageResource(R.drawable.estreladourada);
                orMRuim = true;
                orRuim = true;
                orRazoavel = true;
                orBom = true;
                orMBom = true;
                formulario.setEstrutura("5");
                break;

            /*recursos*/
            case R.id.recursosMRUIM:
                if(recMRuim){
                    recursosMRuim.setImageResource(R.drawable.estreladourada);
                    recursosRuim.setImageResource(R.drawable.estrelacrua);
                    recursosRazoavel.setImageResource(R.drawable.estrelacrua);
                    recursosBom.setImageResource(R.drawable.estrelacrua);
                    recursosMBom.setImageResource(R.drawable.estrelacrua);
                    recRuim = false;
                    recRazoavel = false;
                    recBom = false;
                    recMBom = false;
                    formulario.setRecursos("1");
                }
                else{
                    recursosMRuim.setImageResource(R.drawable.estreladourada);
                    recMRuim = true;
                    formulario.setRecursos("1");
                }
                break;
            case R.id.recursosRUIM:
                if(recRuim){
                    recursosMRuim.setImageResource(R.drawable.estreladourada);
                    recursosRuim.setImageResource(R.drawable.estreladourada);
                    recursosRazoavel.setImageResource(R.drawable.estrelacrua);
                    recursosBom.setImageResource(R.drawable.estrelacrua);
                    recursosMBom.setImageResource(R.drawable.estrelacrua);
                    recRazoavel = false;
                    recBom = false;
                    recMBom = false;
                    formulario.setRecursos("2");
                }
                else {
                    recursosMRuim.setImageResource(R.drawable.estreladourada);
                    recursosRuim.setImageResource(R.drawable.estreladourada);
                    recMRuim = true;
                    recRuim = true;
                    formulario.setRecursos("2");
                }
                break;
            case R.id.recursosRAZOAVEL:
                if(recRazoavel){
                    recursosMRuim.setImageResource(R.drawable.estreladourada);
                    recursosRuim.setImageResource(R.drawable.estreladourada);
                    recursosRazoavel.setImageResource(R.drawable.estreladourada);
                    recursosBom.setImageResource(R.drawable.estrelacrua);
                    recursosMBom.setImageResource(R.drawable.estrelacrua);
                    recBom = false;
                    recMBom = false;
                    formulario.setRecursos("3");
                }
                else {
                    recursosMRuim.setImageResource(R.drawable.estreladourada);
                    recursosRuim.setImageResource(R.drawable.estreladourada);
                    recursosRazoavel.setImageResource(R.drawable.estreladourada);
                    recMRuim = true;
                    recRuim = true;
                    recRazoavel = true;
                    formulario.setRecursos("3");
                }
                break;
            case R.id.recursosBOM:
                if(recBom){
                    recursosMRuim.setImageResource(R.drawable.estreladourada);
                    recursosRuim.setImageResource(R.drawable.estreladourada);
                    recursosRazoavel.setImageResource(R.drawable.estreladourada);
                    recursosBom.setImageResource(R.drawable.estreladourada);
                    recursosMBom.setImageResource(R.drawable.estrelacrua);
                    recMBom = false;
                    formulario.setRecursos("4");
                }
                else {
                    recursosMRuim.setImageResource(R.drawable.estreladourada);
                    recursosRuim.setImageResource(R.drawable.estreladourada);
                    recursosRazoavel.setImageResource(R.drawable.estreladourada);
                    recursosBom.setImageResource(R.drawable.estreladourada);
                    recMRuim = true;
                    recRuim = true;
                    recRazoavel = true;
                    recBom = true;
                    formulario.setRecursos("4");
                }
                break;
            case R.id.recursosMBOM:
                recursosMRuim.setImageResource(R.drawable.estreladourada);
                recursosRuim.setImageResource(R.drawable.estreladourada);
                recursosRazoavel.setImageResource(R.drawable.estreladourada);
                recursosBom.setImageResource(R.drawable.estreladourada);
                recursosMBom.setImageResource(R.drawable.estreladourada);
                recMRuim = true;
                recRuim = true;
                recRazoavel = true;
                recBom = true;
                recMBom = true;
                formulario.setRecursos("5");
                break;

            /*enviar*/
            case R.id.enviar:
                verificaRespostas();
                formulario.setSugestao(mensagem.getText().toString());
                Database_Firebase db = new Database_Firebase();
                db.salvarFormulario(formulario);
                novaTela(usuario,1,this,0);
                break;
        }
    }

    private void verificaRespostas(){
        ArrayList<String> respostas = new ArrayList<>();
        if(nivel2.isChecked()){
            respostas.add("2");
        }
        if(nivel3.isChecked()){
            respostas.add("3");
        }
        if(nivel4.isChecked()){
            respostas.add("4");
        }
        if(nivel5.isChecked()){
            respostas.add("5");
        }
        if(nivel6.isChecked()){
            respostas.add("6");
        }
        if(nivel7.isChecked()){
            respostas.add("7");
        }
        if(nivel8.isChecked()){
            respostas.add("8");
        }
        if(nivel9.isChecked()){
            respostas.add("9");
        }
        if(nivel10.isChecked()){
            respostas.add("10");
        }
        if(nivel11.isChecked()){
            respostas.add("11");
        }
        if(nivel12.isChecked()){
            respostas.add("12");
        }

        formulario.setCriterioDificuldade(respostas);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        /*ESCOLARIDADE*/
        if(i == R.id.estudanteEF){
            formulario.setEscolaridade("Ensino Fundamental");
        }
        if(i == R.id.estudanteEM){
            formulario.setEscolaridade("Ensino Médio");
        }
        if(i == R.id.estudanteES){
            formulario.setEscolaridade("Ensino Superior");
        }
        if(i == R.id.estudantePG){
            formulario.setEscolaridade("Pós-graduação");
        }
        if(i == R.id.estudadeNE){
            formulario.setEscolaridade("Não estudante");
        }

        /*DIFICULDADE*/
        if(i == R.id.MFACIL){
            formulario.setDificuldade("1");
        }
        if(i == R.id.FACIL){
            formulario.setDificuldade("2");
        }
        if(i == R.id.MEDIO){
            formulario.setDificuldade("3");
        }
        if(i == R.id.DIFICIL){
            formulario.setDificuldade("4");
        }
        if(i == R.id.MDIFICIL){
            formulario.setDificuldade("5");
        }

        /*ENFOQUE*/
        if(i == R.id.ludico){
            formulario.setEnfoque("Ludico/divertido");
        }
        if(i == R.id.didatico){
            formulario.setEnfoque("Didatico/educativo");
        }

        /*APRENDIZADO*/
        if(i == R.id.infantil){
            formulario.setBeneficiaUsuario("Infantil");
        }
        if(i == R.id.juvenil){
            formulario.setBeneficiaUsuario("Juvenil");
        }
        if(i == R.id.adulto){
            formulario.setBeneficiaUsuario("Adulto");
        }
        if(i == R.id.todos){
            formulario.setBeneficiaUsuario("Todos");
        }

        /*RECOMENDARIA*/
        if(i == R.id.sim){
            formulario.setRecomendaria("Sim");
        }
        if(i == R.id.nao){
            formulario.setRecomendaria("Não");
        }
    }

    private void alertAgradecimento(Context context){

        TextView confirmacao;
        Button confirmar;

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_alert_formulario);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP);

        confirmacao = dialog.findViewById(R.id.confimacao);
            confirmacao.setText(R.string.agradecimento);
        confirmar = dialog.findViewById(R.id.ok);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        novaTela(usuario,1,this,0);
        finish();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
