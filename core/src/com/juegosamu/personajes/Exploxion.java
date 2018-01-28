package com.juegosamu.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Rectangle;

/**
 * Created by samue on 28/01/2018.
 */

public class Exploxion extends Actor {
    private float duracion;
    Rectangle rectangulo;
    Texture texture;
    TextureRegion textureRegion;
    TextureRegion[] naveFrame;
    Animation nave;

    public Exploxion(float x,float y){

        duracion = 0;
        texture = new Texture("explosion.png");
        textureRegion = new TextureRegion(texture,58*5,59);
        TextureRegion[][] temp = textureRegion.split(58,59);
        // transform TextureRegion[][]  to   TextureRegion[]
        int index =0;
        naveFrame = new TextureRegion[5];
        for(int frame = 0; frame < 5 ; frame++){
            naveFrame[index++] = temp[0][frame];
        }
        nave = new Animation(0.1f,naveFrame);
        setSize((float)naveFrame[0].getRegionWidth(),naveFrame[0].getRegionHeight());
        setX( x - getWidth()/2);
        setY( y - getHeight()/2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        TextureRegion frame = (TextureRegion) nave.getKeyFrame(duracion,false);
        batch.draw(frame,getX(),getY());

    }

    @Override
    public void act(float delta) {
        duracion += delta;
        if(duracion > 0.1f *5) remove();
    }

}
