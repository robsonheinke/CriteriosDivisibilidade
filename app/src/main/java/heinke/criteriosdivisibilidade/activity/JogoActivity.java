package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Nivel;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.repositorio.Database;

public class JogoActivity extends Utilitarios implements View.OnClickListener {

    //botoes do jogo
    private Button pos01,pos02,pos03,pos04,pos05
                  ,pos06,pos07,pos08,pos09,pos10
                  ,pos11,pos12,pos13,pos14,pos15
                  ,pos16,pos17,pos18,pos19,pos20;

    //tela
    private TextView tempo, pontos, niveis, ordem;
    private ImageView logo;

    //controle do botoes acessados
    private Boolean b01 = true, b02 = true, b03 = true, b04 = true, b05 = true
                   ,b06 = true, b07 = true, b08 = true, b09 = true, b10 = true
                   ,b11 = true, b12 = true, b13 = true, b14 = true, b15 = true
                   ,b16 = true, b17 = true, b18 = true, b19 = true, b20 = true;

    //cor dos botoes (verde certo e vermelho errado)
    private int certo = R.drawable.resposta_certo;
    private int errado = R.drawable.resposta_errado;

    //uteis para a tela e controle
    private String[] numeros;
    private int controle, total, criterio, erros;

    private ImageView fotoUsuario;

    private Usuario usuario;
    private Nivel nivel;
    private Animation animation;
    private CountDownTimer cronometro;
    private Boolean verificaCronometro = true;


    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");
        nivel = db.pesquisaNivelId(Integer.parseInt(usuario.getNivel()));
        criterio = Integer.parseInt(nivel.getCriterio());

        inicializaItensTela();
        displayLogin(usuario.getImagem(),this,fotoUsuario);

        cronometro = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long segundos) {
                animation = new AlphaAnimation(1,0);                    //altera de visivel para invisivel
                animation.setDuration(500);                             //duração em milisegundos
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(Animation.INFINITE);           //repetir infinitamente
                animation.setRepeatMode(Animation.REVERSE);             //inverte a animação no final
                if (segundos < 10*1000) {
                    tempo.setTextColor(Color.RED);
                    tempo.startAnimation(animation);
                }
                tempo.setText(getCorretcTimer(true, segundos)+":"+getCorretcTimer(false, segundos));
            }

            @Override
            public void onFinish() {
                String auxPontos = (String) pontos.getText();
                if(verificaCronometro)
                    fimDoNivel(usuario,"00:00",auxPontos);
            }
        }.start();


        numeros = gerarNumeros(criterio);

        total = verificaQuantidadeDeNumerosCertos(numeros, criterio);
        erros = 0;

        inicializarValoresBotoes();
    }

    private void inicializaItensTela(){
        pos01 = (Button) findViewById(R.id.posicao_01);
            pos01.setOnClickListener(this);
        pos02 = (Button) findViewById(R.id.posicao_02);
            pos02.setOnClickListener(this);
        pos03 = (Button) findViewById(R.id.posicao_03);
            pos03.setOnClickListener(this);
        pos04 = (Button) findViewById(R.id.posicao_04);
            pos04.setOnClickListener(this);
        pos05 = (Button) findViewById(R.id.posicao_05);
            pos05.setOnClickListener(this);
        pos06 = (Button) findViewById(R.id.posicao_06);
            pos06.setOnClickListener(this);
        pos07 = (Button) findViewById(R.id.posicao_07);
            pos07.setOnClickListener(this);
        pos08 = (Button) findViewById(R.id.posicao_08);
            pos08.setOnClickListener(this);
        pos09 = (Button) findViewById(R.id.posicao_09);
            pos09.setOnClickListener(this);
        pos10 = (Button) findViewById(R.id.posicao_10);
            pos10.setOnClickListener(this);
        pos11 = (Button) findViewById(R.id.posicao_11);
            pos11.setOnClickListener(this);
        pos12 = (Button) findViewById(R.id.posicao_12);
            pos12.setOnClickListener(this);
        pos13 = (Button) findViewById(R.id.posicao_13);
            pos13.setOnClickListener(this);
        pos14 = (Button) findViewById(R.id.posicao_14);
            pos14.setOnClickListener(this);
        pos15 = (Button) findViewById(R.id.posicao_15);
            pos15.setOnClickListener(this);
        pos16 = (Button) findViewById(R.id.posicao_16);
            pos16.setOnClickListener(this);
        pos17 = (Button) findViewById(R.id.posicao_17);
            pos17.setOnClickListener(this);
        pos18 = (Button) findViewById(R.id.posicao_18);
            pos18.setOnClickListener(this);
        pos19 = (Button) findViewById(R.id.posicao_19);
            pos19.setOnClickListener(this);
        pos20 = (Button) findViewById(R.id.posicao_20);
            pos20.setOnClickListener(this);

        tempo = (TextView) findViewById(R.id.tempo);
        pontos = (TextView) findViewById(R.id.pontos);
            pontos.setText("0");
        niveis = (TextView) findViewById(R.id.nivel);
            niveis.setText(nivel.getNivel());
        ordem = (TextView) findViewById(R.id.ordem_nivel);
            ordem.setText(nivel.getOrdem());
        logo = (ImageView) findViewById(R.id.logo);

        fotoUsuario = (ImageView) findViewById(R.id.img_usuario);
    }

    private void inicializarValoresBotoes(){
        pos01.setText(numeros[0]);
        pos02.setText(numeros[1]);
        pos03.setText(numeros[2]);
        pos04.setText(numeros[3]);
        pos05.setText(numeros[4]);
        pos06.setText(numeros[5]);
        pos07.setText(numeros[6]);
        pos08.setText(numeros[7]);
        pos09.setText(numeros[8]);
        pos10.setText(numeros[9]);
        pos11.setText(numeros[10]);
        pos12.setText(numeros[11]);
        pos13.setText(numeros[12]);
        pos14.setText(numeros[13]);
        pos15.setText(numeros[14]);
        pos16.setText(numeros[15]);
        pos17.setText(numeros[16]);
        pos18.setText(numeros[17]);
        pos19.setText(numeros[18]);
        pos20.setText(numeros[19]);
    }

    @Override
    public void onClick(View view) {
        int str;
        switch (view.getId()) {
            case R.id.posicao_01:
                if (b01) {
                    str = Integer.parseInt((String) pos01.getText());
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos01.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos01.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b01 = false;
                }
                break;
            case R.id.posicao_02:
                str = Integer.parseInt((String) pos02.getText());
                if (b02) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos02.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos02.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b02 = false;
                }
                break;
            case R.id.posicao_03:
                str = Integer.parseInt((String) pos03.getText());
                if (b03) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos03.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos03.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b03 = false;
                }
                break;
            case R.id.posicao_04:
                str = Integer.parseInt((String) pos04.getText());
                if (b04) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos04.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos04.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b04 = false;
                }
                break;
            case R.id.posicao_05:
                str = Integer.parseInt((String) pos05.getText());
                if (b05) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos05.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos05.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b05 = false;
                }
                break;
            case R.id.posicao_06:
                str = Integer.parseInt((String) pos06.getText());
                if (b06) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos06.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos06.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b06 = false;
                }
                break;
            case R.id.posicao_07:
                str = Integer.parseInt((String) pos07.getText());
                if (b07) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos07.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos07.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b07 = false;
                }
                break;
            case R.id.posicao_08:
                str = Integer.parseInt((String) pos08.getText());
                if (b08) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos08.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos08.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b08 = false;
                }
                break;
            case R.id.posicao_09:
                str = Integer.parseInt((String) pos09.getText());
                if (b09) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos09.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos09.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b09 = false;
                }
                break;
            case R.id.posicao_10:
                str = Integer.parseInt((String) pos10.getText());
                if (b10) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos10.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos10.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b10 = false;
                }
                break;
            case R.id.posicao_11:
                str = Integer.parseInt((String) pos11.getText());
                if (b11) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos11.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos11.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b11 = false;
                }
                break;
            case R.id.posicao_12:
                str = Integer.parseInt((String) pos12.getText());
                if (b12) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos12.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos12.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b12 = false;
                }
                break;
            case R.id.posicao_13:
                str = Integer.parseInt((String) pos13.getText());
                if (b13) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos13.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos13.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b13 = false;
                }
                break;
            case R.id.posicao_14:
                str = Integer.parseInt((String) pos14.getText());
                if (b14) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos14.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos14.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b14 = false;
                }
                break;
            case R.id.posicao_15:
                str = Integer.parseInt((String) pos15.getText());
                if (b15) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos15.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos15.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b15 = false;
                }
                break;
            case R.id.posicao_16:
                str = Integer.parseInt((String) pos16.getText());
                if (b16) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos16.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos16.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b16 = false;
                }
                break;
            case R.id.posicao_17:
                str = Integer.parseInt((String) pos17.getText());
                if (b17) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos17.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos17.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b17 = false;
                }
                break;
            case R.id.posicao_18:
                str = Integer.parseInt((String) pos18.getText());
                if (b18) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos18.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos18.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b18 = false;
                }
                break;
            case R.id.posicao_19:
                str = Integer.parseInt((String) pos19.getText());
                if (b19) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos19.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos19.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b19 = false;
                }
                break;
            case R.id.posicao_20:
                str = Integer.parseInt((String) pos20.getText());
                if (b20) {
                    if (verificaResultado(str, criterio)) {
                        pontuacao(true);
                        pos20.setBackground(getResources().getDrawable(certo, getTheme()));
                    }
                    else {
                        pontuacao(false);
                        pos20.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b20 = false;
                }
                break;
        }
        if(erros == 5){
            cronometro.onFinish();
        }
        else if(controle == total ){
            fimDoNivel(usuario,(String) tempo.getText(),(String) pontos.getText());
        }
    }

    public void pontuacao(Boolean respostaCerta){
        String aux = (String) pontos.getText();
        int auxPontos = Integer.parseInt(aux);
        if(respostaCerta)   {
            auxPontos += 10;
            controle++;
        }
        else{
            auxPontos += -5;
            erros++;
        }

        pontos.setText(Integer.toString(auxPontos));
    }

    public void fimDoNivel(Usuario usr, String tempo, String pontos){
        int tempoRestante = (Integer.parseInt(tempo.substring(0,1)) * 60) + Integer.parseInt(tempo.substring(3));
        int aux = Integer.parseInt(pontos);
        int somaTotal = 0;

        somaTotal = aux + (tempoRestante*10);

        if(tempoRestante > 0){
                novaTela(usuario, 3, this, somaTotal);
        }
        else{
            if(controle >= (total*0.7) && erros < 5){
                novaTela(usuario, 3, this, somaTotal);
            }
            else{
                if(erros == 5){
                    novaTela(usuario, 4, this, -1);
                }
                else{
                    novaTela(usuario, 4, this, 0);
                }
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        verificaCronometro = false;
        cronometro.onFinish();
        finish();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        novaTela(usuario,1,this,0);
        finish();
    }

    private String getCorretcTimer(boolean minutos, long mSegundos){
        String aux;
        int constCalendar = minutos ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(mSegundos);

        aux = c.get(constCalendar) < 10 ? "0"+c.get(constCalendar) : ""+c.get(constCalendar);
        return(aux);
    }

}
