package io.hussienfahmy.mefab.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.hussienfahmy.mefab.fabs.OnEdgeFabClickListener
import io.hussienfahmy.mefab.sample.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.meFab.setOnEdgeFabClickListener(OnEdgeFabClickListener { id ->
            Toast.makeText(
                context,
                when (id) {
                    R.id.menu_add -> "Add Clicked"
                    R.id.menu_check -> "Check Mark Clicked"
                    R.id.menu_clear -> "Clear Clicked"
                    else -> ""
                }, Toast.LENGTH_SHORT
            ).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}