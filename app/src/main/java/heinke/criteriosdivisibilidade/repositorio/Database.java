package heinke.criteriosdivisibilidade.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import heinke.criteriosdivisibilidade.model.Nivel;
import heinke.criteriosdivisibilidade.model.Ranking;
import heinke.criteriosdivisibilidade.model.Usuario;

/**
 * Created by heinke on 19/09/17.
 */

public class Database extends SQLiteOpenHelper {

    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "db_CriteriosDivisibilidade";

    private static final String TABELA_USUARIO = "tb_usuario";
    private static final String TABELA_NIVEL = "tb_nivel";
    private static final String TABELA_ERRO = "tb_erro";

    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_IMAGEM = "imagem";
    private static final String COLUNA_PONTOS = "pontos";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_IDFIREBASE = "id_firebase";
    private static final String COLUNA_NIVEL = "nivel";
    private static final String COLUNA_PESQUISA = "pesquisa";
    private static final String COLUNA_ORDEM = "ordem";
    private static final String COLUNA_POSICAO_BOTAO = "posicao";
    private static final String COLUNA_CRITERIO = "criterio";

    public Database(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        criarTabelaUsuario(sqlDB);
        criarTabelaErro(sqlDB);
        criarTabelaNivel(sqlDB);
    }

    private void criarTabelaUsuario(SQLiteDatabase sqlDB){
        String QUERY = createTableUsuario();

        sqlDB.execSQL(QUERY);
    }

    private void criarTabelaNivel(SQLiteDatabase sqlDB){
        String QUERY = createTableNivel();

        sqlDB.execSQL(QUERY);
    }

    private void criarTabelaErro(SQLiteDatabase sqlDB){
        String QUERY = createTableErro();

        sqlDB.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i1) {

    }

    private String createTableUsuario(){
        return "CREATE TABLE IF NOT EXISTS "+ TABELA_USUARIO + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, "
                + COLUNA_NOME + " TEXT, "
                + COLUNA_EMAIL + " TEXT, "
                + COLUNA_IMAGEM + " TEXT, "
                + COLUNA_NIVEL + " INTEGER, "
                + COLUNA_PONTOS + " TEXT, "
                + COLUNA_IDFIREBASE + " TEXT, "
                + COLUNA_PESQUISA + " TEXT )";
    }

    private String createTableNivel(){
        return "CREATE TABLE IF NOT EXISTS "+ TABELA_NIVEL + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, "
                + COLUNA_ORDEM + " TEXT, "
                + COLUNA_NIVEL + " INTEGER, "
                + COLUNA_CRITERIO +" INTEGER)";
    }

    private String createTableErro(){
        return "CREATE TABLE IF NOT EXISTS "+ TABELA_ERRO + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, "
                + COLUNA_POSICAO_BOTAO + " INTEGER, "
                + COLUNA_NIVEL + " INTEGER)";
    }

    public void adicionarNiveis(Nivel nivel){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(COLUNA_ORDEM, nivel.getOrdem());
        valores.put(COLUNA_NIVEL, nivel.getNivel());
        valores.put(COLUNA_CRITERIO,nivel.getCriterio());

        db.insert(TABELA_NIVEL,null, valores);

        db.close();
    }

    public void deletaNivel(Nivel nivel){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_NIVEL, COLUNA_ID + " = ?",new String[] {nivel.getId()});

        db.close();
    }

    public Nivel pesquisaNivelId(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] colunas = new String[]{COLUNA_ID,COLUNA_ORDEM,COLUNA_NIVEL,COLUNA_CRITERIO};

        Cursor cursor = db.query(TABELA_NIVEL,colunas,COLUNA_ID + "=" + id, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Nivel nivel = new Nivel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        return nivel;
    }

    public int numeroRegistroNivel(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select count(*) from "+ TABELA_NIVEL;

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        int total = cursor.getInt(0);

        cursor.close();
        return total;
    }

    public void adicionarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(COLUNA_NOME, usuario.getNome());
        valores.put(COLUNA_EMAIL, usuario.getEmail());
        valores.put(COLUNA_IMAGEM, usuario.getImagem());
        valores.put(COLUNA_NIVEL, usuario.getNivel() == null ? 1 : Integer.parseInt(usuario.getNivel()));
        valores.put(COLUNA_PONTOS, usuario.getPontos());
        valores.put(COLUNA_IDFIREBASE,usuario.getIdFirebase());
        valores.put(COLUNA_PESQUISA,usuario.getPesquisa());

        db.insert(TABELA_USUARIO,null, valores);

        db.close();
    }

    public void atualizarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(COLUNA_NOME, usuario.getNome());
        valores.put(COLUNA_EMAIL, usuario.getEmail());
        valores.put(COLUNA_IMAGEM, usuario.getImagem());
        valores.put(COLUNA_NIVEL, usuario.getNivel() == null ? 1 : Integer.parseInt(usuario.getNivel()));
        valores.put(COLUNA_PONTOS, usuario.getPontos());
        valores.put(COLUNA_IDFIREBASE,usuario.getIdFirebase());
        valores.put(COLUNA_PESQUISA,usuario.getPesquisa());

        db.update(TABELA_USUARIO,valores, COLUNA_IDFIREBASE + "= ?", new String[]{usuario.getIdFirebase()});
        db.close();
    }

    public void controleRanking(Usuario usuario){
        if(contarUsuarioPorId(usuario.getIdFirebase()) > 0){
            atualizarUsuario(usuario);
        }
        else{
            adicionarUsuario(usuario);
        }
    }

    public Usuario pesquisaUsuarioIdFirebase(Usuario usuario){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] colunas = new String[]{COLUNA_ID
                                        ,COLUNA_NOME
                                        ,COLUNA_EMAIL
                                        ,COLUNA_IMAGEM
                                        ,COLUNA_NIVEL
                                        ,COLUNA_PONTOS
                                        ,COLUNA_IDFIREBASE
                                        ,COLUNA_PESQUISA};

        Cursor cursor = db.query(TABELA_USUARIO,colunas,COLUNA_IDFIREBASE + "='" + usuario.getIdFirebase() + "'",null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        Usuario usuario1 = new Usuario(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_ID)))
                                       ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_IDFIREBASE))
                                       ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_NOME))
                                       ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_EMAIL))
                                       ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_IMAGEM))
                                       ,String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_NIVEL)))
                                       ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_PONTOS))
                                       ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_PESQUISA)));
        return usuario1;
    }

    public int contarUsuarioPorId(String idFirebase){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM "+ TABELA_USUARIO + " WHERE " + COLUNA_IDFIREBASE + "='" + idFirebase +"'";

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        int total = cursor.getInt(0);

        cursor.close();
        return total;
    }

    public ArrayList<Ranking> pesquisarTopDez(){

        ArrayList<Ranking> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String SQL = "SELECT * FROM " + TABELA_USUARIO + " ORDER BY " + COLUNA_PONTOS + " DESC, " + COLUNA_NIVEL + " ASC LIMIT 10";

        Cursor cursor = db.rawQuery(SQL, null);

        int i = 1;
        while(cursor.moveToNext()){
            Usuario usuario = new Usuario(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_ID)))
                                           ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_IDFIREBASE))
                                           ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_NOME))
                                           ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_EMAIL))
                                           ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_IMAGEM))
                                           ,String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_NIVEL)))
                                           ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_PONTOS)));
            Ranking ranking = new Ranking(usuario,String.valueOf(i));
            System.out.println(ranking.toString());
            lista.add(ranking);
            i++;
        }
        return lista;
    }

    public int posicao(Usuario usuario){

        SQLiteDatabase db = this.getReadableDatabase();

        String SQL = "SELECT * FROM " + TABELA_USUARIO + " ORDER BY " + COLUNA_PONTOS + " DESC, " + COLUNA_NIVEL + " ASC";

        Cursor cursor = db.rawQuery(SQL, null);

        int i = 1;
        while(cursor.moveToNext()){
            Usuario usuario1 = new Usuario(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_ID)))
                    ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_IDFIREBASE))
                    ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_NOME))
                    ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_EMAIL))
                    ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_IMAGEM))
                    ,String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_NIVEL)))
                    ,cursor.getString(cursor.getColumnIndexOrThrow(COLUNA_PONTOS)));
            if(usuario.getIdFirebase().equals(usuario1.getIdFirebase())){
                return i;
            }
            i++;
        }
        return 0;
    }

    public void deletarUsuarios(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_USUARIO, COLUNA_IDFIREBASE + " <> ?",new String[] {id});

        db.close();
    }
}
