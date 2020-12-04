package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;
public class Eat {
    public static final int HEIGHT = 16;
    public static final int WIDTH = 16;
    Random random = new Random();
    Texture fruit_little;
    Texture fruit_big;
    Rectangle position;
    Rectangle rect1;
    public int score;
    int eat_tipe;
    Eat(){
    score=0;
    rect1 = new Rectangle();
    fruit_little = new Texture("eat_little.png");
    fruit_big = new Texture("eat_big.png");
    eat_tipe = random.nextInt(5);
    position = new Rectangle(random.nextInt(752)+16,random.nextInt(448)+8,WIDTH,HEIGHT);
    while ((position.x)%16!=0){
        position.x=random.nextInt(752)+16;
    }
    while ((position.y-8)%16!=0){
            position.y=random.nextInt(448)+8;
        }
    }
    public void render(SpriteBatch batch){
        batch.begin();
        if(eat_tipe==4){batch.draw(fruit_big,position.x,position.y);}
        else {batch.draw(fruit_little,position.x,position.y);}

        batch.end();
    }
    public void update(Person person){
        rect1.set(person.positionarr.get(0).x,person.positionarr.get(0).y,person.positionarr.get(0).width,person.positionarr.get(0).height);

       if(Intersector.overlaps(rect1,position)){
            position.set(random.nextInt(752)+16,random.nextInt(448)+8,WIDTH,HEIGHT);
           while ((position.x)%16!=0){
               position.x=random.nextInt(752)+16;
           }
           while ((position.y-8)%16!=0){
               position.y=random.nextInt(448)+8;
           }

            if(eat_tipe==4){score+=2;}
            else {score+=1;}
           eat_tipe = random.nextInt(5);
       }

    }

    public int getScore() {
        return score;


    }
    public void disose(){
        fruit_little.dispose();
        fruit_big.dispose();
    }
}
