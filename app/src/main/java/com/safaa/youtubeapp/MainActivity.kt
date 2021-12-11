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
    lateinit var mainYouTubePlayer: YouTubePlayer
    var myVideoList : ArrayList<Video> = ArrayList()
    var selectedVideo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        youTubePlayerView = binding.youtubePlayerView
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork?.isConnectedOrConnecting == true) {


            // Adapter setting
            binding.recyclerV.adapter = myAdapter
            binding.recyclerV.layoutManager = LinearLayoutManager(this)

            myVideoList.add(Video("Beautiful Adhan from Masallah al-Aqsa","z2xEwSi2vaI"))
            myVideoList.add(Video("Android Studio - Failed to apply plugin [id 'com.android.application']","sbAPjH2L0Fg"))
            myVideoList.add(Video("Quran recitation to The Prophet Moses and Pharaoh story by Raad alkurdi","JWt5_NJuhME"))
            myVideoList.add(Video("Makkah Beautiful Adhan 2019","ATH-b2EH5wg"))
            myVideoList.add(Video("Abo Mazen song","kvEc6PrSLCw"))


            myAdapter.setvideoList(myVideoList)

            Log.d("setVideoId" , selectedVideo.toString())

            //getLifecycle().addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = selectedVideo
                    mainYouTubePlayer = youTubePlayer
                    mainYouTubePlayer.loadVideo(myVideoList[selectedVideo].id , 0f)
                }
            })


        }
        else
        {
            println("Not Connected To The Internet")
        }
    }

    override fun setVideoId(position:Int){
        selectedVideo = position
        mainYouTubePlayer.loadVideo(myVideoList[selectedVideo].id , 0f)


    }
}