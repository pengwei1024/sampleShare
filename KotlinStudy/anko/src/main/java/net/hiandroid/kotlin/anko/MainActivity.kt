package net.hiandroid.kotlin.anko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            padding = dip(30)
            editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
            }
            button("Login") {
                textSize = 26f
            }
            button("alert") {
                setOnClickListener {
                    alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
                        yesButton { toast("Oh…") }
                        noButton {}
                    }.show()
                }
            }
            button("selector") {
                setOnClickListener {
                    val countries = listOf("Russia", "USA", "Japan", "Australia")
                    selector("Where are you from?", countries) { dialogInterface, i ->
                        toast("So you're living in ${countries[i]}, right?")
                    }
                }
            }
        }


    }
}
