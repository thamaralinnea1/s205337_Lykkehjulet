package com.example.s205337lykkehjulet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.example.s205337lykkehjulet.R
import com.example.s205337lykkehjulet.model.ImageButtonCategories

class ItemAdapter(private val context: Context, private val dataset: List<ImageButtonCategories>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.category_image)
        val cardView: CardView = view.findViewById(R.id.cardview)
        //val button: Button = view.findViewById(R.id.category_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)

        holder.cardView.setOnClickListener {
            val bundle = bundleOf("Title" to item.stringResourceId)
            Navigation.findNavController(it).navigate(R.id.action_start_to_second_fragment, bundle)
            //   Toast.makeText(context, "test " + context.resources.getString(item.stringResourceId), Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int{
        return dataset.size
    }
}