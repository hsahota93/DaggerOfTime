package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.hj.daggeroftime.Scenes.Hud;

import java.util.Iterator;

/**
 * Created by Harman on 4/20/17.
 */

public class Database {
    private Firebase mref;
    private Array<String> nameList;
    private Array<Integer> scoreList;
    private Viewport viewport;
    private Stage stage;
    private Hud hud;
    private Game game;
    private Firebase playerName;

    public Database() {

        scoreList = new Array<Integer>();
        nameList = new Array<String>();
        mref = new Firebase("https://daggeroftime-ddc38.firebaseio.com/scoreStore/playerScore");
        playerName = new Firebase("https://daggeroftime-ddc38.firebaseio.com/scoreStore/playerName");
        addDataTODataBase();
        displayScore();
    }
    public void addDataTODataBase() {

        String name = GetInfo.name;
        if(name== null || name.length() == 0) {

            name = "Anonymous";
        }
        mref.push().setValue(Hud.score);
        playerName.push().setValue(name);
    }

    public Array<Integer> displayScore() {

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();

                while(iterator.hasNext()){
                    scoreList.add(23);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Error");
            }
        });
        return scoreList;
    }

}
