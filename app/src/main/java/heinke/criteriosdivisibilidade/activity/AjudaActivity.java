package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Usuario;

public class AjudaActivity extends Utilitarios implements View.OnClickListener {

    private Button ajuda, criterios;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        ajuda = (Button) findViewById(R.id.ajuda);
            ajuda.setOnClickListener(this);
        criterios = (Button) findViewById(R.id.criterios);
            criterios.setOnClickListener(this);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.criterios:
                LayoutInflater inflater = this.getLayoutInflater();
                inflater.inflate(R.layout.layout_criterios_geral,null);
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
