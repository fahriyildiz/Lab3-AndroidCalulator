package com.zybooks.androidcalulator

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView

class MainActivity : AppCompatActivity() {
    private lateinit var result: TextView
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var scrollView: NestedScrollView
    private var value1: Double = 0.0
    private var value2: Double = 0.0

    private val historyFragment = ResultHistoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.txt_result)
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        scrollView = findViewById(R.id.scroll_view)

        registerForContextMenu(result)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, historyFragment)
            commit()
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                calculateResult("+")
                true
            }
            R.id.action_subtract -> {
                calculateResult("-")
                true
            }
            R.id.action_multiply -> {
                calculateResult("*")
                true
            }
            R.id.action_divide -> {
                calculateResult("/")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun calculateResult(operator: String) {
        if (input1.text.toString() != "" && input2.text.toString() != "") {
            value1 = input1.text.toString().toDouble()
            value2 = input2.text.toString().toDouble()
            var resultValue = 0.0
            when (operator) {
                "+" -> resultValue = value1 + value2
                "-" -> resultValue = value1 - value2
                "*" -> resultValue = value1 * value2
                "/" -> {
                    if (value2 != 0.0) {
                        resultValue = value1 / value2
                    } else {
                        AlertDialog.Builder(this).apply {
                            setTitle("Error")
                            setMessage("Cannot divide by zero")
                            setPositiveButton("OK", null)
                            show()
                        }
                    }
                }
            }
            //result.text = resultValue.toString()
            historyFragment.updateHistory("$value1 $operator $value2 = $resultValue")
        } else {
            AlertDialog.Builder(this).apply {
                setTitle("Error")
                setMessage("Please enter both values")
                setPositiveButton("OK", null)
                show()
            }
        }
        scrollView.fullScroll(View.FOCUS_DOWN)
    }
}