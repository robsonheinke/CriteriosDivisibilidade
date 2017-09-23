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

import java.util.Random;

import heinke.criteriosdivisibilidade.activity.JogoActivity;
import heinke.criteriosdivisibilidade.activity.MenuPrincipal;
import heinke.criteriosdivisibilidade.activity.PassarNivelActivity;
import heinke.criteriosdivisibilidade.model.Usuario;
import heinke.criteriosdivisibilidade.util.Database_Firebase;

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

    public int gerarNumeroAleatorio(int max){
        Random random = new Random();
        return random.nextInt(max);
    }

    public Boolean verificaResultado(int resposta, int criterio){
        return resposta % criterio == 0 ? true : false;
    }

    public String[] gerarNumeros(int criterio){
        System.out.println("to dentro da funcao");
        String NUMEROS = "0123456789";
        String [] numeros = new String[20];
        char a , b;
        int i, j;

        for(int z = 0; z < 20; z++){
            i = gerarNumeroAleatorio(10);
            j = 0;
            if(i == 0){
                while (j == 0) j = gerarNumeroAleatorio(10);
            }
            else{
                j = gerarNumeroAleatorio(10);
            }

            a = NUMEROS.charAt(i);
            b = NUMEROS.charAt(j);


            numeros[z] = ""+a + b;
        }
        return verificaNumerosRepetidos(criterio, numeros) ? numeros : corrigiNumerosRepetidos(criterio, numeros);
    }

    public Boolean verificaNumerosRepetidos (int criterio, String[] numeros){
        int a, b;
        for(int i = 0; i < 20; i++){
            a = Integer.parseInt(numeros[i]);
            for(int j = 0; j < 20; j++){
                b = Integer.parseInt(numeros[j]);
                if(i != j){
                    if(a == b){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String[] corrigiNumerosRepetidos (int criterio, String[] numeros){
        int a, b;
        for(int i = 0; i < 20; i++){
            a = Integer.parseInt(numeros[i]);
            for(int j = 0; j < 20; j++){
                b = Integer.parseInt(numeros[j]);
                if(i != j){
                    if(a == b){
                        a++;
                    }
                }
                numeros[i] = Integer.toString(a);
            }
        }
        return numeros;
    }

    public String[] corrigiMinMaxCertos(int criterios, int total, String[] numeros){
        int min = 8,max = 12;
        int i = 0, aux = Integer.parseInt(numeros[i]);

        if(total < min){
            while(total < min){
                if(!verificaResultado(aux, criterios)){
                    while(!verificaNumerosRepetidos(aux,numeros)) aux++;
                }
                else{
                    numeros[i] = Integer.toString(aux);
                    total++;
                    i++;
                    aux = Integer.parseInt(numeros[i]);
                }
            }
        }
        if(total > max){
            while(total > max) {
                if (verificaResultado(aux, criterios)) {
                    while (!verificaNumerosRepetidos(aux, numeros)) aux++;
                } else {
                    numeros[i] = Integer.toString(aux);
                    total--;
                    i++;
                    aux = Integer.parseInt(numeros[i]);
                }
            }
        }
        return numeros;
    }

    public int verificaQuantidadeDeNumerosCertos(String[] numeros, int criterios){
        int cont = 0, aux;
        for(int i = 0; i < 20; i++){
            aux = Integer.parseInt(numeros[i]);
            if(verificaResultado(aux,criterios))    cont++;
        }
        return cont;
    }

}
