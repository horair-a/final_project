package com.shawonshagor0.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shawonshagor0.calculator.databinding.ActivityMainBinding
import kotlin.math.min
import com.udojava.evalex.Expression
import com.udojava.evalex.Expression.ExpressionException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fromEqual = false

        fun evaluateExpression(expression: String): String {

            return try {
                Expression(expression).eval().toDouble().toString()
            } catch (e: ExpressionException) {
                // Handle ExpressionException (e.g., invalid expression)
                "Invalid"
            } catch (e: NumberFormatException) {
                // Handle NumberFormatException (e.g., invalid number format)
                "Invalid"
            } catch (e: Exception) {
                // Handle other exceptions
                "Invalid"
            }
        }


        fun pressBtn(btn: Int) {
            //Append Section
            if (fromEqual) {
                binding.tvDisp.text = btn.toString()
                fromEqual = false
            } else {
                if (btn == 0) {
                    if (binding.tvDisp.text.toString() != "0") {
                        binding.tvDisp.append(btn.toString())
                    }
                } else {
                    if (binding.tvDisp.text.toString() == "0") {
                        binding.tvDisp.text = btn.toString()
                    } else {
                        binding.tvDisp.append(btn.toString())
                    }
                }
            }
        }

        binding.btn0.setOnClickListener {
            pressBtn(0)
        }
        binding.btn1.setOnClickListener {
            pressBtn(1)
        }
        binding.btn2.setOnClickListener {
            pressBtn(2)
        }
        binding.btn3.setOnClickListener {
            pressBtn(3)
        }
        binding.btn4.setOnClickListener {
            pressBtn(4)
        }
        binding.btn5.setOnClickListener {
            pressBtn(5)
        }
        binding.btn6.setOnClickListener {
            pressBtn(6)
        }
        binding.btn7.setOnClickListener {
            pressBtn(7)
        }
        binding.btn8.setOnClickListener {
            pressBtn(8)
        }
        binding.btn9.setOnClickListener {
            pressBtn(9)
        }
        binding.btnBS.setOnClickListener {

            if (binding.tvDisp.text.length == 1) {
                binding.tvDisp.text = "0"
            } else if (binding.tvDisp.text.toString() == "Invalid") {
                binding.tvDisp.text = "0"
            } else {
                binding.tvDisp.text = binding.tvDisp.text.dropLast(1)
            }
        }
        binding.btnCE.setOnClickListener {
            binding.tvDisp.text = "0"
        }

        fun signPressed(btn: String) {
            var lastChar = binding.tvDisp.text.toString().last()
            if ((lastChar == '+') or (lastChar == '-') or (lastChar == '*') or (lastChar == '/')) {
                binding.tvDisp.text = binding.tvDisp.text.dropLast(1)
            }
            binding.tvDisp.append(btn)
            fromEqual = false
        }

        binding.btnPls.setOnClickListener {
            signPressed("+")
        }
        binding.btnMin.setOnClickListener {
            signPressed("-")
        }
        binding.btnMul.setOnClickListener {
            signPressed("*")
        }
        binding.btnDiv.setOnClickListener {
            signPressed("/")
        }

        binding.btnEql.setOnClickListener {
            binding.tvDisp.text = evaluateExpression(binding.tvDisp.text.toString())
            if (binding.tvDisp.text.takeLast(2).toString() == ".0") {//reducing .0 at last
                binding.tvDisp.text = binding.tvDisp.text.dropLast(2)
            }
            fromEqual = true
        }

        //accommodate all digits::
    }
}