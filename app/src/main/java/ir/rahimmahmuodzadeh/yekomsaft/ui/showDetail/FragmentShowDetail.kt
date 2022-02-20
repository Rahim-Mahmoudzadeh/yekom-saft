package ir.rahimmahmuodzadeh.yekomsaft.ui.showDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ir.rahimmahmuodzadeh.yekomsaft.R
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import ir.rahimmahmuodzadeh.yekomsaft.databinding.ShowDetailBinding
import ir.rahimmahmuodzadeh.yekomsaft.ui.home.ViewModelHome
import ir.rahimmahmuodzadeh.yekomsaft.utils.Constants
import ir.rahimmahmuodzadeh.yekomsaft.utils.ContactFragment
import ir.rahimmahmuodzadeh.yekomsaft.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentShowDetail : ContactFragment() {
    lateinit var binding: ShowDetailBinding
    private val viewModel: ViewModelHome by viewModel()
    var contact: Contact? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.also {
            it?.let { bundle ->
                contact = bundle.getParcelable(Constants.DATA_CONTACT)
            }
        }
        contact?.let { argument ->
            binding.email.text =
                requireContext().resources.getString(R.string.email) + " " + argument.email
            binding.tvName.text =
                requireContext().resources.getString(R.string.firstName) + " " + argument.firstName + "" + argument.lastName
            binding.tvAddress.text =
                requireContext().resources.getString(R.string.address) + " " + argument.address
            binding.tvNumber.text =
                requireContext().resources.getString(R.string.number) + " " + argument.number
            binding.ivDelete.setOnClickListener {
                viewModel.delete(argument).observe(viewLifecycleOwner) { result ->
                    result?.let { resources ->
                        when (resources) {
                            is Resource.Loading -> {
                                showProgressBar(true)
                            }
                            is Resource.Success -> {
                                showProgressBar(false)
                                showSuccessResponse(resources.data.toString())
                                findNavController().navigate(R.id.action_fragmentShowDetail_to_fragmentHome)
                            }
                            is Resource.Error -> {
                                showErrorResponse(resources.message.toString())
                                showProgressBar(false)
                            }
                        }
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        contact = null
    }
}