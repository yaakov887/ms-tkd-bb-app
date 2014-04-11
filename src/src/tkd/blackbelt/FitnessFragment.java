package tkd.blackbelt;

import tkd.blackbelt.track.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FitnessFragment extends Fragment {
	
	private void updateTotals()
	{
		FragmentActivity ctext = this.getActivity();
    	FitnessDatabase database = new FitnessDatabase(ctext);

    	int ids = FitnessDatabase.ExerciseValues.length;
		for(int k = 0; k < ids; k++)
        {
        	TextView v = (TextView)ctext.findViewById(k);
        	int sum = database.getExerciseSum(k);
        	int goal = database.getExerciseGoal(k);
        	String ratio = sum+"/"+goal;
        	float percentComplete = (float)sum/(float)goal;
        	
        	v.setText(ratio);
        	
        	int color_id = 0;
        	if(percentComplete > 0.8)
        	{
        		color_id = this.getResources().getColor(R.color.Green);
        	}
        	else if(percentComplete > 0.5)
        	{
        		color_id = this.getResources().getColor(R.color.Yellow);
        	}
        	else
        	{
        		color_id = this.getResources().getColor(R.color.Red);
        	}
        	
    		v.setTextColor(color_id);
        }
		
		database.close();
	}
	
	private OnClickListener listSelect = new OnClickListener() {
	    public void onClick(View v) {
	      int id = v.getId();
	      String name = "bb.project.space.R";
	      Intent intent = new Intent();

	      switch(id)
	      {
	      	case R.id.sit_ups_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.SitUps);
	      		break;
	      	case R.id.basic_blocks_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.Blocks);
	      		break;
	      	case R.id.basic_punches_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.Punches);
	      		break;
	      	case R.id.endurance_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.Miles);
	      		break;
	      	case R.id.stretch_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.Stretching);
	      		break;
	      	case R.id.form_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.Forms);
	      		break;
	      	case R.id.jump_rope_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.JumpRopes);
	      		break;
	      	case R.id.knee_high_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.KneeHighs);
	      		break;
	      	case R.id.push_ups_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.PushUps);
	      		break;
	      	case R.id.creative_self_defense_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.SelfDefense);
	      		break;
	      	case R.id.squat_text:
	      		intent.putExtra(name, FitnessDatabase.Exercises.Squats);
	      		break;
	      	default:
	      		return;
	      }
	      
	      startActivity(intent);
	    }
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
               
        setContentView(R.layout.physicalrequirements);
        
        for(int i = R.id.push_up_add; i <= R.id.class_add; i++)
        {
        	View v = this.findViewById(i);
    	
        	v.setClickable(true);
        	v.setOnClickListener(addSelect);
        }
        
        for(int i = R.id.push_ups; i <= R.id.classes; i++)
        {
        	View v = this.findViewById(i);
    	
        	v.setClickable(true);
        	v.setOnClickListener(listSelect);
        }
        
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
        updateTotals();
    }
}