package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // atributos que necesitamos
        private lateinit var names: MutableList<String>
        private lateinit var mireciclerVIew : RecyclerView
        private lateinit var miAdapter:MyAdapter
        private lateinit var miLayoutManager:RecyclerView.LayoutManager
        private  var counter=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = getAllNames()
        mireciclerVIew= findViewById(R.id.recyclerView)
        miLayoutManager= GridLayoutManager(this,2)
        miAdapter= MyAdapter(names,R.layout.recycler_view_item,object :MyAdapter.OnItemClickListener{
            override fun onItemClick(name: String?, position: Int) {
               // Toast.makeText(this@MainActivity,name + " - "+ position,Toast.LENGTH_LONG).show()
                deleteName(position)
            }

        })
        mireciclerVIew.setLayoutManager(miLayoutManager)
        mireciclerVIew.setAdapter(miAdapter)
    }
    private fun getAllNames():MutableList<String>{
        return object : ArrayList<String>(){
            init{
             add("Alejandro")
             add("Jose")
             add("Barrera")
             add("Ruben")
             add("Antonio")
             add("Ricardo")
             add("Veronica")
             add("Claudiia")
             add("Ariadna")
             add("MArcos")
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         getMenuInflater().inflate(R.menu.menu_add,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.add_name -> addName(0)
            else->super.onOptionsItemSelected(item)
        }

    }

    private fun addName(posicion : Int): Boolean {
        names.add(posicion,"New name "+(++counter));
        miAdapter.notifyItemInserted(posicion)
        miLayoutManager.scrollToPosition(posicion)
        return true

    }
    private fun deleteName(posicion: Int){
        names.removeAt(posicion)
        miAdapter.notifyItemRemoved(posicion)
    }
}