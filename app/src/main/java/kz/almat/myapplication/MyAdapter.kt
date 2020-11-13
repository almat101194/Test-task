package kz.almat.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MyAdapter(private val comList: HashMap<String, Int>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(), Filterable {

    val list: List<Pair<String, Int>> = comList.toList()
    var cloneList = HashMap<String, Int>().toList()
    init {
        cloneList = comList.toList()
    }
//    var cloneList = comList.toList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cloneList[position]
        holder.firstText.text = currentItem.first
        holder.secText.text = currentItem.second.toString()
    }

    override fun getItemCount() = cloneList.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val firstText: TextView = itemView.findViewById(R.id.firstText)
        val secText: TextView = itemView.findViewById(R.id.secText)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if(charSearch.isEmpty()){
                    cloneList = comList.toList()
                }else {
                    var resultList:HashMap<String, Int> = HashMap<String, Int>()
                    for(row in comList){
                        if(row.key.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)) ||
                            row.value.toString().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))){
                            resultList.put(row.key, row.value)
                        }
                    }
                    cloneList = resultList.toList()
                }
                val filterResults = FilterResults()
                filterResults.values = cloneList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                cloneList = results?.values as List<Pair<String, Int>>
                Log.d("Check", cloneList.toString())
                notifyDataSetChanged()
            }
        }
    }
}