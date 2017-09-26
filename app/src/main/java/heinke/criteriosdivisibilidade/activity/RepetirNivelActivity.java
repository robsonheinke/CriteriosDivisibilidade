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

    private TextView pontos;
    private ImageButton home;
    private ImageView imgUsuario;
    private Button continuar;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repetir_nivel);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");

        pontos = (TextView) findViewById(R.id.pontosAN);
            pontos.setText(usuario.getPontos());
        home = (ImageButton) findViewById(R.id.mHome);
        imgUsuario = (ImageView) findViewById(R.id.img_usuarioAN);
        continuar = (Button) findViewById(R.id.continuar);

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
    protected void onPause(){
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        novaTela(usuario,1,this,0);
        finish();
    }
}
