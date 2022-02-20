package ir.rahimmahmuodzadeh.yekomsaft.data.repository

import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import kotlinx.coroutines.flow.Flow

interface RepositoryContact {

    suspend fun getContact(): Flow<List<Contact>>

    suspend fun addContact(contact: Contact)

    suspend fun deleteAll()

    suspend fun delete(contact: Contact)

    suspend fun update(contact: Contact)

    suspend fun search(name: String): Flow<List<Contact>>
}