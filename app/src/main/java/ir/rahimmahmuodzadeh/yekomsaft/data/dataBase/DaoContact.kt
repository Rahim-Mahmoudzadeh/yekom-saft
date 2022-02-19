package ir.rahimmahmuodzadeh.yekomsaft.data.dataBase

import androidx.room.*
import androidx.room.Dao
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoContact {
    @Insert()
    suspend fun addContact(contact: Contact)

    @Delete()
    suspend fun delete(contact: Contact)

    @Update()
    suspend fun update(contact: Contact)

    @Query("SELECT * FROM Contact")
    fun getContact(): Flow<List<Contact>>

    @Query("SELECT * FROM Contact WHERE firstName LIKE :name")
    fun search(name:String):Flow<List<Contact>>
}