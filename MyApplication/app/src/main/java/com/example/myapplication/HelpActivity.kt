package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.myapplication.adapter.AdapterViewPagerGeneric
import com.example.myapplication.databinding.ActivityHelpBinding
import com.example.myapplication.fragment.InfoFragment
import com.example.myapplication.utils.ToolbarActivity

class HelpActivity : ToolbarActivity(), View.OnClickListener {

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityHelpBinding>(this,R.layout.activity_help) }
    private val adapterPager  by lazy{ AdapterViewPagerGeneric(supportFragmentManager,lifecycle) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding.clickListener = this

        adapterPager.addFragment(InfoFragment.newInstance(1))
        adapterPager.addFragment(InfoFragment.newInstance(2))
        adapterPager.addFragment(InfoFragment.newInstance(3))
        binding.viewPager.adapter = adapterPager
        binding.indicator.setViewPager(binding.viewPager)

    }

    override fun onClick(v: View?) {

    }
}