package org.ieselcaminas.pmdm.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
Button[][] buttons;
Button reset;
GridLayout g;
int counter;
boolean isPlayer1;
boolean gameover;
boolean input;
TextView text;
public static final int NUM_ROWS=3;

public void newGame()
{
	isPlayer1=true;
	gameover=false;
	counter=0;
	input=false;
	text.setText("Turn of Player 1 (X)");
}

@Override protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	text=findViewById(R.id.textView);
	reset=findViewById(R.id.bReset);
	newGame();

	reset.setOnClickListener(new View.OnClickListener()
	{
		@Override public void onClick(View v)
		{
			g.removeAllViews();
			createButtons();
			newGame();
		}
	});

	buttons=new Button[NUM_ROWS][NUM_ROWS];

	g=findViewById(R.id.gridlayout);
	g.setColumnCount(NUM_ROWS);
	g.setRowCount(NUM_ROWS);
	createButtons();

}
public void Check(Boolean player)
{
	String toCheck;
	String winner;
	int count=0;
	if(player)
	{
		winner="Player 1";
		toCheck = "X";
	}
	else
	{
		winner="Player 2";
		toCheck = "O";
	}
	for(int i = 0; i< NUM_ROWS ; i++)
	{
		for(int j=0; j<NUM_ROWS;j++)
		{
			if(buttons[i][j].getText().toString().equals(toCheck))
			{
				count++;
			}
		}
		if(count==3)
		{

			gameover=true;
			text.setText("The winner is "+winner);
			return;
		}
		else
		{
			count=0;
		}
	}
	for(int j = 0; j< NUM_ROWS ; j++)
	{
		for(int i=0; i<NUM_ROWS;i++)
		{
			if(buttons[i][j].getText().toString().equals(toCheck))
			{
				count++;
			}
		}
		if(count==3)
		{

			gameover=true;
			text.setText("The winner is "+winner);
			return;
		}
		else
		{
			count=0;
		}
	}
	for(int i=0;i<NUM_ROWS;i++)
	{
		if(buttons[i][i].getText().toString().equals(toCheck))
		{
			count++;
		}
	}
	if(count==3)
	{
		gameover=true;
		text.setText("The winner is "+winner);
		return;
	}
	else
	{
		count=0;
	}
	for(int i=0;i<NUM_ROWS;i++)
	{
		if(buttons[i][2-i].getText().toString().equals(toCheck))
		{
			count++;
		}
	}
	if(count==3)
	{
		gameover=true;
		text.setText("The winner is "+winner);
		return;
	}
	else
	{
		count=0;
	}
	if(counter==9)
	{
		gameover=true;
		text.setText("Draw!");
		return;
	}
}
public void createButtons()
{
	for(int i=0; i < NUM_ROWS; i++)
	{
		for(int j=0; j < NUM_ROWS; j++)
		{
			buttons[i][j]=new Button(getApplicationContext(), null, android.R.attr.buttonStyleSmall);
			g.addView(buttons[i][j]);
			buttons[i][j].setOnClickListener(new View.OnClickListener()
			{
				@Override public void onClick(View v)
				{
					Button button=(Button) v;
					if (button.getText().toString().equals(""))
					{
						counter++;
						if(!gameover && isPlayer1)
						{
							button.setText("X");
							text.setText("Turn of Player 2 (O)");
							Check(isPlayer1);
							isPlayer1=false;
							input=true;
						}
						else if(!gameover && !isPlayer1)
						{
							button.setText("O");
							text.setText("Turn of Player 1 (X)");
							Check(isPlayer1);
							isPlayer1=true;
							input=true;
						}
					}

				}
			});
		}
	}
}
}
