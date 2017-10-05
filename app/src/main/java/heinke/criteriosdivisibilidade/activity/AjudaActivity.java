package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");

        ajuda = (Button) findViewById(R.id.ajuda);
            ajuda.setOnClickListener(this);

        criterios = (Button) findViewById(R.id.criterios);
            criterios.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.criterios:
                novaTela(usuario,7,this,0);
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
