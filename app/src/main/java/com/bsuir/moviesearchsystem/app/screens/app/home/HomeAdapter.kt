package com.bsuir.moviesearchsystem.app.screens.app.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bsuir.moviesearchsystem.app.dto.utils.Role

class HomeAdapter (fragmentActivity: FragmentActivity, var role: String?) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        var count = when (role) {
            Role.ROLE_ADMIN.name -> {
                2
            }
            Role.ROLE_USER.name, Role.ROLE_SUBSCRIBER.name -> {
                1
            }
            else -> {0}
        }
        return count
    }

    override fun createFragment(position: Int): Fragment {
        return HomeForViewingPage2Fragment.newInstance(position)
    }
}