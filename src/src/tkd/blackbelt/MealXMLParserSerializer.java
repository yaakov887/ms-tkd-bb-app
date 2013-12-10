package tkd.blackbelt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**************************************************************************************************
 * @author jacoberg2
 *
 *************************************************************************************************/
public class MealXMLParserSerializer {
	
	private MealsJournal journal;
	private final String JOURNAL_TAG		= "meal_journal";
	private final String DAY_TAG			= "day";
	private final String NUMBER_ATTR		= "number";
	private final String BREAKFAST_TAG 		= "breakfast";
	private final String LUNCH_TAG			= "lunch";
	private final String DINNER_TAG			= "dinner";
	private final String SNACK_ONE_TAG		= "snack_1";
	private final String SNACK_TWO_TAG		= "snack_2";
	private final String FOOD_TAG			= "food";
	
	/**********************************************************************************************
	 * Constructor
	 *********************************************************************************************/
	public MealXMLParserSerializer () {
		journal = MealsJournal.getJournal();
	}
	
	/**********************************************************************************************
	 * 
	 * @return
	 *********************************************************************************************/
	public MealsJournal getMealsJournal () {
		return journal;
	}
	
	/**********************************************************************************************
	 * 
	 * @param meal
	 * @return Tag name of the selected meal
	 *********************************************************************************************/
	public String getXmlTagFromMealsId (MealsEntry.Meals meal) {
		switch (meal) {
			case Breakfast:
				return BREAKFAST_TAG;
			case Lunch:
				return LUNCH_TAG;
			case Dinner:
				return DINNER_TAG;
			case SnackOne:
				return SNACK_ONE_TAG;
			case SnackTwo:
				return SNACK_TWO_TAG;
			default:
				return null;
		}
	}
	
	/**********************************************************************************************
	 * 
	 * @param in
	 * @throws XmlPullParserException
	 * @throws IOException
	 *********************************************************************************************/
	public void parse (InputStream in) 
		throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			readJournal(parser);
		}
		finally {
			in.close();
		}
	}
	
	/**********************************************************************************************
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 *********************************************************************************************/
	private void readJournal (XmlPullParser parser) 
		throws XmlPullParserException, IOException {
		
		parser.require(XmlPullParser.START_TAG, null, JOURNAL_TAG);
		
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(DAY_TAG)) {
				readDay(parser);
			}
		}
		
		parser.require(XmlPullParser.END_TAG, null, JOURNAL_TAG);
	}

	/**********************************************************************************************
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 *********************************************************************************************/
	private void readDay (XmlPullParser parser) 
		throws XmlPullParserException, IOException {
		
		MealsEntry entry = new MealsEntry();
		parser.require(XmlPullParser.START_TAG, null, DAY_TAG);
		
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(BREAKFAST_TAG)) {
				readMeal (parser, MealsEntry.Meals.Breakfast, entry);
			}
			else if (name.equals(LUNCH_TAG)) {
				readMeal (parser, MealsEntry.Meals.Lunch, entry);
			}
			else if (name.equals(DINNER_TAG)) {
				readMeal (parser, MealsEntry.Meals.Dinner, entry);
			}
			else if (name.equals(SNACK_ONE_TAG)) {
				readMeal (parser, MealsEntry.Meals.SnackOne, entry);
			}
			else if (name.equals(SNACK_TWO_TAG)) {
				readMeal (parser, MealsEntry.Meals.SnackTwo, entry);
			}
			else {
				continue;
			}
		}
		
		parser.require(XmlPullParser.END_TAG, null, DAY_TAG);
		journal.addEntry(entry);
	}
	
	/**********************************************************************************************
	 * 
	 * @param parser
	 * @param meal
	 * @param entry
	 * @throws XmlPullParserException
	 * @throws IOException
	 *********************************************************************************************/
	private void readMeal (XmlPullParser parser, MealsEntry.Meals meal, 
			MealsEntry entry) throws XmlPullParserException, IOException {
		
		String food;
		String tag_name = getXmlTagFromMealsId (meal);
		
		if (tag_name != null) {
			parser.require(XmlPullParser.START_TAG, null, tag_name);
			
			while (parser.next() != XmlPullParser.END_TAG) {
				if (parser.getEventType() != XmlPullParser.START_TAG) {
					continue;
				}
				parser.next();						//get to text
				food = parser.getText();			//read text
				entry.addFoodToMeal(meal, food);	//add food to the meal
				parser.next();						//get end tag
			}
			
			parser.require(XmlPullParser.END_TAG, null, tag_name);
		}
	}
	
	/**********************************************************************************************
	 * 
	 * @param out
	 * @throws IOException
	 *********************************************************************************************/
	public void generateMealJournalFile (OutputStream out) throws IOException {
		try {
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(out, null);
			serializer.startDocument(null, Boolean.TRUE);
			serializer.startTag(null, JOURNAL_TAG);
			writeJournal (serializer);
			serializer.endTag(null, JOURNAL_TAG);
			serializer.endDocument();
			serializer.flush();
		}
		finally {
			out.close();
		}
	}
	
	/**********************************************************************************************
	 * 
	 * @param serializer
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 *********************************************************************************************/
	private void writeJournal (XmlSerializer serializer) 
		throws IllegalArgumentException, IllegalStateException, IOException {
		
		int numEntries = journal.getNumberOfEntries();
		
		for (int i = 0; i < numEntries; i++) {
			MealsEntry entry = journal.getEntry(i);
			serializer.startTag(null, DAY_TAG);
			serializer.attribute(null, NUMBER_ATTR, "" + (i + 1));
			writeEntry (serializer, entry);
			serializer.endTag(null, DAY_TAG);
		}
	}
	
	/**********************************************************************************************
	 * 
	 * @param serializer
	 * @param entry
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws IllegalArgumentException 
	 *********************************************************************************************/
	private void writeEntry (XmlSerializer serializer, MealsEntry entry) 
		throws IllegalArgumentException, IllegalStateException, IOException {
		
		for (int i = 0; i < MealsEntry.MealValues.length; i++) {
			MealsEntry.Meals meal 		= MealsEntry.MealValues[i];
			String tag					= getXmlTagFromMealsId(meal);
			ArrayList<String> food_list = entry.getMeal(meal);
			
			serializer.startTag(null, tag);
			for (int k = 0; k < food_list.size(); k++) {
				String food = food_list.get(k);
				serializer.startTag(null, FOOD_TAG);
				serializer.text(food);
				serializer.endTag(null, FOOD_TAG);
			}
			serializer.endTag(null, tag);
		}
	}
}
