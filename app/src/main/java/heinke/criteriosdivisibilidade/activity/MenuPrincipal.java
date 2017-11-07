package heinke.criteriosdivisibilidade.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.repositorio.Database;
import heinke.criteriosdivisibilidade.repositorio.Database_Firebase;

@SuppressWarnings("WeakerAccess")
public class MenuPrincipal extends Utilitarios implements View.OnClickListener, View.OnTouchListener {

    private TextView login, desenvolvido;
    private ImageView imagemLogin;
    private Button jogar, ranking, ajuda;
    private Usuario usuario;
    private Database_Firebase dbFirebase = new Database_Firebase();
    private Database database = new Database(this);

    private ImageView imagemUsuario;
    private TextView nome, pontos, nivel, posicao;
    private Button fechar, reiniciar, sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbFirebase.pesquisarUsuarios(this);

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

        login.setOnTouchListener(this);
        imagemLogin.setOnTouchListener(this);

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
                if(usuario.getNivel().equals("12")){
                    novaTela(usuario,8,this,0);
                }
                else {
                    novaTela(usuario,2,this,0);
                }
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

    private void alertaTela (final Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dados_usuario);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        imagemUsuario = dialog.findViewById(R.id.img_usuario);
            Glide.with(context).load(usuario.getImagem()).asBitmap().into(new BitmapImageViewTarget(imagemUsuario) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                imagemUsuario.setImageDrawable(circularBitmapDrawable);
            }
        });

        nome = dialog.findViewById(R.id.usuario);
            nome.setText(usuario.getNome());
        pontos = dialog.findViewById(R.id.pontos);
            pontos.setText(usuario.getPontos());
        nivel = dialog.findViewById(R.id.nivel);
            nivel.setText(usuario.getNivel());
        posicao = dialog.findViewById(R.id.posicao);
            posicao.setText(String.valueOf(database.posicao(usuario)));
        fechar = dialog.findViewById(R.id.fechar);

        fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        reiniciar = dialog.findViewById(R.id.reiniciar);

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertaConfirmacao(1,"Você tem certeza?\nAo confirmar, seus pontos serão zerados e você retornará ao nível 1.",context);
            }
        });

        sair = dialog.findViewById(R.id.sair);

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertaConfirmacao(2,"Obrigado pela sua participação!\nGostaria mesmo de sair?",context);
            }
        });

        dialog.show();
    }

    private void alertaConfirmacao(final int opcao, String string, final Context context){
        TextView confirmacao;
        Button confirmar, negar;

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_alert_confirmar);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP);

        confirmacao = dialog.findViewById(R.id.confimacao);
            confirmacao.setText(string);
        confirmar = dialog.findViewById(R.id.confimar);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opcao == 1){
                    usuario.setNivel("1");
                    usuario.setPontos("0");
                    database.atualizarUsuario(usuario);

                    pontos.setText(usuario.getPontos());
                    nivel.setText(usuario.getNivel());
                    posicao.setText(String.valueOf(database.posicao(usuario)));
                    dialog.dismiss();
                }
                else{
                    database.deletarUsuarios("a");
                    FirebaseAuth.getInstance().signOut();
                    novaTela(usuario,9,context,0);
                }
            }
        });

        negar = dialog.findViewById(R.id.negar);

        negar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        alertaTela(this);
        return false;
    }
}