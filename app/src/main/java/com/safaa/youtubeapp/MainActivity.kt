package com.safaa.youtubeapp

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.safaa.youtubeapp.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class MainActivity : AppCompatActivity() , IdVideoInterface {

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityMainBinding
    val myAdapter : RecyclerViewAdapter by lazy { RecyclerViewAdapter(this) }

    lateinit var youTubePlayerView : YouTubePlayerView
    var myVideoList : ArrayList<Video> = ArrayList()
    var selectedVideo = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork?.isConnectedOrConnecting == true) {


            // Adapter setting
            binding.recyclerV.adapter = myAdapter
            binding.recyclerV.layoutManager = LinearLayoutManager(this)

            myVideoList.add(Video("Beautiful Adhan from Masallah al-Aqsa","z2xEwSi2vaI"))
            myVideoList.add(Video("Masjid Al-Haram Makkah Saudi Arabia - The Great Mosque of Mecca","Ry_RV9RDxv4"))
            myVideoList.add(Video("Quran recitation to The Prophet Moses and Pharaoh story by Raad alkurdi","JWt5_NJuhME"))
            myVideoList.add(Video("Makkah Beautiful Adhan 2019","ATH-b2EH5wg"))
            myVideoList.add(Video("Abo Mazen song","kvEc6PrSLCw"))


            myAdapter.setvideoList(myVideoList)


        }
        else
        {
            println("Not Connected To The Internet")
        }
    }

    override fun setVideoId(position:Int){
        selectedVideo = position

        Log.d("setVideoId" , myVideoList[position].id)
        youTubePlayerView = binding.youtubePlayerView
        //getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = myVideoList[position].id

                youTubePlayer.loadVideo(myVideoList[position].id , 0f)
            }
        })

    }
}