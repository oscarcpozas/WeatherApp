package com.study.jam.weather.helper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

public class WeatherProvider extends ContentProvider {

    private static final String uri =
            "content://net.sgoliver.android.contentproviders/clientes";
    public static final Uri CONTENT_URI = Uri.parse(uri);

    private DbHelper database;
    private static final String DB_NOMBRE = "DBWeather";
    private static final int DB_VERSION = 1;
    private static final String TABLA_WEATHER = "Wheater";

    // UriMatcher, capaz de interpretar determinados patrones en una URI
    //UriMatcher
    private static final int WEATHER = 1;
    private static final int WEATHER_ID = 2;
    private static final UriMatcher uriMatcher;

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.study.jam.weather.contentproviders", "clientes", WEATHER);
        uriMatcher.addURI("com.study.jam.weather.contentproviders", "clientes/#", WEATHER_ID);
    }

    /*

    Existirán dos tipos MIME distintos para cada entidad del content provider, uno de ellos destinado
    a cuando se devuelve una lista de registros como resultado, y otro para cuando se devuelve un
    registro único concreto. De esta forma, seguiremos los siguientes patrones para definir uno
    y otro tipo de datos:

        “vnd.android.cursor.item/vnd.xxxxxx” –> Registro único
        “vnd.android.cursor.dir/vnd.xxxxxx” –> Lista de registros
     */

    @Override
    public boolean onCreate() {
        database = new DbHelper(getContext(), DB_NOMBRE, null, DB_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String where = selection;
        if(uriMatcher.match(uri) == WEATHER_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = database.getWritableDatabase();

        Cursor c = db.query(TABLA_WEATHER, projection, where,
                selectionArgs, null, null, sortOrder);

        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);

        switch (match) {
            case WEATHER:
                return "vnd.android.cursor.dir/vnd.sgoliver.cliente";
            case WEATHER_ID:
                return "vnd.android.cursor.item/vnd.sgoliver.cliente";
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == WEATHER_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = database.getWritableDatabase();

        cont = db.delete(TABLA_WEATHER, where, selectionArgs);

        return cont;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == WEATHER_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = database.getWritableDatabase();

        cont = db.update(TABLA_WEATHER, values, where, selectionArgs);

        return cont;
    }

    // Trae predefinidad algunas columnas como _ID
    public static final class WeatherColums implements BaseColumns {
        private WeatherColums() {}

        //Nombres de columnas
        public static final String COL_DAY = "day";
        public static final String COL_TEMP = "temp";

    }

}
