package ir.rahimmahmuodzadeh.yekomsaft.data.repository

import ir.rahimmahmuodzadeh.yekomsaft.data.dataBase.DataBase
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import kotlinx.coroutines.flow.Flow

class RepositoryImplContact(val dataBase: DataBase) : RepositoryContact {
    override suspend fun getContact(): Flow<List<Contact>> = dataBase.getDao().getContact()

    override suspend fun addContact(contact: Contact) = dataBase.getDao().addContact(contact)

    override suspend fun deleteAll() = dataBase.getDao().deleteAll()

    override suspend fun delete(contact: Contact) = dataBase.getDao().delete(contact)

    override suspend fun update(contact: Contact) = dataBase.getDao().update(contact)

    override suspend fun search(number: String): List<Contact> = dataBase.getDao().search(number)
}