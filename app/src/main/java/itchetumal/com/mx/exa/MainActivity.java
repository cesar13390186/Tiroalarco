package itchetumal.com.mx.exa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    float cirCoordX = 0, cirCoordY = 0, coordInicX = 0, coordInicY=0;
    Path ruta = new Path();
    String mensaje = " ", mensaje2 = " ";
    int Tiro = 0;
    int puntos,jugador1,jugador2;

    public void jugadores() {
        Toast.makeText(this, "Jugado uno", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(R.id.circulo);
        Lienzo areaDibujo = new Lienzo(this);
        layout.addView(areaDibujo);

    }

    class Lienzo extends View{

        boolean clickOrigen = false;
        int ancho;
        int alto;

        public Lienzo(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas){

            Paint pincel = new Paint();
            ancho = canvas.getWidth();
            alto = canvas.getHeight();
            pincel.setAntiAlias(true);

            pincel.setTextSize(50);
            canvas.drawText(mensaje,0,this.getMeasuredHeight(),pincel);

            float x1=ancho/2, y1=alto/2;

            pincel.setColor(Color.GREEN);
            canvas.drawCircle(x1, y1, 150, pincel);

            pincel.setColor(Color.BLUE);
            canvas.drawCircle(x1, y1, 100, pincel);

            pincel.setColor(Color.RED);
            canvas.drawCircle(x1, y1, 50, pincel);

            canvas.drawText("Tiros: "+mensaje2,50,50,pincel);
            canvas.drawText("Puntos: "+puntos,50,100,pincel);


        }

        @Override
        public boolean onTouchEvent(MotionEvent evento) {

            cirCoordX = evento.getX();
            cirCoordY = evento.getY();

            if (Tiro == 0){
                jugadores();
            }

            if (evento.getAction()== MotionEvent.ACTION_DOWN){
                ruta.moveTo(cirCoordX,cirCoordY);
                mensaje = "Evento Down ";
                mensaje2 = ConTiros();

                if (Math.sqrt(Math.pow(cirCoordX-(ancho/2), 2) + Math.pow(cirCoordY-(alto/2),2)) <= 150){
                    mensaje = "Diste click en el círculo VERDE";
                    puntos = puntos +1;
                    clickOrigen = true;
                }

                if (Math.sqrt(Math.pow(cirCoordX-(ancho/2), 2) + Math.pow(cirCoordY-(alto/2),2)) <= 100){
                    mensaje = "Diste click en el círculo azul";
                    puntos = puntos +1;
                    clickOrigen = true;
                }
                if (Math.sqrt(Math.pow(cirCoordX-(ancho/2), 2) + Math.pow(cirCoordY-(alto/2),2)) <= 50){
                    mensaje = "Diste click en el círculo rojo";
                    puntos = puntos +1;
                    clickOrigen = true;
                }

            }
            this.invalidate();
            return true;
        }
    }

    private String ConTiros() {
        Tiro ++;
        return String.valueOf(Tiro);
    }
}
