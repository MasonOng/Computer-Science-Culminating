package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    HashMap<String, TextureRegion> cards;
    ArrayList<TextureRegion> player1Cards;
    ArrayList<TextureRegion> player2Cards;
    Rectangle card;
    Scanner scanner = new Scanner(System.in);
    private boolean key_pressed = false;
    Random rand = new Random();
    @Override
    public void create () {
        batch = new SpriteBatch();
        cards = new HashMap<>();
        cards.put("two", new TextureRegion(new Texture("2_of_clubs.png")));
        cards.put("three", new TextureRegion(new Texture("3_of_clubs.png")));
        cards.put("four", new TextureRegion(new Texture("4_of_clubs.png")));
        cards.put("five", new TextureRegion(new Texture("5_of_diamonds.png")));
        cards.put("six", new TextureRegion(new Texture("6_of_spades.png")));
        cards.put("seven", new TextureRegion(new Texture("7_of_clubs.png")));
        cards.put("eight", new TextureRegion(new Texture("8_of_clubs.png")));
        cards.put("nine", new TextureRegion(new Texture("9_of_clubs.png")));
        cards.put("ten", new TextureRegion(new Texture("10_of_diamonds.png")));
        cards.put("jack", new TextureRegion(new Texture("jack_of_clubs2.png")));
        cards.put("queen", new TextureRegion(new Texture("queen_of_diamonds2.png")));
        cards.put("king", new TextureRegion(new Texture("king_of_diamonds2.png")));
        cards.put("ace", new TextureRegion(new Texture("ace_of_spades2.png")));
        player1Cards = new ArrayList<>();
        player2Cards = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            player1Cards.add(cards.get("two"));
            player2Cards.add(cards.get("three"));
            player1Cards.add(cards.get("four"));
            player2Cards.add(cards.get("five"));
            player1Cards.add(cards.get("six"));
            player2Cards.add(cards.get("seven"));
            player1Cards.add(cards.get("eight"));
            player2Cards.add(cards.get("nine"));
            player1Cards.add(cards.get("ten"));
            player2Cards.add(cards.get("jack"));
            player1Cards.add(cards.get("queen"));
            player2Cards.add(cards.get("king"));
            player1Cards.add(cards.get("ace"));
          
        }
        Collections.shuffle(player1Cards);
        Collections.shuffle(player2Cards);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();

        TextureRegion player1Card = player1Cards.get(0);
        float cardWidth = Gdx.graphics.getWidth() / 4;
        float cardHeight = Gdx.graphics.getHeight() / 4;
        batch.draw(player1Card, Gdx.graphics.getWidth() / 2 - cardWidth / 2, Gdx.graphics.getHeight() / 2, cardWidth, cardHeight);

        TextureRegion player2Card = player2Cards.get(0);
        batch.draw(player2Card, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, cardWidth, cardHeight);

        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.Y) && !key_pressed) {
        	key_pressed = true;
        	 int card1 = rand.nextInt(15);
        	 int card2 = rand.nextInt(15);
             if (card1 > card2) {
                 player1Cards.remove(0);
                 quickSort(player1Cards, 0, player1Cards.size() - 1);
             } else {
                 player2Cards.remove(0);
                 quickSort(player2Cards, 0, player2Cards.size() - 1);
             }

            if (player1Cards.isEmpty()) {
                System.out.println("Player 1 wins the game!");
                Gdx.app.exit();
            } else if (player2Cards.isEmpty()) {
                System.out.println("Player 2 wins the game!");
                Gdx.app.exit();
            }
            
            key_pressed = false;
        }

    }

    public int reference(String card) {
        switch (card) {
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            case "ten":
                return 10;
            case "jack":
                return 11;
            case "queen":
                return 12;
            case "king":
                return 13;
            case "ace":
                return 14;
            default:
                return 0;
        }
    }
    public void quickSort(ArrayList<TextureRegion> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(ArrayList<TextureRegion> list, int low, int high) {
        TextureRegion pivot = list.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (reference(list.get(j).toString()) <= reference(pivot.toString())) {
                i++;

                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);

        return (i + 1);
    }
}