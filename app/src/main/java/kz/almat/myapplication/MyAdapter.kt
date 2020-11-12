package kz.almat.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val comList: HashMap<String, Int>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    val list: List<Pair<String, Int>> = comList.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.firstText.text = currentItem.first
        holder.secText.text = currentItem.second.toString()
    }

    override fun getItemCount() = list.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val firstText: TextView = itemView.findViewById(R.id.firstText)
        val secText: TextView = itemView.findViewById(R.id.secText)
    }
}