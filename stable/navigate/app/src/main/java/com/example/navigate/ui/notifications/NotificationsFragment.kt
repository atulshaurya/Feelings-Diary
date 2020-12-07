package com.example.navigate.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navigate.R
import kotlin.random.Random

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)

        val rnds = Random.nextInt(1,41)
        Log.i("Random integer: ", rnds.toString())

        when(rnds) {
            1 -> textView.text = "If we start being honest about our pain, our anger, and our shortcomings instead of pretending they don’t exist, then maybe we’ll leave the world a better place than we found it."
            2 -> textView.text = "The only journey is the journey within!"
            3 -> textView.text = "The strongest people are those who win battles we know nothing about."
            4 -> textView.text = "Don’t let your struggle become your identity."
            5 -> textView.text = "I fight for my health every day in ways most people don’t understand. I’m not lazy. I’m a warrior."
            6 -> textView.text = "Don’t give in to stigma. A diagnosis does not determine who you are or what you can do!"
            7 -> textView.text = "Some of the most comforting words in the universe are ‘me too.’ That moment when you find out that your struggle is also someone else’s struggle, that you’re not alone, and that others have been down the same road."
            8 -> textView.text = "Mental health…is not a destination, but a process. It’s about how you drive, not where you’re going!"
            9 -> textView.text = "What mental health needs is more sunlight, more candor, and more unashamed conversation."
            10 -> textView.text = "Tough times never last, but tough people do!"
            11 -> textView.text = "This feeling will pass. The fear is real but the danger is not."
            12 -> textView.text = "But no matter how much evil I see, I think it’s important for everyone to understand that there is much more light than darkness."
            13 -> textView.text = "Sometimes the people around you won’t understand your journey. They don’t need to, it’s not for them."
            14 -> textView.text = "I keep moving ahead, as always, knowing deep down inside that I am a good person and that I am worthy of a good life."
            15 -> textView.text = "Happiness can be found even in the darkest of times, if one only remembers to turn on the light."
            16 -> textView.text = "Be patient and tough; some day this pain will be useful to you."
            17 -> textView.text = "There is a crack in everything, that’s how the light gets in"
            18 -> textView.text = "Increasing the strength of our minds is the only way to reduce the difficulty of life."
            19 -> textView.text = "Not until we are lost do we begin to understand ourselves"
            20 -> textView.text = "I am not afraid of storms for I am learning how to sail my ship."
            21 -> textView.text = "In the middle of winter I at last discovered that there was in me an invincible summer."
            22 -> textView.text = "If you’re going through hell, keep going."
            23 -> textView.text = "If you have been brutally broken but still have the courage to be gentle to other living beings, then you’re a badass with a heart of an angel."
            24 -> textView.text = "Mental health needs a great deal of attention. It’s the final taboo and it needs to be faced and dealt with."
            25 -> textView.text = "What mental health needs is more sunlight, more candor, and more unashamed conversation."
            26 -> textView.text = "Two things can be true. You can love your family and have deep wounds as a result of your family experiences."
            27 -> textView.text = "Positive vibes only’ isn’t a thing. Humans have a wide range of emotions and that’s OK."
            28 -> textView.text = "No amount of support or generosity justifies someone treating you badly. This includes parents."
            29 -> textView.text = "Change what you can, manage what you can’t."
            30 -> textView.text = "Mental health affects every aspect of your life. It’s not just this neat little issue you can put into a box."
            31 -> textView.text = "We are not our trauma. We are not our brain chemistry. That’s part of who we are, but we’re so much more than that."
            32 -> textView.text = "If there is no struggle, there is no progress."
            33 -> textView.text = "Feeling your feelings will not lead to depression."
            34 -> textView.text = "My recovery from manic depression has been an evolution, not a sudden miracle."
            35 -> textView.text = "Nothing diminishes anxiety faster than action."
            36 -> textView.text = "No amount of anxiety can change the future. No amount of regret can change the past."
            37 -> textView.text = "Feelings come and go like clouds in a windy sky. Conscious breathing is my anchor."
            38 -> textView.text = "Until you make the unconscious conscious, it will direct your life and you will call it fate."
            39 -> textView.text = "You don’t have to control your thoughts. You just have to stop letting them control you."
            40 -> textView.text = "Don’t believe everything you think."
        }


        return root
    }
}