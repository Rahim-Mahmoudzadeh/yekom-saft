package ir.rahimmahmuodzadeh.yekomsaft.ui.addContacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.rahimmahmuodzadeh.yekomsaft.R
import ir.rahimmahmuodzadeh.yekomsaft.databinding.AddContactFragmentBinding

class AddContactFragment : Fragment() {
    lateinit var binding: AddContactFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AddContactFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}