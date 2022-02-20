package ir.rahimmahmuodzadeh.yekomsaft.ui.addContacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import ir.rahimmahmuodzadeh.yekomsaft.data.repository.RepositoryContact
import ir.rahimmahmuodzadeh.yekomsaft.utils.BaseViewModel
import ir.rahimmahmuodzadeh.yekomsaft.utils.Resource

class ViewModelContact(val repositoryContact: RepositoryContact) : BaseViewModel() {

    fun addTask(contact: Contact): LiveData<Resource<String>> = liveData {
        emit(Resource.Loading())
        val insert = runCatching {
            repositoryContact.addContact(contact)
        }
        insert.onSuccess {
            emit(Resource.Success(successText))
        }.onFailure {
            emit(Resource.Error(errorText))
        }
    }

    fun update(contact: Contact): LiveData<Resource<String>> = liveData {
        emit(Resource.Loading())
        val update = runCatching {
            repositoryContact.update(contact)
        }
        update.onSuccess {
            emit(Resource.Success(successText))
        }.onFailure {
            emit(Resource.Error(errorText))
        }
    }
}