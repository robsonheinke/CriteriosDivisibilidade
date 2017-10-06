package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Usuario;

public class CriteriosIndividualActivity extends Utilitarios implements View.OnClickListener{

    private Button ajuda, criterio, regraCriterio;
    private ImageButton avancar, voltar;
    private TextView regra, exUm, exDois, exTres;
    private ImageView imgUm, imgDois, imgTres;

    private Usuario usuario;
    private int numCriterio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterios_individual);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");
        numCriterio = intent.getIntExtra("Criterio",0);

        inicializarBotoes();

        tela();

    }

    private void inicializarBotoes(){

        ajuda = (Button)findViewById(R.id.ajuda);
            ajuda.setOnClickListener(this);
        criterio = (Button)findViewById(R.id.criterios);
            criterio.setOnClickListener(this);
        regraCriterio = (Button)findViewById(R.id.regraCriterios);
        avancar = (ImageButton)findViewById(R.id.avancar);
            avancar.setOnClickListener(this);
        voltar = (ImageButton)findViewById(R.id.voltar);
            voltar.setOnClickListener(this);
        regra = (TextView)findViewById(R.id.regra);

        exUm = (TextView)findViewById(R.id.exUm);
        exDois = (TextView)findViewById(R.id.exDois);
        exTres = (TextView)findViewById(R.id.exTres);

        imgUm = (ImageView)findViewById(R.id.imgUm);
        imgDois = (ImageView)findViewById(R.id.imgDois);
        imgTres = (ImageView)findViewById(R.id.imgTres);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ajuda:
                novaTela(usuario,6,this,0);
                break;
            case R.id.criterios:
                novaTela(usuario,7,this,0);
                break;
            case R.id.avancar:
                switch (numCriterio){
                    case 2:
                        numCriterio++;
                        tela();
                        break;
                    case 3:
                        numCriterio++;
                        tela();
                        break;
                    case 4:
                        numCriterio++;
                        tela();
                        break;
                    case 5:
                        numCriterio++;
                        tela();
                        break;
                    case 6:
                        numCriterio++;
                        tela();
                        break;
                    case 7:
                        numCriterio++;
                        tela();
                        break;
                    case 8:
                        numCriterio++;
                        tela();
                        break;
                    case 9:
                        numCriterio++;
                        tela();
                        break;
                    case 10:
                        numCriterio++;
                        tela();
                        break;
                    case 11:
                        numCriterio++;
                        tela();
                        break;
                }
                break;
            case R.id.voltar:
                switch (numCriterio){
                    case 3:
                        numCriterio--;
                        tela();
                        break;
                    case 4:
                        numCriterio--;
                        tela();
                        break;
                    case 5:
                        numCriterio--;
                        tela();
                        break;
                    case 6:
                        numCriterio--;
                        tela();
                        break;
                    case 7:
                        numCriterio--;
                        tela();
                        break;
                    case 8:
                        numCriterio--;
                        tela();
                        break;
                    case 9:
                        numCriterio--;
                        tela();
                        break;
                    case 10:
                        numCriterio--;
                        tela();
                        break;
                    case 11:
                        numCriterio--;
                        tela();
                        break;
                    case 12:
                        numCriterio--;
                        tela();
                        break;
                }
                break;
        }
    }

    private void tela(){
        String aux;
        String[] str;
        switch (numCriterio){
            case 2:
                voltar.setImageResource(R.drawable.fundo_telas);
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div2);

                exUm.setText(R.string.ex2_1);
                exDois.setText(R.string.ex2_2);
                exTres.setText(R.string.ex2_3);

                verificaSeMultiplo(getString(R.string.ex2_1),1);
                verificaSeMultiplo(getString(R.string.ex2_2),2);
                verificaSeMultiplo(getString(R.string.ex2_3),3);
                break;
            case 3:
                voltar.setImageResource(R.drawable.voltar);
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div3);

                exUm.setText(R.string.ex3_1);
                exDois.setText(R.string.ex3_2);
                exTres.setText(R.string.ex3_3);

                verificaSeMultiplo(getString(R.string.ex3_1),1);
                verificaSeMultiplo(getString(R.string.ex3_2),2);
                verificaSeMultiplo(getString(R.string.ex3_3),3);
                break;
            case 4:
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div4);

                exUm.setText(R.string.ex4_1);
                exDois.setText(R.string.ex4_2);
                exTres.setText(R.string.ex4_3);

                verificaSeMultiplo(getString(R.string.ex4_1),1);
                verificaSeMultiplo(getString(R.string.ex4_2),2);
                verificaSeMultiplo(getString(R.string.ex4_3),3);
                break;
            case 5:
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div5);

                exUm.setText(R.string.ex5_1);
                exDois.setText(R.string.ex5_2);
                exTres.setText(R.string.ex5_3);

                verificaSeMultiplo(getString(R.string.ex5_1),1);
                verificaSeMultiplo(getString(R.string.ex5_2),2);
                verificaSeMultiplo(getString(R.string.ex5_3),3);
                break;
            case 6:
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div6);

                exUm.setText(R.string.ex6_1);
                exDois.setText(R.string.ex6_2);
                exTres.setText(R.string.ex6_3);

                verificaSeMultiplo(getString(R.string.ex6_1),1);
                verificaSeMultiplo(getString(R.string.ex6_2),2);
                verificaSeMultiplo(getString(R.string.ex6_3),3);
                break;
            case 7:
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div7);

                exUm.setText(R.string.ex7_1);
                exDois.setText(R.string.ex7_2);
                exTres.setText(R.string.ex7_3);

                verificaSeMultiplo(getString(R.string.ex7_1),1);
                verificaSeMultiplo(getString(R.string.ex7_2),2);
                verificaSeMultiplo(getString(R.string.ex7_3),3);
                break;
            case 8:
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div8);

                exUm.setText(R.string.ex8_1);
                exDois.setText(R.string.ex8_2);
                exTres.setText(R.string.ex8_3);

                verificaSeMultiplo(getString(R.string.ex8_1),1);
                verificaSeMultiplo(getString(R.string.ex8_2),2);
                verificaSeMultiplo(getString(R.string.ex8_3),3);
                break;
            case 9:
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div9);

                exUm.setText(R.string.ex9_1);
                exDois.setText(R.string.ex9_2);
                exTres.setText(R.string.ex9_3);

                verificaSeMultiplo(getString(R.string.ex9_1),1);
                verificaSeMultiplo(getString(R.string.ex9_2),2);
                verificaSeMultiplo(getString(R.string.ex9_3),3);
                break;
            case 10:
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div10);

                exUm.setText(R.string.ex10_1);
                exDois.setText(R.string.ex10_2);
                exTres.setText(R.string.ex10_3);

                verificaSeMultiplo(getString(R.string.ex10_1),1);
                verificaSeMultiplo(getString(R.string.ex10_2),2);
                verificaSeMultiplo(getString(R.string.ex10_3),3);
                break;
            case 11:
                avancar.setImageResource(R.drawable.avancar);
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div11);

                exUm.setText(R.string.ex11_1);
                exDois.setText(R.string.ex11_2);
                exTres.setText(R.string.ex11_3);

                verificaSeMultiplo(getString(R.string.ex11_1),1);
                verificaSeMultiplo(getString(R.string.ex11_2),2);
                verificaSeMultiplo(getString(R.string.ex11_3),3);
                break;
            case 12:
                avancar.setImageResource(R.drawable.fundo_telas);
                regraCriterio.setText(String.valueOf(numCriterio));
                regra.setText(R.string.div12);

                exUm.setText(R.string.ex12_1);
                exDois.setText(R.string.ex12_2);
                exTres.setText(R.string.ex12_3);

                verificaSeMultiplo(getString(R.string.ex12_1),1);
                verificaSeMultiplo(getString(R.string.ex12_2),2);
                verificaSeMultiplo(getString(R.string.ex12_3),3);
                break;
        }
    }

    private void verificaSeMultiplo(String str, int exemplo){
        String[] aux = str.split(" ");
        int numero = Integer.parseInt(aux[0]);

        if(numero % numCriterio == 0){
            if(exemplo == 1){
                imgUm.setImageResource(R.drawable.certo);
            }
            else if(exemplo == 2){
                imgDois.setImageResource(R.drawable.certo);
            }
            else{
                imgTres.setImageResource(R.drawable.certo);
            }
        }
        else{
            if(exemplo == 1){
                imgUm.setImageResource(R.drawable.errado);
            }
            else if(exemplo == 2){
                imgDois.setImageResource(R.drawable.errado);
            }
            else{
                imgTres.setImageResource(R.drawable.errado);
            }
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        novaTela(usuario,7,this,0);
        finish();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
