package ir.rahimmahmuodzadeh.yekomsaft.ui.addContacts

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ir.rahimmahmuodzadeh.yekomsaft.R
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import ir.rahimmahmuodzadeh.yekomsaft.databinding.AddContactFragmentBinding
import ir.rahimmahmuodzadeh.yekomsaft.utils.Constants
import ir.rahimmahmuodzadeh.yekomsaft.utils.ContactFragment
import ir.rahimmahmuodzadeh.yekomsaft.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddContactFragment : ContactFragment() {
    lateinit var binding: AddContactFragmentBinding
    private val viewModelAdd: ViewModelAddContact by viewModel()
    var contact: Contact? = null
    var update: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AddContactFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.also {
            it?.let { bundle ->
                contact = bundle.getParcelable(Constants.DATA_CONTACT)
                update = bundle.getString(Constants.UPDATE)
            }
        }
        if (!update.isNullOrEmpty() && update == Constants.UPDATE) {
            binding.button.text = requireActivity().resources.getString(R.string.update)
            contact?.let { contact ->
                binding.tieFirstName.text =
                    Editable.Factory.getInstance().newEditable(contact.firstName)
                binding.tieLastName.text =
                    Editable.Factory.getInstance().newEditable(contact.lastName)
                binding.tieNumber.text = Editable.Factory.getInstance().newEditable(contact.number)
                binding.tieAddress.text =
                    Editable.Factory.getInstance().newEditable(contact.address)
                binding.tieEmail.text = Editable.Factory.getInstance().newEditable(contact.email)

            }
        }
        binding.button.setOnClickListener {
            if (!checkEmpty()) {
                setContact()
            }
        }
    }

    private fun setContact() {

        if (!update.isNullOrEmpty() && update == Constants.UPDATE) {
            contact?.let { contact ->
                update(contact)
            }
        } else {
            addContact()
        }
    }

    private fun checkEmpty(): Boolean {
        var errorTextField = 0
        if (binding.tieFirstName.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.etFirstNameAdd.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.etFirstNameAdd.error = ""
        }
        if (binding.tieLastName.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.etLastNameAdd.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.etLastNameAdd.error = ""
        }
        if (binding.tieNumber.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.etNumberAdd.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else if (binding.tieNumber.text.toString().length < 11 && !binding.tieNumber.text.toString()
                .substring(
                    0,
                    1
                ).equals("09")
        ) {
            errorTextField = errorTextField.plus(1)
            binding.etNumberAdd.error =
                requireContext().resources.getString(R.string.error_text_phone)

        } else {
            binding.etNumberAdd.error = ""
        }
        if (binding.tieAddress.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.etAddressAdd.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.etAddressAdd.error = ""
        }
        if (binding.tieEmail.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.etEmailAdd.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.etEmailAdd.error = ""
        }

        return errorTextField > 0
    }

    override fun onDestroy() {
        super.onDestroy()
        contact = null
        update = null
    }

    fun update(contact: Contact) {
        contact.firstName = binding.tieFirstName.text.toString()
        contact.lastName = binding.tieLastName.text.toString()
        contact.number = binding.tieNumber.text.toString()
        contact.address = binding.tieAddress.text.toString()
        contact.email = binding.tieEmail.text.toString()
        viewModelAdd.update(contact).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        showProgressBar(true)
                    }
                    is Resource.Success -> {
                        showSuccessResponse(resource.data.toString())
                        findNavController().navigate(R.id.action_addContactFragment_to_fragmentHome)
                        showProgressBar(false)
                    }
                    is Resource.Error -> {
                        showErrorResponse(resource.message.toString())
                        showProgressBar(false)
                    }
                }
            }
        }
    }

    fun addContact() {
        var contact =
            Contact(
                binding.tieFirstName.text.toString(),
                binding.tieLastName.text.toString(),
                binding.tieNumber.text.toString(),
                binding.tieAddress.text.toString(),
                binding.tieEmail.text.toString()
            )
        viewModelAdd.addTask(contact).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        showProgressBar(true)
                    }
                    is Resource.Success -> {
                        showSuccessResponse(resource.data.toString())
                        findNavController().navigate(R.id.action_addContactFragment_to_fragmentHome)
                        showProgressBar(false)
                    }
                    is Resource.Error -> {
                        showErrorResponse(resource.message.toString())
                        showProgressBar(false)
                    }
                }
            }
        }
    }

}