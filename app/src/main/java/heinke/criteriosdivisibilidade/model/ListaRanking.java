package heinke.criteriosdivisibilidade.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;

import heinke.criteriosdivisibilidade.R;
import heinke.criteriosdivisibilidade.ferramentas.Utilitarios;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by heinke on 30/09/17.
 */

public class ListaRanking extends ArrayAdapter<Ranking> {

    private Context context;
    private ArrayList<Ranking> listaRanking;

    public ListaRanking (Context _context, ArrayList<Ranking> lista){
        super(_context, 0, lista);
        this.context = _context;
        this.listaRanking = lista;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ranking ranking = this.listaRanking.get(position);
        Utilitarios util = new Utilitarios();
        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_ranking,null);

        TextView posicao, nome, nivel, pontos;
        ImageView imagem;

        posicao = (TextView) convertView.findViewById(R.id.AdaptPos);
            posicao.setText(ranking.getPos());
        nome = (TextView) convertView.findViewById(R.id.AdaptNome);
            nome.setText(ranking.getUsuario().getNome());
        nivel = (TextView) convertView.findViewById(R.id.AdaptNivel);
            nivel.setText(ranking.getUsuario().getNivel());
        pontos = (TextView) convertView.findViewById(R.id.AdaptPontos);
            pontos.setText(ranking.getUsuario().getPontos());
        imagem = (ImageView) convertView.findViewById(R.id.AdaptImagem);
            displayLogin(ranking.getUsuario().getImagem(),context,imagem);

        return convertView;
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
}
