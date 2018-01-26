package com.juegosamu.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    Texture texture;
    TextureRegion textureRegion;
    TextureRegion[] naveFrame;
    Animation nave;

    public Jugador(float x, float y) {
        this.duracion = 0;
        vida = 100;
        texture = new Texture("nave.png");
        textureRegion = new TextureRegion(texture,128,60);
        TextureRegion[][] temp = textureRegion.split(128/2,60);
        int index =0;
        naveFrame = new TextureRegion[2];
        for(int frame = 0; frame < 2 ; frame++){
            naveFrame[index++] = temp[0][frame];
        }
        nave = new Animation(0.1f,naveFrame);
        //rectangulo = new Rectangle((int)getX(),(int)getY(),(int)getWidth(),(int)getHeight());
        this.setSize((float)naveFrame[0].getRegionWidth(),naveFrame[0].getRegionHeight());
        this.setY(y);
        this.setX(x-ancho/2);
    }

    public float getDuracion() {
        return duracion;
    }
    public float incrementarDuracion(float incremento){
        duracion += incremento;
        return duracion;
    }


    public void ia(float delta) {
    incrementarDuracion(delta);
        if(getX()> Gdx.graphics.getWidth()-ancho) setX(Gdx.graphics.getWidth()-ancho/2);
        else if(getX()< ancho/2) setX(ancho/2);
        //rectangulo.x = (int)getX();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.draw();
        TextureRegion frame = (TextureRegion) nave.getKeyFrame(duracion,true);
        batch.draw(frame,getX(),getY());

    }
}
