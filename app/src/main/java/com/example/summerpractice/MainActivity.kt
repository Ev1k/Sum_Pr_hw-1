package com.example.summerpractice

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var nameField: EditText? = null
    private var heightField: EditText? = null
    private var weightField: EditText? = null
    private var ageField: EditText? = null
    private var button: Button? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameField = findViewById<EditText>(R.id.name)
        heightField = findViewById(R.id.height)
        weightField = findViewById(R.id.weight)
        ageField = findViewById(R.id.age)

        button = findViewById<Button>(R.id.button)

        button?.setOnClickListener {
            var user = User(
                name = nameField?.text.toString(),
                height = heightField?.text.toString().toInt(),
                weight = weightField?.text.toString().toDouble(),
                age = ageField?.text.toString().toInt()
            )

            if (user.age == 0 || user.name == "" || user.height == 0 || user.weight == 0.0){
                button?.setText("Данные введены некорректно")}
            else {
                var value: Float = user.calcIndex().toFloat()
                button?.setText("Ответ: $value")
            }

        }
    }


    class User {
        var name: String = ""
        var height: Int = 0
        var weight: Double = 0.0
        var age: Int = 0
        var index: Double = 0.0

        constructor(name: String, height: Int, weight: Double, age: Int) {
            if (name.length in 2..50) this.name = name
            if (height in 1..249) this.height = height
            if (weight > 0 && weight < 250) this.weight = weight
            if (age in 1..149) this.age = age
        }

        fun calcIndex(): Double {
            index = this.weight / (this.height*this.height) + this.name.length -this.age.toString().length
            return index
        }

    }
}