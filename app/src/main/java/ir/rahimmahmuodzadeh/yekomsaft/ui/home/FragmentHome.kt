package ir.rahimmahmuodzadeh.yekomsaft.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.rahimmahmuodzadeh.yekomsaft.R
import ir.rahimmahmuodzadeh.yekomsaft.databinding.HomeFragmentBinding


class FragmentHome : Fragment() {
    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}