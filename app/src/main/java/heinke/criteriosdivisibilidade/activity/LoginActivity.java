package heinke.criteriosdivisibilidade.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.Arrays;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;
import heinke.criteriosdivisibilidade.model.Nivel;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.repositorio.Database;
import heinke.criteriosdivisibilidade.repositorio.Database_Firebase;

public class LoginActivity extends Utilitarios implements View.OnClickListener
                                                         ,GoogleApiClient.OnConnectionFailedListener{

    private FirebaseAuth firebaseAuth;

    private CallbackManager callbackManager;
    private AccessToken accessToken;

    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;

    private Database db = new Database(this);
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.activity_login);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        //inicia o firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //inicia o sdk do facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        //adiciona os niveis no banco local
        Nivel nivel = new Nivel();
        ArrayList<Nivel> niveis = nivel.adicionaNiveis();
        if(db.numeroRegistroNivel() < 11){
            for (Nivel a :niveis) {
                db.adicionarNiveis(a);
            }
        }

        //login do facebook
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AuthCredential credential = FacebookAuthProvider
                        .getCredential(loginResult.getAccessToken().getToken());
                signInCredential(credential);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //login do google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */
)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //inicializa os componentes da tela
        inicializa();
    }


    private void inicializa(){
        Button botaoFacebook = (Button) findViewById(R.id.botaoFacebook);
        botaoFacebook.setOnClickListener(this);

        Button botaoGoogle = (Button) findViewById(R.id.botaoGoogle);
        botaoGoogle.setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        login(firebaseAuth.getCurrentUser());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                loginGoogle(account);
            }
        }
        else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.botaoFacebook:
                LoginManager.getInstance().logInWithReadPermissions(this,
                        Arrays.asList("public_profile", "email"));
                break;
            case R.id.botaoGoogle:
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
        }
    }

    private void signInCredential(AuthCredential credential){
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            login(firebaseAuth.getCurrentUser());
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void loginGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            login(firebaseAuth.getCurrentUser());
                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void login(FirebaseUser firebaseUser) {
        Database_Firebase db_firebase = new Database_Firebase();
        db_firebase.pesquisarUsuarios(this);

        if (firebaseUser != null) {
            String nome = firebaseUser.getDisplayName();
            String email = firebaseUser.getEmail();
            String imagem = firebaseUser.getPhotoUrl().toString();
            String idFirebase = firebaseUser.getUid();

            if (db.contarUsuarioPorId(idFirebase) == 1) {
                usuario = db.pesquisaUsuarioIdFirebase(new Usuario(idFirebase, nome, email, imagem));
            } else {
                usuario = new Usuario(idFirebase, nome, email, imagem,"1","0");
                db.adicionarUsuario(usuario);
            }
            db_firebase.salvarUsuario(usuario);
            novaTela(usuario, 1, LoginActivity.this,0);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        finish();
    }

}