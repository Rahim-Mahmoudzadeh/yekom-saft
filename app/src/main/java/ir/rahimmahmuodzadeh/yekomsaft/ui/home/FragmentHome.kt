package ir.rahimmahmuodzadeh.yekomsaft.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.rahimmahmuodzadeh.yekomsaft.R
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import ir.rahimmahmuodzadeh.yekomsaft.databinding.HomeFragmentBinding
import ir.rahimmahmuodzadeh.yekomsaft.utils.Constants
import ir.rahimmahmuodzadeh.yekomsaft.utils.ContactFragment
import ir.rahimmahmuodzadeh.yekomsaft.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentHome : ContactFragment(), AdapterHome.Detail {
    lateinit var binding: HomeFragmentBinding
    lateinit var homeAdapterHome: AdapterHome
    val viewModel: ViewModelHome by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeAdapterHome = AdapterHome(this)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHome_to_addContactFragment)
        }
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = homeAdapterHome

        binding.ivHomeDeleteAllContant.setOnClickListener {
            deleteAll()
        }
        binding.etHomeSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.isNullOrEmpty()) {
                    getContent()
                } else {
                    search(s.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    override fun onResume() {
        super.onResume()
        getContent()

    }

    private fun getContent() {
        viewModel.operationsGetContact.observe(viewLifecycleOwner) { Contact ->
            Contact?.let { resources ->
                when (resources) {
                    is Resource.Loading -> {
                        showProgressBar(true)
                    }
                    is Resource.Success -> {
                        showProgressBar(false)
                        homeAdapterHome.contact = resources.data as ArrayList<Contact>
                        chekEmpty(resources.data)
                    }
                    is Resource.Error -> {
                        showProgressBar(false)
                    }
                }
            }
        }
    }

    private fun chekEmpty(listContent: List<Contact>) {
        if (listContent.isEmpty()) {
            binding.rlHomeEmpty.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.rlHomeEmpty.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    override fun show(contact: Contact) {
        val argument = Bundle().apply {
            putParcelable(Constants.DATA_CONTACT, contact)
        }
        findNavController().navigate(R.id.action_fragmentHome_to_fragmentShowDetail, argument)
    }

    override fun update(contact: Contact) {
        val argument = Bundle().apply {
            putParcelable(Constants.DATA_CONTACT, contact)
            putString(Constants.UPDATE, Constants.UPDATE)
        }
        findNavController().navigate(R.id.action_fragmentHome_to_addContactFragment, argument)
    }

    private fun deleteAll() {
        viewModel.deleteAll().observe(viewLifecycleOwner) { result ->
            result?.let { resources ->
                when (resources) {
                    is Resource.Loading -> {
                        showProgressBar(true)
                    }
                    is Resource.Success -> {
                        showProgressBar(false)
                        showSuccessResponse(resources.data.toString())
                    }
                    is Resource.Error -> {
                        showProgressBar(false)
                        showErrorResponse(resources.message.toString())
                    }
                }
            }
        }
    }
    fun search(number:String){
        viewModel.search(number).observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar(true)
                    }
                    is Resource.Success -> {
                        showProgressBar(false)
                        homeAdapterHome.contact = result.data as ArrayList<Contact>
                    }
                    is Resource.Error -> {
                        showProgressBar(false)
                        showErrorResponse(result.message.toString())
                    }
                }
            }
        }

    }

}