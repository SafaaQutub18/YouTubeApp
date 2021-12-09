package com.safaa.youtubeapp


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.safaa.youtubeapp.databinding.RowRecyclerviewBinding

class RecyclerViewAdapter(var delegation : IdVideoInterface) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(val binding: RowRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    var videoList: ArrayList<Video> = ArrayList()

    fun setvideoList(messageArrayList: ArrayList<Video>) {
        this.videoList = messageArrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(RowRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var currentVideo = videoList[position]
        holder.binding.apply {
            videoNameTV.text = currentVideo.name
            //videoImageIV.setImageResource(R.drawable.)

        }
        holder.binding.videoItem.setOnClickListener {
            delegation.setVideoId(position)
            Log.d("MainAdapter" , "item clicked")
        }
    }
    override fun getItemCount() = videoList.size

}

interface IdVideoInterface {
    fun setVideoId(position:Int)
}