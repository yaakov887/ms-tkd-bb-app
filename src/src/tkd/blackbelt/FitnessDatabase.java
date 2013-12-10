package tkd.blackbelt;


import tkd.blackbelt.track.R;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FitnessDatabase extends SQLiteOpenHelper {
	public enum Exercises 		{SitUps, PushUps, JumpRopes, Stretching, Blocks, KneeHighs, 
									Miles, Punches, Squats, Forms, SelfDefense};
	public static final Exercises[] ExerciseValues = Exercises.values();
	
		
	private static final int DATABASE_VERSION 			= 1;
	private static final String DATABASE_NAME 			= "fitness.db";
    private static final String TABLE_NAME 				= "fitness_table";

    private static final String DATE_COLUMN 			= "date";
    private static final String SIT_UP_COUNT_COLUMN 	= "sit_up_count";
    private static final String PUSH_UP_COUNT_COLUMN 	= "push_up_count";
    private static final String JUMP_ROPE_COUNT_COLUMN 	= "jump_rope_count";
    private static final String FLEX_COUNT_COLUMN 		= "flex_count";
    private static final String BLOCK_COUNT_COLUMN 		= "block_count";
    private static final String KNEE_COUNT_COLUMN 		= "knee_count";
    private static final String MILE_COUNT_COLUMN 		= "mile_count";
    private static final String PUNCH_COUNT_COLUMN 		= "punch_count";
    private static final String SQUAT_COUNT_COLUMN 		= "squat_count";
    private static final String FORM_COUNT_COLUMN 		= "form_count";
    private static final String DEFENSE_COUNT_COLUMN 	= "defense_count";
    
    private static final String TABLE_CREATE = 
    	"CREATE TABLE " + TABLE_NAME + " (" + DATE_COLUMN+ " INTEGER UNIQUE" +
    	 ", " + SIT_UP_COUNT_COLUMN 	+ " INTEGER" + 
    	 ", " + PUSH_UP_COUNT_COLUMN 	+ " INTEGER" +
    	 ", " + JUMP_ROPE_COUNT_COLUMN 	+ " INTEGER" +
    	 ", " + FLEX_COUNT_COLUMN 		+ " INTEGER" +
    	 ", " + BLOCK_COUNT_COLUMN 		+ " INTEGER" +
    	 ", " + KNEE_COUNT_COLUMN 		+ " INTEGER" +
    	 ", " + MILE_COUNT_COLUMN 		+ " INTEGER" +
    	 ", " + PUNCH_COUNT_COLUMN 		+ " INTEGER" +
    	 ", " + SQUAT_COUNT_COLUMN 		+ " INTEGER" +
    	 ", " + FORM_COUNT_COLUMN 		+ " INTEGER" +
    	 ", " + DEFENSE_COUNT_COLUMN 	+ " INTEGER" +
    	 ");";
    
    
    public static final int YEAR_MODIFIER = 10000;
    public static final int MONTH_MODIFIER = 100;
    
    private Context context;
    
    public String getExerciseColumnString (int id) {
    	switch (ExerciseValues[id]) {
    	case SitUps:
    		return SIT_UP_COUNT_COLUMN;
    	case PushUps:
    		return PUSH_UP_COUNT_COLUMN;
    	case JumpRopes:
    		return JUMP_ROPE_COUNT_COLUMN;
    	case Stretching:
    		return FLEX_COUNT_COLUMN;
    	case Blocks:
    		return BLOCK_COUNT_COLUMN;
    	case KneeHighs:
    		return KNEE_COUNT_COLUMN;
    	case Miles:
    		return MILE_COUNT_COLUMN;
    	case Squats:
    		return SQUAT_COUNT_COLUMN;
    	case Punches:
    		return PUNCH_COUNT_COLUMN;
    	case Forms:
    		return FORM_COUNT_COLUMN;
    	case SelfDefense:
    		return DEFENSE_COUNT_COLUMN;
    	default:
    		return null;
    	}
    }
    
    public int getExerciseGoal (int id) {
    	Resources resrc = context.getResources();
    	switch (ExerciseValues[id]) {
    	case SitUps:
    		return resrc.getInteger(R.integer.sit_up_minimum);
    	case PushUps:
    		return resrc.getInteger(R.integer.push_up_minimum);
    	case JumpRopes:
    		return resrc.getInteger(R.integer.jump_rope_minimum);
    	case Stretching:
    		return resrc.getInteger(R.integer.stretch_minimum);
    	case Blocks:
    		return resrc.getInteger(R.integer.basic_block_minimum);
    	case KneeHighs:
    		return resrc.getInteger(R.integer.knee_high_minimum);
    	case Miles:
    		return resrc.getInteger(R.integer.endurance_minimum);
    	case Squats:
    		return resrc.getInteger(R.integer.squat_minimum);
    	case Punches:
    		return resrc.getInteger(R.integer.basic_punches_minimum);
    	case Forms:
    		return resrc.getInteger(R.integer.form_minimum);
    	case SelfDefense:
    		return resrc.getInteger(R.integer.creative_self_defense_minimum);
    	default:
    		return 0;
    	}
    }

	public FitnessDatabase(Context ctext) 
	{
		super(ctext,DATABASE_NAME, null, DATABASE_VERSION);
		context = ctext;
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{	
		//create tables
		db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void insertExercise(int exercise_id_in, int count_in, int date_in)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		String columns[] = {getExerciseColumnString(exercise_id_in)};
		
		Cursor cursor = db.query(TABLE_NAME, 
				 				 columns, 
				 				 DATE_COLUMN+"="+date_in, 
				 				 null, 
				 				 null, 
				 				 null, 
				 				 null);
		
		if(cursor.getCount() == 0)
		{
			values.put(DATE_COLUMN, date_in);		
			values.put(columns[0], count_in);
		
			db.insert(TABLE_NAME, null, values);
		}
		else
		{
			values.put(columns[0], count_in);
			db.update(TABLE_NAME, 
					  values, 
					  DATE_COLUMN+"="+date_in, 
					  null);
		}
	}
	
	public int getExerciseByDate(int exercise_id_in, int date_in)
	{
		int count_out = 0;
		SQLiteDatabase db = this.getReadableDatabase();
		String columns[] = {getExerciseColumnString(exercise_id_in)};
		
		Cursor cursor = db.query(TABLE_NAME, 
								 columns, 
								 DATE_COLUMN+"="+date_in, 
								 null, 
								 null, 
								 null, 
								 null);
		
		if(cursor.getCount() == 1)
		{
			cursor.moveToFirst();
			count_out = cursor.getInt(0);
		}
		else
		{
			count_out = 0;
		}
		
		return count_out;
	}
	
	public int[][] getExerciseList(int exercise_id_in)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String columns[] = {DATE_COLUMN, getExerciseColumnString(exercise_id_in)};
		
		Cursor cursor = db.query(TABLE_NAME, 
								 columns,
								 columns[1] + "> 0", 
								 null, 
								 null, 
								 null, 
								 DATE_COLUMN);
		
		int length = cursor.getCount();
		
		if(length > 0)
		{
			cursor.moveToFirst();
		
			int list[][] = new int[length][2];
		
			for(int i = 0; i < length; i++)
			{
				list[i][0] = cursor.getInt(0);
				list[i][1] = cursor.getInt(1);
			
				cursor.moveToNext();
			}
			
			return list;
		}
		
		return null;
	}
	
	public int getExerciseSum(int exercise_id_in)
	{
		int sum = 0;
		String columns[] = {getExerciseColumnString(exercise_id_in)};
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_NAME, 
								 columns, 
								 columns[0] + "> 0", 
								 null, 
								 null, 
								 null, 
								 null);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast())
		{
			sum += cursor.getInt(0);
			
			cursor.moveToNext();
		}
		
		return sum;
	}
	
	@SuppressLint("DefaultLocale")
	static public String getDateString(int date)
	{
		String date_string;
		
		int year = date / YEAR_MODIFIER;
		int month = (date - (year * YEAR_MODIFIER)) / MONTH_MODIFIER;
		int day = date - ((year * YEAR_MODIFIER) + (month * MONTH_MODIFIER));
		
		date_string = String.format("%02d-%02d-%d",month,day,year);
		return date_string;
	}

}
