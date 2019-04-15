package com.wipro.day3uicontrols

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.graphics.Typeface
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var boldCheckBox: CheckBox
    private lateinit var italicCheckBox: CheckBox
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonOne: RadioButton
    private lateinit var radioButtonTwo: RadioButton
    private lateinit var seekBar: SeekBar
    private lateinit var toggleButton: ToggleButton
    private lateinit var exitButton: Button
    private lateinit var textView: TextView
    private lateinit var typeface1: Typeface
    private lateinit var typeface2: Typeface
    private var isOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_text)
        boldCheckBox = findViewById(R.id.check_box_bold)
        italicCheckBox = findViewById(R.id.check_box_italic)
        radioGroup = findViewById(R.id.radio_group)
        radioButtonOne = findViewById(R.id.radio_button_manuale)
        radioButtonTwo = findViewById(R.id.radio_button_indie_flower)
        seekBar = findViewById(R.id.seek_bar)
        toggleButton = findViewById(R.id.toggle_button)
        exitButton = findViewById(R.id.exit_button)
        textView = findViewById(R.id.text_view)

        // Listen for changes in the editable Edit Text
        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isOn) textView.text = editText.text
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Listen for changes when Seek Bar is being changed
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (isOn) textView.textSize = progress.toFloat()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Listen for changes when Check Boxes are checked/unchecked
        boldCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isOn) {
                if (isChecked) {
                    textView.apply {setTypeface(typeface, Typeface.BOLD)}
                } else {
                    textView.apply {setTypeface(typeface, Typeface.NORMAL)}
                }
            }
        }

        italicCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isOn) {
                if (isChecked) {
                    textView.apply {setTypeface(typeface, Typeface.ITALIC)}
                } else {
                    textView.apply {setTypeface(typeface, Typeface.NORMAL)}
                }
            }
        }

        // Listen for changes when Radio Buttons are selected (lambda expression)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            typeface1 = Typeface.createFromAsset(assets, "manuale.ttf")
            typeface2 = Typeface.createFromAsset(assets, "indie_flower.ttf")

            if (isOn) {
                when (checkedId) {
                    R.id.radio_button_manuale -> textView.typeface = typeface1
                    R.id.radio_button_indie_flower -> textView.typeface = typeface2
                }
            }
        }

        // Switching the Toggle Button state - On/Off
        toggleButton.setOnClickListener {
            isOn = toggleButton.isChecked
        }

        // Exit an App when clicking Exit Button
        exitButton.setOnClickListener {
            finish()
        }
    }
}
