package com.gd.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.AlteredCharSequence
import android.widget.Button
import com.gd.dialog.R.*
import com.gd.dialog.R.id.*
import android.view.View
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        var button1 = findViewById<Button>(button1)

        button1.setOnClickListener{
            var dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("제목입니다")
            dlg.setMessage("이곳이 내용입니다")
            dlg.setIcon(mipmap.ic_launcher)
            dlg.setPositiveButton("확인",null)
            dlg.show()
        }
    }
}