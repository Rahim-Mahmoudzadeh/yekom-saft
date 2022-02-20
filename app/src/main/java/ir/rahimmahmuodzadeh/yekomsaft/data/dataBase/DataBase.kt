package ir.rahimmahmuodzadeh.yekomsaft.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import ir.rahimmahmuodzadeh.yekomsaft.utils.Constants

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): DaoContact

    companion object {
        private var instance: DataBase? = null
        fun getInstance(context: Context): DataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .also {
                        instance = it
                    }
            }
        }

        private fun buildDatabase(context: Context): DataBase {
            return Room.databaseBuilder(context, DataBase::class.java, Constants.DATA_BASE).build()
        }
    }
}