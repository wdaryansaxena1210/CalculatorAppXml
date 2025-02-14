package com.example.calculatorxml

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var resultTv: TextView
    private lateinit var solutionTv: TextView
    private lateinit var buttonC: MaterialButton
    private lateinit var buttonBrackOpen: MaterialButton
    private lateinit var buttonBrackClose: MaterialButton
    private lateinit var buttonDivide: MaterialButton
    private lateinit var buttonMultiply: MaterialButton
    private lateinit var buttonPlus: MaterialButton
    private lateinit var buttonMinus: MaterialButton
    private lateinit var buttonEquals: MaterialButton
    private lateinit var button0: MaterialButton
    private lateinit var button1: MaterialButton
    private lateinit var button2: MaterialButton
    private lateinit var button3: MaterialButton
    private lateinit var button4: MaterialButton
    private lateinit var button5: MaterialButton
    private lateinit var button6: MaterialButton
    private lateinit var button7: MaterialButton
    private lateinit var button8: MaterialButton
    private lateinit var button9: MaterialButton
    private lateinit var buttonAC: MaterialButton
    private lateinit var buttonDot: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTv = findViewById(R.id.result_tv)
        solutionTv = findViewById(R.id.solution_tv)


        assignId(R.id.button_c)
        assignId(R.id.button_open_bracket)
        assignId(R.id.button_close_bracket)
        assignId(R.id.button_divide)
        assignId(R.id.button_multiply)
        assignId(R.id.button_plus)
        assignId(R.id.button_minus)
        assignId(R.id.button_equals)
        assignId(R.id.button_0)
        assignId(R.id.button_1)
        assignId(R.id.button_2)
        assignId(R.id.button_3)
        assignId(R.id.button_4)
        assignId(R.id.button_5)
        assignId(R.id.button_6)
        assignId(R.id.button_7)
        assignId(R.id.button_8)
        assignId(R.id.button_9)
        assignId(R.id.button_ac)
        assignId(R.id.button_dot)
    }

    private fun assignId(id: Int) {
        val btn: MaterialButton = findViewById(id)
        btn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val button = view as MaterialButton
        val buttonText = button.text.toString()
        var dataToCalculate = solutionTv.text.toString()

        when (buttonText) {
            "AC" -> {
                solutionTv.text = ""
                resultTv.text = "0"
                return
            }
            "=" -> {
                solutionTv.text = resultTv.text
                return
            }
            "C" -> {
                dataToCalculate = dataToCalculate.dropLast(1)
            }
            else -> {
                dataToCalculate += buttonText
            }
        }
        solutionTv.text = dataToCalculate

        val finalResult = getResult(dataToCalculate)
        if (finalResult != "Err") {
            resultTv.text = finalResult
        }
    }

    private fun getResult(data: String): String {
        return try {
            val context = Context.enter()
            context.optimizationLevel = -1
            val scriptable: Scriptable = context.initStandardObjects()
            var finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString()
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "")
            }
            finalResult
        } catch (e: Exception) {
            "Err"
        }
    }
}
