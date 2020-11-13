package kz.almat.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {
    lateinit var itemText: TextView
    lateinit var sItemText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        itemText = findViewById(R.id.disp_text)
        sItemText = findViewById(R.id.disp_sText)
        var smth = "Smth not set"
        var sSmth = "sSmth not set"

        var extras = intent.extras
        if (extras != null) {
            smth = extras.getString("itemFirstName").toString()
            sSmth = extras.getString("itemSecondName").toString()
        }

        itemText.text = smth
        sItemText.text = sSmth

    }
}