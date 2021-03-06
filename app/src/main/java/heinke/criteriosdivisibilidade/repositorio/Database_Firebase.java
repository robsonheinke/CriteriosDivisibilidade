package heinke.criteriosdivisibilidade.repositorio;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import heinke.criteriosdivisibilidade.model.Formulario;
import heinke.criteriosdivisibilidade.model.Usuario;

/**
 * Created by heinke on 07/09/17.
 */


public class Database_Firebase {

    private FirebaseDatabase database;

    public Database_Firebase(){
        database = FirebaseDatabase.getInstance();
    }

    public void salvarUsuario(Usuario usuario){
        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("Usuario").child(usuario.getIdFirebase()).setValue(usuario);
    }

    public void salvarFormulario(Formulario formulario){
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Formulario").child(formulario.getIdUsuario()).setValue(formulario);
    }

    public void pesquisarUsuarios(final Context context){
        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Database db = new Database(context);
                for (DataSnapshot a: dataSnapshot.getChildren()) {
                    Usuario usuario = a.getValue(Usuario.class);
                    db.controleRanking(usuario);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
