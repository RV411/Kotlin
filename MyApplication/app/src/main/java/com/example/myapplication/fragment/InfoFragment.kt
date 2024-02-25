package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var page=0
    companion object {
        private const val ARG_NUMBER = "number"
        @JvmStatic
        fun newInstance(number:Int ) = InfoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_NUMBER,number)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(ARG_NUMBER)
        }
    }

    private lateinit var binding : FragmentInfoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentInfoBinding.inflate(inflater,container,false)
        setScreen()
        return binding.root
    }

    private fun setScreen(){
        when(page){
            1 -> setData(R.drawable.background2,R.string.title_1,R.string.text_info1)
            2 -> setData(R.drawable.image_1,R.string.title_2,R.string.text_info2)
            3 -> setData(R.drawable.ic_bt_menu, R.string.title_3,R.string.text_info3)
        }
    }

    private fun setData(image:Int,title:Int,text:Int){
        binding.image = ContextCompat.getDrawable(requireActivity(),image)
        binding.title = getString(title)
        binding.text = getString(text)
    }
}