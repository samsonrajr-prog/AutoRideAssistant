package com.autorideassistant

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private val bg = Color.rgb(18, 20, 28)
    private val card = Color.rgb(32, 36, 48)
    private val white = Color.WHITE
    private val green = Color.rgb(0, 200, 130)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(20, 25, 20, 20)
        root.setBackgroundColor(bg)

        val scroll = ScrollView(this)
        val content = LinearLayout(this)
        content.orientation = LinearLayout.VERTICAL

        val title = TextView(this)
        title.text = "AUTO RIDE ASSISTANT"
        title.textSize = 23f
        title.setTextColor(green)
        title.gravity = Gravity.CENTER
        title.setPadding(0, 15, 0, 25)
        content.addView(title)

        addLabel(content, "SELECT RIDE APPS")

        val rapido = CheckBox(this)
        rapido.text = "Rapido"
        rapido.setTextColor(white)
        rapido.isChecked = true
        content.addView(rapido)

        val ola = CheckBox(this)
        ola.text = "Ola"
        ola.setTextColor(white)
        ola.isChecked = true
        content.addView(ola)

        addLabel(content, "ACCEPTANCE MODE")

        val mode = Spinner(this)
        mode.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("Manual", "Automatic")
        )
        content.addView(mode)

        addLabel(content, "MINIMUM FARE (₹)")
        val minFare = edit("50")
        content.addView(minFare)

        addLabel(content, "MAXIMUM FARE (₹)")
        val maxFare = edit("500")
        content.addView(maxFare)

        addLabel(content, "MAXIMUM DISTANCE (KM)")
        val distance = edit("10")
        content.addView(distance)

        val start = Button(this)
        start.text = "START MONITORING"
        start.setTextColor(white)
        start.setBackgroundColor(green)
        content.addView(start)

        val status = TextView(this)
        status.text = "Status: Not monitoring"
        status.setTextColor(green)
        status.textSize = 16f
        status.setPadding(0, 20, 0, 20)
        content.addView(status)

        start.setOnClickListener {
            if (!rapido.isChecked && !ola.isChecked) {
                status.text = "Select Rapido or Ola first"
            } else {
                status.text = "Settings ready. Ride detection not connected yet."
            }
        }

        addLabel(content, "RIDE HISTORY")
        addText(content, "No rides recorded yet.")

        scroll.addView(content)
        root.addView(scroll)

        setContentView(root)
    }

    private fun addLabel(parent: LinearLayout, text: String) {
        val label = TextView(this)
        label.text = text
        label.textSize = 15f
        label.setTextColor(green)
        label.setPadding(0, 22, 0, 8)
        parent.addView(label)
    }

    private fun addText(parent: LinearLayout, text: String) {
        val view = TextView(this)
        view.text = text
        view.textSize = 14f
        view.setTextColor(white)
        parent.addView(view)
    }

    private fun edit(value: String): EditText {
        val input = EditText(this)
        input.setText(value)
        input.setTextColor(white)
        input.setHintTextColor(Color.GRAY)
        input.setSingleLine(true)
        return input
    }
}
