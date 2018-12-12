package upc.edu.pe.starwarsguide.ViewController.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_character.view.*
import upc.edu.pe.starwarsguide.Model.Character
import upc.edu.pe.starwarsguide.R

class CharactersAdapter(var characters: ArrayList<Character>, val context: Context) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters.get(position)
        holder.updateFrom(character)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pictureImageView = view.pictureImageView
        val nameTextView = view.nameTextView
        val characterLayout = view.characterLayout
        fun updateFrom(character: Character) {
            nameTextView.text = character.name
            var id = character.url!!.drop(28)
            id = id.dropLast(1)
            pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher)
            pictureImageView.setErrorImageResId(R.mipmap.ic_launcher)
            pictureImageView.setImageUrl("https://starwars-visualguide.com/assets/img/characters/$id.jpg")

        }
    }
}