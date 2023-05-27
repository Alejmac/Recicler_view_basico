package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent
import org.w3c.dom.Text

class MyAdapter constructor(var names:List<String>,var layout:Int, var itemClickListener: OnItemClickListener) : RecyclerView.Adapter<MyAdapter.ViewHolder?>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // se utiliza para crear la vista sin persoalizar y dicha vista se envia al constructor de la clase
        val view:View = LayoutInflater.from(parent.context).inflate(layout, parent,false)
        val vh = ViewHolder(view)// esa vita se la enviamos al viewholder
        return vh
    }

    override fun getItemCount(): Int {
        return names.size; // nos regresa la cnatidad
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        holder.bind(names.get(position),itemClickListener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtViewName:TextView= itemView.findViewById<View>(R.id.textViewName) as TextView
         //
         fun bind (name: String?, itemListener: OnItemClickListener){
             txtViewName.text= name
             itemView.setOnClickListener{
                 itemListener.onItemClick(name , absoluteAdapterPosition)
             }
         }

    }

    interface OnItemClickListener{
        fun onItemClick(name :String? , position: Int)
    }
}