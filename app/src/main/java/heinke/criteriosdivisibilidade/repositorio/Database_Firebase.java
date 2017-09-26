package heinke.criteriosdivisibilidade.repositorio;

import android.content.Context;
import android.widget.TableLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import heinke.criteriosdivisibilidade.model.Usuario;

/**
 * Created by heinke on 07/09/17.
 */


public class Database_Firebase {

    public void salvarUsuario(Usuario usuario){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("Usuario").child(usuario.getIdFirebase()).setValue(usuario);
    }

    public ArrayList<Usuario> pesquisarUsuarios(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        final ArrayList<Usuario> usuarios = new ArrayList<>();
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot a: dataSnapshot.getChildren()) {
                    Usuario usuario = a.getValue(Usuario.class);
                    usuarios.add(usuario);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return usuarios;
    }

}
