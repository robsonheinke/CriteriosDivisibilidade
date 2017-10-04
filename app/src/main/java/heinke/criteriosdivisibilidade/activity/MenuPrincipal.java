package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.login.LoginManager;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.repositorio.Database_Firebase;

@SuppressWarnings("WeakerAccess")
public class MenuPrincipal extends Utilitarios implements View.OnClickListener {

    private TextView login, desenvolvido;
    private ImageView imagemLogin;
    private Button jogar, ranking, ajuda;
    private Usuario usuario;
    private Database_Firebase dbFirebase = new Database_Firebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        login = (TextView) findViewById(R.id.nome_usuario);
        desenvolvido = (TextView) findViewById(R.id.desenvolvido);
        imagemLogin = (ImageView) findViewById(R.id.fb_img);

        Intent intent = getIntent();
            usuario = (Usuario) intent.getSerializableExtra("Usuario");
            displayLogin(usuario.getNome(), usuario.getImagem(),this,login,imagemLogin);

        jogar = (Button) findViewById(R.id.jogar);
            jogar.setOnClickListener(this);

        ranking = (Button) findViewById(R.id.ranking);
            ranking.setOnClickListener(this);

        ajuda = (Button) findViewById(R.id.ajuda);
            ajuda.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        dbFirebase.salvarUsuario(usuario);
        switch (v.getId()){
            case R.id.jogar:
                novaTela(usuario, 2,this,0);
                break;
            case R.id.ranking:
                novaTela(usuario, 5, this,0);
                break;
            case R.id.ajuda:
                novaTela(usuario, 6, this,0);
                break;
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

}