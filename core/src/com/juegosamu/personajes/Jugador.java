package com.juegosamu.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Rectangle;

/**
 * Created by samue on 26/01/2018.
 */

public class Jugador extends Actor {
    private float duracion;
    double vida;
    Rectangle rectangulo;
    int ancho;

    public Jugador(float x, float y,int ancho,int alto) {
        this.duracion = 0;
        //this.setX(x);
        this.setSize((float)ancho,alto);
        this.setY(y);
        //this.setOrigin(400,0);
        this.ancho = ancho;
        this.setX(x-ancho/2);

        vida = 100;
        //rectangulo = new Rectangle((int)getX(),(int)getY(),(int)getWidth(),(int)getHeight());

    }

    public float getDuracion() {
        return duracion;
    }
    public float incrementarDuracion(float incremento){
        duracion += incremento;
        return duracion;
    }


    public void ia(float delta) {

        if(getX()> Gdx.graphics.getWidth()-ancho) setX(Gdx.graphics.getWidth()-ancho/2);
        else if(getX()< ancho/2) setX(ancho/2);
        //rectangulo.x = (int)getX();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {


    }
}
