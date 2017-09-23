package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private Boolean b01 = false, b02 = false, b03 = false, b04 = false, b05 = false
                   ,b06 = false, b07 = false, b08 = false, b09 = false, b10 = false
                   ,b11 = false, b12 = false, b13 = false, b14 = false, b15 = false
                   ,b16 = false, b17 = false, b18 = false, b19 = false, b20 = false;

    //cor dos botoes (verde certo e vermelho errado)
    private int certo = R.drawable.resposta_certo;
    private int errado = R.drawable.resposta_errado;

    //uteis para a tela e controle
    private String[] numeros;
    private int controle, total, criterio;

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

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");
        nivel = db.pesquisaNivelId(Integer.parseInt(usuario.getNivel()));
        criterio = Integer.parseInt(nivel.getNivel())+1;

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

        total = verificaQuantidadeDeNumerosCertos(numeros, Integer.parseInt(nivel.getNivel())+1);
        if(total < 8)  {
            numeros = corrigiMinMaxCertos(Integer.parseInt(nivel.getNivel()+1), total, numeros);
            total = verificaQuantidadeDeNumerosCertos(numeros, Integer.parseInt(nivel.getNivel()));
        }
        else if(total > 12)   {
            numeros = corrigiMinMaxCertos(Integer.parseInt(nivel.getNivel()+1), total, numeros);
            total = verificaQuantidadeDeNumerosCertos(numeros, Integer.parseInt(nivel.getNivel())+1);
        }
        System.out.println("total="+total);
        System.out.println("controle="+controle);
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

    @Override
    public void onClick(View view) {
        String str;
        switch (view.getId()) {
            case R.id.posicao_01:
                if (!b01) {
                    str = (String) pos01.getText();
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos01.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos01.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b01 = true;
                }
                break;
            case R.id.posicao_02:
                str = (String) pos02.getText();
                if (!b02) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos02.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos02.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b02 = true;
                }
                break;
            case R.id.posicao_03:
                str = (String) pos03.getText();
                if (!b03) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos03.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos03.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b03 = true;
                }
                break;
            case R.id.posicao_04:
                str = (String) pos04.getText();
                if (!b04) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos04.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos04.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b04 = true;
                }
                break;
            case R.id.posicao_05:
                str = (String) pos05.getText();
                if (!b05) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos05.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos05.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b05 = true;
                }
                break;
            case R.id.posicao_06:
                str = (String) pos06.getText();
                if (!b06) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos06.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos06.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b06 = true;
                }
                break;
            case R.id.posicao_07:
                str = (String) pos07.getText();
                if (!b07) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos07.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos07.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b07 = true;
                }
                break;
            case R.id.posicao_08:
                str = (String) pos08.getText();
                if (!b08) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos08.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos08.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b08 = true;
                }
                break;
            case R.id.posicao_09:
                str = (String) pos09.getText();
                if (!b09) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos09.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos09.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b09 = true;
                }
                break;
            case R.id.posicao_10:
                str = (String) pos10.getText();
                if (!b10) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos10.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos10.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b10 = true;
                }
                break;
            case R.id.posicao_11:
                str = (String) pos11.getText();
                if (!b11) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos11.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos11.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b11 = true;
                }
                break;
            case R.id.posicao_12:
                str = (String) pos12.getText();
                if (!b12) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos12.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos12.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b12 = true;
                }
                break;
            case R.id.posicao_13:
                str = (String) pos13.getText();
                if (!b13) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos13.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos13.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b13 = true;
                }
                break;
            case R.id.posicao_14:
                str = (String) pos14.getText();
                if (!b14) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos14.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos14.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b14 = true;
                }
                break;
            case R.id.posicao_15:
                str = (String) pos15.getText();
                if (!b15) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos15.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos15.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b15 = true;
                }
                break;
            case R.id.posicao_16:
                str = (String) pos16.getText();
                if (!b16) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos16.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos16.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b16 = true;
                }
                break;
            case R.id.posicao_17:
                str = (String) pos17.getText();
                if (!b17) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos17.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos17.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b17 = true;
                }
                break;
            case R.id.posicao_18:
                str = (String) pos18.getText();
                if (!b18) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos18.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos18.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b18 = true;
                }
                break;
            case R.id.posicao_19:
                str = (String) pos19.getText();
                if (!b19) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos19.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos19.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b19 = true;
                }
                break;
            case R.id.posicao_20:
                str = (String) pos20.getText();
                if (!b20) {
                    if (verificaResultado(Integer.parseInt(str), Integer.parseInt(nivel.getNivel()))) {
                        pontuacao(true);
                        pos20.setBackground(getResources().getDrawable(certo, getTheme()));
                    } else {
                        pontuacao(false);
                        pos20.setBackground(getResources().getDrawable(errado, getTheme()));
                    }
                    b20 = true;
                }
                break;
        }
        if(controle == total){
            fimDoNivel(usuario,(String) tempo.getText(),(String) pontos.getText());
        }
        System.out.println("controle="+controle);
    }

    public void pontuacao(Boolean respostaCerta){
        String aux = (String) pontos.getText();
        int auxPontos = Integer.parseInt(aux);
        if(respostaCerta)   {
            auxPontos += 10;
            controle++;
        }
        else auxPontos += -5;

        pontos.setText(Integer.toString(auxPontos));
    }

    public void fimDoNivel(Usuario usr, String tempo, String pontos){
        int tempoRestante = (Integer.parseInt(tempo.substring(0,1)) * 60) + Integer.parseInt(tempo.substring(3));
        int aux = Integer.parseInt(pontos);
        int somaTotal = 0;

        if(tempoRestante > 0){
            Toast.makeText(this,"completei o nivel e sobrou tempo",Toast.LENGTH_LONG).show();
            somaTotal = aux + (tempoRestante*10);
            verificaCronometro = false;
            novaTela(usuario, 3, this,Integer.parseInt(pontos));
        }
        else{
            if(controle >= (total*0.7)){
                Toast.makeText(this,"terminou o tempo mas acertei 70%",Toast.LENGTH_LONG).show();
                novaTela(usuario, 3, this,Integer.parseInt(pontos));
            }
            else{
                Toast.makeText(this,"nao deu tempo :(",Toast.LENGTH_LONG).show();
                //novaTela(usr, 0, RepetirNivel.class);
            }
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        verificaCronometro = false;
        cronometro.onFinish();
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
