package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.ListaRanking;
import heinke.criteriosdivisibilidade.model.Ranking;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.repositorio.Database;

public class RankingActivity extends Utilitarios {

    private Database db;
    private ArrayList<Ranking> ranking;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");

        ListView listView = (ListView) findViewById(R.id.list_item);
        db = new Database(this);
        ranking = db.pesquisarTopDez();

        ListaRanking listaRanking = new ListaRanking(this, ranking);

        listView.setAdapter(listaRanking);
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
