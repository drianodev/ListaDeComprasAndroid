import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ListaComprasDatabase(context: Context) : SQLiteOpenHelper(ctx = context, name = "listaCommpras.db", version = 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(
            "produtos",	true,
            "id"	to	INTEGER	+	PRIMARY_KEY	+	UNIQUE,
            "nome"	to	TEXT,
            "quantidade"	to	INTEGER,
            "valor"	to	REAL,
            "foto"	to	BLOB
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
