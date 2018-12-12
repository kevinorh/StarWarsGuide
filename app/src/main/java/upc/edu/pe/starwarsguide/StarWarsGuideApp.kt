package upc.edu.pe.starwarsguide

import android.app.Application
import com.androidnetworking.AndroidNetworking

class StarWarsGuideApp: Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(getApplicationContext());
    }
}