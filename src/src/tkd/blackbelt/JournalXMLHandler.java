package tkd.blackbelt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**
 * 
 * @author jacoberg2
 *
 */
public class JournalXMLHandler {

	private Journal journal = null;
	private static final String TITLE_TAG = "weekly_journal";
	private static final String WEEK_TAG = 	"weeky";
	private static final String NUMB_ATTR = "number";
	
	/**
	 * 
	 */
	public JournalXMLHandler () {
		journal = Journal.getJournal ();
	}
	
	/**
	 * 
	 * @return
	 */
	public Journal getJournal () {
		return journal;
	}
	
	/************
	 * PARSER
	 ************/
	
	/**
	 * 
	 * @param in
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public void parse (InputStream in) throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser ();
			parser.setFeature (XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput (in, null);
			parser.nextTag ();
			readJournal (parser);
		}
		finally {
			in.close ();
		}
	}
	
	/**
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readJournal (XmlPullParser parser) throws XmlPullParserException, IOException {

		parser.require (XmlPullParser.START_TAG, null, TITLE_TAG);

		while (parser.next () != XmlPullParser.END_TAG) {
			if (parser.getEventType () != XmlPullParser.START_TAG) {
				continue;
			}
			
			readEntry (parser);
		}

		parser.require (XmlPullParser.END_TAG, null, TITLE_TAG);
	}
	
	/**
	 * 
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void readEntry (XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require (XmlPullParser.START_TAG, null, WEEK_TAG);

		while (parser.next () != XmlPullParser.END_TAG) {
			if (parser.getEventType () != XmlPullParser.START_TAG) {
				continue;
			}
			
			int week = Integer.getInteger (parser.getAttributeValue(null, NUMB_ATTR));
			String text = parser.getText ();
			
			JournalEntry j = new JournalEntry (week, text);
			journal.addEntry (j);
		}

		parser.require (XmlPullParser.END_TAG, null, WEEK_TAG);
	}
	
	/*****************
	 * SERIALIZER
	 *****************/
	
	/**
	 * 
	 * @param out
	 * @throws IOException
	 */
	public void generateJournalFile (OutputStream out) throws IOException {
		try {
			XmlSerializer serializer = Xml.newSerializer ();
			serializer.setOutput (out, null);
			serializer.startDocument (null, Boolean.TRUE);
			serializer.startTag (null, TITLE_TAG);
			writeJournal (serializer);
			serializer.endTag (null, TITLE_TAG);
			serializer.endDocument ();
			serializer.flush ();
		}
		finally {
			out.close ();
		}
	}
	
	/**
	 * 
	 * @param serializer
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private void writeJournal (XmlSerializer serializer) 
	throws IllegalArgumentException, IllegalStateException, IOException {

		for (Iterator<JournalEntry> iter = journal.iterator (); iter.hasNext ();) {
			JournalEntry j = iter.next ();
			serializer.startTag (null, WEEK_TAG);
			serializer.attribute (null, NUMB_ATTR, Integer.toString (j.getWeekNumber ()));
			serializer.text (j.getEntry ());
			serializer.endTag (null, WEEK_TAG);
		}
	}
}
