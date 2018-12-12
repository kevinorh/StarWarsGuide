package upc.edu.pe.starwarsguide.Network

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class StarWarsApi{
    companion object {
        val baseUrl = "https://swapi.co/api"
        val people = "$baseUrl/people/"

        fun getCharacters(page: String,
                      responseHandler: (CharactersResponse?) -> Unit,
                      errorHandler: (ANError?) -> Unit) {
            AndroidNetworking.get(StarWarsApi.people).addQueryParameter("page",page)
                .setPriority(Priority.LOW)
                .setTag("StarWarsGuide")
                .build()
                .getAsObject(CharactersResponse::class.java,
                    object : ParsedRequestListener<CharactersResponse> {
                        override fun onResponse(response: CharactersResponse?) {
                            responseHandler(response)
                        }
                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }
    }
}