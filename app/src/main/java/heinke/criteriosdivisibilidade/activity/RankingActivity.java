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
import heinke.criteriosdivisibilidade.repositorio.Database_Firebase;

public class RankingActivity extends Utilitarios {

    private Database db;
    private Database_Firebase db_firebase;
    private ArrayList<Ranking> ranking;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        db = new Database(this);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");

        ListView listView = (ListView) findViewById(R.id.list_item);

        ranking = db.pesquisarTopDez();

        ListaRanking listaRanking = new ListaRanking(this, ranking);

        listView.setAdapter(listaRanking);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        db.deletarUsuarios(usuario.getIdFirebase());
        novaTela(usuario,1,this,0);
        finish();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}