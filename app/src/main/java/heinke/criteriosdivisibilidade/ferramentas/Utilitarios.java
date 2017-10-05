package heinke.criteriosdivisibilidade.ferramentas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.Collections;

import heinke.criteriosdivisibilidade.activity.AjudaActivity;
import heinke.criteriosdivisibilidade.activity.CriteriosGeraisActivity;
import heinke.criteriosdivisibilidade.activity.JogoActivity;
import heinke.criteriosdivisibilidade.activity.MenuPrincipal;
import heinke.criteriosdivisibilidade.activity.PassarNivelActivity;
import heinke.criteriosdivisibilidade.activity.RankingActivity;
import heinke.criteriosdivisibilidade.activity.RepetirNivelActivity;
import heinke.criteriosdivisibilidade.model.Usuario;

/**
 * Created by heinke on 03/09/17.
 */

public class Utilitarios extends AppCompatActivity {

    protected AutoCompleteTextView email;
    protected EditText password;
    private final ProgressBar progressBar = null;

    protected void showSnackbar(String message ){
        Snackbar.make(progressBar,
                message,
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    protected void showToast( String message ){
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }

    public void novaTela(Usuario usuario, int tela, Context context, int pontos){
        Intent intent = null;
        switch (tela){
            case 1:     //tela de login para o menu principal
                intent = new Intent(context, MenuPrincipal.class);
                break;
            case 2:     //menu principal para tela de jogo
                intent = new Intent(context, JogoActivity.class);
                break;
            case 3:     //menu principal para tela de ranking
                intent = new Intent(context, PassarNivelActivity.class);
                intent.putExtra("pontos",pontos);
                break;
            case 4:     //menu principal para tela de ajuda
                intent = new Intent(context, RepetirNivelActivity.class);
                break;
            case 5:     //menu principal para tela de ranking
                intent = new Intent(context, RankingActivity.class);
                break;
            case 6:
                intent = new Intent(context,AjudaActivity.class);
                break;
            case 7:
                intent = new Intent(context, CriteriosGeraisActivity.class);
                break;
        }
        intent.putExtra("Usuario", usuario);
        startActivity(intent);
    }

    protected void openProgressBar(){
        progressBar.setVisibility( View.VISIBLE );
    }

    protected void closeProgressBar(){
        progressBar.setVisibility( View.GONE );
    }

    public void displayLogin(String nome, String urlImagem, Context context, TextView txt, final ImageView img) {
        txt.setText(nome);

        Glide.with(context).load(urlImagem).asBitmap().into(new BitmapImageViewTarget(img) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                img.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public void displayLogin(String urlImagem, Context context, final ImageView img) {
        Glide.with(context).load(urlImagem).asBitmap().into(new BitmapImageViewTarget(img) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                img.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public Boolean verificaResultado(int resposta, int criterio){
        return resposta % criterio == 0;
    }

    public String[] gerarNumeros(int criterio){
        ArrayList<Integer> numeros = new ArrayList<>();
        int i = 2, j = 0, z = 2;
        int controle = 0;
        int aux;

        for (i = 2; i < 100; i++) {
            numeros.add(i);
        }

        Collections.shuffle(numeros);

        for(i=0; i < 20; i++) {
            if (numeros.get(i) % criterio == 0) {
                controle++;
            }
        }

        i=0;
        while (controle < 9 && i < 20) {
            if (numeros.get(i) % criterio != 0) {
                for (j = i; j<98; j++) {
                    if (numeros.get(j) % criterio == 0 && j >= 20) {
                        aux = numeros.get(i);
                        numeros.set(i, numeros.get(j));
                        numeros.set(j, aux);
                        controle++;
                        break;
                    }
                }
            }
            i+=z;
            if(z==2)    z++;
            else    z--;
        }

        i=0;
        while (controle > 12 && i < 20) {
            if (numeros.get(i) % criterio == 0) {
                for (j = i; j < 98; j++) {
                    if (numeros.get(j) % criterio != 0 && j >= 20) {
                        aux = numeros.get(i);
                        numeros.set(i, numeros.get(j));
                        numeros.set(j, aux);
                        controle--;
                        break;
                    }
                }
            }
            i+=z;
            if(z==2)    z++;
            else    z--;
        }

        String[] retorno = new String[20];
        for(i=0; i < 20; i++){
            retorno[i] = String.valueOf(numeros.get(i));
        }
        return retorno;
    }

    public int verificaQuantidadeDeNumerosCertos(String[] numeros, int criterios){
        int cont = 0, aux;
        for(int i = 0; i < 20; i++){
            aux = Integer.parseInt(numeros[i]);
            if(aux % criterios == 0)    cont++;
        }
        return cont;
    }

}
