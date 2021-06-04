package com.app.ynvest_tube.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.ynvest_tube.R
import com.app.ynvest_tube.adapters.AuctionsAdapter
import com.app.ynvest_tube.model.Auction
import com.app.ynvest_tube.refresher.AuctionListSubscriber
import com.app.ynvest_tube.refresher.DataRefresher
import com.app.ynvest_tube.repository.Repository

class AuctionListFragment(private val auctionClickListener: (Auction) -> Unit) : Fragment() {

    private val dataRefresher = DataRefresher()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerEmpty: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createdView = inflater.inflate(R.layout.fragment_auction_list, container, false)
        recyclerEmpty = createdView.findViewById(R.id.auctionsRecyclerEmpty)
        recyclerView = createdView.findViewById(R.id.auctionsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = AuctionsAdapter(auctionClickListener, resources)
        recyclerView.isNestedScrollingEnabled = false
        dataRefresher.subscribeToAuctionListEndpoint("auctionListView", ::auctionsObtained)
        return createdView
    }

    private fun auctionsObtained(auctions: ArrayList<Auction>) {
        (recyclerView.adapter as AuctionsAdapter).dataSet = auctions
        recyclerView.adapter?.notifyDataSetChanged()

        if (auctions.size > 0) {
            recyclerView.visibility = View.VISIBLE
            recyclerEmpty.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            recyclerEmpty.visibility = View.VISIBLE
        }
    }
}