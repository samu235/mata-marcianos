package com.juegosamu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.SpinnerDateModel;

/**
 * Created by samue on 26/01/2018.
 */

public class MataMarcianos extends Game {
    public SpriteBatch batch;
    public Pantalla juego;


    @Override
    public void create() {
        batch = new SpriteBatch();
        juego = new PantallaJuego(this);
        setScreen(juego);

    }
}
