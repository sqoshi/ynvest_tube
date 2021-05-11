package com.app.client.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.client.fragments.auctions.AuctionListFragment
import com.app.client.fragments.profile.ProfileFragment
import com.app.client.model.Auction

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val auctionClickListener: (Auction) -> Unit
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> AuctionListFragment(auctionClickListener)
            else -> throw IllegalArgumentException()
        }
    }
}