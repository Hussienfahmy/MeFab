package com.hfahmy.mefab.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hfahmy.mefab.EdgeFloatingActionButton
import com.hfahmy.mefab.sample.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var toast: Toast

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toast = Toast(context).apply {
            duration = Toast.LENGTH_SHORT
        }
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.meFab.setOnEdgeClickListener(EdgeFloatingActionButton.OnClickListener { id ->
            toast.cancel()
            val string =  when(id) {
                R.id.menu_add -> "Add Clicked"
                R.id.menu_check -> "Check Mark Clicked"
                R.id.menu_clear -> "Clear Clicked"
                else -> ""
            }
            toast.setText(string)
            toast.show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}