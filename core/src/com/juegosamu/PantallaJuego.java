package com.juegosamu;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.juegosamu.personajes.Bala;
import com.juegosamu.personajes.Jugador;
import com.juegosamu.personajes.Marciano1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by samue on 26/01/2018.
 */

public class PantallaJuego extends Pantalla {
    SpriteBatch batch;
    Texture texture;
    TextureRegion textureRegion;
    TextureRegion[] naveFrame;
    Animation nave;
    private float duracion = 0;
    Jugador jugador;
    Stage stage;
    TextureRegion flecha;


    public PantallaJuego(MataMarcianos game) {
        super(game);
        batch = new SpriteBatch();
        texture = new Texture("nave.png");
        textureRegion = new TextureRegion(texture,128,60);
        TextureRegion[][] temp = textureRegion.split(128/2,60);
        int index =0;
        naveFrame = new TextureRegion[2];
        for(int frame = 0; frame < 2 ; frame++){
           naveFrame[index++] = temp[0][frame];
        }
        nave = new Animation(0.1f,naveFrame);

        flecha = new TextureRegion(new Texture("botonFlecha.png"),64,64);



    }

    @Override
    public void show() {
        super.show();
        stage = new Stage();
        //stage.
        Gdx.input.setInputProcessor(stage);
        Marciano1 aux = new Marciano1(100,Gdx.graphics.getHeight()/2);
        stage.addActor(aux);
        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            jugador = new Jugador(Gdx.graphics.getWidth()/2,64);
            //stage
        } else{
            jugador = new Jugador(Gdx.graphics.getWidth()/2,0);
        }
        stage.addActor(jugador);
        stage.setKeyboardFocus(jugador);





    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0f,0f,0f,1);
        super.render(delta);
        //comprovamos colisiones
        Array<Actor> actores = stage.getActors();
        Iterator i =  actores.iterator();
        List<Marciano1> listaMarcianos1 = new ArrayList<Marciano1>();
        List<Bala> listaBalas = new ArrayList<Bala>();
        if(Math.floor(Math.random()*1000) < 20){
            float aux = (float)Math.floor(Math.random()*stage.getWidth());
            stage.addActor(new Marciano1(aux, stage.getHeight())) ;
        }
        while (i.hasNext()){
            Actor actor = (Actor) i.next();
            if(actor.getClass() == Marciano1.class){
                listaMarcianos1.add((Marciano1) actor);

            }else if(actor.getClass() == Bala.class){
                listaBalas.add((Bala)actor);
            }
        }
        for (Marciano1 mar:listaMarcianos1) {
            for(Bala bal:listaBalas){
                if(mar.getRectangle().overlaps(bal.getRectangle())){
                    mar.kill();
                    bal.kill();
                }
            }
        }


        batch.begin();
        //duracion += delta;
        stage.act();
        stage.draw();
        batch.end();

    }

    public void ia(float delta){


        jugador.setX(jugador.getX());
        jugador.ia(delta);
    }
    public void drawNave(){

    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
