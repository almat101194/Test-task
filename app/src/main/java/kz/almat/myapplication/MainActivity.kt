package kz.almat.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var toSort: Button
    lateinit var toRev: Button
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var data: Data = getDataFromDb()
        var view = MainActivity()
        var controller = Controller(data, view)


        toSort = findViewById(R.id.toSort)
        toRev = findViewById(R.id.toReverse)

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
        recyclerView.adapter = MyAdapter(sArr)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    fun sortedArr(sArr: HashMap<String, Int>) {
        var sorted: Map<String, Int> = sArr.entries.sortedBy { it.value }.associate { it.toPair() }
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = MyAdapter(sorted as HashMap<String, Int>)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    fun revArr(sArr: HashMap<String, Int>) {
        var sorted: Map<String, Int> = sArr.entries.sortedBy { it.value }.associate { it.toPair() }
        var rev : Map<String, Int> = sorted.entries.sortedByDescending { it.value }.associate { it.toPair() }
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = MyAdapter(rev as HashMap<String, Int>)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

}