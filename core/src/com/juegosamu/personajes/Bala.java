package com.juegosamu.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by samue on 27/01/2018.
 */

public class Bala extends Actor {
    private TextureRegion imagen;
    private final int speed = 100;
    private static final String rootTexture= "bala.jpg";

    public Bala (float x ,float y){
        setX(x);
        setY(y);
        imagen = new TextureRegion(new Texture(rootTexture));

    }
    public static int getWidthTexture(){
        return new Texture(rootTexture).getWidth();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(imagen,getX(),getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0,speed * delta);
        if(getY() > (getStage().getHeight())){
            remove();
        }
    }

}
