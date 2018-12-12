package upc.edu.pe.starwarsguide.ViewController.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.fragment_characters.view.*
import upc.edu.pe.starwarsguide.Model.Character
import upc.edu.pe.starwarsguide.Network.CharactersResponse
import upc.edu.pe.starwarsguide.Network.StarWarsApi

import upc.edu.pe.starwarsguide.R
import upc.edu.pe.starwarsguide.ViewController.Adapters.CharactersAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CharactersFragment : Fragment() {
    var characters = ArrayList<Character>()
    var currentPage: Int = 1
    lateinit var charactersRecyclerView: RecyclerView
    lateinit var charactersAdapter: CharactersAdapter
    lateinit var charactersLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        charactersRecyclerView = view.charactersRecyclerView
        charactersAdapter = CharactersAdapter(characters, view.context)
        charactersLayoutManager = GridLayoutManager(view.context, 2) as RecyclerView.LayoutManager
        charactersRecyclerView.adapter = charactersAdapter
        charactersRecyclerView.layoutManager = charactersLayoutManager
        do {
            println("Current Page: $currentPage")
            StarWarsApi.getCharacters(currentPage.toString(),
                {response -> handleResponse(response)},
                {error -> handleError(error)})
            currentPage++
        }while(currentPage <= 9)

        return view
    }

    private fun handleResponse(response: CharactersResponse?) {
        characters.addAll(response!!.results!!)
        Log.d("StarWarsGuide", "Found ${characters.size} characters")
        //println("Characters: $characters")
        charactersAdapter.characters = characters
        charactersAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?) {
        Log.d("StarWarsGuide", anError!!.message)
    }
}
