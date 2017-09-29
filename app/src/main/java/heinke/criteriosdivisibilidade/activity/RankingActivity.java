package heinke.criteriosdivisibilidade.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.repositorio.Database_Firebase;

public class RankingActivity extends Utilitarios {

    private Database_Firebase dbFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        dbFirebase = new Database_Firebase();

        dbFirebase.pesquisarUsuarios(this);

    }
}
