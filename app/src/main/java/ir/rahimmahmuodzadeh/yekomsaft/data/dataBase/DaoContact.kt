package ir.rahimmahmuodzadeh.yekomsaft.data.dataBase

import androidx.room.*
import androidx.room.Dao
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoContact {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(contact: Contact)

    @Delete()
    suspend fun delete(contact: Contact)

    @Update()
    suspend fun update(contact: Contact)

    @Query("SELECT * FROM Contact")
    fun getContact(): Flow<List<Contact>>

    @Query("SELECT * FROM Contact WHERE number LIKE '%' || :number || '%'")
    suspend fun search(number:String):List<Contact>

    @Query("DELETE FROM Contact")
    suspend fun deleteAll()
}