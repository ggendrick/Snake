package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
public class Person {
public static final int WIDTH = 16;
public static final int HEIGHT = 16;
boolean up,down,left,right;
Texture person;
boolean snake_resize;
float time=0;
public static final int speed=16;
public Array <Rectangle> positionarr;
private Rectangle rect1;
private Rectangle rect2;
float input_time;

    Person(int x,int y){
        input_time=0;
        rect1 = new Rectangle();
         rect2 = new Rectangle();
        snake_resize=false;
        person = new Texture("person.png");
        positionarr = new Array <Rectangle>();
        for(int i=0;i<4;i++)
        positionarr.add(new Rectangle(464+i*16,200,WIDTH,HEIGHT));//464 200


    }
    public void render(SpriteBatch batch,float dt){

        batch.begin();
        for (int i=0;i<positionarr.size;i++){
            batch.draw(person,positionarr.get(i).x,positionarr.get(i).y);
        }
        batch.end();
        input();
        time+=dt;
        if(time>0.3){
        rotation();
        time=0;
        }

    }
    public void snake_resize(int score){
        if(score>positionarr.size-4){
            snake_resize=true;
        }
    }





    public void input(){



        if(Gdx.input.isKeyPressed(Input.Keys.UP)&&!down&&(TimeUtils.nanoTime()-input_time>300000000)){
            up=true;
            down=false;
            left=false;
            right=false;
            input_time=TimeUtils.nanoTime();

        }
       else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)&&!up&&(TimeUtils.nanoTime()-input_time>300000000)){
            up=false;
            down=true;
            left=false;
            right=false;
            input_time=TimeUtils.nanoTime();
        }
       else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&!left&&(TimeUtils.nanoTime()-input_time>300000000)){
            up=false;
            down=false;
            left=false;
            right=true;
            input_time=TimeUtils.nanoTime();
        }
      else   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&&!right&&(TimeUtils.nanoTime()-input_time>300000000)){
            up=false;
            down=false;
            left=true;
            right=false;
            input_time=TimeUtils.nanoTime();
        }

    }
    public void rotation(){

        if(snake_resize){
            positionarr.add(new Rectangle(0,0,WIDTH,HEIGHT));
            snake_resize=false;
        }

        else if(left){
            for (int i=positionarr.size-1;i>0;i--){
                positionarr.get(i).x=positionarr.get(i-1).x;
                positionarr.get(i).y=positionarr.get(i-1).y;
            }
            positionarr.get(0).x-=speed;

        }

        else if(up){

            for (int i=positionarr.size-1;i>0;i--){
                positionarr.get(i).x=positionarr.get(i-1).x;
                positionarr.get(i).y=positionarr.get(i-1).y;
            }
            positionarr.get(0).y+=speed;

        }
        else if(right){

            for (int i=positionarr.size-1;i>0;i--){
                positionarr.get(i).x=positionarr.get(i-1).x;
                positionarr.get(i).y=positionarr.get(i-1).y;
            }
            positionarr.get(0).x+=speed;

        }
      else  if(down){

            for (int i=positionarr.size-1;i>0;i--){
                positionarr.get(i).x=positionarr.get(i-1).x;
                positionarr.get(i).y=positionarr.get(i-1).y;
            }
            positionarr.get(0).y-=speed;
        }

    }

    public boolean crush(){
        boolean rez=true;

        if(positionarr.get(0).x<16||positionarr.get(0).x>784||positionarr.get(0).y<8||positionarr.get(0).y>472){//784 472
            rez=false;
        }


            for(int i=1;i<positionarr.size;i++) {
                rect1.set(positionarr.get(0).x, positionarr.get(0).y, positionarr.get(0).width, positionarr.get(0).height);
                rect2.set(positionarr.get(i).x, positionarr.get(i).y, positionarr.get(i).width, positionarr.get(i).height);
                if (Intersector.overlaps(rect1, rect2)) {
                    rez = false;
                }
            }



        return rez;
}
public void dispose(){
        person.dispose();


}
}
