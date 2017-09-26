package heinke.criteriosdivisibilidade.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.repositorio.Database;

public class PassarNivelActivity extends Utilitarios implements View.OnClickListener{

    private AlertDialog alert;
    private TextView pontos, parabens;
    private ImageView imgUsuario;
    private ImageButton home;
    private Button avancar, alertOk;

    private Usuario usuario;
    private int auxPontos;

    Database db = new Database(this);

    private TextView mPontos, nPontos,tPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passar_nivel);

        Intent intent = getIntent();

        usuario = (Usuario) intent.getSerializableExtra("Usuario");
        auxPontos = intent.getIntExtra("pontos",0);
        int nivel =  Integer.parseInt(usuario.getNivel());

        pontos = (TextView) findViewById(R.id.pontosAN);
        parabens = (TextView) findViewById(R.id.parabens);
        home = (ImageButton) findViewById(R.id.mHome);
            home.setOnClickListener(this);
        imgUsuario = (ImageView) findViewById(R.id.img_usuarioAN);
        avancar = (Button) findViewById(R.id.continuar);
            avancar.setOnClickListener(this);

        String aux = (String) parabens.getText();

        aux += " "+usuario.getNivel();

        displayLogin(usuario.getImagem(),this,imgUsuario);

        parabens.setText(aux);

        alertaTela(this);

        auxPontos += Integer.parseInt(usuario.getPontos());
        pontos.setText(""+auxPontos);

        usuario.setPontos(String.valueOf(auxPontos));
        usuario.setNivel(String.valueOf(nivel+1));

        db.atualizarUsuario(usuario);


    }


    private void alertaTela (Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_alert);

        mPontos = dialog.findViewById(R.id.alert_meusPontos);
            mPontos.setText(usuario.getPontos());
        nPontos = dialog.findViewById(R.id.alert_meusPontosNivel);
            nPontos.setText(""+auxPontos);
        tPontos = dialog.findViewById(R.id.alert_meusPontosTotal);

        alertOk = dialog.findViewById(R.id.ok);

        int aux = Integer.parseInt(usuario.getPontos());
        int fim = aux + auxPontos;
        int i = aux;

       for(;i <= fim; i++){
           tPontos.setText(""+i);
        }

        dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
