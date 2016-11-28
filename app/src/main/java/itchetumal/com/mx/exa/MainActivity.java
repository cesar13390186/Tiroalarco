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

    float cirCoordX = 0, cirCoordY = 0;
    Path ruta = new Path();
    String mensaje = " ";
    int jugador1 = 0, jugador2=0;
    int Tiro=0,op=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(R.id.circulo);
        Lienzo areaDibujo = new Lienzo(this);
        layout.addView(areaDibujo);

    }

    public void jugadores(int tiro, int puntos) {
        int div = tiro/2;
        if( div == op) {
            jugador1 = jugador1 + puntos;
            op++;
        }else {
            jugador2 = jugador2 + puntos;
        }

        if (tiro == 20){
            if (jugador1 > jugador2){
                Toast.makeText(this, "ganador jugador 1", Toast.LENGTH_SHORT).show();
            }else if (jugador1 < jugador2){
                Toast.makeText(this, "ganador jugador 2", Toast.LENGTH_SHORT).show();
            }else if(jugador1 == jugador2){
                Toast.makeText(this, "draw game", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int ConTiros() {
        Tiro ++;
        return Tiro;
    }

    class Lienzo extends View{

        int ancho;
        int alto;

        public Lienzo(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

            Paint pincel = new Paint();
            ancho = canvas.getWidth();
            alto = canvas.getHeight();
            pincel.setAntiAlias(true);

            pincel.setTextSize(50);
            canvas.drawText(mensaje, 0, this.getMeasuredHeight(), pincel);

            float x1 = ancho / 2, y1 = alto / 2;

            pincel.setColor(Color.GREEN);
            canvas.drawCircle(x1, y1, 150, pincel);

            pincel.setColor(Color.BLUE);
            canvas.drawCircle(x1, y1, 100, pincel);

            pincel.setColor(Color.RED);
            canvas.drawCircle(x1, y1, 50, pincel);

            canvas.drawText("Tiros: " + Tiro, 50, 50, pincel);
             //canvas.drawText("Puntos: "+puntos,50,100,pincel);

            canvas.drawText("Puntos 1: "+jugador1,50,150,pincel);
            canvas.drawText("Puntos 2: "+jugador2,50,200,pincel);

        }

        @Override
        public boolean onTouchEvent(MotionEvent evento) {

            cirCoordX = evento.getX();
            cirCoordY = evento.getY();
            int puntos=0;

            if(Tiro<=20) {

                if (evento.getAction() == MotionEvent.ACTION_DOWN) {
                    ruta.moveTo(cirCoordX, cirCoordY);
                    ConTiros();

                    double cordenadas = Math.sqrt(Math.pow(cirCoordX - (ancho / 2), 2) + Math.pow(cirCoordY - (alto / 2), 2));

                    if ( cordenadas <= 150) {
                        puntos = puntos + 1;
                    }
                    if (cordenadas <= 100) {
                        puntos = puntos + 1;
                    }
                    if (cordenadas <= 50) {
                        puntos = puntos + 1;
                    }
                    jugadores(Tiro, puntos);
                }
                this.invalidate();
                return true;
            }
            return false;
        }
    }
}
