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

public class RepetirNivelActivity extends Utilitarios implements View.OnClickListener {

    private TextView pontos, cabecalho;
    private ImageButton home;
    private ImageView imgUsuario;
    private Button continuar;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repetir_nivel);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");
        int aux = intent.getIntExtra("pontos", 0);
        pontos = (TextView) findViewById(R.id.pontosAN);
            pontos.setText(usuario.getPontos());
        home = (ImageButton) findViewById(R.id.mHome);
            home.setOnClickListener(this);
        imgUsuario = (ImageView) findViewById(R.id.img_usuarioAN);
        continuar = (Button) findViewById(R.id.continuar);
            continuar.setOnClickListener(this);

        cabecalho = (TextView) findViewById(R.id.parabens);

        if(aux == 0){
            cabecalho.setText(R.string.perdeu);
        }
        else{
            cabecalho.setText(R.string.perdeu2);
        }

        displayLogin(usuario.getImagem(),this,imgUsuario);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mHome:
                novaTela(usuario,1,this,0);
                break;
            case R.id.continuar:
                novaTela(usuario,2,this,0);
                break;
        }
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
