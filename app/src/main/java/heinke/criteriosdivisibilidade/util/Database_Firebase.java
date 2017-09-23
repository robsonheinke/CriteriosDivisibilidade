package heinke.criteriosdivisibilidade.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import heinke.criteriosdivisibilidade.model.Usuario;

/**
 * Created by heinke on 07/09/17.
 */


public class Database_Firebase {


    public static void salvarUsuario(Usuario usuario){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Usuarios");

       // myRef.child("Usuarios").child(usuario.getID()).setValue(usuario);
        databaseReference.child(usuario.getIdFirebase()).setValue(usuario);
    }

    public String pontosUsuario(String email){
        return email;
    }
}
