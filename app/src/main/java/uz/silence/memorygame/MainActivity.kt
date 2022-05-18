package uz.silence.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import uz.silence.memorygame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler()

        val images: MutableList<Int> = mutableListOf(
            R.drawable.ic_animal_bear,
            R.drawable.ic_animal_bee,
            R.drawable.ic_animal_camel,
            R.drawable.ic_animal_cat,
            R.drawable.ic_animal_cheetah,
            R.drawable.ic_animal_crocodile,
            R.drawable.ic_animal_bear,
            R.drawable.ic_animal_bee,
            R.drawable.ic_animal_camel,
            R.drawable.ic_animal_cat,
            R.drawable.ic_animal_cheetah,
            R.drawable.ic_animal_crocodile
        )

        val buttons: Array<Button> = arrayOf(
            binding.oneB,
            binding.twoB,
            binding.threeB,
            binding.fourB,
            binding.fiveB,
            binding.sixB,
            binding.sevenB,
            binding.eightB,
            binding.nineB,
            binding.tenB,
            binding.elevenB,
            binding.tweleveB
        )

        var clicked = 0
        var turnOver = false
        var lastClicked = -1

        var oneId = -1
        var twoId = -1

        var returnN = 0

        val animation = AnimationUtils.loadAnimation(this, R.anim.alpha)

        images.shuffle()

        for (i in 0..11) {

            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F

            buttons[i].setOnClickListener {

                if (buttons[i].text == "cardBack" && clicked < 2) {

                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].text = images[i].toString()

                    clicked++
                    if (clicked == 1) {
                        oneId = i
                    } else if (clicked == 2) {
                        twoId = i

                        if (buttons[oneId].text == buttons[twoId].text) {


                            buttons[oneId].visibility = View.INVISIBLE
                            buttons[twoId].visibility = View.INVISIBLE

                            returnN++

                            if (returnN == 6){
                                binding.returnView.visibility = View.VISIBLE


                                binding.buttonReturn.setOnClickListener {
                                    for (i in 0 until 12){

                                        buttons[i].text = "cardBack"

                                        buttons[i].visibility = View.VISIBLE
                                        buttons[i].visibility = View.VISIBLE

                                        buttons[i].setBackgroundResource(R.drawable.ic_circle_icons_memory)
                                        clicked = 0

                                        binding.returnView.visibility = View.GONE

                                    }
                                }
                            }

                            Toast.makeText(this, "Same", Toast.LENGTH_SHORT).show()
                            clicked = 0
                        } else {


                            handler.postDelayed({

                                buttons[oneId].text = "cardBack"
                                buttons[twoId].text = "cardBack"
                                buttons[twoId].setBackgroundResource(R.drawable.ic_circle_icons_memory)
                                buttons[oneId].setBackgroundResource(R.drawable.ic_circle_icons_memory)
                                clicked = 0


                            }, 1000)

                            Toast.makeText(this, "Different", Toast.LENGTH_SHORT).show()
                        }
                    }


                } else if (buttons[i].text !in "cardBack") {

                    buttons[i].setBackgroundResource(R.drawable.ic_circle_icons_memory)
                    buttons[i].text = "cardBack"

                    clicked--

                }

            }

        }

    }
}