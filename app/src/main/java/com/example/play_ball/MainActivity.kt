package com.example.play_ball

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(),SensorEventListener {
    lateinit var sensorMa:SensorManager
    lateinit var sensor: Sensor
    lateinit var pelota:ImageView
    lateinit var barra:ImageView
    lateinit var tvcoli:TextView
    var cont:Float=0f
    var alto=0
    var ancho=0
    var x:Int=0
    var y:Int=0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pelota=findViewById(R.id.idPelotita)
        barra=findViewById(R.id.idbarra)
        tvcoli=findViewById(R.id.idvistaColision)
        sensorMa=getSystemService(SENSOR_SERVICE) as SensorManager
        sensor=sensorMa.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        var pantalla= windowManager.defaultDisplay
        ancho=pantalla.width
        alto=pantalla.height

        x=pelota.x.toInt()
        y=pelota.y.toInt()

        if(sensor==null){
            finish()
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
    x= x - p0!!.values[0].toInt() * 12
        y= y + p0!!.values[1].toInt() * 12

        pelota.animate().translationX(x.toFloat())
        pelota.animate().translationY(y.toFloat())

        colisionObjetos()



    }

    private fun colisionObjetos() {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensor.also { sensorm-> sensorMa.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME) }
    }
    override fun onPause() {
        super.onPause()
        sensorMa.unregisterListener(this)
    }
}