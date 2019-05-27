package com.inzy.practice.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.inzy.practice.R;
import com.inzy.practice.viewmodel.ScoreViewModel;

public class BasicViewModelActivity extends AppCompatActivity {

    private TextView team_a_score, team_b_score;
    ScoreViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view_model);

        mViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);

        team_a_score = findViewById(R.id.team_a_score);
        team_b_score = findViewById(R.id.team_b_score);

        displayForTeamA(mViewModel.scoreTeamA);
        displayForTeamB(mViewModel.scoreTeamB);
    }

    // An example of both reading and writing to the ViewModel
    public void addOneForTeamA(View v) {
        mViewModel.scoreTeamA = mViewModel.scoreTeamA + 1;
        displayForTeamA(mViewModel.scoreTeamA);
    }

    public void addOneForTeamB(View v) {
        mViewModel.scoreTeamB = mViewModel.scoreTeamB + 1;
        displayForTeamB(mViewModel.scoreTeamB);
    }

    public void displayForTeamA(int score) {
        team_a_score.setText(score + "");
    }

    public void displayForTeamB(int score) {
        team_b_score.setText(score + "");
    }

    public void resetScore(View view) {
        mViewModel.scoreTeamA = 0;
        mViewModel.scoreTeamB = 0;
        displayForTeamA(mViewModel.scoreTeamA);
        displayForTeamB(mViewModel.scoreTeamB);
    }
}
