package com.juegosamu.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by samue on 27/01/2018.
 */

public class Marciano1 extends Actor {
    private float duracion;
    final private  Texture texture;
    final private TextureRegion textureRegion;
    final private TextureRegion[] naveFrame;
    private Animation nave;
    private Rectangle rectangle;
    private boolean die;

    Vector2 velocidad = new Vector2(0,-100);

    public Marciano1(float x , float y){
        setX(x);
        setY(y);
        duracion = 0;
        die = false;

        texture = new Texture("naveMarciano1.png");
        textureRegion = new TextureRegion(texture,texture.getWidth(),texture.getHeight());
        TextureRegion[][] temp = textureRegion.split(texture,texture.getWidth()/2,texture.getHeight());
        // transform TextureRegion[][]  to   TextureRegion[]
        int index =0;
        naveFrame = new TextureRegion[2];
        for(int frame = 0; frame < 2 ; frame++){
            naveFrame[index++] = temp[0][frame];
        }
        nave = new Animation(0.1f,naveFrame);
        setSize((float)naveFrame[0].getRegionWidth(),naveFrame[0].getRegionHeight());
        rectangle = new Rectangle();
        rectangle.set(getX(),getY(),getWidth(),getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        TextureRegion frame = (TextureRegion) nave.getKeyFrame(duracion,true);
        batch.draw(frame,getX(),getY());
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
    public void kill(){
        die = true;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        duracion += delta;
        moveBy(velocidad.x * delta , delta * velocidad.y);
        rectangle.set(getX(),getY(),getWidth(),getHeight());
        //System.out.println(getX());
        if(die){
            getStage().addActor(new Exploxion(getX()+getWidth()/2,getY()+getHeight()/2));
            remove();
        }
        if(getY() < (0-getWidth())){
            remove();
        }



    }
}
