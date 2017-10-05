package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Usuario;

/**
 * Created by heinke on 05/10/17.
 */

public class CriteriosGeraisActivity extends Utilitarios implements View.OnClickListener {

    private Button ajuda, criterios;
    private Usuario usuario;

    private Button cr2,cr3,cr4,cr5,cr6,cr7,cr8,cr9,cr10,cr11,cr12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterios_geral);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");

        inicializaBotoes();

    }

    private void inicializaBotoes(){
        cr2 = (Button)findViewById(R.id.botao2);
            cr2.setOnClickListener(this);
        cr3 = (Button)findViewById(R.id.botao3);
            cr3.setOnClickListener(this);
        cr4 = (Button)findViewById(R.id.botao4);
            cr4.setOnClickListener(this);
        cr5 = (Button)findViewById(R.id.botao5);
            cr5.setOnClickListener(this);
        cr6 = (Button)findViewById(R.id.botao6);
            cr6.setOnClickListener(this);
        cr7 = (Button)findViewById(R.id.botao7);
            cr7.setOnClickListener(this);
        cr8 = (Button)findViewById(R.id.botao8);
            cr8.setOnClickListener(this);
        cr9 = (Button)findViewById(R.id.botao9);
            cr9.setOnClickListener(this);
        cr10 = (Button)findViewById(R.id.botao10);
            cr10.setOnClickListener(this);
        cr11 = (Button)findViewById(R.id.botao11);
            cr11.setOnClickListener(this);
        cr12 = (Button)findViewById(R.id.botao12);
            cr12.setOnClickListener(this);

        ajuda = (Button) findViewById(R.id.ajuda);
            ajuda.setOnClickListener(this);
        criterios = (Button) findViewById(R.id.criterios);
            criterios.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.botao2:
                telaCriterioIndividual(usuario,2);
                break;
            case R.id.botao3:
                telaCriterioIndividual(usuario,3);
                break;
            case R.id.botao4:
                telaCriterioIndividual(usuario,4);
                break;
            case R.id.botao5:
                telaCriterioIndividual(usuario,5);
                break;
            case R.id.botao6:
                telaCriterioIndividual(usuario,6);
                break;
            case R.id.botao7:
                telaCriterioIndividual(usuario,7);
                break;
            case R.id.botao8:
                telaCriterioIndividual(usuario,8);
                break;
            case R.id.botao9:
                telaCriterioIndividual(usuario,9);
                break;
            case R.id.botao10:
                telaCriterioIndividual(usuario,10);
                break;
            case R.id.botao11:
                telaCriterioIndividual(usuario,11);
                break;
            case R.id.botao12:
                telaCriterioIndividual(usuario,12);
                break;
            case R.id.ajuda:
                novaTela(usuario,6,this,0);
                break;
        }
    }

    public void telaCriterioIndividual(Usuario user, int criterio){
        Intent intent = new Intent(this, CriterioEspecificoActivity.class);
        intent.putExtra("Usuario", usuario);
        intent.putExtra("Criterio",criterio);
        startActivity(intent);
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
