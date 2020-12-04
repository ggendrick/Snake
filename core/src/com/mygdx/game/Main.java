package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

public class Main extends ApplicationAdapter {
	Texture background;
	Texture game_over;
	SpriteBatch batch;
	Eat eat;
	Person person;
	public int score;
	boolean game;
	BitmapFont font;
	boolean snake_plus;
	@Override
	public void create () {
		batch = new SpriteBatch();
		eat = new Eat();
		person = new Person(400,240);
		score=0;
		background = new Texture("background.png");
		game_over = new Texture("game_over.png");
		game=true;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		snake_plus=false;
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(game) {
			batch.begin();
			batch.draw(background, 0, 0);
			batch.end();
			person.render(batch, Gdx.graphics.getDeltaTime());
			person.snake_resize(score);
			eat.render(batch);
			eat.update(person);
			scoreupdate();
			batch.begin();
			font.draw(batch, Integer.toString(score),5,20);
			batch.end();



		}
		else{
			batch.begin();
			batch.draw(game_over, 80, 168);
			batch.end();
			if(Gdx.input.justTouched()){
				create();
			}
		}
	}
	public void scoreupdate(){
		score=eat.getScore();
		game=person.crush();
	}
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		game_over.dispose();
		font.dispose();
		person.dispose();
		eat.disose();
	}




}
