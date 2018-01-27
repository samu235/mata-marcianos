package com.juegosamu.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samue on 26/01/2018.
 */

public class Jugador extends Actor {
    private float duracion;
    double vida;
    private final int speedMax = 400;
    Rectangle rectangulo;
    Texture texture;
    TextureRegion textureRegion;
    TextureRegion[] naveFrame;
    Animation nave;

    Vector2 velocidad = new Vector2(0,0);

    List<Bala> balas;


    public Jugador(float x, float y) {
        this.duracion = 0;
        vida = 100;
        texture = new Texture("nave.png");
        textureRegion = new TextureRegion(texture,128,60);
        TextureRegion[][] temp = textureRegion.split(128/2,60);
        // transform TextureRegion[][]  to   TextureRegion[]
        int index =0;
        naveFrame = new TextureRegion[2];
        for(int frame = 0; frame < 2 ; frame++){
            naveFrame[index++] = temp[0][frame];
        }
        nave = new Animation(0.1f,naveFrame);
        this.setSize((float)naveFrame[0].getRegionWidth(),naveFrame[0].getRegionHeight());
        this.setY(y);


        addListener(new InputListener(){
            @Override
            public boolean keyTyped(InputEvent event, char character) {
                return super.keyTyped(event, character);

            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode){
                    case Input.Keys.RIGHT:
                        velocidad.x = 0;
                        break;
                    case Input.Keys.LEFT:
                        velocidad.x = 0;
                        break;
                    case Input.Keys.SPACE:
                        getStage().addActor( new Bala(((getX() + (getWidth()/2) - Bala.getWidthTexture()/2)),(getY())+getHeight()));
                        break;
                }
                return super.keyUp(event, keycode);
            }

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode){
                    case Input.Keys.RIGHT:
                        velocidad.x = speedMax;
                        break;
                    case Input.Keys.LEFT:
                        velocidad.x = -speedMax;
                        break;
                }
                return super.keyDown(event, keycode);
            }
        });

    }

    public float getDuracion() {
        return duracion;
    }
    public float incrementarDuracion(float incremento){
        duracion += incremento;
        return duracion;
    }


    public void ia(float delta) {
    //incrementarDuracion(delta);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.draw();
        TextureRegion frame = (TextureRegion) nave.getKeyFrame(duracion,true);
        batch.draw(frame,getX(),getY());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        incrementarDuracion(delta);
        moveBy(velocidad.x * delta , 0);
        if(getX()> getStage().getWidth() - getWidth()/2 ) setX(getStage().getWidth() - getWidth()/2 );
        else if(getX()< 0 - getWidth()/2 ) setX( - getWidth()/2 );

    }

}
