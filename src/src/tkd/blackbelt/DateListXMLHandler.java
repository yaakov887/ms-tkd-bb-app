package tkd.blackbelt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.annotation.SuppressLint;
import android.util.Xml;

/**
 * Date List XML Parser and Serializer Class
 * 
 * This class takes care of parsing the Date List XML file <dates.xml> into the internal
 * storage. It also will output the internal date list to the Date List XML file by
 * overwriting the file <dates.xml>.
 * 
 * @author jacoberg2
 *
 */
@SuppressLint("DefaultLocale")
public class DateListXMLHandler {
	
	private DateList date_list;
	private static final String TITLE_TAG	= "dates";
	private static final String START_TAG	= "start";
	private static final String END_TAG		= "end";
	private static final String DATE_ATTR	= "date";
	private static final String TIME_ATTR	= "time";
	
	/**
	 * Constructor
	 */
	public DateListXMLHandler () {
		date_list = DateList.getDateList ();
	}

	/**
	 * getDateList
	 * 
	 * @return	Date List reference
	 */
	public DateList getDateList () {
		return date_list;
	}
	
	/*************
	 * PARSER
	 *************/
	
	/**********************************************************************************************
	 * parse
	 * 
	 * Starts reading the XML file passed in through the input stream.
	 * 
	 * @param in
	 * @throws XmlPullParserException
	 * @throws IOException
	 *********************************************************************************************/
	public void parse (InputStream in) 
		throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser ();
			parser.setFeature (XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput (in, null);
			parser.nextTag ();
			readDateList (parser);
		}
		finally {
			in.close();
		}
	}
	
	/**********************************************************************************************
	 * readDateList
	 * 
	 * Reads the date list beginning and end tags for the file and calls the parsers for
	 * subsequent tags.
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 *********************************************************************************************/
	@SuppressLint("DefaultLocale")
	private void readDateList (XmlPullParser parser) 
		throws XmlPullParserException, IOException {
		
		parser.require(XmlPullParser.START_TAG, null, TITLE_TAG);
		
		while (parser.next () != XmlPullParser.END_TAG) {
			if (parser.getEventType () != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName ();
			ImportantDate.Dates d_name = ImportantDate.Dates.valueOf (name.toUpperCase ());
			if (d_name != null) {
				readDate (parser, name, d_name);
			}
			else if (name == ImportantDate.PREQUAL_TAG) {
				readDate (parser, name, ImportantDate.Dates.PREQUALIFICATION_START);
			}
		}
		
		parser.require (XmlPullParser.END_TAG, null, TITLE_TAG);
	}

	/**********************************************************************************************
	 * readDate
	 * 
	 * Reads a specific date based on the start and end tag.
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 *********************************************************************************************/
	private void readDate (XmlPullParser parser, String name, ImportantDate.Dates d_name) 
		throws XmlPullParserException, IOException {
		
		parser.require(XmlPullParser.START_TAG, null, name);
		
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			if (d_name != null) {
				ImportantDate entry_1 = new ImportantDate ();
				ImportantDate entry_2 = new ImportantDate ();
				switch (d_name) {
				case COMPLETE_BOOK:
					readCompleteBook (parser, entry_1);
					date_list.setDate(d_name, entry_1);
				case PART_ONE:
				case PART_TWO:
					readPartsOneAndTwo (parser, entry_1);
					date_list.setDate(d_name, entry_1);
				case PREQUALIFICATION_START:
				case PREQUALIFICATION_END:
					readPrequalification (parser, entry_1, entry_2);
					date_list.setDate(ImportantDate.Dates.PREQUALIFICATION_START, entry_1);
					date_list.setDate(ImportantDate.Dates.PREQUALIFICATION_END, entry_2);
				case REHEARSAL:
					readRehearsal (parser, entry_1);
					date_list.setDate(d_name, entry_1);					
				}
			}
		}
		
		parser.require(XmlPullParser.END_TAG, null, name);
	}
	
	/**
	 * readCompleteBook
	 * 
	 * Reads the date/times for the Complete Book Date.
	 * 
	 * @param parser
	 * @param date
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readCompleteBook (XmlPullParser parser, ImportantDate date) 
		throws XmlPullParserException, IOException {

		parser.require(XmlPullParser.START_TAG, null, END_TAG);

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String d_str = parser.getAttributeValue(null, DATE_ATTR);
			
			date.setDate(d_str);
		}

		parser.require(XmlPullParser.END_TAG, null, END_TAG);		
	}
	
	/**
	 * readPartsOneAndTwo
	 * 
	 * Read the Part One and Part Two Testing dates and times
	 * 
	 * @param parser
	 * @param date
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readPartsOneAndTwo (XmlPullParser parser, ImportantDate date) 
	throws XmlPullParserException, IOException {

		/**
		 * Read Start Date and Time
		 */
		parser.require (XmlPullParser.START_TAG, null, START_TAG);

		while (parser.next () != XmlPullParser.END_TAG) {
			if (parser.getEventType () != XmlPullParser.START_TAG) {
				continue;
			}
			String d_str = parser.getAttributeValue (null, DATE_ATTR);
			String t_str = parser.getAttributeValue (null, TIME_ATTR);

			date.setDate (d_str);
			date.setStartTime (t_str);
		}

		parser.require (XmlPullParser.END_TAG, null, START_TAG);

		/**
		 * Read end time
		 */
		parser.require (XmlPullParser.START_TAG, null, END_TAG);

		while (parser.next () != XmlPullParser.END_TAG) {
			if (parser.getEventType () != XmlPullParser.START_TAG) {
				continue;
			}
			String t_str = parser.getAttributeValue (null, TIME_ATTR);

			date.setEndTime (t_str);
		}

		parser.require (XmlPullParser.END_TAG, null, END_TAG);
	}
	
	/**
	 * readPrequalification
	 * 
	 * Read the Prequalification Start and End Dates
	 * 
	 * @param parser
	 * @param s_date
	 * @param e_date
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readPrequalification (XmlPullParser parser, ImportantDate s_date, 
			ImportantDate e_date) throws XmlPullParserException, IOException {

		/**
		 * Read Start Date
		 */
		parser.require (XmlPullParser.START_TAG, null, START_TAG);

		while (parser.next () != XmlPullParser.END_TAG) {
			if (parser.getEventType () != XmlPullParser.START_TAG) {
				continue;
			}
			String d_str = parser.getAttributeValue (null, DATE_ATTR);

			s_date.setDate (d_str);
		}

		parser.require (XmlPullParser.END_TAG, null, START_TAG);

		/**
		 * Read End Date
		 */
		parser.require (XmlPullParser.START_TAG, null, END_TAG);

		while (parser.next () != XmlPullParser.END_TAG) {
			if (parser.getEventType () != XmlPullParser.START_TAG) {
				continue;
			}
			String d_str = parser.getAttributeValue (null, DATE_ATTR);

			e_date.setDate (d_str);
		}

		parser.require (XmlPullParser.END_TAG, null, END_TAG);
	}
	
	/**
	 * readRehearsal
	 * 
	 * Reads the Rehearsal date and time
	 * 
	 * @param parser
	 * @param date
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readRehearsal (XmlPullParser parser, ImportantDate date) 
		throws XmlPullParserException, IOException {

		parser.require (XmlPullParser.START_TAG, null, START_TAG);

		while (parser.next () != XmlPullParser.END_TAG) {
			if (parser.getEventType () != XmlPullParser.START_TAG) {
				continue;
			}
			String d_str = parser.getAttributeValue (null, DATE_ATTR);
			
			date.setDate (d_str);
		}

		parser.require (XmlPullParser.END_TAG, null, START_TAG);		
	}
	
	/*******************
	 * SERIALIZER
	 *******************/
	
	/**********************************************************************************************
	 * generateDateFile
	 * 
	 * Outputs the Date List to file, beginning with the title tags and calls the serializers
	 * for the subsequent tags.
	 * 
	 * @param out
	 * @throws IOException
	 *********************************************************************************************/
	public void generateDateFile (OutputStream out) throws IOException {
		try {
			XmlSerializer serializer = Xml.newSerializer ();
			serializer.setOutput (out, null);
			serializer.startDocument (null, Boolean.TRUE);
			serializer.startTag (null, TITLE_TAG);
			writeDateList (serializer);
			serializer.endTag (null, TITLE_TAG);
			serializer.endDocument ();
			serializer.flush ();
		}
		finally {
			out.close ();
		}
	}
	
	/**********************************************************************************************
	 * writeDateList
	 * 
	 * Outputs the dates to the file with the proper tags.
	 * 
	 * @param serializer
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 *********************************************************************************************/
	private void writeDateList (XmlSerializer serializer) 
		throws IllegalArgumentException, IllegalStateException, IOException {
		
		for (ImportantDate.Dates date : ImportantDate.Dates.values ()) {
			if (date.ordinal () <= ImportantDate.Dates.PREQUALIFICATION_END.ordinal ()) {
				serializer.startTag (null, ImportantDate.PREQUAL_TAG);
				
				/**
				 * Write Prequalification Start Date
				 */
				serializer.startTag (null, START_TAG);
				serializer.attribute (null, DATE_ATTR, 
						date_list.getDate (ImportantDate.Dates.PREQUALIFICATION_START).toString ());
				serializer.endTag (null, START_TAG);
				
				/**
				 * Write Prequalification End Date
				 */
				serializer.startTag (null, END_TAG);
				serializer.attribute (null, DATE_ATTR, 
						date_list.getDate (ImportantDate.Dates.PREQUALIFICATION_END).toString ());
				serializer.endTag (null, END_TAG);
				
				serializer.endTag (null, ImportantDate.PREQUAL_TAG);
			}
			else {
				writeDate (serializer, date);
			}	
		}
	}
	
	/**
	 * writeDate
	 * 
	 * Writes the start and end tags with the date/time attributes for the specified Dates value.
	 * 
	 * @param serializer
	 * @param date
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private void writeDate (XmlSerializer serializer, ImportantDate.Dates date) 
		throws IllegalArgumentException, IllegalStateException, IOException {

		ImportantDate entry = date_list.getDate (date);

		serializer.startTag (null, date.toString ().toLowerCase ());

		if (entry.getDate () != null) {
			if (date == ImportantDate.Dates.COMPLETE_BOOK) {
				serializer.startTag (null, END_TAG);
				serializer.attribute (null, DATE_ATTR, date_list.getDate (date).toString ());
				serializer.endTag (null, END_TAG);
			}
			else {
				serializer.startTag (null, START_TAG);
				serializer.attribute (null, DATE_ATTR, entry.getDate ().toString ());
				if (entry.getStartTime () != null) {
					serializer.attribute (null, TIME_ATTR, entry.getStartTime ().toString ());	
				}
				serializer.endTag (null, START_TAG);

				if (entry.getEndTime () != null) {
					serializer.startTag (null, END_TAG);
					serializer.attribute (null, TIME_ATTR, entry.getEndTime ().toString ());	
					serializer.endTag (null, END_TAG);
				}				
			}
		}

		serializer.endTag (null, date.toString ().toLowerCase ());
	}
}
