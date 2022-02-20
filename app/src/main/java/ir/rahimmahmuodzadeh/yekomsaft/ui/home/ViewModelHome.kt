package ir.rahimmahmuodzadeh.yekomsaft.ui.home

import androidx.lifecycle.*
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import ir.rahimmahmuodzadeh.yekomsaft.data.repository.RepositoryContact
import ir.rahimmahmuodzadeh.yekomsaft.utils.BaseViewModel
import ir.rahimmahmuodzadeh.yekomsaft.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModelHome(val repositoryContact: RepositoryContact) : BaseViewModel() {

    private val _operationsGetContact = MutableLiveData<Resource<List<Contact>>>()
    val operationsGetContact: LiveData<Resource<List<Contact>>> = _operationsGetContact

    fun getContact() {
        viewModelScope.launch {
            _operationsGetContact.value = Resource.Loading()
            repositoryContact.getContact().catch {
                _operationsGetContact.value = Resource.Error(errorText)
            }.collect {
                _operationsGetContact.value = Resource.Success(it)
            }
        }
    }

    fun deleteAll(): LiveData<Resource<String>> = liveData {
        emit(Resource.Loading())
        val deleteAll = runCatching {
            repositoryContact.deleteAll()
        }
        deleteAll.onSuccess {
            emit(Resource.Success(successText))
        }.onFailure {
            emit(Resource.Error(errorText))
        }
    }

    fun delete(contact: Contact): LiveData<Resource<String>> = liveData {
        emit(Resource.Loading())
        val delete = runCatching {
            repositoryContact.delete(contact)
        }
        delete.onSuccess {
            emit(Resource.Success(successText))
        }.onFailure {
            emit(Resource.Error(errorText))
        }
    }

    fun search(name: String): LiveData<Resource<String>> = liveData {
        emit(Resource.Loading())
        val search = runCatching {
            repositoryContact.search(name)
        }
        search.onSuccess {
            emit(Resource.Success(successText))
        }.onFailure {
            emit(Resource.Error(errorText))
        }
    }

}