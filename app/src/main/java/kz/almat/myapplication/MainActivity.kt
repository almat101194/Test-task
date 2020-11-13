package kz.almat.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MyAdapter.OnItemClickListener{
    lateinit var toSort: Button
    lateinit var toRev: Button
    lateinit var recyclerView: RecyclerView
    lateinit var toSearch: SearchView
//    val data = Data(Somedata.somePassArr)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var data: Data = getDataFromDb()
        var view = MainActivity()
        var controller = Controller(data, view)

        toSort = findViewById(R.id.toSort)
        toRev = findViewById(R.id.toReverse)
        toSearch = findViewById(R.id.item_search)

        displayTable(controller.getAllData())

        toSort.setOnClickListener{
            sortedArr(controller.getAllData())
        }

        toRev.setOnClickListener{
            revArr(controller.getAllData())
        }



    }

    private fun getDataFromDb(): Data {
        return Data(Somedata.somePassArr)
    }

    fun displayTable(sArr: HashMap<String, Int>) {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = MyAdapter(sArr, this)  // 'instance
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        toSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                (recyclerView.adapter as MyAdapter).filter.filter(p0)
                return false
            }
        })
    }

    fun sortedArr(sArr: HashMap<String, Int>) {
        var sorted: Map<String, Int> = sArr.entries.sortedBy { it.value }.associate { it.toPair() }
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = MyAdapter(sorted as HashMap<String, Int>, this) // 'instance
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        toSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                (recyclerView.adapter as MyAdapter).filter.filter(p0)
                return false
            }
        })
    }

    fun revArr(sArr: HashMap<String, Int>) {
        var sorted: Map<String, Int> = sArr.entries.sortedBy { it.value }.associate { it.toPair() }
        var rev : Map<String, Int> = sorted.entries.sortedByDescending { it.value }.associate { it.toPair() }
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = MyAdapter(rev as HashMap<String, Int>, this) // 'instance
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        toSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                (recyclerView.adapter as MyAdapter).filter.filter(p0)
                return false
            }
        })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DisplayActivity::class.java)
        intent.putExtra("itemFirstName", (recyclerView.adapter as MyAdapter).cloneList[position].first)
        intent.putExtra("itemSecondName", (recyclerView.adapter as MyAdapter).cloneList[position].second.toString())
        startActivity(intent)
    }
}